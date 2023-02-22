/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.carbon.extension.identity.verification.api.rest.v1.impl;

import org.wso2.carbon.extension.identity.verification.api.rest.v1.ProvidersApiService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationProviderService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.IdVProviderRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.IdVProviderResponse;
import javax.ws.rs.core.Response;

/**
 * This class implements the ProvidersApiService interface.
 */
public class ProvidersApiServiceImpl implements ProvidersApiService {

    IdentityVerificationProviderService identityVerificationProviderService;

    @Override
    public Response addIdVProvider(IdVProviderRequest idVProviderRequest) {

        IdVProviderResponse idVProviderResponse =
                identityVerificationProviderService.addIdVProvider(idVProviderRequest);
        return Response.ok().entity(idVProviderResponse).build();
    }

    @Override
    public Response deleteIdVProvider(String idvProviderId) {

        identityVerificationProviderService.deleteIdVProvider(idvProviderId);
        return Response.noContent().build();
    }

    @Override
    public Response getIdVProvider(String idvProviderId) {

        IdVProviderResponse idVProviderResponse =
                identityVerificationProviderService.getIdVProvider(idvProviderId);
        return Response.ok().entity(idVProviderResponse).build();
    }

    @Override
    public Response getIdVProviders(Integer limit, Integer offset) {

        // do some magic!
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response updateIdVProviders(String idvProviderId, IdVProviderRequest idVProviderRequest) {

        IdVProviderResponse idVProviderResponse =
                identityVerificationProviderService.updateIdVProvider(idvProviderId, idVProviderRequest);
        return Response.ok().entity(idVProviderResponse).build();
    }
}
