package org.wso2.carbon.extension.identity.verifier.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.extension.identity.verifier.IdentityVerifierManager;

public class IdentityVerifierMgtServiceComponent {

    private static final Log log = LogFactory.getLog(IdentityVerifierMgtServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        IdentityVerifierManager idVProviderManager = new IdentityVerifierManager();
        ctxt.getBundleContext().registerService(IdentityVerifierManager.class.getName(),
                idVProviderManager, null);
        log.info("IdentityVerifierManager bundle activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("IdentityVerifierManager bundle is deactivated ");
        }
    }



}
