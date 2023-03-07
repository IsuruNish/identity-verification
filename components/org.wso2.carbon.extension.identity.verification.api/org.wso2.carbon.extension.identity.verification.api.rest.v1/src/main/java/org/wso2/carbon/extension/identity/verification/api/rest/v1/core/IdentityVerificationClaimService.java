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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getClaimMetadataMap;
import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getTenantId;

/**
 * Service class for identity verification Claims.
 */
public class IdentityVerificationClaimService {

    /**
     * Update verification claim.
     *
     * @param verificationClaimPostRequest verificationClaimPostRequest.
     * @return Verification claim response.
     */
    public VerificationClaimResponse addVerificationClaimData(VerificationClaimPostRequest
                                                                      verificationClaimPostRequest) {

        IdVClaim idvClaim;
        int tenantId = getTenantId();
        try {
            idvClaim = IdentityVerificationServiceHolder.
                    getIdVClaimManager().addIDVClaim(getIdVClaim(verificationClaimPostRequest), tenantId);
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_ADDING_VERIFICATION_CLAIM, null);
        }
        return getVerificationClaimResponse(idvClaim);
    }

    /**
     * Get verification claim.
     *
     * @param claimId Claim id.
     * @return Verification claim response.
     */
    public VerificationClaimResponse getVerificationClaimData(String claimId) {

        IdVClaim idVClaim;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    getIDVClaim(claimId, tenantId);
            if (idVClaim == null) {
                throw IdentityVerificationUtils.handleException(Response.Status.NOT_FOUND,
                        Constants.ErrorMessage.ERROR_CODE_IDV_CLAIM_NOT_FOUND, claimId);
            }
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_GETTING_VERIFICATION_CLAIM, claimId);
        }
        return getVerificationClaimResponse(idVClaim);
    }

    /**
     * Update verification claim.
     *
     * @param claimId                        Claim id.
     * @param verificationClaimUpdateRequest Verification claim update request.
     * @return Verification claim response.
     */
    public VerificationClaimResponse updateVerificationClaim(String claimId, VerificationClaimUpdateRequest
            verificationClaimUpdateRequest) {

        IdVClaim idVClaim;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    getIDVClaim(claimId, tenantId);
            if (idVClaim == null) {
                throw IdentityVerificationUtils.handleException(Response.Status.NOT_FOUND,
                        Constants.ErrorMessage.ERROR_CODE_IDV_CLAIM_NOT_FOUND, claimId);
            }
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    updateIDVClaim(getIdVClaim(verificationClaimUpdateRequest, claimId), tenantId);
        } catch (IdVClaimMgtException e) {
            throw IdentityVerificationUtils.handleException(e,
                    Constants.ErrorMessage.ERROR_UPDATING_VERIFICATION_CLAIM, claimId);
        }
        return getVerificationClaimResponse(idVClaim);
    }

    private VerificationClaimResponse getVerificationClaimResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getUuid());
        verificationClaimResponse.setUri(idVClaim.getClaimUri());
        verificationClaimResponse.setValue(idVClaim.getClaimValue());
        verificationClaimResponse.setStatus(idVClaim.getStatus());
        verificationClaimResponse.setClaimMetadata(getClaimMetadataMap(idVClaim.getMetadata()));
        return verificationClaimResponse;
    }

    private IdVClaim getIdVClaim(VerificationClaimPostRequest
                                         verificationClaimPostRequest) {

        IdVClaim idVClaim = new IdVClaim();
        idVClaim.setUuid(UUID.randomUUID().toString());
        idVClaim.setUserId(verificationClaimPostRequest.getUserId());
        idVClaim.setClaimUri(verificationClaimPostRequest.getUri());
        idVClaim.setStatus(verificationClaimPostRequest.getStatus());
        idVClaim.setIdvProviderId(verificationClaimPostRequest.getIdvpId());
        Map<String, Object> claimMetadata = verificationClaimPostRequest.getClaimMetadata();
        idVClaim.setMetadata(new JSONObject(claimMetadata));
        return idVClaim;
    }

    private IdVClaim getIdVClaim(VerificationClaimUpdateRequest verificationClaimUpdateRequest, String claimId) {

        IdVClaim idVClaim = new IdVClaim();
        idVClaim.setStatus(verificationClaimUpdateRequest.getStatus());
        Map<String, Object> claimMetadata = verificationClaimUpdateRequest.getClaimMetadata();
        idVClaim.setMetadata(new JSONObject(claimMetadata));
        return idVClaim;
    }
}
