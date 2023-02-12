package org.wso2.carbon.extension.identity.verification.provider.mgt;

import org.wso2.carbon.extension.identity.verification.provider.mgt.dao.IdVProviderManagementDAO;
import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;

import java.util.List;

public class IdentityVerificationProviderManager implements IdVProviderManager {

    IdVProviderManagementDAO idVProviderManagementDAO = new IdVProviderManagementDAO();

    public void addIdV(IdentityVerificationProvider identityVerificationProvider, String tenantDomain)
            throws IdVProviderMgtException {

    }

    public IdentityVerificationProvider getIdV(String idVProviderId) throws IdVProviderMgtException {

    }

    public void deleteIdV(String idVProviderId) throws IdVProviderMgtException {

        idVProviderManagementDAO.deleteIdVProvider(idVProviderId);
    }

    public void updateIdV() throws IdVProviderMgtException {

    }

    List<IdentityVerificationProvider> getIdVs() throws IdVProviderMgtException {

    }

}
