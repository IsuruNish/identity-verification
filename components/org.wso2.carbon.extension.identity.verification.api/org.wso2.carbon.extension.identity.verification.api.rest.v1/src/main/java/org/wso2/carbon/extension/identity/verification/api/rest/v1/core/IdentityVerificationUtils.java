/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.common.ContextLoader;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;

import javax.ws.rs.core.Response;

/**
 * This class contains the utils for the Identity Verification APIs.
 */
public class IdentityVerificationUtils {

    private static final Log LOG = LogFactory.getLog(IdentityVerificationUtils.class);

    /**
     * Handle exceptions generated in API.
     *
     * @param status HTTP Status.
     * @param error  Error Message information.
     * @return APIError.
     */
    public static APIError handleException(Response.Status status, Constants.ErrorMessage error,
                                     String data) {

        return new APIError(status, getErrorBuilder(error, data).build());
    }

    public static APIError handleException(String errorCode, String errorMessage, String errorDescription) {

        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = getErrorBuilder(errorCode, errorMessage, errorDescription)
                .build(LOG, errorDescription, false);
        return new APIError(status, errorResponse);
    }

    /**
     * Return error builder.
     *
     * @param errorMsg Error Message information.
     * @return ErrorResponse.Builder.
     */
    public static ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorMsg,
                                                  String data) {

        return new ErrorResponse.Builder().withCode(errorMsg.getCode()).withMessage(errorMsg.getMessage())
                .withDescription(includeData(errorMsg, data));
    }

    public static ErrorResponse.Builder getErrorBuilder(String errorCode, String errorMessage,
                                                        String errorDescription) {

        return new ErrorResponse.Builder()
                .withCode(errorCode)
                .withMessage(errorMessage)
                .withDescription(errorDescription);
    }

    /**
     * Include context data to error message.
     *
     * @param error Error message.
     * @param data  Context data.
     * @return Formatted error message.
     */
    public static String includeData(Constants.ErrorMessage error, String data) {

        if (StringUtils.isNotBlank(data)) {
            return String.format(error.getDescription(), data);
        } else {
            return error.getDescription();
        }
    }

    public static int getTenantId() {

        String tenantDomain = ContextLoader.getTenantDomainFromAuthUser();
        if (StringUtils.isBlank(tenantDomain)) {
            throw handleException(
                    Constants.ErrorMessage.ERROR_COMMON_SERVER_ERROR.getCode(),
                    Constants.ErrorMessage.ERROR_COMMON_SERVER_ERROR.getMessage(),
                    "Unable to retrieve tenant information.");
        }

        return IdentityTenantUtil.getTenantId(tenantDomain);
    }
}
