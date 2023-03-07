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

package org.wso2.carbon.extension.identity.verifier.util;

/**
 * This class contains the constants of the identity verifier.
 */
public class IdentityVerifierConstants {

    public static final String IDV_ERROR_PREFIX = "IDV-";

    private IdentityVerifierConstants() {

    }

    /**
     * User management service error messages.
     */
    public enum ErrorMessage {

        ERROR_IDV_FACTORY_NOT_EXISTS("65000",
                "Identity verifier factory is not available for the identity verifier name: %s");

        private final String code;
        private final String message;

        ErrorMessage(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String code() {

            return IDV_ERROR_PREFIX + code;
        }

        public String message() {

            return message;
        }

        @Override
        public String toString() {

            return code + " | " + message;
        }
    }
}
