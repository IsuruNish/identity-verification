package org.wso2.carbon.extension.identity.verification.provider.mgt.dao;

import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtException;
import org.wso2.carbon.extension.identity.verification.provider.mgt.util.IdVProviderMgtConstants;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

public class IdVProviderManagementDAO {

    public void getIdP(int idvUUID) throws IdVProviderMgtException {

        try (Connection conn = IdentityDatabaseUtil.getDBConnection(false)) {
            PreparedStatement prepStmt = conn.prepareStatement(
                    IdVProviderMgtConstants.SQLQueries.GET_IDV_SQL);
            prepStmt.setInt(1, idvUUID);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IdVProviderMgtException("Error occurred while deleting Identity Verification Provider" + UUID, e);
        }
    }

    public void addIdP(int idvUUID) throws IdVProviderMgtException {

        try (Connection conn = IdentityDatabaseUtil.getDBConnection(false)) {
            PreparedStatement prepStmt = conn.prepareStatement(
                    IdVProviderMgtConstants.SQLQueries.GET_IDV_SQL);
            prepStmt.setInt(1, idvUUID);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IdVProviderMgtException("Error occurred while deleting Identity Verification Provider" + UUID, e);
        }
    }

    public void deleteIdP(int idvUUID) throws IdVProviderMgtException {

        try (Connection conn = IdentityDatabaseUtil.getDBConnection(false);
             PreparedStatement prepStmt = conn.prepareStatement(IdVProviderMgtConstants.SQLQueries.DELETE_IDV_SQL)) {
                prepStmt.setInt(1, idvUUID);
                prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IdVProviderMgtException("Error occurred while deleting " +
                    "Identity Verification Provider" + idvUUID, e);
        }
    }
}
