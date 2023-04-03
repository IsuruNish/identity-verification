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
package org.wso2.carbon.extension.identity.verification.provider.util;

/**
 * This class contains the constants used in the IdentityVerificationProvider.
 */
public class IdVProviderMgtConstants {

    public static final String IDVP_ERROR_PREFIX = "IDVP-";
    public static final String IDVP_SECRET_TYPE_IDVP_SECRETS = "IDVP_SECRET_PROPERTIES";
    public static final String ID = "ID";
    public static final String UUID = "UUID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String IS_ENABLED = "IS_ENABLED";
    public static final String PROPERTY_KEY = "PROPERTY_KEY";
    public static final String PROPERTY_VALUE = "PROPERTY_VALUE";
    public static final String IS_SECRET = "IS_SECRET";
    public static final String CLAIM = "CLAIM";
    public static final String LOCAL_CLAIM = "LOCAL_CLAIM";

    /**
     * This class contains the constants used in the IdentityVerificationProvider.
     */
    public static class SQLQueries {

        public static final String GET_IDVP_SQL = "SELECT ID, UUID, NAME, DESCRIPTION, IS_ENABLED" +
                " FROM IDVP WHERE UUID=? AND TENANT_ID=?";
        public static final String IS_IDVP_EXIST_SQL = "SELECT ID FROM IDVP WHERE UUID=? AND TENANT_ID=?";
        public static final String GET_IDVP_BY_NAME_SQL = "SELECT ID, UUID, NAME, DESCRIPTION, " +
                "IS_ENABLED FROM IDVP WHERE NAME=? AND TENANT_ID=?";
        public static final String GET_IDVP_CONFIG_SQL = "SELECT PROPERTY_KEY, PROPERTY_VALUE, IS_SECRET FROM " +
                "IDVP_CONFIG WHERE IDVP_ID=? AND TENANT_ID=?";
        public static final String GET_IDVP_CLAIMS_SQL = "SELECT CLAIM, LOCAL_CLAIM FROM " +
                "IDVP_CLAIM_MAPPING WHERE IDVP_ID=? AND TENANT_ID=?";
        public static final String GET_IDVPS_SQL = "SELECT ID, UUID, NAME, DESCRIPTION, IS_ENABLED FROM " +
                "IDVP WHERE TENANT_ID=? ORDER BY UUID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        public static final String GET_COUNT_OF_IDVPS_SQL = "SELECT COUNT(*) FROM IDVP WHERE TENANT_ID=?";
        public static final String DELETE_IDV_SQL = "DELETE FROM IDVP WHERE UUID=? AND TENANT_ID=?";
        public static final String ADD_IDVP_SQL = "INSERT INTO IDVP(UUID, TENANT_ID, NAME, " +
                "DESCRIPTION, IS_ENABLED) VALUES (?, ?, ?, ?, ?)";
        public static final String ADD_IDVP_CONFIG_SQL = "INSERT INTO IDVP_CONFIG " +
                "(IDVP_ID, TENANT_ID, PROPERTY_KEY, PROPERTY_VALUE, IS_SECRET) VALUES (?, ?, ?, ?, ?)";
        public static final String ADD_IDVP_CLAIM_SQL = "INSERT INTO IDVP_CLAIM_MAPPING " +
                "(IDVP_ID, TENANT_ID, CLAIM, LOCAL_CLAIM) VALUES (?, ?, ?, ?)";
        public static final String UPDATE_IDVP_SQL = "UPDATE IDVP SET NAME=?, DESCRIPTION=?, " +
                "IS_ENABLED = ? WHERE UUID=? AND TENANT_ID=?";
        public static final String DELETE_IDVP_CONFIG_SQL = "DELETE FROM IDVP_CONFIG " +
                "WHERE IDVP_ID=? AND TENANT_ID=?";
        public static final String DELETE_IDVP_CLAIM_SQL = "DELETE FROM IDVP_CLAIM_MAPPING " +
                "WHERE IDVP_ID=? AND TENANT_ID=?";
    }

    /**
     * Error messages.
     */
    public enum ErrorMessage {

        ERROR_DATABASE_CONNECTION("65001", "Couldn't get a database connection."),
        ERROR_RETRIEVING_IDV_PROVIDERS("65002",
                "An error occurred while retrieving Identity Verification Providers."),
        ERROR_RETRIEVING_IDV_PROVIDER("65003",
                "An error occurred while retrieving Identity Verification Provider by %s."),
        ERROR_RETRIEVING_IDV_PROVIDER_CONFIGS("65004",
                "An error occurred while retrieving Identity Verification Provider configs."),
        ERROR_RETRIEVING_IDV_PROVIDER_CLAIMS("65005",
                "An error occurred while retrieving Identity Verification Provider claims."),
        ERROR_ADDING_IDV_PROVIDER("65006", "Error while adding Identity Verification Provider."),
        ERROR_DELETING_IDV_PROVIDER("65007",
                "An error occurred while deleting Identity Verification Provider: %s."),
        ERROR_IDVP_ALREADY_EXISTS("65008",
                "An Identity Verification Provider already exists with the name: %s."),
        ERROR_EMPTY_IDVP_ID("65009",
                "Identity Verification Provider ID value is empty.");

        private final String code;
        private final String message;

        ErrorMessage(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return IDVP_ERROR_PREFIX + code;
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
