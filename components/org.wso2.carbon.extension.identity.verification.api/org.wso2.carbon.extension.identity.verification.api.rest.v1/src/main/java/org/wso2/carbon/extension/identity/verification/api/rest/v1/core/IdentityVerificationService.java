/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.wso2.carbon.extension.identity.verification.api.rest.common.IdVProviderServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verifier.IdentityVerificationException;
import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierResponse;

/**
 * Service class for identity verification.
 */
public class IdentityVerificationService {

    /**
     * Get identity verification info.
     *
     * @param userId User id.
     * @return Identity verification info.
     */
    public VerificationGetResponse getIdentityVerificationInfo(String userId) {

        IdVClaim[] idVClaim;
        try {
            idVClaim = IdVProviderServiceHolder.getIdVClaimManager().getIDVClaims(userId);
        } catch (IdVClaimMgtException e) {
            return null;
        }
        return getVerificationInfoResponse(userId, idVClaim);
    }

    /**
     * Get verification claim metadata.
     *
     * @param userId User id.
     * @param claimId Claim id.
     * @return Verification claim response.
     */
    public VerificationClaimResponse getVerificationClaimMetadata(String userId, String claimId) {

        IdVClaim idVClaim;
        try {
            idVClaim = IdVProviderServiceHolder.getIdVClaimManager().getIDVClaim(userId, claimId);
        } catch (IdVClaimMgtException e) {
            return null;
        }
        return getVerificationClaimResponse(idVClaim);
    }

    /**
     * Verify an identity.
     *
     * @param verificationPostRequest Verification post request.
     * @return Verification post response.
     */
    public VerificationPostResponse verifyIdentity(VerificationPostRequest verificationPostRequest) {

        IdentityVerifierResponse identityVerifierResponse;
        try {
            identityVerifierResponse = IdVProviderServiceHolder.getIdentityVerifierFactory().
                    getIdentityVerifier("ONFIDO").verifyIdentity(verificationPostRequest.getUsername(),
                            verificationPostRequest.getIdentityVerificationProvider());
        } catch (IdentityVerificationException e) {
            throw new RuntimeException(e);
        }
        return getVerificationPostResponse(identityVerifierResponse);
    }

    private VerificationPostResponse getVerificationPostResponse(IdentityVerifierResponse identityVerifierResponse) {

        VerificationPostResponse verificationPostResponse = new VerificationPostResponse();
        verificationPostResponse.username(identityVerifierResponse.getUserId());
        verificationPostResponse.setIdentityVerificationProvider(identityVerifierResponse.getIdentityVerifierName());
        for (IdVClaim idVClaim : identityVerifierResponse.getIdVClaims()) {
            verificationPostResponse.addClaimsItem(getVerificationClaimAttributeResponse(idVClaim));
        }
        return verificationPostResponse;
    }

    private VerificationClaimResponse getVerificationClaimAttributeResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getIdVClaimId());
        verificationClaimResponse.setStatus(idVClaim.getIdVStatus());
        verificationClaimResponse.setClaimMetadata(idVClaim.getIdVClaimMetadata());
        return verificationClaimResponse;
    }

    private VerificationClaimResponse getVerificationClaimResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getIdVClaimId());
        verificationClaimResponse.setStatus(idVClaim.getIdVStatus());
        verificationClaimResponse.setClaimMetadata(idVClaim.getIdVClaimMetadata());
        return verificationClaimResponse;
    }

    private VerificationGetResponse getVerificationInfoResponse(String userId, IdVClaim[] idVClaims) {

        VerificationGetResponse verificationGetResponse = new VerificationGetResponse();
        verificationGetResponse.setUserId(userId);
        for (IdVClaim idVClaim : idVClaims) {
            verificationGetResponse.addClaimsItem(getVerificationClaimResponse(idVClaim));
        }
        return verificationGetResponse;
    }
}
