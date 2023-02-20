/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.extension.identity.verification.provider.model;

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
