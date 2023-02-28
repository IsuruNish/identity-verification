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

package org.wso2.carbon.extension.identity.verifier;

import org.wso2.carbon.extension.identity.verifier.internal.IdentityVerifierDataHolder;
import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierData;

/**
 * This interface of IdentityVerifierFactory to retrieve the required identity verifier.
 */
public class IdentityVerificationServiceImpl implements IdentityVerificationService {

    @Override
    public IdentityVerifierData verifyIdentity(IdentityVerifierData identityVerifierData, int tenantId)
            throws IdentityVerificationException {

        String identityVerifierName = identityVerifierData.getIdentityVerifierName();
        IdentityVerifierFactory identityVerifierFactory =
                IdentityVerifierDataHolder.getIdentityVerifierFactory(identityVerifierName);
        if (identityVerifierFactory == null) {
            throw new IdentityVerificationException("Identity verifier factory is not available for the identity " +
                    "verifier name: " + identityVerifierName);
        }
        return identityVerifierFactory.getIdentityVerifier(identityVerifierName).
                verifyIdentity(identityVerifierData, tenantId);
    }

}
