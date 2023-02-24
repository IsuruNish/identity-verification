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
package org.wso2.carbon.extension.identity.verification.provider.dao;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pdfbox.util.MapUtil;
import org.wso2.carbon.extension.identity.verification.provider.IdVProviderMgtException;
import org.wso2.carbon.extension.identity.verification.provider.IdvProviderMgtServerException;
import org.wso2.carbon.extension.identity.verification.provider.model.IdVConfigProperty;
import org.wso2.carbon.extension.identity.verification.provider.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtConstants;
import org.wso2.carbon.extension.identity.verification.provider.util.IdVProviderMgtExceptionManagement;
import org.wso2.carbon.identity.base.IdentityRuntimeException;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Layer functionality for Identity Verification Provider management.
 */
public class IdVProviderManagementDAO {

    private static final Log log = LogFactory.getLog(IdVProviderManagementDAO.class);

    /**
     * Get Identity Verification Provider.
     *
     * @param idvProviderId Identity Verification Provider ID.
     * @param tenantId      Tenant ID.
     * @return Identity Verification Provider.
     * @throws IdVProviderMgtException Error when getting Identity Verification Provider.
     */
    public IdentityVerificationProvider getIdVProvider(String idvProviderId, int tenantId)
            throws IdVProviderMgtException {

        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProvidersStmt = connection
                    .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVP_SQL)) {
                getIdVProvidersStmt.setString(1, idvProviderId);
                getIdVProvidersStmt.setInt(2, tenantId);

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
            // Get configs of identity verification provider.
            getIdVProviderWithConfigs(identityVerificationProvider, idvProviderId, tenantId, connection);

            // Get claim mappings of identity verification provider.
            getIdVProvidersWithClaims(identityVerificationProvider, idvProviderId, tenantId, connection);
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVING_IDV_PROVIDERS, e);
        } catch (IdentityRuntimeException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
        return identityVerificationProvider;
    }

    /**
     * Add Identity Verification Provider.
     *
     * @param identityVerificationProvider Identity Verification Provider.
     * @param tenantId                     Tenant ID.
     * @throws IdVProviderMgtException Identity Verification Provider Management Exception.
     */
    public void addIdVProvider(IdentityVerificationProvider identityVerificationProvider, int tenantId)
            throws IdVProviderMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            connection.setAutoCommit(false);
            try (PreparedStatement addIdVProviderStmt = connection.prepareStatement(IdVProviderMgtConstants.
                    SQLQueries.ADD_IDV_SQL)) {
                addIdVProviderStmt.setString(1, identityVerificationProvider.getIdVProviderId());
                addIdVProviderStmt.setString(2, identityVerificationProvider.getIdVProviderName());
                addIdVProviderStmt.setString(3, identityVerificationProvider.getDisplayName());
                addIdVProviderStmt.setString(4,
                        identityVerificationProvider.getIdVProviderDescription());
                addIdVProviderStmt.executeUpdate();
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage
                        .ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e1);
            }
            // Add configs of identity verification provider.
            addIDVProviderConfigs(identityVerificationProvider, tenantId, connection);

            // Add claims of identity verification provider.
            addIDVProviderClaims(identityVerificationProvider, tenantId, connection);
            connection.commit();
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e);
        }
    }

    /**
     * Update Identity Verification Provider.
     *
     * @param idVProviderId                Identity Verification Provider ID.
     * @param tenantId                     Tenant ID.
     * @param identityVerificationProvider Identity Verification Provider.
     * @throws IdVProviderMgtException Error when updating Identity Verification Provider configs.
     */
    public void updateIdVProvider(String idVProviderId, int tenantId,
                                  IdentityVerificationProvider identityVerificationProvider)
            throws IdVProviderMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement updateIdVProviderStmt = connection.prepareStatement(IdVProviderMgtConstants.
                    SQLQueries.UPDATE_IDVP_SQL)) {
                updateIdVProviderStmt.setString(1, identityVerificationProvider.getIdVProviderName());
                updateIdVProviderStmt.setString(2, identityVerificationProvider.getDisplayName());
                updateIdVProviderStmt.setString(3,
                        identityVerificationProvider.getIdVProviderDescription());
                updateIdVProviderStmt.setBoolean(4, identityVerificationProvider.isEnable());
                updateIdVProviderStmt.setString(5, idVProviderId);
                updateIdVProviderStmt.setInt(6, tenantId);
                updateIdVProviderStmt.executeUpdate();
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage
                        .ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e1);
            }
            // Update configs of identity verification provider.
            updateIDVProviderConfigs(identityVerificationProvider, idVProviderId, tenantId, connection);

            // Update claims of identity verification provider.
            updateIDVProviderClaims(identityVerificationProvider, idVProviderId, tenantId, connection);
            connection.commit();
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_ADD_IDV_PROVIDER, identityVerificationProvider.getDisplayName(), e);
        }
    }

    public List<IdentityVerificationProvider> getIdVProviders(int tenantId) throws IdVProviderMgtException {

        List<IdentityVerificationProvider> identityVerificationProviders = new ArrayList<>();

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement getIdVProvidersStmt = connection
                    .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVPS_SQL)) {
                getIdVProvidersStmt.setInt(1, tenantId);

                try (ResultSet idVProviderResultSet = getIdVProvidersStmt.executeQuery()) {
                    while (idVProviderResultSet.next()) {
                        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
                        identityVerificationProvider.
                                setIdVProviderId(idVProviderResultSet.getString("UUID"));
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

    /**
     * Delete Identity Verification Provider by ID.
     *
     * @param idVProviderId Identity Verification Provider ID.
     * @param tenantId      Tenant ID.
     * @throws IdVProviderMgtException Error when getting Identity Verification Provider.
     */
    public void deleteIdVProvider(String idVProviderId, int tenantId) throws IdVProviderMgtException {

        try (Connection connection = IdentityDatabaseUtil.getDBConnection(false)) {
            try (PreparedStatement deleteIdVProviderStmt =
                         connection.prepareStatement(IdVProviderMgtConstants.SQLQueries.DELETE_IDV_SQL)) {
                deleteIdVProviderStmt.setString(1, idVProviderId);
                deleteIdVProviderStmt.setInt(2, tenantId);
                deleteIdVProviderStmt.executeUpdate();
                IdentityDatabaseUtil.commitTransaction(connection);
            } catch (SQLException e1) {
                IdentityDatabaseUtil.rollbackTransaction(connection);
                throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                        ERROR_CODE_DELETING_IDV_PROVIDER, idVProviderId, e1);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_DATABASE_CONNECTION, e);
        }
    }

    private void addIDVProviderConfigs(IdentityVerificationProvider identityVerificationProvider,
                                              int tenantId, Connection connection) throws SQLException {

        if (identityVerificationProvider.getIdVConfigProperties() == null) {
            identityVerificationProvider.setIdVConfigProperties(new IdVConfigProperty[0]);
        }
        try (PreparedStatement addIDVProviderConfigsStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.ADD_IDVP_CONFIG_SQL)) {
            for (IdVConfigProperty idVConfigProperty : identityVerificationProvider.getIdVConfigProperties()) {
                addIDVProviderConfigsStmt.setString(1, identityVerificationProvider.getIdVProviderId());
                addIDVProviderConfigsStmt.setInt(2, tenantId);
                addIDVProviderConfigsStmt.setString(3, idVConfigProperty.getName());
                addIDVProviderConfigsStmt.setString(4, idVConfigProperty.getValue());
                addIDVProviderConfigsStmt.executeUpdate();
            }
        }
    }

    private void addIDVProviderClaims(IdentityVerificationProvider identityVerificationProvider, int tenantId,
                                             Connection connection) throws SQLException {

        try (PreparedStatement addIDVProviderClaimsStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.ADD_IDVP_CLAIM_SQL)) {
            for (Map.Entry<String, String> claimMapping : identityVerificationProvider.getClaimMappings().entrySet()) {
                addIDVProviderClaimsStmt.setString(1, identityVerificationProvider.getIdVProviderId());
                addIDVProviderClaimsStmt.setInt(2, tenantId);
                addIDVProviderClaimsStmt.setString(3, claimMapping.getKey());
                addIDVProviderClaimsStmt.setString(4, claimMapping.getValue());
                addIDVProviderClaimsStmt.executeUpdate();
            }
        }
    }

    private void updateIDVProviderConfigs(IdentityVerificationProvider identityVerificationProvider,
                                          String idVProviderId, int tenantId, Connection connection)
            throws SQLException {

        deleteIDVProviderConfigs (idVProviderId, tenantId, connection);
        if (ArrayUtils.isEmpty(identityVerificationProvider.getIdVConfigProperties())) {
            return;
        }
        addIDVProviderConfigs(identityVerificationProvider, tenantId, connection);
    }

    private void deleteIDVProviderConfigs (String idVProviderId, int tenantId, Connection connection)
            throws SQLException {

        try (PreparedStatement deleteIDVProviderConfigsStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.DELETE_IDVP_CONFIG_SQL)) {
            deleteIDVProviderConfigsStmt.setString(1, idVProviderId);
            deleteIDVProviderConfigsStmt.setInt(2, tenantId);
            deleteIDVProviderConfigsStmt.executeUpdate();
        }
    }

    private void deleteIDVProviderClaims (String idVProviderId, int tenantId, Connection connection)
            throws SQLException {

        try (PreparedStatement deleteIDVProviderClaimsStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.DELETE_IDVP_CLAIM_SQL)) {
            deleteIDVProviderClaimsStmt.setString(1, idVProviderId);
            deleteIDVProviderClaimsStmt.setInt(2, tenantId);
            deleteIDVProviderClaimsStmt.executeUpdate();
        }
    }

    private void updateIDVProviderClaims(IdentityVerificationProvider identityVerificationProvider,
                                         String idVProviderId, int tenantId, Connection connection)
            throws SQLException {

        deleteIDVProviderClaims (idVProviderId, tenantId, connection);
        if (MapUtils.isEmpty(identityVerificationProvider.getClaimMappings())) {
            return;
        }
        addIDVProviderClaims(identityVerificationProvider, tenantId, connection);
    }

    private void getIdVProviderWithConfigs(IdentityVerificationProvider identityVerificationProvider,
                                                  String idvProviderId, int tenantId, Connection connection)
            throws IdvProviderMgtServerException {

        IdVConfigProperty[] idVConfigProperties = new IdVConfigProperty[0];
        List<IdVConfigProperty> idVConfigPropertyList = new ArrayList<>();
        try (PreparedStatement getIdVProvidersStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVP_CONFIG_SQL)) {
            getIdVProvidersStmt.setString(1, idvProviderId);
            getIdVProvidersStmt.setInt(2, tenantId);

            try (ResultSet idVProviderResultSet = getIdVProvidersStmt.executeQuery()) {
                if (idVProviderResultSet.next()) {
                    IdVConfigProperty idVConfigProperty = new IdVConfigProperty();
                    idVConfigProperty.setName(idVProviderResultSet.getString("PROPERTY_NAME"));
                    idVConfigProperty.setValue(idVProviderResultSet.getString("PROPERTY_VALUE"));
                    idVConfigPropertyList.add(idVConfigProperty);
                }
                identityVerificationProvider.setIdVConfigProperties(idVConfigPropertyList.toArray(idVConfigProperties));
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVING_IDV_PROVIDER_CONFIGS, e);
        }
    }

    private void getIdVProvidersWithClaims(IdentityVerificationProvider identityVerificationProvider,
                                           String idvProviderId, int tenantId, Connection connection)
            throws IdvProviderMgtServerException {

        Map<String, String> idVClaimMap = new HashMap<>();
        try (PreparedStatement getIdVProvidersStmt = connection
                .prepareStatement(IdVProviderMgtConstants.SQLQueries.GET_IDVP_CLAIMS_SQL)) {
            getIdVProvidersStmt.setString(1, idvProviderId);
            getIdVProvidersStmt.setInt(2, tenantId);

            try (ResultSet idVProviderResultSet = getIdVProvidersStmt.executeQuery()) {
                while (idVProviderResultSet.next()) {
                    idVClaimMap.put(idVProviderResultSet.getString("CLAIM"),
                            idVProviderResultSet.getString("LOCAL_CLAIM"));
                }
                identityVerificationProvider.setClaimMappings(idVClaimMap);
            }
        } catch (SQLException e) {
            throw IdVProviderMgtExceptionManagement.handleServerException(IdVProviderMgtConstants.ErrorMessage.
                    ERROR_CODE_RETRIEVING_IDV_PROVIDER_CLAIMS, e);
        }
    }
}
