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
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.validation.Valid;

public class VerificationClaimResponse  {
  
    private String id;
    private String uri;
    private String value;
    private String status;
    private ClaimMetadata metadata;

    /**
    **/
    public VerificationClaimResponse id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(example = "aHR0cDovL3dzbzIub3JnL2NsYWltcy91c2VybmFtZQ", value = "")
    @JsonProperty("id")
    @Valid
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    **/
    public VerificationClaimResponse uri(String uri) {

        this.uri = uri;
        return this;
    }
    
    @ApiModelProperty(example = "http://wso2.org/claims/country", value = "")
    @JsonProperty("uri")
    @Valid
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
    **/
    public VerificationClaimResponse value(String value) {

        this.value = value;
        return this;
    }
    
    @ApiModelProperty(example = "Sri Lanka", value = "")
    @JsonProperty("value")
    @Valid
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    /**
    **/
    public VerificationClaimResponse status(String status) {

        this.status = status;
        return this;
    }
    
    @ApiModelProperty(example = "Verified", value = "")
    @JsonProperty("status")
    @Valid
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    /**
    **/
    public VerificationClaimResponse metadata(ClaimMetadata metadata) {

        this.metadata = metadata;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("metadata")
    @Valid
    public ClaimMetadata getMetadata() {
        return metadata;
    }
    public void setMetadata(ClaimMetadata metadata) {
        this.metadata = metadata;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VerificationClaimResponse verificationClaimResponse = (VerificationClaimResponse) o;
        return Objects.equals(this.id, verificationClaimResponse.id) &&
            Objects.equals(this.uri, verificationClaimResponse.uri) &&
            Objects.equals(this.value, verificationClaimResponse.value) &&
            Objects.equals(this.status, verificationClaimResponse.status) &&
            Objects.equals(this.metadata, verificationClaimResponse.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uri, value, status, metadata);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationClaimResponse {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

