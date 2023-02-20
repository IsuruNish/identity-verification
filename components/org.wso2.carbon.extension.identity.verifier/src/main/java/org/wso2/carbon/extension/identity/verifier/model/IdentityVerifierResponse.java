package org.wso2.carbon.extension.identity.verifier.model;

import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

public class IdentityVerifierResponse {

    String userId;
    String identityVerifierName;

    IdVClaim[] idVClaims;

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getIdentityVerifierName() {

        return identityVerifierName;
    }

    public void setIdentityVerifierName(String identityVerifierName) {

        this.identityVerifierName = identityVerifierName;
    }

    public IdVClaim[] getIdVClaims() {

        return idVClaims;
    }

    public void setIdVClaims(IdVClaim[] idVClaims) {

        this.idVClaims = idVClaims;
    }
}
