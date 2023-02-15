/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.extension.identity.verification.api.rest.common;

import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimManager;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderManager;

/**
 * Service holder class for identity providers.
 */
public class IdVProviderServiceHolder {

    private static IdVProviderManager idVProviderManager;
    private static IdVClaimManager idVClaimManager;

    /**
     * Get IdVProviderManager osgi service.
     *
     * @return IdVProviderManager
     */
    public static IdVProviderManager getIdVProviderManager() {

        return idVProviderManager;
    }

    /**
     * Set IdVProviderManager osgi service.
     *
     * @param idVProviderManager IdVProviderManager.
     */
    public static void setIdVProviderManager(IdVProviderManager idVProviderManager) {

        IdVProviderServiceHolder.idVProviderManager = idVProviderManager;
    }

    public static IdVClaimManager getIdVClaimManager() {

        return idVClaimManager;
    }


    public static void setIdVClaimManager(IdVClaimManager idVClaimManager) {

        IdVProviderServiceHolder.idVClaimManager = idVClaimManager;
    }
}
