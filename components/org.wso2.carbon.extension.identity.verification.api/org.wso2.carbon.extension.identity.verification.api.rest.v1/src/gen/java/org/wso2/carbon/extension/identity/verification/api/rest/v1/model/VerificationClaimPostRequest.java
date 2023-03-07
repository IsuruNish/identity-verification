/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.carbon.extension.identity.verification.api.rest.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class VerificationClaimPostRequest  {
  
    private String userId;
    private String idvpId;
    private String uri;
    private String status;
    private Map<String, Object> claimMetadata = null;


    /**
    **/
    public VerificationClaimPostRequest userId(String userId) {

        this.userId = userId;
        return this;
    }
    
    @ApiModelProperty(example = "123e4567-e89b-12d3-a456-556642440000", required = true, value = "")
    @JsonProperty("userId")
    @Valid
    @NotNull(message = "Property userId cannot be null.")

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
    **/
    public VerificationClaimPostRequest idvpId(String idvpId) {

        this.idvpId = idvpId;
        return this;
    }
    
    @ApiModelProperty(example = "2159375-r567-8524-a456-5566424414527", required = true, value = "")
    @JsonProperty("idvpId")
    @Valid
    @NotNull(message = "Property idvpId cannot be null.")

    public String getIdvpId() {
        return idvpId;
    }
    public void setIdvpId(String idvpId) {
        this.idvpId = idvpId;
    }

    /**
    **/
    public VerificationClaimPostRequest uri(String uri) {

        this.uri = uri;
        return this;
    }
    
    @ApiModelProperty(example = "http://wso2.org/claims/country", required = true, value = "")
    @JsonProperty("uri")
    @Valid
    @NotNull(message = "Property uri cannot be null.")

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
    **/
    public VerificationClaimPostRequest status(String status) {

        this.status = status;
        return this;
    }
    
    @ApiModelProperty(example = "Verified", required = true, value = "")
    @JsonProperty("status")
    @Valid
    @NotNull(message = "Property status cannot be null.")

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    /**
    **/
    public VerificationClaimPostRequest claimMetadata(Map<String, Object> claimMetadata) {

        this.claimMetadata = claimMetadata;
        return this;
    }
    
    @ApiModelProperty(example = "{\"source\": \"evidentID\", \"verifiedAt\": \"2020-10-10T12:00:00.000Z\", \"trackingId\": \"123e4567-e89b-12d3-a456-556642440000\" }", value = "")
    @JsonProperty("claimMetadata")
    @Valid
    public Map<String, Object> getClaimMetadata() {
        return claimMetadata;
    }
    public void setClaimMetadata(Map<String, Object> claimMetadata) {
        this.claimMetadata = claimMetadata;
    }


    public VerificationClaimPostRequest putClaimMetadataItem(String key, Object claimMetadataItem) {
        if (this.claimMetadata == null) {
            this.claimMetadata = new HashMap<>();
        }
        this.claimMetadata.put(key, claimMetadataItem);
        return this;
    }

    

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationClaimPostRequest verificationClaimPostRequest = (VerificationClaimPostRequest) o;
        return Objects.equals(this.userId, verificationClaimPostRequest.userId) &&
            Objects.equals(this.idvpId, verificationClaimPostRequest.idvpId) &&
            Objects.equals(this.uri, verificationClaimPostRequest.uri) &&
            Objects.equals(this.status, verificationClaimPostRequest.status) &&
            Objects.equals(this.claimMetadata, verificationClaimPostRequest.claimMetadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, idvpId, uri, status, claimMetadata);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationClaimPostRequest {\n");
        
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    idvpId: ").append(toIndentedString(idvpId)).append("\n");
        sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    claimMetadata: ").append(toIndentedString(claimMetadata)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n");
    }
}

