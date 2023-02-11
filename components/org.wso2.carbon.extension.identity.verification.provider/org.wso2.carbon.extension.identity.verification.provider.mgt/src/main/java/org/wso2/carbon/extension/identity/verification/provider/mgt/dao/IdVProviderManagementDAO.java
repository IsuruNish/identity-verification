package org.wso2.carbon.extension.identity.verification.provider.mgt.dao;

import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtException;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IdVProviderManagementDAO {

    /**
     * Delete all IDPs of a given tenant id.
     *
     * @param tenantId Id of the tenant
     * @throws IdVProviderMgtException
     */
    public void deleteIdPs(int tenantId) throws IdVProviderMgtException {

        try (Connection conn = IdentityDatabaseUtil.getDBConnection(false)) {
            PreparedStatement prepStmt = conn.prepareStatement(
                    IdPManagementConstants.SQLQueries.DELETE_ALL_IDP_BY_TENANT_ID_SQL);
            prepStmt.setInt(1, tenantId);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IdentityProviderManagementException("Error occurred while deleting Identity Providers of tenant "
                    + tenantId, e);
        }
    }
}
