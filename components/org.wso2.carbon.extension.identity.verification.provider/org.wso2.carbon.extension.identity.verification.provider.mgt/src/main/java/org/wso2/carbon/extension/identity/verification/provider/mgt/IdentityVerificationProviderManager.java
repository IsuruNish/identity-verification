package org.wso2.carbon.extension.identity.verification.provider.mgt;

import org.wso2.carbon.identity.core.util.IdentityTenantUtil;

public class IdentityVerificationProviderManager implements IdVProviderManager {

    private void deleteIDV(String idvID, String idpName, String tenantDomain) throws
            IdVProviderMgtException {

        int tenantId = IdentityTenantUtil.getTenantId(tenantDomain);

        IdVProviderManagementDAO.deleteIdV(idvID, tenantId, tenantDomain);
    }

}
