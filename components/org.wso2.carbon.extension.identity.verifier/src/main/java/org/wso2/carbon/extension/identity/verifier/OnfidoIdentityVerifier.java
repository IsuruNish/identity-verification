package org.wso2.carbon.extension.identity.verifier;

import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierResponse;

public class OnfidoIdentityVerifier implements IdentityVerifier {

    private static OnfidoIdentityVerifier instance = new OnfidoIdentityVerifier();

    @Override
    public IdentityVerifierResponse verifyIdentity(String userId, String identityVerifierName)
            throws IdentityVerificationException {

        return null;
    }
}
