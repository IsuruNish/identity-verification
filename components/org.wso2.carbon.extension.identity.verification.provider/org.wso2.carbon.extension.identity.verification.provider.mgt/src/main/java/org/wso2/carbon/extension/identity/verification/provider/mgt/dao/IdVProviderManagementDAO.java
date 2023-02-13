package org.wso2.carbon.extension.identity.verification.provider.mgt.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtException;
import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdVConfigProperty;
import org.wso2.carbon.extension.identity.verification.provider.mgt.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.provider.mgt.util.IdVProviderMgtConstants;
import org.wso2.carbon.extension.identity.verification.provider.mgt.util.IdVProviderMgtExceptionManagement;
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;
import org.wso2.carbon.identity.core.util.IdentityTenantUtil;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdVProviderManagementDAO {

    private static final Log log = LogFactory.getLog(IdVProviderManagementDAO.class);

    public void getIdP(int idVProviderUID) throws IdVProviderMgtException {

        try (Connection conn = IdentityDatabaseUtil.getDBConnection(false)) {
            PreparedStatement prepStmt = conn.prepareStatement(
                    IdVProviderMgtConstants.SQLQueries.ADD_IDV_SQL);
            prepStmt.setInt(1, idVProviderUID);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            throw new IdVProviderMgtException("Error occurred while deleting Identity " +
                    "Verification Provider" + idVProviderUID, e);
        }
    }

    public void addIdVProvider(IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException {

        String idvProviderId = identityVerificationProvider.getIdVProviderId();
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement addIdVProviderStmt = connection.prepareStatement(IdVProviderMgtConstants.
                    SQLQueries.ADD_IDV_SQL)) {
                addIdVProviderStmt.setString(1, identityVerificationProvider.getIdVProviderId());
                addIdVProviderStmt.setString(2, identityVerificationProvider.getIdVProviderName());
                addIdVProviderStmt.setString(3, identityVerificationProvider.getDisplayName());
                addIdVProviderStmt.setString(4,
                        identityVerificationProvider.getIdVProviderDescription());
                addIdVProviderStmt.executeUpdate();

                // TODO
                //IdentityVerificationProvider createdIDP = getIdVProvider(dbConnection, resourceId, tenantId,
                //        IdentityTenantUtil.getTenantDomain(tenantId));
                // add federated authenticators.
                addIDVProviderConfigs(identityVerificationProvider, connection);

                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage
                                .ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e);
        }
    }

    public void updateIdVProvider(IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException {

        String idvProviderId = identityVerificationProvider.getIdVProviderId();
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement addIdVProviderStmt = connection.prepareStatement(IdVProviderMgtConstants.
                    SQLQueries.UPDATE_IDV_SQL)) {
                addIdVProviderStmt.setString(1, identityVerificationProvider.getIdVProviderName());
                addIdVProviderStmt.setString(2, identityVerificationProvider.getDisplayName());
                addIdVProviderStmt.setString(3,
                        identityVerificationProvider.getIdVProviderDescription());
                addIdVProviderStmt.setString(4, idvProviderId);

                addIdVProviderStmt.executeUpdate();

                // TODO
                //IdentityVerificationProvider createdIDP = getIdVProvider(dbConnection, resourceId, tenantId,
                //        IdentityTenantUtil.getTenantDomain(tenantId));
                // add federated authenticators.
                addIDVProviderConfigs(identityVerificationProvider, connection);

                IdentityDatabaseUtil.commitTransaction(connection);

                if (log.isDebugEnabled()) {
                    log.debug("");
                }
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage
                        .ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e);
        }
    }

    private static void addIDVProviderConfigs(IdentityVerificationProvider identityVerificationProvider,
                                              Connection connection) throws SQLException {

        PreparedStatement prepStmt2 = null;
        IdVConfigProperty[] idVConfigProperties = identityVerificationProvider.getIdVConfigProperties();
        String sqlStmt = IdVProviderMgtConstants.SQLQueries.ADD_IDV_CONFIG_SQL;
        if (idVConfigProperties == null) {
            identityVerificationProvider.setIdVConfigProperties(new IdVConfigProperty[0]);
        }
        for (IdVConfigProperty idVConfigProperty : idVConfigProperties) {

            prepStmt2 = connection.prepareStatement(sqlStmt);
            prepStmt2.setString(1, identityVerificationProvider.getIdVProviderId());
            prepStmt2.setString(2, idVConfigProperty.getName());
            prepStmt2.setString(3, idVConfigProperty.getValue());
            prepStmt2.executeUpdate();
        }
    }

    public List<IdentityVerificationProvider> getIdVProviders(String tenantDomain) throws IdVProviderMgtException{

        int tenantID = MultitenantConstants.INVALID_TENANT_ID;

        if (tenantDomain != null) {
            tenantID = IdentityTenantUtil.getTenantId(tenantDomain);
        }

        List<IdentityVerificationProvider> identityVerificationProviders = new ArrayList<>();

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProvidersStmt = connection
                    .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVS_SQL)) {
                getIdVProvidersStmt.setInt(1, tenantID);

                try (ResultSet idVProviderResultSet = getIdVProvidersStmt.executeQuery()) {
                    while (idVProviderResultSet.next()) {
                        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
                        identityVerificationProvider.
                                setIdVProviderId(idVProviderResultSet.getString("IDV_PROVIDER_UID"));
                        identityVerificationProvider.setIdVProviderName(idVProviderResultSet.getString("NAME"));
                        identityVerificationProvider.setDisplayName(idVProviderResultSet.getString("DISPLAY_NAME"));
                        identityVerificationProvider.
                                setIdVProviderDescription(idVProviderResultSet.getString("DESCRIPTION"));
                        identityVerificationProvider.setEnable(idVProviderResultSet.getBoolean("IS_ENABLE"));
                        identityVerificationProviders.add(identityVerificationProvider);
                    }
                }
            } catch (SQLException e1) {
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                        ERROR_CODE_RETRIEVING_IDV_PROVIDERS, e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVING_IDV_PROVIDERS, e);
        } catch (IdentityRuntimeException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
        return identityVerificationProviders;
    }

    // TODO
    public IdentityVerificationProvider getIdVProvider(String idvProviderId) throws IdVProviderMgtException{

        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProvidersStmt = connection
                    .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDV_SQL)) {
                getIdVProvidersStmt.setString(1, idvProviderId);

                try (ResultSet idVProviderResultSet = getIdVProvidersStmt.executeQuery()) {
                        identityVerificationProvider.setIdVProviderId(idVProviderResultSet.getString("UUID"));
                        identityVerificationProvider.setIdVProviderName(idVProviderResultSet.getString("NAME"));
                        identityVerificationProvider.setDisplayName(idVProviderResultSet.getString("DISPLAY_NAME"));
                        identityVerificationProvider.setIdVProviderDescription(idVProviderResultSet.
                                getString("DESCRIPTION"));
                        identityVerificationProvider.setEnable(idVProviderResultSet.getBoolean("IS_ENABLE"));
                }
            } catch (SQLException e1) {
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                        ERROR_CODE_RETRIEVING_IDV_PROVIDERS, e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVING_IDV_PROVIDERS, e);
        } catch (IdentityRuntimeException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
        return identityVerificationProvider;
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
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
    }
}
