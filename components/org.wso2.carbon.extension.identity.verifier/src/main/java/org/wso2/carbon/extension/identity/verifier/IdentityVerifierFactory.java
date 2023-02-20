package org.wso2.carbon.extension.identity.verifier;

public interface IdentityVerifierFactory {

    IdentityVerifier getIdentityVerifier(String identityVerifierName);

    String getIdentityVerifierName();

}
