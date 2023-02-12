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

public class Attributes  {
  
    private String httpColonWso2OrgClaimsUsername;
    private String httpColonWso2OrgClaimsAddress;

    /**
    **/
    public Attributes httpColonWso2OrgClaimsUsername(String httpColonWso2OrgClaimsUsername) {

        this.httpColonWso2OrgClaimsUsername = httpColonWso2OrgClaimsUsername;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("http://wso2.org/claims/username")
    @Valid
    public String getHttpColonWso2OrgClaimsUsername() {
        return httpColonWso2OrgClaimsUsername;
    }
    public void setHttpColonWso2OrgClaimsUsername(String httpColonWso2OrgClaimsUsername) {
        this.httpColonWso2OrgClaimsUsername = httpColonWso2OrgClaimsUsername;
    }

    /**
    **/
    public Attributes httpColonWso2OrgClaimsAddress(String httpColonWso2OrgClaimsAddress) {

        this.httpColonWso2OrgClaimsAddress = httpColonWso2OrgClaimsAddress;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("http://wso2.org/claims/address")
    @Valid
    public String getHttpColonWso2OrgClaimsAddress() {
        return httpColonWso2OrgClaimsAddress;
    }
    public void setHttpColonWso2OrgClaimsAddress(String httpColonWso2OrgClaimsAddress) {
        this.httpColonWso2OrgClaimsAddress = httpColonWso2OrgClaimsAddress;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) o;
        return Objects.equals(this.httpColonWso2OrgClaimsUsername, attributes.httpColonWso2OrgClaimsUsername) &&
            Objects.equals(this.httpColonWso2OrgClaimsAddress, attributes.httpColonWso2OrgClaimsAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpColonWso2OrgClaimsUsername, httpColonWso2OrgClaimsAddress);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class Attributes {\n");
        
        sb.append("    httpColonWso2OrgClaimsUsername: ").append(toIndentedString(httpColonWso2OrgClaimsUsername)).append("\n");
        sb.append("    httpColonWso2OrgClaimsAddress: ").append(toIndentedString(httpColonWso2OrgClaimsAddress)).append("\n");
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

