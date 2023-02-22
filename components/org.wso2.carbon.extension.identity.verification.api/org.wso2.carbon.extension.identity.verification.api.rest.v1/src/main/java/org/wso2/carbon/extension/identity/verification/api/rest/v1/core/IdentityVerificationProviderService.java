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

package org.wso2.carbon.extension.identity.verification.api.rest.v1.core;

import org.wso2.carbon.extension.identity.verification.api.rest.common.Constants;
import org.wso2.carbon.extension.identity.verification.api.rest.common.IdVProviderServiceHolder;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.APIError;
import org.wso2.carbon.extension.identity.verification.api.rest.common.error.ErrorResponse;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.ConfigProperty;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.IdVProviderRequest;
import org.wso2.carbon.extension.identity.verification.api.rest.v1.model.IdVProviderResponse;
import org.wso2.carbon.extension.identity.verification.provider.IdVProviderMgtException;
import org.wso2.carbon.extension.identity.verification.provider.model.IdVConfigProperty;
import org.wso2.carbon.extension.identity.verification.provider.model.IdentityVerificationProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

/**
 * Service class for identity verification providers.
 */
public class IdentityVerificationProviderService {

    /**
     * Add an identity verification provider.
     *
     * @param idVProviderRequest Identity verification provider request.
     * @return Identity verification providers.
     */
    public IdVProviderResponse addIdVProvider(IdVProviderRequest idVProviderRequest) {

        IdentityVerificationProvider identityVerificationProvider;
        try {
            identityVerificationProvider = IdVProviderServiceHolder.getIdVProviderManager().
                    addIdVProvider(createIdVProvider(idVProviderRequest));
        } catch (IdVProviderMgtException e) {
            throw handleError(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_ADDING_IDVP);
        }
        return getIdVProviderResponse(identityVerificationProvider);
    }

    /**
     * Update identity verification provider.
     *
     * @param idVProviderId Identity verification provider id.
     * @param idVProviderRequest Identity verification provider request.
     * @return Identity verification provider response.
     */
    public IdVProviderResponse updateIdVProvider(String idVProviderId, IdVProviderRequest idVProviderRequest) {

        IdentityVerificationProvider identityVerificationProvider ;
        try {
            identityVerificationProvider =
                    IdVProviderServiceHolder.getIdVProviderManager().getIdVProvider(idVProviderId);

            if (identityVerificationProvider == null) {
                throw handleError(Response.Status.NOT_FOUND,
                        Constants.ErrorMessage.ERROR_CODE_IDVP_NOT_FOUND);
            }

            identityVerificationProvider = IdVProviderServiceHolder.getIdVProviderManager().
                    updateIdVProvider(idVProviderId, createIdVProvider(idVProviderRequest));
        } catch (IdVProviderMgtException e) {
            throw handleError(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_UPDATING_IDVP);
        }
        return getIdVProviderResponse(identityVerificationProvider);
    }

    /**
     * Get identity verification provider by id.
     *
     * @param idVProviderId Identity verification provider id.
     * @return Identity verification provider response.
     */
    public IdVProviderResponse getIdVProvider(String idVProviderId) {

        try {
            IdentityVerificationProvider identityVerificationProvider =
                    IdVProviderServiceHolder.getIdVProviderManager().getIdVProvider(idVProviderId);
            IdVProviderResponse idVProviderResponse = new IdVProviderResponse();
            idVProviderResponse.setId(identityVerificationProvider.getIdVProviderId());
            idVProviderResponse.setName(identityVerificationProvider.getIdVProviderName());
            idVProviderResponse.setDisplayName(identityVerificationProvider.getDisplayName());
            idVProviderResponse.setDisplayName(identityVerificationProvider.getIdVProviderDescription());

            List<ConfigProperty> configProperties =
                    Arrays.stream(identityVerificationProvider.getIdVConfigProperties()).
                            map(propertyToExternal).collect(Collectors.toList());

            idVProviderResponse.setConfigProperties(configProperties);

            return idVProviderResponse;
        } catch (IdVProviderMgtException e) {
            throw handleError(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_RETRIEVING_IDVP);
        }
    }

    /**
     * Delete identity verification provider by id.
     *
     * @param identityVerificationProviderId Identity verification provider id.
     */
    public void deleteIdVProvider(String identityVerificationProviderId) {

        try {
            IdVProviderServiceHolder.getIdVProviderManager().deleteIdVProvider(identityVerificationProviderId);
        } catch (IdVProviderMgtException e) {
            throw handleError(Response.Status.INTERNAL_SERVER_ERROR,
                    Constants.ErrorMessage.ERROR_CODE_ERROR_DELETING_IDVP);
        }
    }

    private IdVProviderResponse getIdVProviderResponse(IdentityVerificationProvider
                                                               identityVerificationProvider) {

        IdVProviderResponse idvProviderResponse = new IdVProviderResponse();
        idvProviderResponse.setId(identityVerificationProvider.getIdVProviderId());
        idvProviderResponse.setName(identityVerificationProvider.getIdVProviderName());
        idvProviderResponse.setDisplayName(identityVerificationProvider.getDisplayName());
        idvProviderResponse.setDescription(identityVerificationProvider.getIdVProviderDescription());
        List<ConfigProperty> configProperties =
                Arrays.stream(identityVerificationProvider.getIdVConfigProperties()).
                        map(propertyToExternal).collect(Collectors.toList());

        idvProviderResponse.setConfigProperties(configProperties);
        return idvProviderResponse;
    }

    private IdentityVerificationProvider createIdVProvider(IdVProviderRequest idVProviderRequest) {

        IdentityVerificationProvider identityVerificationProvider = new IdentityVerificationProvider();
        identityVerificationProvider.setIdVProviderName(idVProviderRequest.getName());
        identityVerificationProvider.setDisplayName(idVProviderRequest.getDisplayName());
        identityVerificationProvider.setIdVProviderDescription(idVProviderRequest.getDescription());

        List<ConfigProperty> properties = idVProviderRequest.getConfigProperties();

        identityVerificationProvider.setIdVConfigProperties(
                properties.stream().map(propertyToInternal).toArray(IdVConfigProperty[]::new));
        identityVerificationProvider.setEnable(true);
        return identityVerificationProvider;
    }

    private final Function<ConfigProperty, IdVConfigProperty> propertyToInternal = apiProperty -> {

        IdVConfigProperty idVConfigProperty = new IdVConfigProperty();
        idVConfigProperty.setName(apiProperty.getKey());
        idVConfigProperty.setValue(apiProperty.getValue());
        return idVConfigProperty;
    };

    private final Function<IdVConfigProperty, ConfigProperty> propertyToExternal = apiProperty -> {

        ConfigProperty configProperty = new ConfigProperty();
        configProperty.setKey(apiProperty.getName());
        configProperty.setValue(apiProperty.getValue());
        return configProperty;
    };

    private APIError handleInvalidInput(Constants.ErrorMessage errorEnum, String... data) {

        return handleError(Response.Status.BAD_REQUEST, errorEnum);
    }

    private APIError handleError(Response.Status status, Constants.ErrorMessage error) {

        return new APIError(status, getErrorBuilder(error).build());
    }

    private ErrorResponse.Builder getErrorBuilder(Constants.ErrorMessage errorEnum) {

        return new ErrorResponse.Builder().withCode(errorEnum.getCode()).withMessage(errorEnum.getMessage())
                .withDescription(errorEnum.getDescription());
    }
}
