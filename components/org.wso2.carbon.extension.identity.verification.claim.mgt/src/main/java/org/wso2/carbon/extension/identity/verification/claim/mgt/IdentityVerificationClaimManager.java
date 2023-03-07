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
package org.wso2.carbon.extension.identity.verification.claim.mgt;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.claim.mgt.dao.IdentityVerificationClaimDAOImpl;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtExceptionManagement;

/**
 * This class provides methods to manage identity verification claims.
 */
public class IdentityVerificationClaimManager implements IdVClaimManager {

    IdentityVerificationClaimDAOImpl identityVerificationClaimDAO = new IdentityVerificationClaimDAOImpl();

    @Override
    public IdVClaim getIDVClaim(String idvClaimId, int tenantId) throws IdVClaimMgtException {

        if (StringUtils.isBlank(idvClaimId)) {
            throw IdVClaimMgtExceptionManagement.handleClientException(
                    IdVClaimMgtConstants.ErrorMessage.ERROR_CODE_INVALID_INPUTS, "idvClaimId");
        }
        return identityVerificationClaimDAO.getIDVClaim(idvClaimId, tenantId);
    }

    @Override
    public IdVClaim addIDVClaim(IdVClaim idvClaim, int tenantId) throws IdVClaimMgtException {

        validateAddIDVClaimInputs(idvClaim, tenantId);
        identityVerificationClaimDAO.addIdVClaim(idvClaim, tenantId);
        return idvClaim;
    }

    @Override
    public IdVClaim updateIDVClaim(IdVClaim idvClaim, int tenantId) throws IdVClaimMgtException {

        validateUpdateIDVClaimUpdateInputs(idvClaim, tenantId);
        identityVerificationClaimDAO.updateIdVClaim(idvClaim, tenantId);
        return idvClaim;
    }

    @Override
    public void deleteIDVClaim(String idvClaimId, int tenantId) throws IdVClaimMgtException {

        identityVerificationClaimDAO.deleteIdVClaim(idvClaimId, tenantId);
    }

    @Override
    public IdVClaim[] getIDVClaims(String userId, int tenantId) throws IdVClaimMgtException {

        return identityVerificationClaimDAO.getIDVClaims(userId, tenantId);
    }

    @Override
    public boolean isIDVClaimExists(String userId, String idvId, String uri, int tenantId)
            throws IdVClaimMgtException {

        return identityVerificationClaimDAO.isIdVClaimExist(userId, idvId, uri, tenantId);
    }

    private void validateAddIDVClaimInputs(IdVClaim idVClaim, int tenantId) throws IdVClaimMgtException {

        String userId = idVClaim.getUserId();
        String idvProviderId = idVClaim.getIdvProviderId();
        String claimUri = idVClaim.getClaimUri();
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(idvProviderId) || StringUtils.isBlank(claimUri)) {
            throw IdVClaimMgtExceptionManagement.handleClientException(
                    IdVClaimMgtConstants.ErrorMessage.ERROR_CODE_INVALID_INPUTS, null);
        }
        boolean idvClaimExists = isIDVClaimExists(userId, idvProviderId, claimUri, tenantId);
        if (idvClaimExists) {
            throw IdVClaimMgtExceptionManagement.handleClientException(
                    IdVClaimMgtConstants.ErrorMessage.ERROR_IDV_CLAIM_DATA_ALREADY_EXISTS, null);
        }
        validateStatus(idVClaim.getStatus());
    }

    private void validateUpdateIDVClaimUpdateInputs(IdVClaim idVClaim, int tenantId) throws IdVClaimMgtException {

        String status = idVClaim.getStatus();
        JSONObject claimMetadata = idVClaim.getMetadata();
        if (StringUtils.isBlank(status) || claimMetadata == null) {
            throw IdVClaimMgtExceptionManagement.handleClientException(
                    IdVClaimMgtConstants.ErrorMessage.ERROR_CODE_INVALID_INPUTS, null);
        }
        validateStatus(status);
    }

    private void validateStatus(String status) throws IdVClaimMgtException {

        if (StringUtils.isBlank(status) || !(IdVClaimMgtConstants.Status.VERIFIED.equals(status) ||
                IdVClaimMgtConstants.Status.NOT_VERIFIED.equals(status))) {
            throw IdVClaimMgtExceptionManagement.handleClientException(
                    IdVClaimMgtConstants.ErrorMessage.ERROR_CODE_INVALID_STATUS, null);
        }
    }
}
