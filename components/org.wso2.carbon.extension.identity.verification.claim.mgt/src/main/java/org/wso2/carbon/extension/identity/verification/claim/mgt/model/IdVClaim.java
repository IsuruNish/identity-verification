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
package org.wso2.carbon.extension.identity.verification.claim.mgt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

/**
 * IdVClaim model class.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdVClaim {

    private String id;
    private String _id;
    private String userId;
    private boolean isVerified;
    private boolean status;
    private String uuid;    //claimID
    private String claimUri;
    private String claimValue;
    private Document claimMetadata;
    private JSONObject claims;
    private String idvProviderId;
    private String idvId;
    private int tenantId;
    private JSONObject metadata;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String get_id() {

        return _id;
    }

    public void set_id(String _id) {

        this._id = _id;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public boolean isVerified() {

        return isVerified;
    }

    public void setVerified(boolean verified) {

        isVerified = verified;
    }

    public boolean isStatus() {

        return status;
    }

    public void setStatus(boolean status) {

        this.status = status;
    }

    public String getUuid() {

        return uuid;
    }

    public void setUuid(String uuid) {

        this.uuid = uuid;
    }

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

    public Document getClaimMetadata() {

        return claimMetadata;
    }

    public void setClaimMetadata(Document claimMetadata) {

        this.claimMetadata = claimMetadata;
    }

    public JSONObject getClaims() {

        return claims;
    }

    public void setClaims(JSONObject claims) {

        this.claims = claims;
    }

    public String getIdvProviderId() {

        return idvProviderId;
    }

    public void setIdvProviderId(String idvProviderId) {

        this.idvProviderId = idvProviderId;
    }

    public String getIdvId() {

        return idvId;
    }

    public void setIdvId(String idvId) {

        this.idvId = idvId;
    }

    public int getTenantId() {

        return tenantId;
    }

    public void setTenantId(int tenantId) {

        this.tenantId = tenantId;
    }

    public JSONObject getMetadata() {

        return metadata;
    }

    public void setMetadata(JSONObject metadata) {

        this.metadata = metadata;
    }
}
