package org.wso2.carbon.extension.identity.verification.provider.mgt.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
        name = "org.wso2.carbon.extension.identity.verification.provider.mgt",
        immediate = true
)
public class IdVProviderMgtDataHolder {

    private static Log log = LogFactory.getLog(IdVProviderMgtDataHolder.class);

    @Activate
    protected void activate(ComponentContext ctxt) {

        UserTenantAssociationManager userTenantAssociationManager = new DefaultUserTenantAssociationManager();
        ctxt.getBundleContext().registerService(UserTenantAssociationManager.class.getName(),
                userTenantAssociationManager, null);
        log.info("Asgardeo UserTenantAssociationManager bundle activated successfully.");
    }
}
