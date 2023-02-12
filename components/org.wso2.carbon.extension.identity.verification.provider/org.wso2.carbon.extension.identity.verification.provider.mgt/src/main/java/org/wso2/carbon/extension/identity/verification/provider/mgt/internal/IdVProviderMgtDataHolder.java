package org.wso2.carbon.extension.identity.verification.provider.mgt.internal;

import org.wso2.carbon.user.core.service.RealmService;

public class IdVProviderMgtDataHolder {

    private static RealmService realmService;

    /**
     * Get the RealmService.
     *
     * @return RealmService.
     */
    public static RealmService getRealmService() {

        if (realmService == null) {
            throw new RuntimeException("RealmService was not set during the " +
                    "IdVProviderMgtServiceComponent startup");
        }
        return realmService;
    }

    /**
     * Set the RealmService.
     *
     * @param realmService RealmService.
     */
    public static void setRealmService(RealmService realmService) {

        IdVProviderMgtDataHolder.realmService = realmService;
    }
}
