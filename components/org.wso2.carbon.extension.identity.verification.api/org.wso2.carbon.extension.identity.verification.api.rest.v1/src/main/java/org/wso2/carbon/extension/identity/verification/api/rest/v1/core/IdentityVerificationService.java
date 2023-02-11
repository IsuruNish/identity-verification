package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationPostResponse;

import javax.ws.rs.core.Response;

public class IdentityVerificationService {

    public VerificationGetResponse getIdentityVerificationInfo(String userId) {

    }

    public VerificationClaimResponse getVerificationClaimMetadata(String userId, String claimId) {

    }

    public VerificationPostResponse verifyIdentity(VerificationPostRequest verificationPostRequest) {

    }

    private APIError handleInvalidInput(Constants.ErrorMessage errorEnum, String... data) {

        return handleError(Response.Status.BAD_REQUEST, errorEnum);
    }

    private APIError handleError(Response.Status status, Constants.ErrorMessage error) {

        return new APIError(status, getErrorBuilder(error).build());
    }

    private ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorEnum) {

        return new ErrorResponse.Builder().withCode(errorEnum.getCode()).withMessage(errorEnum.getMessage())
                .withDescription(errorEnum.getDescription());
    }
}
