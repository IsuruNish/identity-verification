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


}
