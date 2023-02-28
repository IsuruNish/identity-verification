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
package org.wso2.carbon.extension.identity.verification.claim.mgt.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Identity verification claim DAO class.
 */
public class IdentityVerificationClaimDAO {

    private static final Log log = LogFactory.getLog(IdentityVerificationClaimDAO.class);

    /**
     * Get the identity verification claim by the user id.
     *
     * @param userId   User id.
     * @param idVClaim IdentityVerificationClaim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    public void addIdVClaim(String userId, IdVClaim idVClaim) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement addIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.ADD_IDV_CLAIM_SQL)) {
                addIdVProviderStmt.setString(1, idVClaim.getUuid());
                addIdVProviderStmt.setString(2, idVClaim.getLocalClaimId());
                addIdVProviderStmt.setString(3, idVClaim.getClaimUri());
                addIdVProviderStmt.setString(4, idVClaim.getClaimValue());
                addIdVProviderStmt.setString(5, userId);
                addIdVProviderStmt.setString(6, idVClaim.getStatus());
                addIdVProviderStmt.setObject(7, idVClaim.getMetadata());
                addIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while adding the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while adding the identity verification claim.", e);
        }
    }

    /**
     * Get the identity verification claim by the user id.
     *
     * @param userId   User id.
     * @param idVClaim IdentityVerificationClaim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    public void updateIdVClaim(String userId, IdVClaim idVClaim) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement updateIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.UPDATE_IDV_CLAIM_SQL)) {
                updateIdVProviderStmt.setString(1, idVClaim.getStatus());
                updateIdVProviderStmt.setObject(2, idVClaim.getMetadata());
                updateIdVProviderStmt.setString(3, userId);
                updateIdVProviderStmt.setString(4, idVClaim.getUuid());
                updateIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while updating the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while updating the identity verification claim.", e);
        }
    }

    /**
     * Get the identity verification claim.
     *
     * @param idVClaimId Identity verification claim id.
     * @return Identity verification claim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    public IdVClaim getIDVClaim(String userId, String idVClaimId, int tenantId) throws IdVClaimMgtException {

        IdVClaim idVClaim = null;
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.GET_IDV_CLAIM_SQL)) {
                getIdVProviderStmt.setString(1, idVClaimId);
                getIdVProviderStmt.setString(2, userId);
                getIdVProviderStmt.setInt(3, tenantId);
                getIdVProviderStmt.execute();
                try (ResultSet idVProviderResultSet = getIdVProviderStmt.executeQuery()) {
                    while (idVProviderResultSet.next()) {
                        idVClaim = new IdVClaim();
                        idVClaim.setId(idVProviderResultSet.getString("ID"));
                        idVClaim.setUuid(idVProviderResultSet.getString("UUID"));
                        idVClaim.setUserId(idVProviderResultSet.getString("USER_ID"));
                        idVClaim.setLocalClaimId(idVProviderResultSet.getString("LOCAL_CLAIM_ID"));
                        idVClaim.setStatus(idVProviderResultSet.getString("STATUS"));
                        idVClaim.setMetadata((JSONObject) idVProviderResultSet.getObject("METADATA"));
                    }
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e);
        }
        return idVClaim;
    }

    /**
     * Get the identity verification claim.
     *
     * @param tenantId Identity verification claim id.
     * @param userId   User id.
     * @return Identity verification claim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    public IdVClaim[] getIDVClaims(String userId, int tenantId) throws IdVClaimMgtException {

        List<IdVClaim> idVClaims = new ArrayList<>();
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.GET_IDV_CLAIMS_SQL)) {
                getIdVProviderStmt.setString(1, userId);
                getIdVProviderStmt.setInt(2, tenantId);
                getIdVProviderStmt.execute();
                try (ResultSet idVProviderResultSet = getIdVProviderStmt.executeQuery()) {
                    while (idVProviderResultSet.next()) {
                        IdVClaim idVClaim = new IdVClaim();
                        idVClaim.setId(idVProviderResultSet.getString("ID"));
                        idVClaim.setUuid(idVProviderResultSet.getString("UUID"));
                        idVClaim.setUserId(idVProviderResultSet.getString("USER_ID"));
                        idVClaim.setLocalClaimId(idVProviderResultSet.getString("LOCAL_CLAIM_ID"));
                        idVClaim.setStatus(idVProviderResultSet.getString("STATUS"));
                        idVClaim.setMetadata((JSONObject) idVProviderResultSet.getObject("METADATA"));
                        idVClaims.add(idVClaim);
                    }
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e);
        }
        return idVClaims.toArray(new IdVClaim[0]);
    }

    /**
     * Delete the identity verification claim.
     *
     * @param idVClaimId Identity verification claim id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    public void deleteIdVClaim(String userId, String idVClaimId) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement deleteIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.DELETE_IDV_CLAIM_SQL)) {
                deleteIdVProviderStmt.setString(1, userId);
                deleteIdVProviderStmt.setString(2, idVClaimId);
                deleteIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while deleting the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while deleting the identity verification claim.", e);
        }
    }
}
