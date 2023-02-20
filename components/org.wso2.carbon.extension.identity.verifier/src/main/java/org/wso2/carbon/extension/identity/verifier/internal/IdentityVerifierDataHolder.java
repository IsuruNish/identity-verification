package org.wso2.carbon.extension.identity.verifier.internal;

import org.wso2.carbon.extension.identity.verifier.IdentityVerifierFactory;

import java.util.HashMap;
import java.util.Map;

public class IdentityVerifierDataHolder {

    private static final IdentityVerifierDataHolder instance = new IdentityVerifierDataHolder();
    private Map<String, IdentityVerifierFactory> identityVerifierFactoryMap;

    private IdentityVerifierDataHolder() {

    }

    public static IdentityVerifierDataHolder getInstance() {

        return instance;
    }

    public void setIdentityVerifierFactory(IdentityVerifierFactory identityVerifierFactory) {

        if (identityVerifierFactoryMap == null) {
            identityVerifierFactoryMap = new HashMap<>();
        }
        identityVerifierFactoryMap.put(identityVerifierFactory.getIdentityVerifierName(), identityVerifierFactory);
    }

    public IdentityVerifierFactory getIdentityVerifierFactory(String identityVerifierName) {

        if (identityVerifierFactoryMap == null) {
            return null;
        }
        return identityVerifierFactoryMap.get(identityVerifierName);
    }

    public void unbindIdentityVerifierFactory(IdentityVerifierFactory identityVerifierFactory) {

        identityVerifierFactoryMap.remove(identityVerifierFactory.getIdentityVerifierName());
    }

}
