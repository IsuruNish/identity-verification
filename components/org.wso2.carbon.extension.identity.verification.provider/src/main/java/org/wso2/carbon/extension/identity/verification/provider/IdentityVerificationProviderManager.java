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

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.extension.identity.verification.provider.dao.IdVProviderManagementDAO;
import org.wso2.carbon.extension.identity.verification.provider.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtConstants;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtExceptionManagement;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;

import java.util.List;

/**
 * This class contains the implementation for the IdVProviderManager.
 */
public class IdentityVerificationProviderManager implements IdVProviderManager {

    IdVProviderManagementDAO idVProviderManagementDAO = new IdVProviderManagementDAO();

    public IdentityVerificationProvider addIdVProvider(IdentityVerificationProvider identityVerificationProvider,
                                                       int tenantId) throws IdVProviderMgtException {

        validateAddIdPVInputValues(identityVerificationProvider.getIdVProviderName(), tenantId);
        idVProviderManagementDAO.addIdVProvider(identityVerificationProvider, tenantId);
        return identityVerificationProvider;
    }

    public IdentityVerificationProvider getIdVProvider(String idVProviderId, int tenantId)
            throws IdVProviderMgtException {

        return idVProviderManagementDAO.getIdVProvider(idVProviderId, tenantId);
    }

    public void deleteIdVProvider(String idVProviderId, int tenantId) throws IdVProviderMgtException {

        idVProviderManagementDAO.deleteIdVProvider(idVProviderId, tenantId);
    }

    public IdentityVerificationProvider updateIdVProvider(String idVProviderId,
                                                          IdentityVerificationProvider identityVerificationProvider,
                                                          int tenantId) throws IdVProviderMgtException {

        idVProviderManagementDAO.updateIdVProvider(idVProviderId, tenantId, identityVerificationProvider);
        return identityVerificationProvider;
    }

    public List<IdentityVerificationProvider> getIdVProviders(int tenantId)
            throws IdVProviderMgtException {

        return idVProviderManagementDAO.getIdVProviders(tenantId);
    }

    /**
     * Validate input parameters for the addIdVProvider function.
     *
     * @param idVPName Identity Verification Provider name.
     * @param tenantId Tenant Id.
     * @throws IdVProviderMgtException IdVProviderMgtException
     */
    private void validateAddIdPVInputValues(String idVPName, int tenantId) throws
            IdVProviderMgtException {

        if (getIdVPByName(idVPName, tenantId) != null) {
            throw IdVProviderMgtExceptionManagement.handleClientException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_IDVP_ALREADY_EXISTS, idVPName, null);
        }
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
