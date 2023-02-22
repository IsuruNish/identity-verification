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

import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

/**
 * This interface contains the methods to manage the IdVClaim.
 */
public interface IdVClaimManager {

    IdVClaim getIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException;

    IdVClaim[] getIDVClaims(String userId) throws IdVClaimMgtException;

    void addIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException;

    void updateIDVClaim(String userId, IdVClaim idvClaim) throws IdVClaimMgtException;

    void deleteIDVClaim(String userId, String idvClaimId) throws IdVClaimMgtException;
}
