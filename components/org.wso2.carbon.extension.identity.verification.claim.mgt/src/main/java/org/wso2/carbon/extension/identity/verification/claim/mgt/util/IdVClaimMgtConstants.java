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

package org.wso2.carbon.extension.identity.verification.claim.mgt.util;

/**
 * Holds constants related to IdV Claim Management components.
 */
public class IdVClaimMgtConstants {

    public static final String IDVC_ERROR_PREFIX = "IDVC-";

    /**
     * Holds constants related to IdV Claim Management database tables.
     */
    public static class SQLQueries {

        public static final String ADD_IDV_CLAIM_SQL =
                "INSERT INTO IDV_CLAIM (UUID, USER_ID, CLAIM_URI, IDVP_ID, TENANT_ID, STATUS, METADATA) " +
                        "VALUES (?,?,?,?,?,?,?)";

        public static final String GET_IDV_CLAIM_SQL =
                "SELECT ID, UUID, USER_ID, CLAIM_URI, TENANT_ID, IDVP_ID, STATUS, METADATA FROM IDV_CLAIM WHERE " +
                        "UUID = ? AND TENANT_ID = ?";

        public static final String GET_IDV_CLAIMS_SQL =
                "SELECT ID, UUID, USER_ID, CLAIM_URI, STATUS, METADATA FROM IDV_CLAIM WHERE " +
                        "USER_ID = ? AND TENANT_ID = ?";
        public static final String UPDATE_IDV_CLAIM_SQL =
                "UPDATE IDV_CLAIM SET STATUS = ?, METADATA = ? WHERE UUID = ? AND TENANT_ID = ?";

        public static final String DELETE_IDV_CLAIM_SQL =
                "DELETE FROM IDV_CLAIM WHERE USER_ID = ? AND UUID = ? AND TENANT_ID = ?";
    }

    /**
     * Error messages.
     */
    public enum ErrorMessage {

        ERROR_DATABASE_CONNECTION("65001", "Couldn't get a database connection."),
        ERROR_IDVP_REQUEST_INVALID("65005",
                "An Identity Verification Provider already exists with the name: %s.");
        private final String code;
        private final String message;

        ErrorMessage(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return IDVC_ERROR_PREFIX + code;
        }

        public String getMessage() {

            return message;
        }

        @Override
        public String toString() {

            return code + ":" + message;
        }
    }
}
