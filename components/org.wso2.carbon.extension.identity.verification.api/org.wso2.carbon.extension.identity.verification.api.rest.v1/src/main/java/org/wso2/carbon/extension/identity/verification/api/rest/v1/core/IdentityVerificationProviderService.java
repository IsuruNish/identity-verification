package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdVProviderServiceHolder;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtException;

import javax.ws.rs.core.Response;

public class IdentityVerificationProviderService {


    public void deleteIDV(String identityVerificationProviderId) {

        try {
            IdVProviderServiceHolder.getIdVProviderManager().deleteIdVProvider(identityVerificationProviderId);
        } catch (IdVProviderMgtException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * Handle IDVProviderManagementException, extract error code, error description and status code to be sent
//     * in the response.
//     *
//     * @param e         IDVProviderManagementException
//     * @param errorEnum Error Message information.
//     * @return APIError.
//     */
//    private APIError handleIdPException(IdentityProviderManagementException e,
//                                        Constants.ErrorMessage errorEnum, String data) {
//
//        ErrorResponse errorResponse = getErrorBuilder(errorEnum, data).build(log, e, includeData(errorEnum, data));
//
//        Response.Status status;
//
//        if (e instanceof IdentityProviderManagementClientException) {
//            if (e.getErrorCode() != null) {
//                String errorCode = e.getErrorCode();
//                errorCode =
//                        errorCode.contains(org.wso2.carbon.identity.api.server.common.Constants.ERROR_CODE_DELIMITER) ?
//                                errorCode : Constants.IDP_MANAGEMENT_PREFIX + errorCode;
//                errorResponse.setCode(errorCode);
//            }
//            errorResponse.setDescription(e.getMessage());
//            status = Response.Status.BAD_REQUEST;
//        } else if (e instanceof IdentityProviderManagementServerException) {
//            if (e.getErrorCode() != null) {
//                String errorCode = e.getErrorCode();
//                errorCode =
//                        errorCode.contains(org.wso2.carbon.identity.api.server.common.Constants.ERROR_CODE_DELIMITER) ?
//                                errorCode : Constants.IDP_MANAGEMENT_PREFIX + errorCode;
//                errorResponse.setCode(errorCode);
//            }
//            errorResponse.setDescription(e.getMessage());
//            status = Response.Status.INTERNAL_SERVER_ERROR;
//        } else {
//            status = Response.Status.INTERNAL_SERVER_ERROR;
//        }
//        return new APIError(status, errorResponse);
//    }
//
//    /**
//     * Return error builder.
//     *
//     * @param errorMsg Error Message information.
//     * @return ErrorResponse.Builder.
//     */
//    private ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorMsg, String data) {
//
//        return new ErrorResponse.Builder().withCode(errorMsg.getCode()).withMessage(errorMsg.getMessage())
//                .withDescription(includeData(errorMsg, data));
//    }
}
