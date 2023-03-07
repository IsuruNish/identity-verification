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

import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdentityVerificationServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Claims;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Property;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verifier.IdentityVerificationException;
import org.wso2.carbon.extension.identity.verifier.model.IdVProperty;
import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierData;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getTenantId;
import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.handleException;

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
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().getIDVClaims(userId, tenantId);
        } catch (IdVClaimMgtException e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_USER_IDV_CLAIMS, null);
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
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().getIDVClaim(userId, claimId, tenantId);
        } catch (IdVClaimMgtException e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_IDV_CLAIM_METADATA, null);
        }
        return getVerificationClaimResponse(idVClaim);
    }

    /**
     * Verify an identity.
     *
     * @param verificationClaimRequest Verification claim request.
     * @return Verification post response.
     */
    public VerificationPostResponse verifyIdentity(VerificationClaimRequest verificationClaimRequest) {

        IdentityVerifierData identityVerifierResponse;
        IdentityVerifierData identityVerifier = getIdentityVerifier(verificationClaimRequest);
        int tenantId = getTenantId();
        try {
            identityVerifierResponse = IdentityVerificationServiceHolder.getIdentityVerificationService().
                    verifyIdentity(identityVerifier, tenantId);
        } catch (IdentityVerificationException e) {
            throw handleException(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_IDV_CLAIM_METADATA, null);
        }
        return getVerificationPostResponse(identityVerifierResponse);
    }

    private IdentityVerifierData getIdentityVerifier(VerificationClaimRequest verificationClaimRequest) {

        IdentityVerifierData identityVerifier = new IdentityVerifierData();
        identityVerifier.setUserId(verificationClaimRequest.getUsername());
        identityVerifier.setIdentityVerifierName(verificationClaimRequest.getIdentityVerificationProvider());
        for (Claims claim : verificationClaimRequest.getClaims()) {
            IdVClaim idVClaim = new IdVClaim();
            idVClaim.setClaimUri(claim.getClaimUri());
            idVClaim.setClaimValue(claim.getClaimValue());
            identityVerifier.addIdVClaimProperty(idVClaim);
        }
        for (Property property : verificationClaimRequest.getProperties()) {
            IdVProperty idVProperty = new IdVProperty();
            idVProperty.setName(property.getKey());
            idVProperty.setValue(property.getValue());
            identityVerifier.addIdVProperty(idVProperty);
        }
        return identityVerifier;
    }
    private VerificationPostResponse getVerificationPostResponse(IdentityVerifierData identityVerifierData) {

        VerificationPostResponse verificationPostResponse = new VerificationPostResponse();
        verificationPostResponse.username(identityVerifierData.getUserId());
        verificationPostResponse.setIdentityVerificationProvider(identityVerifierData.getIdentityVerifierName());
        for (IdVClaim idVClaim : identityVerifierData.getIdVClaim()) {
            VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
            verificationClaimResponse.setId(idVClaim.getUuid());
            verificationClaimResponse.setUri(idVClaim.getClaimUri());
            verificationClaimResponse.setStatus(idVClaim.getStatus());
            verificationClaimResponse.setValue(idVClaim.getClaimValue());
            verificationClaimResponse.setClaimMetadata(idVClaim.getMetadata());
            verificationPostResponse.addClaimsItem(verificationClaimResponse);
        }
        return verificationPostResponse;
    }

    private VerificationClaimResponse getVerificationClaimResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getUuid());
        verificationClaimResponse.setUri(idVClaim.getClaimUri());
        verificationClaimResponse.setValue(idVClaim.getClaimValue());
        verificationClaimResponse.setStatus(idVClaim.getStatus());
        verificationClaimResponse.setClaimMetadata(idVClaim.getMetadata());
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
