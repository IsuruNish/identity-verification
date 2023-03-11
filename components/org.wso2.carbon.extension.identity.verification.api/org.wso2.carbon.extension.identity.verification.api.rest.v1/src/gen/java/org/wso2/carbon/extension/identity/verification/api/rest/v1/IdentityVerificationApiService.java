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

import org.wso2.carbon.extension.identity.verification.api.rest.v1.*;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.*;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;
import java.util.List;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Error;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import javax.ws.rs.core.Response;


public interface IdentityVerificationApiService {

      public Response addIdVClaim(String userId, VerificationClaimPostRequest verificationClaimPostRequest);

      public Response getIdVClaim(String userId, String claimId);

      public Response getIdVClaims(String userId);

      public Response updateIdVClaim(String claimId, String userId, VerificationClaimUpdateRequest verificationClaimUpdateRequest);

      public Response verifyIdentity(VerificationClaimRequest verificationClaimRequest);
}
