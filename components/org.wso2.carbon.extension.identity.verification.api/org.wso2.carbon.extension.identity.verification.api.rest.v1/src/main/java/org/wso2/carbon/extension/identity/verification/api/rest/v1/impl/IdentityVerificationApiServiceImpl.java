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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.IdentityVerificationApiService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import javax.ws.rs.core.Response;

/**
 * This class implements the IdentityVerificationApiService interface.
 */
public class IdentityVerificationApiServiceImpl implements IdentityVerificationApiService {

    @Autowired
    IdentityVerificationService identityVerificationService;

    @Override
    public Response addIdVClaim(String userId, VerificationClaimPostRequest verificationClaimPostRequest) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.addIdVClaim(userId, verificationClaimPostRequest);
        return Response.ok().entity(verificationClaimResponse).build();
    }

    @Override
    public Response getIdVClaim(String userId, String claimId) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.getIdVClaim(userId, claimId);
        return Response.ok().entity(verificationClaimResponse).build();
    }

    @Override
    public Response getIdVClaims(String userId) {

        VerificationGetResponse verificationGetResponse = identityVerificationService.getIdVClaims(userId);
        return Response.ok().entity(verificationGetResponse).build();
    }

    @Override
    public Response getIdentityVerification() {

//        VerificationGetResponse verificationGetResponse =
//                identityVerificationService.getIdVClaims();
        return Response.ok().entity("verificationGetResponse").build();
    }

    @Override
    public Response updateIdVClaim(String claimId, String userId,
                                   VerificationClaimUpdateRequest verificationClaimUpdateRequest) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.updateIdVClaim(userId, claimId, verificationClaimUpdateRequest);
        return Response.ok().entity(verificationClaimResponse).build();
    }

    @Override
    public Response verifyIdentity(VerificationClaimRequest verificationClaimRequest) {

        VerificationPostResponse verificationPostResponse =
                identityVerificationService.verifyIdentity(verificationClaimRequest);
        return Response.ok().entity(verificationPostResponse).build();
    }
}
