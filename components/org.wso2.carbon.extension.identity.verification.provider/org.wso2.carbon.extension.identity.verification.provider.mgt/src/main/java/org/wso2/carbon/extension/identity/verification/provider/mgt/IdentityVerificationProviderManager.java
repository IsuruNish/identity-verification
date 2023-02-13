package org.wso2.carbon.extension.identity.verification.provider.mgt;

import org.wso2.carbon.extension.identity.verification.provider.mgt.dao.IdVProviderManagementDAO;
import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;

import java.util.List;

public class IdentityVerificationProviderManager implements IdVProviderManager {

    IdVProviderManagementDAO idVProviderManagementDAO = new IdVProviderManagementDAO();

    public void addIdVProvider(IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException {

        idVProviderManagementDAO.addIdVProvider(identityVerificationProvider);
    }

    public IdentityVerificationProvider getIdVProvider(String idVProviderId) throws IdVProviderMgtException {

        return idVProviderManagementDAO.getIdVProvider(idVProviderId);
    }

    public void deleteIdVProvider(String idVProviderId) throws IdVProviderMgtException {

        idVProviderManagementDAO.deleteIdVProvider(idVProviderId);
    }

    public void updateIdVProvider(IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException {

        idVProviderManagementDAO.updateIdVProvider(identityVerificationProvider);
    }

    public List<IdentityVerificationProvider> getIdVProviders(String tenantDomain)
            throws IdVProviderMgtException {

        return idVProviderManagementDAO.getIdVProviders(tenantDomain);
    }
}
