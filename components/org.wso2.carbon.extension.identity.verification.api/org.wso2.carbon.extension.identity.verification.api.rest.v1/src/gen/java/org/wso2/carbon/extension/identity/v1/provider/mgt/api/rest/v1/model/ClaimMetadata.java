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

import java.util.Objects;
import javax.validation.Valid;

public class ClaimMetadata  {
  
    private String verifiedConnectorId;
    private String trackingID;
    private String expiareDate;

    /**
    **/
    public ClaimMetadata verifiedConnectorId(String verifiedConnectorId) {

        this.verifiedConnectorId = verifiedConnectorId;
        return this;
    }
    
    @ApiModelProperty(example = "123471267", value = "")
    @JsonProperty("verifiedConnectorId")
    @Valid
    public String getVerifiedConnectorId() {
        return verifiedConnectorId;
    }
    public void setVerifiedConnectorId(String verifiedConnectorId) {
        this.verifiedConnectorId = verifiedConnectorId;
    }

    /**
    **/
    public ClaimMetadata trackingID(String trackingID) {

        this.trackingID = trackingID;
        return this;
    }
    
    @ApiModelProperty(example = "aHR0cDovL3dzbzIub3JnL2NsYWltcy91c2V", value = "")
    @JsonProperty("TrackingID")
    @Valid
    public String getTrackingID() {
        return trackingID;
    }
    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    /**
    **/
    public ClaimMetadata expiareDate(String expiareDate) {

        this.expiareDate = expiareDate;
        return this;
    }
    
    @ApiModelProperty(example = "02/08/2026", value = "")
    @JsonProperty("ExpiareDate")
    @Valid
    public String getExpiareDate() {
        return expiareDate;
    }
    public void setExpiareDate(String expiareDate) {
        this.expiareDate = expiareDate;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClaimMetadata claimMetadata = (ClaimMetadata) o;
        return Objects.equals(this.verifiedConnectorId, claimMetadata.verifiedConnectorId) &&
            Objects.equals(this.trackingID, claimMetadata.trackingID) &&
            Objects.equals(this.expiareDate, claimMetadata.expiareDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(verifiedConnectorId, trackingID, expiareDate);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class ClaimMetadata {\n");
        
        sb.append("    verifiedConnectorId: ").append(toIndentedString(verifiedConnectorId)).append("\n");
        sb.append("    trackingID: ").append(toIndentedString(trackingID)).append("\n");
        sb.append("    expiareDate: ").append(toIndentedString(expiareDate)).append("\n");
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

