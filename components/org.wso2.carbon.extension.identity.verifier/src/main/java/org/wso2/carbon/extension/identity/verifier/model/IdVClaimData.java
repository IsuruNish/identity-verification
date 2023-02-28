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

package org.wso2.carbon.extension.identity.verifier.model;

import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

/**
 * This class contains the properties of the identity verifier.
 */
public class IdVClaimData {

    private String claimUri;
    private String claimValue;

    private IdVClaim idVClaim;

    public String getClaimUri() {

        return claimUri;
    }

    public void setClaimUri(String claimUri) {

        this.claimUri = claimUri;
    }

    public String getClaimValue() {

        return claimValue;
    }

    public void setClaimValue(String claimValue) {

        this.claimValue = claimValue;
    }

    public IdVClaim getIdVClaim() {

        return idVClaim;
    }

    public void setIdVClaim(IdVClaim idVClaim) {

        this.idVClaim = idVClaim;
    }
}
