package org.wso2.carbon.extension.identity.verification.claim.mgt;

import org.wso2.carbon.identity.base.IdentityException;

public class IdVClaimMgtException extends IdentityException {

    public IdVClaimMgtException(String message) {
        super(message);
    }

    public IdVClaimMgtException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdVClaimMgtException(String errorCode, String message) {
        super(errorCode, message);
    }

    public IdVClaimMgtException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
