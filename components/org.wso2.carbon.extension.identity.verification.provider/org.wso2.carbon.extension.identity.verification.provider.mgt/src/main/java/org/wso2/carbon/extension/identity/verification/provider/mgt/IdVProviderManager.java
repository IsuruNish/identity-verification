package org.wso2.carbon.extension.identity.verification.provider.mgt;

import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;

import java.util.List;

public interface IdVProviderManager {

    IdentityVerificationProvider addIdVProvider(IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException;

    IdentityVerificationProvider getIdVProvider(String idVProviderId) throws IdVProviderMgtException;

    void deleteIdVProvider(String idVProviderId) throws IdVProviderMgtException;

    IdentityVerificationProvider updateIdVProvider(IdentityVerificationProvider identityVerificationProvider) throws IdVProviderMgtException;

    List<IdentityVerificationProvider> getIdVProviders(String tenantDomain) throws IdVProviderMgtException;
}
