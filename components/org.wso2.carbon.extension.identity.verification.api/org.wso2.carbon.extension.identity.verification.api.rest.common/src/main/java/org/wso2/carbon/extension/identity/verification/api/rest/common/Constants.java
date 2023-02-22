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

package org.wso2.carbon.extension.identity.verification.api.rest.common;

/**
 * Constants related to federated user association service.
 */
public class Constants {

    public static final String CORRELATION_ID_MDC = "Correlation-ID";
    // todo
    public static final String CONFLICT_ASSOCIATION_CODE = "10004";
    public static final String INVALID_IDP_CODE = "10007";
    public static final String NON_EXISTING_USER_CODE = "30007 - ";

    // Audit constants.
    public static final String ACTION_FEDERATED_ASC_CREATE = "Federated Association - Create Association";
    public static final String USER_NAME = "Username";
    public static final String USER_ID = "UserId";
    public static final String FIDP = "Federated IDP";
    public static final String ERROR_CODE = "Error Code";
    public static final String ERROR_MSG = "Error Message";

    /**
     * Enum for federated user association creation related errors.
     * Error Code - code to identify the error.
     * Error Message - What went wrong.
     * Error Description - Why it went wrong.
     */
    public enum ErrorMessage {

        ERROR_CODE_ERROR_ADDING_IDVP("IDV-65000",
                "Unable to add identity verification provider.",
                "Server encountered an error while adding the identity verification provider."),

        // todo
        // Server errors.
        ERROR_CODE_CREATING_FEDERATED_USER_ASSOCIATIONS("FUA-15001", "Error creating federated user association",
                "Error occurred while creating federated user association for local user: %s with " +
                        "federated user: %s in IDP: %s"),
        ERROR_CODE_RETRIEVE_USER_NAME_BY_ID("FUA-15002", "Error occurred while retrieving username",
                "Error occurred while retrieving username by userid: %s."),
        ERROR_CODE_RETRIEVING_USERSTORE_MANAGER("FUA-15003", "Error retrieving userstore manager.",
                "Error occurred while retrieving userstore manager."),

        // Client errors.
        ERROR_CODE_NON_EXISTING_USER_ID("FUA-10001", "User not found",
                "Non exiting user for the given userid: %s."),
        ERROR_CODE_CONFLICT_ASSOCIATION("FUA-10002", "Association already exists.",
                "User: %s has a federated association against the given idp: %s."),
        ERROR_CODE_INVALID_IDP("FUA-10003", "Invalid federated IDP.",
                "Could not find an identity provider by id: %s.");

        private final String code;
        private final String message;
        private final String description;

        ErrorMessage(String code, String message, String description) {

            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode() {

            return code;
        }

        public String getMessage() {

            return message;
        }

        public String getDescription() {

            return description;
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }
}
