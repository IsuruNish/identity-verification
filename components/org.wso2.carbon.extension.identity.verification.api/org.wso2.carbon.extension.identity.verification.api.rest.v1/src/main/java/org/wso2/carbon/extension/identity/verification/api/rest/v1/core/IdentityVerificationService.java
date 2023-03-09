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

import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdentityVerificationServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Claims;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Property;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verifier.IdentityVerificationException;
import org.wso2.carbon.extension.identity.verifier.model.IdVProperty;
import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierData;

import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getClaimMetadataMap;
import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getTenantId;
import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.handleException;

/**
 * Service class for identity verification.
 */
public class IdentityVerificationService {

    /**
     * Add identity verification claim.
     *
     * @param verificationClaimPostRequest verificationClaimPostRequest.
     * @return Verification claim response.
     */
    public VerificationClaimResponse addIdVClaim(String userId,
                                                 VerificationClaimPostRequest verificationClaimPostRequest) {

        IdVClaim idvClaim;
        int tenantId = getTenantId();
        try {
            idvClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    addIdVClaim(createIdVClaim(userId, verificationClaimPostRequest), tenantId);
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_ADDING_VERIFICATION_CLAIM, null);
        }
        return createVerificationClaimResponse(idvClaim);
    }

    /**
     * Get identity verification claim.
     *
     * @param claimId Claim id.
     * @return Verification claim response.
     */
    public VerificationClaimResponse getIdVClaim(String userId, String claimId) {

        IdVClaim idVClaim;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.
                    getIdVClaimManager().getIdVClaim(userId, claimId, tenantId);
            if (idVClaim == null) {
                throw IdentityVerificationUtils.handleException(Response.Status.NOT_FOUND,
                        Constants.ErrorMessage.ERROR_CODE_IDV_CLAIM_NOT_FOUND, claimId);
            }
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_GETTING_VERIFICATION_CLAIM, claimId);
        }
        return createVerificationClaimResponse(idVClaim);
    }

    /**
     * Update identity verification claim.
     *
     * @param userId                         User id.
     * @param claimId                        Claim id.
     * @param verificationClaimUpdateRequest Verification claim update request.
     * @return Verification claim response.
     */
    public VerificationClaimResponse updateIdVClaim(String userId, String claimId,
                                                    VerificationClaimUpdateRequest verificationClaimUpdateRequest) {

        IdVClaim idVClaim;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    getIdVClaim(userId, claimId, tenantId);
            if (idVClaim == null) {
                throw IdentityVerificationUtils.handleException(Response.Status.NOT_FOUND,
                        Constants.ErrorMessage.ERROR_CODE_IDV_CLAIM_NOT_FOUND, claimId);
            }
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    updateIdVClaim(userId, createIdVClaim(verificationClaimUpdateRequest, claimId), tenantId);
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_UPDATING_VERIFICATION_CLAIM, claimId);
        }
        return createVerificationClaimResponse(idVClaim);
    }

    /**
     * Get identity verification info.
     *
     * @param userId User id.
     * @return Identity verification info.
     */
    public VerificationGetResponse getIdVClaims(String userId) {

        IdVClaim[] idVClaim;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().getIDVClaims(userId, tenantId);
        } catch (IdVClaimMgtException e) {
            throw handleException(e, Constants.ErrorMessage.ERROR_RETRIEVING_USER_IDV_CLAIMS, null);
        }
        return createVerificationGetResponse(userId, idVClaim);
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
            throw handleException(e, Constants.ErrorMessage.ERROR_RETRIEVING_IDV_CLAIM_METADATA, null);
        }
        return getVerificationPostResponse(identityVerifierResponse);
    }

    /**
     * Create IdVClaim object based on the VerificationClaimPostRequest.
     *
     * @param userId                        User id.
     * @param verificationClaimPostRequest Verification claim post request.
     * @return Identity verification info.
     */
    private IdVClaim createIdVClaim(String userId, VerificationClaimPostRequest verificationClaimPostRequest) {

        IdVClaim idVClaim = new IdVClaim();
        idVClaim.setUuid(UUID.randomUUID().toString());
        idVClaim.setUserId(userId);
        idVClaim.setClaimUri(verificationClaimPostRequest.getUri());
        idVClaim.setStatus(verificationClaimPostRequest.getStatus());
        idVClaim.setIdvProviderId(verificationClaimPostRequest.getIdvpId());
        Map<String, Object> claimMetadata = verificationClaimPostRequest.getClaimMetadata();
        idVClaim.setMetadata(new JSONObject(claimMetadata));
        return idVClaim;
    }

    private IdVClaim createIdVClaim(VerificationClaimUpdateRequest verificationClaimUpdateRequest, String claimId) {

        IdVClaim idVClaim = new IdVClaim();
        idVClaim.setStatus(verificationClaimUpdateRequest.getStatus());
        Map<String, Object> claimMetadata = verificationClaimUpdateRequest.getClaimMetadata();
        idVClaim.setMetadata(new JSONObject(claimMetadata));
        return idVClaim;
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
            verificationClaimResponse.setClaimMetadata(getClaimMetadataMap(idVClaim.getMetadata()));
            verificationPostResponse.addClaimsItem(verificationClaimResponse);
        }
        return verificationPostResponse;
    }

    /**
     * Create verification claim response.
     *
     * @param idVClaim IdVClaim object.
     * @return Verification claim metadata.
     */
    private VerificationClaimResponse createVerificationClaimResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getUuid());
        verificationClaimResponse.setUri(idVClaim.getClaimUri());
        verificationClaimResponse.setValue(idVClaim.getClaimValue());
        verificationClaimResponse.setStatus(idVClaim.getStatus());
        verificationClaimResponse.setClaimMetadata(getClaimMetadataMap(idVClaim.getMetadata()));
        return verificationClaimResponse;
    }

    private VerificationGetResponse createVerificationGetResponse(String userId, IdVClaim[] idVClaims) {

        VerificationGetResponse verificationGetResponse = new VerificationGetResponse();
        verificationGetResponse.setUserId(userId);
        for (IdVClaim idVClaim : idVClaims) {
            verificationGetResponse.addClaimsItem(createVerificationClaimResponse(idVClaim));
        }
        return verificationGetResponse;
    }
}
