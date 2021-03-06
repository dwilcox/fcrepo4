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
package org.fcrepo.kernel.impl.services;

import org.fcrepo.kernel.api.RdfStream;
import org.fcrepo.kernel.api.exception.UnsupportedAlgorithmException;
import org.fcrepo.kernel.api.models.Binary;
import org.fcrepo.kernel.api.services.FixityService;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Collection;

/**
 * Implementation of {@link org.fcrepo.kernel.api.services.FixityService}
 *
 * @author dbernstein
 */
@Component
public class FixityServiceImpl extends AbstractService implements FixityService {
    @Override
    public RdfStream getFixity(final Binary binary) {
        return null;
    }

    @Override
    public RdfStream getFixity(final Binary binary, final URI contentDigest, final long size) {
        return null;
    }

    @Override
    public Collection<URI> checkFixity(final Binary binary, final Collection<String> algorithms)
            throws UnsupportedAlgorithmException {
        return null;
    }
}
