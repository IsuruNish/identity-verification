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

package org.wso2.carbon.extension.identity.verification.api.rest.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class VerificationPostResponse  {
  
    private String identityVerificationProvider;
    private List<VerificationClaimResponse> claims = null;


    /**
    **/
    public VerificationPostResponse identityVerificationProvider(String identityVerificationProvider) {

        this.identityVerificationProvider = identityVerificationProvider;
        return this;
    }
    
    @ApiModelProperty(example = "evidentID", value = "")
    @JsonProperty("identityVerificationProvider")
    @Valid
    public String getIdentityVerificationProvider() {
        return identityVerificationProvider;
    }
    public void setIdentityVerificationProvider(String identityVerificationProvider) {
        this.identityVerificationProvider = identityVerificationProvider;
    }

    /**
    **/
    public VerificationPostResponse claims(List<VerificationClaimResponse> claims) {

        this.claims = claims;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("claims")
    @Valid
    public List<VerificationClaimResponse> getClaims() {
        return claims;
    }
    public void setClaims(List<VerificationClaimResponse> claims) {
        this.claims = claims;
    }

    public VerificationPostResponse addClaimsItem(VerificationClaimResponse claimsItem) {
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
        VerificationPostResponse verificationPostResponse = (VerificationPostResponse) o;
        return Objects.equals(this.identityVerificationProvider, verificationPostResponse.identityVerificationProvider) &&
            Objects.equals(this.claims, verificationPostResponse.claims);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identityVerificationProvider, claims);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class VerificationPostResponse {\n");
        
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

