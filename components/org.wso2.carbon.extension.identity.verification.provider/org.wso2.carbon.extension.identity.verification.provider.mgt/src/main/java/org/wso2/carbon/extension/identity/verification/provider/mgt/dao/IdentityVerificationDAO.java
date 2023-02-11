package org.wso2.carbon.extension.identity.verification.provider.mgt.dao;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdvProviderMgtServerException;
import org.wso2.carbon.extension.identity.verification.provider.mgt.internal.IdVProviderMgtDataHolder;
import org.wso2.carbon.user.api.RealmConfiguration;
import org.wso2.carbon.user.core.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.user.core.util.DatabaseUtil;
import org.wso2.carbon.utils.multitenancy.MultitenantConstants;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class IdentityVerificationDAO {

    private void deleteIDV(String idvID, String idpName, String tenantDomain) {

    }

    private Connection createDatabaseConnection() throws IdvProviderMgtServerException {

        RealmService realmService = IdVProviderMgtDataHolder.getRealmService();
        if (realmService == null) {
            throw new AssociationManagerServerException(ErrorMessage.ERROR_CODE_ERROR_GETTING_REALM.getCode(),
                    ErrorMessage.ERROR_CODE_ERROR_GETTING_REALM.getDescription());
        }
        try {
            String asgardeoUserStoreDomainName = realmService.getTenantMgtConfiguration().
                    getTenantStoreProperty(ASGARDEO_USER_STORE_DOMAIN_NAME);
            if (StringUtils.isBlank(asgardeoUserStoreDomainName)) {
                throw new AssociationManagerServerException(
                        ErrorMessage.ERROR_CODE_UNABLE_TO_FIND_LOCAL_USER_BASE.getCode(),
                        ErrorMessage.ERROR_CODE_UNABLE_TO_FIND_LOCAL_USER_BASE.getDescription());
            }

            UserStoreManager userStoreManager =
                    (UserStoreManager) realmService.getTenantUserRealm(MultitenantConstants.SUPER_TENANT_ID)
                            .getUserStoreManager();

            UserStoreManager asgardeoUserStore =
                    userStoreManager.getSecondaryUserStoreManager(asgardeoUserStoreDomainName);
            RealmConfiguration realmConfiguration = asgardeoUserStore.getRealmConfiguration();

            DataSource dataSource = DatabaseUtil.createUserStoreDataSource(realmConfiguration);
            return dataSource.getConnection();
        } catch (org.wso2.carbon.user.api.UserStoreException | SQLException exception) {
            throw handleServerException(exception, ErrorMessage.ERROR_CODE_GETTING_DATABASE_CONNECTION);
        }
    }
}
