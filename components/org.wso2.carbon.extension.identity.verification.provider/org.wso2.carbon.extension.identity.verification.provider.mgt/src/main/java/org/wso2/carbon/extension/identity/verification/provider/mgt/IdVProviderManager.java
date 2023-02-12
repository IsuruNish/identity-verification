package org.wso2.carbon.extension.identity.verification.provider.mgt;

import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;

import java.util.List;

public interface IdVProviderManager {

    void addIdV(IdentityVerificationProvider identityVerificationProvider, String tenantDomain)
            throws IdVProviderMgtException;

    IdentityVerificationProvider getIdV(String idVProviderId) throws IdVProviderMgtException;

    void deleteIdV(String idVProviderId) throws IdVProviderMgtException;

    void updateIdV() throws IdVProviderMgtException;

    List<IdentityVerificationProvider> getIdVs() throws IdVProviderMgtException;
}
