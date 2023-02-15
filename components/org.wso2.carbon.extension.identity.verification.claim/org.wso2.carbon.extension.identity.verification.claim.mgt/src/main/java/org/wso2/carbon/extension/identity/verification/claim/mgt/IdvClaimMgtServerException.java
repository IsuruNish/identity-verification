package org.wso2.carbon.extension.identity.verification.claim.mgt;

public class IdvClaimMgtServerException extends IdVClaimMgtException {

    public IdvClaimMgtServerException(String message) {

        super(message);
    }

    public IdvClaimMgtServerException(String message, Throwable cause) {

        super(message, cause);
    }

    public IdvClaimMgtServerException(String errorCode, String message) {

        super(errorCode, message);
    }

    public IdvClaimMgtServerException(String errorCode, String message, Throwable throwable) {

        super(errorCode, message, throwable);
    }

}
