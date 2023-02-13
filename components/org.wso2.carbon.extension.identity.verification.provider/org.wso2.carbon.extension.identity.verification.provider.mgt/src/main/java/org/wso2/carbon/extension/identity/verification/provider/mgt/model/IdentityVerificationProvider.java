package org.wso2.carbon.extension.identity.verification.provider.mgt.model;

public class IdentityVerificationProvider {

    private String idVProviderId;
    private String idVProviderName;
    private boolean enable;
    private String displayName;
    private String idVProviderDescription;
    private IdVConfigProperty[] idVConfigProperties = new IdVConfigProperty[0];

    public String getIdVProviderName() {

        return idVProviderName;
    }

    public void setIdVProviderName(String idVProviderName) {

        this.idVProviderName = idVProviderName;
    }

    public IdVConfigProperty[] getIdVConfigProperties() {

        return idVConfigProperties;
    }

    public void setIdVConfigProperties(IdVConfigProperty[] idVConfigProperties) {

        this.idVConfigProperties = idVConfigProperties;
    }

    public void setIdVProviderId(String idVProviderId) {

        this.idVProviderId = idVProviderId;
    }

    public String getIdVProviderId() {

        return idVProviderId;
    }

    public boolean isEnable() {

        return enable;
    }

    public void setEnable(boolean enable) {

        this.enable = enable;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public String getIdVProviderDescription() {

        return idVProviderDescription;
    }

    public void setIdVProviderDescription(String idVProviderDescription) {

        this.idVProviderDescription = idVProviderDescription;
    }
}
