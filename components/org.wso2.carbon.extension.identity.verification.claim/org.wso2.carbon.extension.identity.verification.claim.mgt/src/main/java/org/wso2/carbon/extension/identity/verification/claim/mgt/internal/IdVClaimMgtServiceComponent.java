package org.wso2.carbon.extension.identity.verification.claim.mgt.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimManager;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdentityVerificationClaimManager;
import org.wso2.carbon.user.core.service.RealmService;

public class IdVClaimMgtServiceComponent {

    private static final Log log = LogFactory.getLog(IdVClaimMgtServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        IdVClaimManager idVProviderManager = new IdentityVerificationClaimManager();
        ctxt.getBundleContext().registerService(IdentityVerificationClaimManager.class.getName(),
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

        IdVClaimMgtDataHolder.setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        IdVClaimMgtDataHolder.setRealmService(null);
    }
}
