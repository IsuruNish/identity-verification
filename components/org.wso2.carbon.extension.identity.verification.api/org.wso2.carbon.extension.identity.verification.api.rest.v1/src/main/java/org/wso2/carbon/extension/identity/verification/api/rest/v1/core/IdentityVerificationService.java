package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdVProviderServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.ClaimMetadata;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;

import javax.ws.rs.core.Response;

public class IdentityVerificationService {

    public VerificationGetResponse getIdentityVerificationInfo(String userId) {

        IdVClaim[] idVClaim;
        try {
            idVClaim = IdVProviderServiceHolder.getIdVClaimManager().getIDVClaims(userId);
        } catch (IdVClaimMgtException e) {
            return null;
        }
        return getVerificationInfoResponse(userId, idVClaim);
    }

    public VerificationClaimResponse getVerificationClaimMetadata(String userId, String claimId) {

        IdVClaim idVClaim;
        try {
            idVClaim = IdVProviderServiceHolder.getIdVClaimManager().getIDVClaim(userId, claimId);
        } catch (IdVClaimMgtException e) {
            return null;
        }
        return getVerificationClaimResponse(idVClaim);
    }

    public VerificationPostResponse verifyIdentity(VerificationPostRequest verificationPostRequest) {

    }

    private APIError handleInvalidInput(Constants.ErrorMessage errorEnum, String... data) {

        return handleError(Response.Status.BAD_REQUEST, errorEnum);
    }

    private VerificationClaimResponse getVerificationClaimResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getIdVClaimId());
        verificationClaimResponse.setStatus(idVClaim.getIdVStatus());
        verificationClaimResponse.setClaimMetadata(idVClaim.getIdVClaimMetadata());
        return verificationClaimResponse;
    }

    private VerificationGetResponse getVerificationInfoResponse(String userId, IdVClaim[] idVClaims) {

        VerificationGetResponse verificationGetResponse = new VerificationGetResponse();
        verificationGetResponse.setUserId(userId);
        // conver
        verificationGetResponse.setClaims(idVClaims);
        return verificationGetResponse;
    }

    private

    private VerificationPostResponse getVerificationPostResponse(IdVClaim idVClaim) {

        VerificationPostResponse verificationPostResponse = new VerificationPostResponse();
        verificationPostResponse.setId(idVClaim.getIdVClaimId());
        verificationPostResponse.setStatus(idVClaim.getIdVStatus());
        verificationPostResponse.setClaimMetadata(idVClaim.getIdVClaimMetadata());
        return verificationPostResponse;
    }

    private APIError handleError(Response.Status status, Constants.ErrorMessage error) {

        return new APIError(status, getErrorBuilder(error).build());
    }

    private ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorEnum) {

        return new ErrorResponse.Builder().withCode(errorEnum.getCode()).withMessage(errorEnum.getMessage())
                .withDescription(errorEnum.getDescription());
    }
}
