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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verification.provider.IdVProviderMgtException;

import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.getTenantId;
import static org.wso2.carbon.extension.identity.verification.api.rest.v1.core.IdentityVerificationUtils.handleException;

/**
 * Service class for identity verification Claims.
 */
public class IdentityVerificationClaimService {

    public VerificationClaimResponse addVerificationClaimData(VerificationClaimPostRequest
                                                                      verificationClaimPostRequest) {

        // do some magic!
        return;
    }

    public VerificationClaimResponse getVerificationClaimData(String claimId) {

        // todo
        IdVClaim idVClaim = null;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    getIDVClaim(claimId, tenantId);
        } catch (IdVClaimMgtException e) {
            // throw handleException(e, Constants.ErrorMessage.ERROR_CODE_ERROR_ADDING_IDVP, null);
        }
        return getVerificationClaimResponse(idVClaim);
    }

    public VerificationClaimResponse updateVerificationClaim(String claimId, VerificationClaimUpdateRequest
            verificationClaimUpdateRequest) {

        IdVClaim idVClaim = null;
        int tenantId = getTenantId();
        try {
            idVClaim = IdentityVerificationServiceHolder.getIdVClaimManager().
                    updateIDVClaim(claimId, tenantId);
        } catch (IdVClaimMgtException e) {
            // throw handleException(e, Constants.ErrorMessage.ERROR_CODE_ERROR_ADDING_IDVP, null);
        }
        return getVerificationClaimResponse(idVClaim);
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

    private IdVClaim getIdVClaim(VerificationClaimRequest verificationClaimRequest) {

        IdVClaim idVClaim = new IdVClaim();
        idVClaim.setClaimUri(verificationClaimRequest.ugetUri());
        idVClaim.setClaimValue(verificationClaimRequest.getValue());
        idVClaim.setStatus(verificationClaimRequest.getStatus());
        idVClaim.setMetadata(verificationClaimRequest.getClaimMetadata());
        return idVClaim;
    }
}
