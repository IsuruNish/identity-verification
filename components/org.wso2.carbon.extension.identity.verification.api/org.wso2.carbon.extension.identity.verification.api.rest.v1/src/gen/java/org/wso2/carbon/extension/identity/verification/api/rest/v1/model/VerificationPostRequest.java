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
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;

import java.util.Objects;
import javax.validation.Valid;

public class VerificationPostRequest  {
  
    private String username;
    private String identityVerificationProvider;
    private List<Attributes> claims = null;


    /**
    **/
    public VerificationPostRequest username(String username) {

        this.username = username;
        return this;
    }
    
    @ApiModelProperty(example = "username", value = "")
    @JsonProperty("username")
    @Valid
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    /**
    **/
    public VerificationPostRequest identityVerificationProvider(String identityVerificationProvider) {

        this.identityVerificationProvider = identityVerificationProvider;
        return this;
    }
    
    @ApiModelProperty(example = "evidentID", required = true, value = "")
    @JsonProperty("identityVerificationProvider")
    @Valid
    @NotNull(message = "Property identityVerificationProvider cannot be null.")

    public String getIdentityVerificationProvider() {
        return identityVerificationProvider;
    }
    public void setIdentityVerificationProvider(String identityVerificationProvider) {
        this.identityVerificationProvider = identityVerificationProvider;
    }

    /**
    **/
    public VerificationPostRequest claims(List<Attributes> claims) {

        this.claims = claims;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("claims")
    @Valid
    public List<Attributes> getClaims() {
        return claims;
    }
    public void setClaims(List<Attributes> claims) {
        this.claims = claims;
    }

    public VerificationPostRequest addClaimsItem(Attributes claimsItem) {
        if (this.claims == null) {
            this.claims = new ArrayList<>();
        }
        this.claims.add(claimsItem);
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
        VerificationPostRequest verificationPostRequest = (VerificationPostRequest) o;
        return Objects.equals(this.username, verificationPostRequest.username) &&
            Objects.equals(this.identityVerificationProvider, verificationPostRequest.identityVerificationProvider) &&
            Objects.equals(this.claims, verificationPostRequest.claims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, identityVerificationProvider, claims);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationPostRequest {\n");
        
        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    identityVerificationProvider: ").append(toIndentedString(identityVerificationProvider)).append("\n");
        sb.append("    claims: ").append(toIndentedString(claims)).append("\n");
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

