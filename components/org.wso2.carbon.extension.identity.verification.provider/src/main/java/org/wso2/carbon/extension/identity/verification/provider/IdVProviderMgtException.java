package org.wso2.carbon.extension.identity.verification.provider;

import org.wso2.carbon.identity.base.IdentityException;

public class IdVProviderMgtException extends IdentityException {

    public IdVProviderMgtException(String message) {
        super(message);
    }

    public IdVProviderMgtException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdVProviderMgtException(String errorCode, String message) {
        super(errorCode, message);
    }

    public IdVProviderMgtException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
