package org.wso2.carbon.extension.identity.verification.claim.mgt;

import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

public class IdentityVerificationClaimManager implements IdVClaimManager {

    @Override
    public IdVClaim getIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException {

        return null;
    }

    @Override
    public IdVClaim addIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException {

        return null;
    }

    @Override
    public IdVClaim updateIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException {

        return null;
    }

    @Override
    public void deleteIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException {

    }

    @Override
    public IdVClaim[] getIDVClaims(String userId) throws IdVClaimMgtException {

        return null;
    }
}
