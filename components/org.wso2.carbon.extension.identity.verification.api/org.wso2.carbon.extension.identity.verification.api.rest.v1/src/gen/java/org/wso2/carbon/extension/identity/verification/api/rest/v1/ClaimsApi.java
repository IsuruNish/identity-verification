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
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimUpdateRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.ClaimsApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/claims")
@Api(description = "The claims API")

public class ClaimsApi  {

    @Autowired
    private ClaimsApiService delegate;

    @Valid
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json", "application/xml",  })
    @ApiOperation(value = "Add verification claim data ", notes = "This API provides the capability to add verification claim data", response = VerificationClaimResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Identity Verification Claims", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful response", response = VerificationClaimResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response addVerificationClaimData(@ApiParam(value = "This represents the identity provider to be created." ,required=true) @Valid VerificationClaimPostRequest verificationClaimPostRequest) {

        return delegate.addVerificationClaimData(verificationClaimPostRequest );
    }

    @Valid
    @GET
    @Path("/{claim-id}")
    
    @Produces({ "application/json" })
    @ApiOperation(value = "Get the verification data of a claim", notes = "This API provides the capability to retrive the verification metadata of a user claim", response = VerificationClaimResponse.class, responseContainer = "List", authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Identity Verification Claims", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = VerificationClaimResponse.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid status value", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response getVerificationClaimData(@ApiParam(value = "Claim that needs to retrieve verification metadata",required=true) @PathParam("claim-id") String claimId) {

        return delegate.getVerificationClaimData(claimId );
    }

    @Valid
    @PUT
    @Path("/{claim-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Update the verification data of a claim", notes = "", response = VerificationClaimResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "Identity Verification Claims" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful response", response = VerificationClaimResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Forbidden", response = Void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response updateVerificationClaim(@ApiParam(value = "Claim that needs to retrieve verification metadata",required=true) @PathParam("claim-id") String claimId, @ApiParam(value = "" ,required=true) @Valid VerificationClaimUpdateRequest verificationClaimUpdateRequest) {

        return delegate.updateVerificationClaim(claimId,  verificationClaimUpdateRequest );
    }

}
