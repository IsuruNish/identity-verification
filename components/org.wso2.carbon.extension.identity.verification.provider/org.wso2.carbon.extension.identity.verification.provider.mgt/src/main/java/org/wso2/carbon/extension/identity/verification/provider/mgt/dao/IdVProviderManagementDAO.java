package org.wso2.carbon.extension.identity.verification.provider.mgt.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtException;
import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.provider.mgt.util.IdVProviderMgtConstants;
import org.wso2.carbon.extension.identity.verification.provider.mgt.util.IdVProviderMgtExceptionManagement;
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdVProviderManagementDAO {

    private static final Log log = LogFactory.getLog(IdVProviderManagementDAO.class);

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

    public void deleteIdVProvider(String idvUUID) throws IdVProviderMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement deleteIdVProviderStmt =
                         connection.prepareStatement(IdVProviderMgtConstants.SQLQueries.DELETE_IDV_SQL)) {
                deleteIdVProviderStmt.setString(1, idvUUID);
                deleteIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);
                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                        ERROR_CODE_DELETING_IDV_PROVIDER, idvUUID, e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DELETING_IDV_PROVIDER, String.valueOf(idvUUID), e);
        }
    }

    public List<IdentityVerificationProvider> getIdVProviders(String tenantDomain) {

        int tenantID = MultitenantConstants.INVALID_TENANT_ID;

        if (tenantDomain != null) {
            tenantID = IdentityTenantUtil.getTenantId(tenantDomain);
        }

        List<IdentityVerificationProvider> identityVerificationProviders = new ArrayList<>();

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProvidersStmt = connection
                    .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVS_SQL)) {
                getIdVProvidersStmt.setInt(1, tenantID);

                try (ResultSet functionLibsResultSet = getIdVProvidersStmt.executeQuery()) {
                    while (functionLibsResultSet.next()) {
                        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
                        identityVerificationProvider.setFunctionLibraryName(functionLibsResultSet.getString("NAME"));
                        identityVerificationProvider.setDescription(functionLibsResultSet.getString("DESCRIPTION"));
                        identityVerificationProviders.add(identityVerificationProvider);
                    }
                }
            } catch (SQLException e1) {
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                        ERROR_CODE_RETRIEVE_SCRIPT_LIBRARIES, e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVE_SCRIPT_LIBRARIES, e);
        } catch (IdentityRuntimeException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
        return identityVerificationProviders;
    }
}
