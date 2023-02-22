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
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class Attributes  {
  
    private String claimUri;
    private String claimValue;

    /**
    **/
    public Attributes claimUri(String claimUri) {

        this.claimUri = claimUri;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("claim-uri")
    @Valid
    public String getClaimUri() {
        return claimUri;
    }
    public void setClaimUri(String claimUri) {
        this.claimUri = claimUri;
    }

    /**
    **/
    public Attributes claimValue(String claimValue) {

        this.claimValue = claimValue;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("claim-value")
    @Valid
    public String getClaimValue() {
        return claimValue;
    }
    public void setClaimValue(String claimValue) {
        this.claimValue = claimValue;
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
        return Objects.equals(this.claimUri, attributes.claimUri) &&
            Objects.equals(this.claimValue, attributes.claimValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claimUri, claimValue);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class Attributes {\n");
        
        sb.append("    claimUri: ").append(toIndentedString(claimUri)).append("\n");
        sb.append("    claimValue: ").append(toIndentedString(claimValue)).append("\n");
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

