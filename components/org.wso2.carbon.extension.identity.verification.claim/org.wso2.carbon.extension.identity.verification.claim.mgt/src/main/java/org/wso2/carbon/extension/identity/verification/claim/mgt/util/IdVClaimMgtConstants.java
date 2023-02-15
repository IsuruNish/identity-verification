package org.wso2.carbon.extension.identity.verification.claim.mgt.util;

public class IdVClaimMgtConstants {

    public static class SQLQueries {

        public static final String ADD_IDV_CLAIM_SQL =
                "INSERT INTO IDV_CLAIM (IDV_CLAIM_ID, IDV_STATUS, IDV_CLAIM_METADATA) VALUES (?,?,?)";

        public static final String GET_IDV_CLAIM_SQL =
                "SELECT IDV_CLAIM_ID, IDV_STATUS, IDV_CLAIM_METADATA FROM IDV_CLAIM WHERE IDV_CLAIM_ID = ?";

        public static final String UPDATE_IDV_CLAIM_SQL =
                "UPDATE IDV_CLAIM SET IDV_STATUS = ?, IDV_CLAIM_METADATA = ? WHERE IDV_CLAIM_ID = ?";

        public static final String DELETE_IDV_CLAIM_SQL =
                "DELETE FROM IDV_CLAIM WHERE IDV_CLAIM_ID = ?";
    }

}
