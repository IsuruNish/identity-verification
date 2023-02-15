package org.wso2.carbon.extension.identity.verification.claim.mgt.model;

import org.json.JSONObject;

public class IdVClaim {

    private String idVClaimId;
    private String idVStatus;
    private JSONObject idVClaimMetadata;

    public String getIdVClaimId() {

        return idVClaimId;
    }

    public void setIdVClaimId(String idVClaimId) {

        this.idVClaimId = idVClaimId;
    }

    public String getIdVStatus() {

        return idVStatus;
    }

    public void setIdVStatus(String idVStatus) {

        this.idVStatus = idVStatus;
    }

    public JSONObject getIdVClaimMetadata() {

        return idVClaimMetadata;
    }

    public void setIdVClaimMetadata(JSONObject idVClaimMetadata) {

        this.idVClaimMetadata = idVClaimMetadata;
    }
}
