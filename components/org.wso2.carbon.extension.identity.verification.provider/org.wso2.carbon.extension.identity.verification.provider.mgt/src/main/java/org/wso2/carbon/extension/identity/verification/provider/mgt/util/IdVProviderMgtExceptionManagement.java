package org.wso2.carbon.extension.identity.verification.provider.mgt.util;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdVProviderMgtClientException;
import org.wso2.carbon.extension.identity.verification.provider.mgt.IdvProviderMgtServerException;

public class IdVProviderMgtExceptionManagement {

    /**
     * This method can be used to generate a FunctionLibraryManagementClientException from
     * FunctionLibraryManagementConstants.ErrorMessage object when no exception is thrown.
     *
     * @param error FunctionLibraryManagementConstants.ErrorMessage.
     * @param data  data to replace if message needs to be replaced.
     * @return FunctionLibraryManagementClientException.
     */
    public static IdVProviderMgtClientException handleClientException(
            IdVProviderMgtConstants.ErrorMessage error, String data) {

        String message = includeData(error, data);
        return new IdVProviderMgtClientException(error.getCode(), message);
    }

    public static IdVProviderMgtClientException handleClientException(
            IdVProviderMgtConstants.ErrorMessage error, String data, Throwable e) {

        String message = includeData(error, data);
        return new IdVProviderMgtClientException(error.getCode(), message, e);
    }

    public static IdVProviderMgtClientException handleClientException(
            IdVProviderMgtConstants.ErrorMessage error) {

        String message = error.getMessage();
        return new IdVProviderMgtClientException(error.getCode(), message);
    }

    /**
     * This method can be used to generate a FunctionLibraryManagementServerException from
     * FunctionLibraryManagementConstants.ErrorMessage object when no exception is thrown.
     *
     * @param error FunctionLibraryManagementConstants.ErrorMessage.
     * @param data  data to replace if message needs to be replaced.
     * @return FunctionLibraryManagementServerException.
     */
    public static IdvProviderMgtServerException handleServerException(
            IdVProviderMgtConstants.ErrorMessage error, String data, Throwable e) {

        String message = includeData(error, data);
        return new IdvProviderMgtServerException(error.getCode(), message, e);
    }

    public static IdvProviderMgtServerException handleServerException(
            IdVProviderMgtConstants.ErrorMessage error) {

        String message = error.getMessage();
        return new IdvProviderMgtServerException(error.getCode(), message);
    }

    public static IdvProviderMgtServerException handleServerException(
            IdVProviderMgtConstants.ErrorMessage error, Throwable e) {

        String message = error.getMessage();
        return new IdvProviderMgtServerException(error.getCode(), message, e);
    }

    /**
     * Include the data to the error message.
     *
     * @param error FunctionLibraryManagementConstants.ErrorMessage.
     * @param data  data to replace if message needs to be replaced.
     * @return message format with data.
     */
    private static String includeData(IdVProviderMgtConstants.ErrorMessage error, String data) {

        String message;
        if (StringUtils.isNotBlank(data)) {
            message = String.format(error.getMessage(), data);
        } else {
            message = error.getMessage();
        }
        return message;
    }
}
