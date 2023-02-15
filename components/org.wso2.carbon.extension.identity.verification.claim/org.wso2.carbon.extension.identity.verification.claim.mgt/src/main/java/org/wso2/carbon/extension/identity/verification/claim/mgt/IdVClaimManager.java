package org.wso2.carbon.extension.identity.verification.claim.mgt;

import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

public interface IdVClaimManager {

    IdVClaim getIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException;

    IdVClaim[] getIDVClaims(String userId) throws IdVClaimMgtException;

    IdVClaim addIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException;

    IdVClaim updateIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException;

    void deleteIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException;
}
