package org.wso2.carbon.extension.identity.verifier.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.extension.identity.verifier.IdentityVerifierFactory;

import java.util.Collection;

public class IdentityVerifierServiceComponent {

    private static final Log log = LogFactory.getLog(IdentityVerifierServiceComponent.class);
    IdentityVerifierDataHolder identityVerifierDataHolder = IdentityVerifierDataHolder.getInstance();

    @Reference(
            name = "hash.provider.component",
            service = org.wso2.carbon.extension.identity.verifier.IdentityVerifierFactory.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unsetHashProviderFactory"
    )
    protected void setHashProviderFactory(IdentityVerifierFactory identityVerifierFactory) {

        identityVerifierDataHolder.setIdentityVerifierFactory(identityVerifierFactory);
    }

    protected void unsetIdentityVerifierFactory(IdentityVerifierFactory identityVerifierFactory) {

        identityVerifierDataHolder.unbindIdentityVerifierFactory(identityVerifierFactory);
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("IdentityVerifierManager bundle is deactivated ");
        }
    }



}
