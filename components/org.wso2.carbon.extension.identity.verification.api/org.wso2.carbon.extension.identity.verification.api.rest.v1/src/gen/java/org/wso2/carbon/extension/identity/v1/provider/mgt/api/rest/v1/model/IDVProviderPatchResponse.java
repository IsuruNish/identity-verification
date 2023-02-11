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

package org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import javax.validation.Valid;

public class IDVProviderPatchResponse  {
  
    private String id;
    private String displayName;
    private String description;
    private List<Verificationclaim> claims = null;

    private List<Property> properties = null;


    /**
    **/
    public IDVProviderPatchResponse id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(example = "123e4567-e89b-12d3-a456-556642440000", value = "")
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
    public IDVProviderPatchResponse displayName(String displayName) {

        this.displayName = displayName;
        return this;
    }
    
    @ApiModelProperty(example = "EVIDENTID", value = "")
    @JsonProperty("displayName")
    @Valid
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
    **/
    public IDVProviderPatchResponse description(String description) {

        this.description = description;
        return this;
    }
    
    @ApiModelProperty(example = "identity verification provider", value = "")
    @JsonProperty("description")
    @Valid
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    **/
    public IDVProviderPatchResponse claims(List<Verificationclaim> claims) {

        this.claims = claims;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("claims")
    @Valid
    public List<Verificationclaim> getClaims() {
        return claims;
    }
    public void setClaims(List<Verificationclaim> claims) {
        this.claims = claims;
    }

    public IDVProviderPatchResponse addClaimsItem(Verificationclaim claimsItem) {
        if (this.claims == null) {
            this.claims = new ArrayList<>();
        }
        this.claims.add(claimsItem);
        return this;
    }

        /**
    **/
    public IDVProviderPatchResponse properties(List<Property> properties) {

        this.properties = properties;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("properties")
    @Valid
    public List<Property> getProperties() {
        return properties;
    }
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public IDVProviderPatchResponse addPropertiesItem(Property propertiesItem) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(propertiesItem);
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
        IDVProviderPatchResponse idVProviderPatchResponse = (IDVProviderPatchResponse) o;
        return Objects.equals(this.id, idVProviderPatchResponse.id) &&
            Objects.equals(this.displayName, idVProviderPatchResponse.displayName) &&
            Objects.equals(this.description, idVProviderPatchResponse.description) &&
            Objects.equals(this.claims, idVProviderPatchResponse.claims) &&
            Objects.equals(this.properties, idVProviderPatchResponse.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName, description, claims, properties);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class IDVProviderPatchResponse {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    claims: ").append(toIndentedString(claims)).append("\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

