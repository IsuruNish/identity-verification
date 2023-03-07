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

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;
import java.util.List;

import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.Error;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.VerificationApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/")
@Api(description = "The  API")

public class VerificationApi  {

    @Autowired
    private VerificationApiService delegate;

    @Valid
    @GET
    @Path("/{user-id}")
    
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
    public Response getIdentityVerificationInfo(@ApiParam(value = "user id of the user",required=true) @PathParam("user-id") String userId) {

        return delegate.getIdentityVerificationInfo(userId );
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
        @ApiResponse(code = 201, message = "Accepted", response = VerificationPostResponse.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "Successful operation", response = VerificationPostResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response verifyIdentity(@ApiParam(value = "Verify an identity" ,required=true) @Valid VerificationClaimRequest verificationClaimRequest) {

        return delegate.verifyIdentity(verificationClaimRequest );
    }

}
