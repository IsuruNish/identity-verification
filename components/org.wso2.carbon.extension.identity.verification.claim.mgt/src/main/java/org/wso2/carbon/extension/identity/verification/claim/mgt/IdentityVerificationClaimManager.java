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
package org.wso2.carbon.extension.identity.verification.claim.mgt;

import org.wso2.carbon.extension.identity.verification.claim.mgt.dao.IdentityVerificationClaimDAOImpl;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

/**
 * This class provides methods to manage identity verification claims.
 */
public class IdentityVerificationClaimManager implements IdVClaimManager {

    IdentityVerificationClaimDAOImpl identityVerificationClaimDAO = new IdentityVerificationClaimDAOImpl();

    @Override
    public IdVClaim getIDVClaim(String idvClaimId, int tenantId) throws IdVClaimMgtException {

        return identityVerificationClaimDAO.getIDVClaim(idvClaimId, tenantId);
    }

    @Override
    public void addIDVClaim(String userId, IdVClaim idvClaim, int tenantId) throws IdVClaimMgtException {

        identityVerificationClaimDAO.addIdVClaim(userId, idvClaim, tenantId);
    }

    @Override
    public void updateIDVClaim(IdVClaim idvClaim, int tenantId) throws IdVClaimMgtException {

        identityVerificationClaimDAO.updateIdVClaim(idvClaim, tenantId);
    }

    @Override
    public void deleteIDVClaim(String userId, String idvClaimId, int tenantId) throws IdVClaimMgtException {

        identityVerificationClaimDAO.deleteIdVClaim(userId, idvClaimId);
    }

    @Override
    public IdVClaim[] getIDVClaims(String userId, int tenantId) throws IdVClaimMgtException {

        return identityVerificationClaimDAO.getIDVClaims(userId, tenantId);
    }
}
