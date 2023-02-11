package org.wso2.carbon.extension.identity.verification.provider.mgt.util;

public class IdVProviderMgtConstants {

    public static class SQLQueries {

        public static final String DELETE_IDV_SQL = "DELETE FROM IDV WHERE (TENANT_ID=? AND IDV_UUID=?)";
    }

}
