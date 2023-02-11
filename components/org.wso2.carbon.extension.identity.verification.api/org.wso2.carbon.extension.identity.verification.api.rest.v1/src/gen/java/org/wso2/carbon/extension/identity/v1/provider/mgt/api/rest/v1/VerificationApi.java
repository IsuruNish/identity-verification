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

package org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1;

import org.springframework.beans.factory.annotation.Autowired;

import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.Error;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.v1.provider.mgt.api.rest.v1.model.InitVerificationResponse;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/verification")
@Api(description = "The verification API")

public class VerificationApi  {

    @Autowired
    private VerificationApiService delegate;

    @Valid
    @GET
    
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the verification details of a user", notes = "This API provides the capability to retrive the verification details of a user", response = VerificationGetResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Verification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VerificationGetResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getIdentityVerificationInfo(    @Valid @NotNull(message = "Property  cannot be null.") @ApiParam(value = "User id of the user",required=true)  @QueryParam("user-id") String userId) {

        return delegate.getIdentityVerificationInfo(userId );
    }

    @Valid
    @GET
    @Path("/{user-id}/claim/{claim-id}")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the verification metadata of a claim", notes = "This API provides the capability to retrive the verification metadata of a user claim", response = VerificationClaimResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Verification", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VerificationClaimResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getVerificationClaimMetadata(@ApiParam(value = "user id of the user",required=true) @PathParam("user-id") String userId, @ApiParam(value = "Claim that needs to retrive verification metadaa",required=true) @PathParam("claim-id") String claimId) {

        return delegate.getVerificationClaimMetadata(userId,  claimId );
    }

    @Valid
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Verify an Identity", notes = "This API provides the capability to verify a user with the configured verification required attributes", response = VerificationPostResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Verification" })
    @ApiResponses(value = { 
        @ApiResponse(code = 202, message = "Accepted", response = InitVerificationResponse.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "Successful operation", response = VerificationPostResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response verifyIdentity(@ApiParam(value = "Verify an identity" ,required=true) @Valid VerificationPostRequest verificationPostRequest) {

        return delegate.verifyIdentity(verificationPostRequest );
    }

}
