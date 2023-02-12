package org.wso2.carbon.extension.identity.verification.provider.mgt.util;

public class IdVProviderMgtConstants {

    public static class SQLQueries {

        public static final String DELETE_IDV_SQL = "DELETE FROM IDV WHERE IDV_UUID=?";
        public static final String GET_IDV_SQL = "SELECT UUID, NAME, DESCRIPTION, CLAIMS, CONFIGS " +
                "FROM IDV WHERE IDV_UUID=?";

        public static final String GET_IDVS_SQL = "SELECT UUID, NAME, DESCRIPTION FROM IDV WHERE TENANT_ID=?";

        public static final String ADD_IDV_SQL = "INSERT INTO IDV(UUID, NAME, DESCRIPTION) VALUES (?, ?, ?)";

        public static final String ADD_IDV_CONFIG_SQL = "INSERT INTO IDP_CONFIG_PROPERTY " +
                "(IDV_ID, TENANT_ID, PROPERTY_KEY, PROPERTY_VALUE) VALUES (?, ?, ?, ?)";

        public static final String ADD_IDP_CLAIMS_SQL = "INSERT INTO IDV_CLAIM (IDV_ID, TENANT_ID, CLAIM) "
                + "VALUES (?, ?, ?)";

        public static final String UPDATE_IDV_SQL = "UPDATE IDV SET NAME=?, DESCRIPTION=? WHERE UUID=?";

        public static final String UPDATE_IDV_CONFIG_SQL = "UPDATE IDP_CONFIG_PROPERTY SET " +
                "PROPERTY_VALUE=? WHERE IDV_ID=? AND TENANT_ID=? AND PROPERTY_KEY=?";

        public static final String UPDATE_IDP_CLAIMS_SQL = "UPDATE IDV_CLAIM SET CLAIM=? "
                + "WHERE IDV_ID=? AND TENANT_ID=? AND CLAIM=?";
    }

    public enum ErrorMessage {

        ERROR_CODE_DELETING_IDV_PROVIDER("SCL-65000",
                "An error occurred while deleting Identity Verification Provider: %s.");
        private final String code;
        private final String message;

        ErrorMessage(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return code;
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
