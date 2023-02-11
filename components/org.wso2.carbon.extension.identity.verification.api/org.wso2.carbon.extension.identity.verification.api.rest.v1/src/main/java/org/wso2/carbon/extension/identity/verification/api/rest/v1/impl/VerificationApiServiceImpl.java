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

import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.VerificationApiService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.*;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationPostRequest;

import javax.ws.rs.core.Response;

public class VerificationApiServiceImpl implements VerificationApiService {

    @Autowired
    private IdentityVerificationService identityVerificationService;

    @Override
    public Response getIdentityVerificationInfo(String userId) {

        identityVerificationService.getIdentityVerificationInfo(userId);
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response getVerificationClaimMetadata(String userId, String claimId) {

        identityVerificationService.getVerificationClaimMetadata(userId, claimId);
        return Response.ok().entity("magic!").build();
    }

    @Override
    public Response verifyIdentity(VerificationPostRequest verificationPostRequest) {

        identityVerificationService.verifyIdentity(verificationPostRequest);
        return Response.ok().entity("magic!").build();
    }
}
