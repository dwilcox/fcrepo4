/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.kernel.impl.models;

import static org.apache.jena.graph.NodeFactory.createURI;

import java.util.stream.Stream;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.fcrepo.kernel.api.RdfStream;
import org.fcrepo.kernel.api.Transaction;
import org.fcrepo.kernel.api.exception.PathNotFoundException;
import org.fcrepo.kernel.api.exception.PathNotFoundRuntimeException;
import org.fcrepo.kernel.api.identifiers.FedoraId;
import org.fcrepo.kernel.api.models.FedoraResource;
import org.fcrepo.kernel.api.models.NonRdfSourceDescription;
import org.fcrepo.kernel.api.models.ResourceFactory;
import org.fcrepo.kernel.api.rdf.DefaultRdfStream;
import org.fcrepo.persistence.api.PersistentStorageSessionManager;

/**
 * Implementation of a non-rdf source description
 *
 * @author bbpennel
 */
public class NonRdfSourceDescriptionImpl extends FedoraResourceImpl implements NonRdfSourceDescription {

    /**
     * Construct a description resource
     *
     * @param fedoraID internal identifier
     * @param tx transaction
     * @param pSessionManager session manager
     * @param resourceFactory resource factory
     */
    public NonRdfSourceDescriptionImpl(final FedoraId fedoraID,
            final Transaction tx,
            final PersistentStorageSessionManager pSessionManager,
            final ResourceFactory resourceFactory) {
        super(fedoraID, tx, pSessionManager, resourceFactory);
    }

    @Override
    public String getId() {
        return getFedoraId().getResourceId();
    }

    @Override
    public FedoraResource getDescribedResource() {
        // Get a FedoraId for the binary
        final FedoraId describedId = FedoraId.create(this.getFedoraId().getContainingId());
        try {
            return this.resourceFactory.getResource(tx, describedId);
        } catch (final PathNotFoundException e) {
            throw new PathNotFoundRuntimeException(e);
        }
    }

    @Override
    public RdfStream getTriples() {
        // Remap the subject to the described resource
        final Node describedID = createURI(this.getDescribedResource().getId());
        final Stream<Triple> triples = super.getTriples().map(t ->
                new Triple(describedID, t.getPredicate(), t.getObject()));
        return new DefaultRdfStream(describedID, triples);
    }
}
