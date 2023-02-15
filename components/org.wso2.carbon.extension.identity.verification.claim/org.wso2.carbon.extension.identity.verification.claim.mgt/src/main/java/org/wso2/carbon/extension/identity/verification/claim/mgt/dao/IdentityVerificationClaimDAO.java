package org.wso2.carbon.extension.identity.verification.claim.mgt.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IdentityVerificationClaimDAO {

    private static final Log log = LogFactory.getLog(IdentityVerificationClaimDAO.class);

    public void addIdVClaim(IdVClaim idVClaim) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement addIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.ADD_IDV_CLAIM_SQL)) {
                addIdVProviderStmt.setString(1, idVClaim.getIdVClaimId());
                addIdVProviderStmt.setString(2, idVClaim.getIdVStatus());
                addIdVProviderStmt.setObject(3, idVClaim.getIdVClaimMetadata());
                addIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while adding the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while adding the identity verification claim.", e);
        }
    }

    public void updateIdVClaim(IdVClaim idVClaim) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement updateIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.UPDATE_IDV_CLAIM_SQL)) {
                updateIdVProviderStmt.setString(1, idVClaim.getIdVStatus());
                updateIdVProviderStmt.setObject(2, idVClaim.getIdVClaimMetadata());
                updateIdVProviderStmt.setString(3, idVClaim.getIdVClaimId());
                updateIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while updating the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while updating the identity verification claim.", e);
        }
    }

    public IdVClaim getIDVClaim(String idVClaimId) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.GET_IDV_CLAIM_SQL)) {
                getIdVProviderStmt.setString(1, idVClaimId);
                getIdVProviderStmt.execute();
                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while retrieving the identity verification claim.", e);
        }
        return null;
    }

    public void deleteIdVClaim(String idVClaimId) throws IdVClaimMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement deleteIdVProviderStmt = connection.prepareStatement(IdVClaimMgtConstants.
                    SQLQueries.DELETE_IDV_CLAIM_SQL)) {
                deleteIdVProviderStmt.setString(1, idVClaimId);
                deleteIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw new IdVClaimMgtException("Error while deleting the identity verification claim.", e1);
            }
        } catch (SQLException e) {
            throw new IdVClaimMgtException("Error while deleting the identity verification claim.", e);
        }
    }
}
