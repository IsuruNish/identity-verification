package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdVProviderServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationClaimResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationGetResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.VerificationPostResponse;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verifier.IdentityVerificationException;
import org.wso2.carbon.extension.identity.verifier.model.IdentityVerifierResponse;

import java.util.ArrayList;
import java.util.List;

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

        IdentityVerifierResponse identityVerifierResponse;
        try {
            identityVerifierResponse = IdVProviderServiceHolder.getIdentityVerifierFactory().getIdentityVerifier("ONFIDO").
                    verifyIdentity(verificationPostRequest.getUsername(),
                            verificationPostRequest.getIdentityVerificationProvider());
        } catch (IdentityVerificationException e) {
            throw new RuntimeException(e);
        }
        return getVerificationPostResponse(identityVerifierResponse);
    }

    private VerificationPostResponse getVerificationPostResponse(IdentityVerifierResponse identityVerifierResponse) {

        VerificationPostResponse verificationPostResponse = new VerificationPostResponse();
        verificationPostResponse.username(identityVerifierResponse.getUserId());
        verificationPostResponse.setIdentityVerificationProvider(identityVerifierResponse.getIdentityVerifierName());
        for (IdVClaim idVClaim : identityVerifierResponse.getIdVClaims()) {
            verificationPostResponse.addClaimsItem(getVerificationClaimAttributeResponse(idVClaim));
        }
        return verificationPostResponse;
    }

    private VerificationClaimResponse getVerificationClaimAttributeResponse(IdVClaim idVClaim) {

        VerificationClaimResponse verificationClaimResponse = new VerificationClaimResponse();
        verificationClaimResponse.setId(idVClaim.getIdVClaimId());
        verificationClaimResponse.setStatus(idVClaim.getIdVStatus());
        verificationClaimResponse.setClaimMetadata(idVClaim.getIdVClaimMetadata());
        return verificationClaimResponse;
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
        List<VerificationClaimResponse> claims = new ArrayList<>();
        for (IdVClaim idVClaim : idVClaims) {
            verificationGetResponse.addClaimsItem(getVerificationClaimResponse(idVClaim));
        }
        return verificationGetResponse;
    }

    private APIError handleError(Response.Status status, Constants.ErrorMessage error) {

        return new APIError(status, getErrorBuilder(error).build());
    }

    private ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorEnum) {

        return new ErrorResponse.Builder().withCode(errorEnum.getCode()).withMessage(errorEnum.getMessage())
                .withDescription(errorEnum.getDescription());
    }
}
