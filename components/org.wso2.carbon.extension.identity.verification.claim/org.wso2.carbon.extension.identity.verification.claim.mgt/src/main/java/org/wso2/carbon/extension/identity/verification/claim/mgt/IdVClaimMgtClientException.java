package org.wso2.carbon.extension.identity.verification.claim.mgt;

public class IdVClaimMgtClientException extends IdVClaimMgtException {

    public IdVClaimMgtClientException(String message) {

        super(message);
    }

    public IdVClaimMgtClientException(String message, Throwable cause) {

        super(message, cause);
    }

    public IdVClaimMgtClientException(String errorCode, String message) {

        super(errorCode, message);
    }

    public IdVClaimMgtClientException(String errorCode, String message, Throwable throwable) {

        super(errorCode, message, throwable);
    }
}
