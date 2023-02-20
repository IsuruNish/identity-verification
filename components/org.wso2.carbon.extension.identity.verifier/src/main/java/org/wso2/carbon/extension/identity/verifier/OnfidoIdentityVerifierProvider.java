package org.wso2.carbon.extension.identity.verifier;

import org.wso2.carbon.extension.identity.verifier.IdentityVerifier;
import org.wso2.carbon.extension.identity.verifier.IdentityVerifierFactory;
import org.wso2.carbon.extension.identity.verifier.OnfidoIdentityVerifier;

public class OnfidoIdentityVerifierProvider implements IdentityVerifierFactory {

    @Override
    public IdentityVerifier getIdentityVerifier(String identityVerifierName) {

        if (identityVerifierName.equals("ONFIDO")) {
            return new OnfidoIdentityVerifier();
        }
        return null;
    }

    @Override
    public String getIdentityVerifierName() {

        return "ONFIDO";
    }
}
