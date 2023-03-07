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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.ClaimsApiService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationClaimService;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;

import javax.ws.rs.core.Response;

/**
 * This class implements the ClaimsApiService.
 */
public class ClaimsApiServiceImpl implements ClaimsApiService {

    @Autowired
    IdentityVerificationClaimService identityVerificationService;

    @Override
    public Response addVerificationClaimData(VerificationClaimPostRequest verificationClaimPostRequest) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.addVerificationClaimData(verificationClaimPostRequest);
        return Response.ok().entity(verificationClaimResponse).build();
    }

    @Override
    public Response getVerificationClaimData(String claimId) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.getVerificationClaimData(claimId);
        return Response.ok().entity(verificationClaimResponse).build();
    }

    @Override
    public Response updateVerificationClaim(String claimId,
                                            VerificationClaimUpdateRequest verificationClaimUpdateRequest) {

        VerificationClaimResponse verificationClaimResponse =
                identityVerificationService.updateVerificationClaim(claimId, verificationClaimUpdateRequest);
        return Response.ok().entity(verificationClaimResponse).build();
    }
}
