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
