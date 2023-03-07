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
package org.wso2.carbon.extension.identity.verifier.onfido;

import org.wso2.carbon.extension.identity.verifier.IdentityVerifier;
import org.wso2.carbon.extension.identity.verifier.IdentityVerifierFactory;
import org.wso2.carbon.extension.identity.verifier.onfido.OnfidoIdentityVerifier;

/**
 * This class contains the implementation for the OnfidoIdentityVerifierProvider.
 */
public class OnfidoIdentityVerifierFactory implements IdentityVerifierFactory {

    @Override
    public IdentityVerifier getIdentityVerifier(String identityVerifierName) {

        if (identityVerifierName.equals("ONFIDO")) {
            return new OnfidoIdentityVerifier();
        } else {
            throw new IllegalArgumentException("Identity verifier not found for the name: " + identityVerifierName);
        }
    }

    @Override
    public String getIdentityVerifierName() {

        return "ONFIDO";
    }
}
