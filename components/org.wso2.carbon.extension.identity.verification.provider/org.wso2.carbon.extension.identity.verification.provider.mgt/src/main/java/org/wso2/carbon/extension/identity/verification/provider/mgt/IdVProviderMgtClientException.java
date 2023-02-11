package org.wso2.carbon.extension.identity.verification.provider.mgt;

public class IdVProviderMgtClientException extends IdVProviderMgtException{

    public IdVProviderMgtClientException(String message) {

        super(message);
    }

    public IdVProviderMgtClientException(String message, Throwable cause) {

        super(message, cause);
    }

    public IdVProviderMgtClientException(String errorCode, String message) {

        super(errorCode, message);
    }

    public IdVProviderMgtClientException(String errorCode, String message, Throwable throwable) {

        super(errorCode, message, throwable);
    }
}
