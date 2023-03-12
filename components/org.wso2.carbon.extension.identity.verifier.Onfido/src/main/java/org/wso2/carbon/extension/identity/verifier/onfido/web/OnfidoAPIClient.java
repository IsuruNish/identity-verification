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

package org.wso2.carbon.extension.identity.verifier.onfido.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.wso2.carbon.extension.identity.verifier.onfido.OnfidoException;

import java.io.IOException;

/**
 * This class contains the implementation of OnfidoAPIClient.
 */
public class OnfidoAPIClient {

    public static JsonObject createApplicantResponse() throws OnfidoException, IOException {

        HttpResponse response = HYPRWebUtils.
                httpPost("api_sandbox.zf20aqvpfGl.MGh9sYKZFflunrTVedkK_X9p75HX4aVT",
                        "https://api.eu.onfido.com/v3.6/applicants/",
                        "{\n" +
                                "  \"first_name\": \"Jane\",\n" +
                                "  \"last_name\": \"Smith\"\n" +
                                "}");
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {

            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            return gson.fromJson(jsonResponse, JsonObject.class);

        }
        return null;
    }

    public static JsonObject uploadDocument() throws OnfidoException, IOException {

        HttpResponse response = HYPRWebUtils.
                httpPost("api_sandbox.zf20aqvpfGl.MGh9sYKZFflunrTVedkK_X9p75HX4aVT",
                        "https://api.eu.onfido.com/v3.6/documents",
                        "file=@\"/home/gangani/Pictures/sample_driving_licence.png",
                        "f29cfa8b-80cb-4ad5-a2b4-891fdd5a7b56");
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {

            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            return gson.fromJson(jsonResponse, JsonObject.class);

        }
        return null;
    }

    public static JsonObject verificationCheck() throws OnfidoException, IOException {

        HttpResponse response = HYPRWebUtils.
                httpPost("api_sandbox.zf20aqvpfGl.MGh9sYKZFflunrTVedkK_X9p75HX4aVT",
                        "https://api.eu.onfido.com/v3.6/checks",
                        "{\n" +
                                "  \"applicant_id\": \"f29cfa8b-80cb-4ad5-a2b4-891fdd5a7b56\",\n" +
                                "  \"report_names\": [\"document\", \"document_with_driving_licence_information\"]\n" +
                                "}");
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {

            Gson gson = new GsonBuilder().create();
            HttpEntity entity = response.getEntity();
            String jsonResponse = EntityUtils.toString(entity);
            return gson.fromJson(jsonResponse, JsonObject.class);

        }
        return null;
    }

    public static HttpResponse getApplicantResponse() throws OnfidoException, IOException {

        return HYPRWebUtils.
                httpGet("api_sandbox.zf20aqvpfGl.MGh9sYKZFflunrTVedkK_X9p75HX4aVT",
                        "https://api.eu.onfido.com/v3.6/applicants/9f419d72-6f19-4b4f-9bf7-e51ca868386f");
    }
}
