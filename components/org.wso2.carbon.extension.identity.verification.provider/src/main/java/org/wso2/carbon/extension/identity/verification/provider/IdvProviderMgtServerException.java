package org.wso2.carbon.extension.identity.verification.provider;

public class IdvProviderMgtServerException extends IdVProviderMgtException{

    public IdvProviderMgtServerException(String message) {

        super(message);
    }

    public IdvProviderMgtServerException(String message, Throwable cause) {

        super(message, cause);
    }

    public IdvProviderMgtServerException(String errorCode, String message) {

        super(errorCode, message);
    }

    public IdvProviderMgtServerException(String errorCode, String message, Throwable throwable) {

        super(errorCode, message, throwable);
    }

}
