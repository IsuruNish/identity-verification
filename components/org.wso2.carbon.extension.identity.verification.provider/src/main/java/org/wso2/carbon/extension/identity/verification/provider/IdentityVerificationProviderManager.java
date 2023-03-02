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
package org.wso2.carbon.extension.identity.verification.provider;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.extension.identity.verification.provider.dao.IdVProviderManagementDAO;
import org.wso2.carbon.extension.identity.verification.provider.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtConstants;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtExceptionManagement;

import java.util.List;
import java.util.Map;

/**
 * This class contains the implementation for the IdVProviderManager.
 */
public class IdentityVerificationProviderManager implements IdVProviderManager {

    IdVProviderManagementDAO idVProviderManagementDAO = new IdVProviderManagementDAO();

    public IdentityVerificationProvider addIdVProvider(IdentityVerificationProvider identityVerificationProvider,
                                                       int tenantId) throws IdVProviderMgtException {

        validateAddIdPVInputValues(identityVerificationProvider.getIdVProviderName(), tenantId);
        // todo
        validateLocalClaims(identityVerificationProvider.getClaimMappings(), tenantId);
        idVProviderManagementDAO.addIdVProvider(identityVerificationProvider, tenantId);
        return identityVerificationProvider;
    }

    public IdentityVerificationProvider getIdVProvider(String idVProviderId, int tenantId)
            throws IdVProviderMgtException {

        validateGetIdPInputValues(idVProviderId);
        return idVProviderManagementDAO.getIdVProvider(idVProviderId, tenantId);
    }

    public void deleteIdVProvider(String idVProviderId, int tenantId) throws IdVProviderMgtException {

        idVProviderManagementDAO.deleteIdVProvider(idVProviderId, tenantId);
    }

    public IdentityVerificationProvider updateIdVProvider(IdentityVerificationProvider oldIdVProvider,
                                                          IdentityVerificationProvider updatedIdVProvider,
                                                          int tenantId) throws IdVProviderMgtException {

        idVProviderManagementDAO.updateIdVProvider(oldIdVProvider, updatedIdVProvider, tenantId);
        return updatedIdVProvider;
    }

    public List<IdentityVerificationProvider> getIdVProviders(Integer limit, Integer offset, int tenantId)
            throws IdVProviderMgtException {

        return idVProviderManagementDAO.getIdVProviders(limit, offset, tenantId);
    }

    /**
     * Validate input parameters for the addIdVProvider function.
     *
     * @param idVPName Identity Verification Provider name.
     * @param tenantId Tenant Id.
     * @throws IdVProviderMgtException IdVProviderMgtException
     */
    private void validateAddIdPVInputValues(String idVPName, int tenantId) throws IdVProviderMgtException {

        if (getIdVPByName(idVPName, tenantId) != null) {
            throw IdVProviderMgtExceptionManagement.handleClientException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_IDVP_ALREADY_EXISTS, idVPName, null);
        }
    }

    /**
     * Validate input parameters for the getIdPByResourceId function.
     *
     * @param idVProviderId Identity Provider ID.
     * @throws IdVProviderMgtException IdVProviderMgtException.
     */
    private void validateGetIdPInputValues(String idVProviderId) throws IdVProviderMgtException {

        if (StringUtils.isEmpty(idVProviderId)) {
            String data = "Invalid argument: Identity Verification Provider ID value is empty";
            throw IdVProviderMgtExceptionManagement.handleClientException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_IDVP_REQUEST_INVALID, data);
        }
    }

    private void validateLocalClaims(Map<String, String> claimMappings, int tenantId) {

        if (MapUtils.isEmpty(claimMappings)) {
            return;
        }

        // todo
//        for (Map.Entry<String, String> entry : claimMappings.entrySet()) {
//            if (claimManager.getClaim(entry.getKey()) {
//                throw IdVProviderMgtExceptionManagement.handleClientException(IdVProviderMgtConstants.ErrorMessage.
//                        ERROR_CODE_LOCAL_CLAIMS_NOT_ALLOWED, entry.getKey(), null);
//            }
//        }

    }

    @Override
    public IdentityVerificationProvider getIdVPByName(String idVPName, int tenantId)
            throws IdVProviderMgtException {

        if (StringUtils.isEmpty(idVPName)) {
            // todo
            String msg = "Invalid argument: Identity Verification Provider Name value is empty";
            throw new IdVProviderMgtClientException(msg);
        }

        return idVProviderManagementDAO.getIdVPByName(idVPName, tenantId);
    }
}
