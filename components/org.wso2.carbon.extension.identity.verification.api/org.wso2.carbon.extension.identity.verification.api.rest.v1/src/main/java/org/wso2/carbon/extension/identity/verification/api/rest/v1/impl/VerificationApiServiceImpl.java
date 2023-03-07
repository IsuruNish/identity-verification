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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.VerificationApiService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;

import javax.ws.rs.core.Response;

/**
 * This class implements the VerificationApiService interface.
 */
public class VerificationApiServiceImpl implements VerificationApiService {

    @Autowired
    IdentityVerificationService identityVerificationService;

    @Override
    public Response getIdentityVerificationInfo(String userId) {

        VerificationGetResponse verificationGetResponse =
                identityVerificationService.getIdentityVerificationInfo(userId);
        return Response.ok().entity(verificationGetResponse).build();
    }

    @Override
    public Response verifyIdentity(VerificationClaimRequest verificationClaimRequest) {

        VerificationPostResponse verificationPostResponse =
                identityVerificationService.verifyIdentity(verificationClaimRequest);
        return Response.ok().entity(verificationPostResponse).build();
    }
}
