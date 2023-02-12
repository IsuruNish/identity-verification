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

public class Verifiedattributes  {
  
    private String claim;
    private String value;
    private String status;

    /**
    **/
    public Verifiedattributes claim(String claim) {

        this.claim = claim;
        return this;
    }
    
    @ApiModelProperty(example = "http://wso2.org/claims/country", value = "")
    @JsonProperty("claim")
    @Valid
    public String getClaim() {
        return claim;
    }
    public void setClaim(String claim) {
        this.claim = claim;
    }

    /**
    **/
    public Verifiedattributes value(String value) {

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
    public Verifiedattributes status(String status) {

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



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Verifiedattributes verifiedattributes = (Verifiedattributes) o;
        return Objects.equals(this.claim, verifiedattributes.claim) &&
            Objects.equals(this.value, verifiedattributes.value) &&
            Objects.equals(this.status, verifiedattributes.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claim, value, status);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class Verifiedattributes {\n");
        
        sb.append("    claim: ").append(toIndentedString(claim)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

