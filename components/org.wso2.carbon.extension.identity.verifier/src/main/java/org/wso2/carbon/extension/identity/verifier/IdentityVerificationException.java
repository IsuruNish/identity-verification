package org.wso2.carbon.extension.identity.verifier;

public class IdentityVerificationException extends Exception {

    //todo
    public IdentityVerificationException(String message) {

        super(message);
    }

    public IdentityVerificationException(String message, Throwable cause) {

        super(message, cause);
    }

    public IdentityVerificationException(Throwable cause) {

        super(cause);
    }

    public IdentityVerificationException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}


