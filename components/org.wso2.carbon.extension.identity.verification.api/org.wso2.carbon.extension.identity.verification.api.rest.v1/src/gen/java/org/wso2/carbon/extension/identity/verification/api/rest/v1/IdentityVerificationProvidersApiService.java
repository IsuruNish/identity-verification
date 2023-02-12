/*
* Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.extension.identity.verification.api.rest.v1;

import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.IdentityVerificationProvider;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Patch;

import java.util.List;

import javax.ws.rs.core.Response;


public interface IdentityVerificationProvidersApiService {

      public Response addIDV(IdentityVerificationProvider identityVerificationProvider);

      public Response deleteIDV(String identityVerificationProviderId);

      public Response getIDV(String identityVerificationProviderId);

      public Response getIDVs(Integer limit, Integer offset);

      public Response patchIDV(String identityVerificationProviderId, List<Patch> patch);

      public Response putIDV(String identityVerificationProviderId, IdentityVerificationProvider identityVerificationProvider);
}
