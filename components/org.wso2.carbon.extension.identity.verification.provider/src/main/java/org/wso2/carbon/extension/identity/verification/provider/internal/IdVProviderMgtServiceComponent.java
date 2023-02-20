package org.wso2.carbon.extension.identity.verification.provider.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.extension.identity.verification.provider.IdVProviderManager;
import org.wso2.carbon.extension.identity.verification.provider.IdentityVerificationProviderManager;
import org.wso2.carbon.user.core.service.RealmService;

@Component(
        name = "org.wso2.carbon.extension.identity.verification.provider.mgt",
        immediate = true
)
public class IdVProviderMgtServiceComponent {

    private static final Log log = LogFactory.getLog(IdVProviderMgtDataHolder.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        IdVProviderManager idVProviderManager = new IdentityVerificationProviderManager();
        ctxt.getBundleContext().registerService(IdentityVerificationProviderManager.class.getName(),
                idVProviderManager, null);
        log.info("IdentityVerificationProviderManager bundle activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("IdentityVerificationProviderManager bundle is deactivated.");
        }
    }

    @Reference(
            name = "RealmService",
            service = org.wso2.carbon.user.core.service.RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {

        IdVProviderMgtDataHolder.setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        IdVProviderMgtDataHolder.setRealmService(null);
    }
}
