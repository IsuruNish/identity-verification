package org.wso2.carbon.extension.identity.verifier;

import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierResponse;

public interface IdentityVerifier {

    IdentityVerifierResponse verifyIdentity(String userId, String identityVerifierName)
            throws IdentityVerificationException;
}
