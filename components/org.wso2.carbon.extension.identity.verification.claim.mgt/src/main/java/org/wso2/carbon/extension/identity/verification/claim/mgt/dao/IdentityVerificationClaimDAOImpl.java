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
package org.wso2.carbon.extension.identity.verification.claim.mgt.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.json.JSONObject;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants;
import org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtExceptionManagement;
import org.wso2.carbon.identity.core.util.IdentityDatabaseUtil;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_ADDING_IDV_CLAIM;
import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_CHECKING_IDV_CLAIM_EXISTENCE;
import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_DELETING_IDV_CLAIM;
import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_RETRIEVING_IDV_CLAIM;
import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_RETRIEVING_IDV_CLAIMS;
import static org.wso2.carbon.extension.identity.verification.claim.mgt.util.IdVClaimMgtConstants.ErrorMessage.ERROR_UPDATING_IDV_CLAIM;

/**
 * Identity verification claim DAO class.
 */
public class IdentityVerificationClaimDAOImpl implements IdentityVerificationClaimDAO {

    private static final Log log = LogFactory.getLog(IdentityVerificationClaimDAOImpl.class);

    private static String host = "localhost";
    private static int port = 27017;
    private static MongoClient mongoClient = new MongoClient( host , port );

    /**
     * Add the identity verification claim.
     *
     * @param idVClaim IdentityVerificationClaim.
     * @param tenantId Tenant id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public void addIdVClaim(IdVClaim idVClaim, int tenantId) throws IdVClaimMgtException {

        MongoDatabase dbObj = mongoClient.getDatabase("identitydb");

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(idVClaim);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ObjectNode jsonNode = null;
        try {
            jsonNode = (ObjectNode) mapper.readTree(jsonStr);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        jsonNode.put("tenantId",tenantId);

        String json = null;
        try {
            json = mapper.writeValueAsString((jsonNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Document doc = Document.parse(json);
        MongoCollection<Document> collection = dbObj.getCollection("idv_claim");
        collection.insertOne(doc);
    }

    /**
     * Update the identity verification claim by the user id.
     *
     * @param idVClaim IdentityVerificationClaim.
     * @param tenantId Tenant id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public void updateIdVClaim(IdVClaim idVClaim, int tenantId) throws IdVClaimMgtException {
        System.out.println("to do");
    }

    /**
     * Get the identity verification claim.
     *
     * @param userId   User id.
     * @param idVClaimId Identity verification claim id.
     * @return Identity verification claim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public IdVClaim getIDVClaim(String userId, String idVClaimId, int tenantId) throws IdVClaimMgtException {

        MongoDatabase dbObj = mongoClient.getDatabase("identitydb");
        ObjectMapper mapper = new ObjectMapper();
        MongoCollection<Document> collection = dbObj.getCollection("idv_claim");
        Document doc = collection.find(Filters.and(Filters.eq("id", idVClaimId), Filters.eq("tenantId", tenantId))).first();

        try {
            return doc != null ? mapper.readValue(doc.toJson(), IdVClaim.class) : null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the identity verification claims.
     *
     * @param tenantId Identity verification claim id.
     * @param userId   User id.
     * @return Identity verification claim.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public IdVClaim[] getIDVClaims(String userId, int tenantId) throws IdVClaimMgtException {

        MongoDatabase dbObj = mongoClient.getDatabase("identitydb");
        ObjectMapper mapper = new ObjectMapper();

        MongoCollection<Document> collection = dbObj.getCollection("idv_claim");

        Document query = new Document("userId", userId).append("tenantId", tenantId);

        FindIterable<Document> results = collection.find(query);

        List<IdVClaim> idVClaims = new ArrayList<>();
        for (Document result : results) {
            IdVClaim idVClaim = null;
            try {
                idVClaim = mapper.readValue(result.toJson(), IdVClaim.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            idVClaims.add(idVClaim);
        }

        return idVClaims.toArray(new IdVClaim[0]);
    }

    /**
     * Delete the identity verification claim.
     *
     * @param idVClaimId Identity verification claim id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public void deleteIdVClaim(String idVClaimId, int tenantId) throws IdVClaimMgtException {

        MongoDatabase dbObj = mongoClient.getDatabase("identitydb");
        MongoCollection<Document> collection = dbObj.getCollection("idv_claim");

        Document query = new Document("id", idVClaimId).append("tenantId", tenantId);
        DeleteResult result = collection.deleteMany(query);
        System.out.println(result.getDeletedCount());
    }

    /**
     * Check whether the identity verification claim data already exist.
     *
     * @param userId   User id.
     * @param idvId    Identity verification id.
     * @param uri      Claim uri.
     * @param tenantId Tenant id.
     * @return True if the identity verification claim exist.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public boolean isIdVClaimDataExist(String userId, String idvId, String uri, int tenantId)
            throws IdVClaimMgtException {

        MongoDatabase dbObj = mongoClient.getDatabase("identitydb");
        MongoCollection<Document> collection = dbObj.getCollection("idv_claim");
        Document query = new Document("userId", userId).append("tenantId", tenantId).append("idvId", idvId).append("uri", uri);
        long results = collection.countDocuments(query);

        return results > 0;
    }

    /**
     * Check whether the identity verification claim data already exist.
     *
     * @param claimId Identity verification claim id.
     * @param tenantId Tenant id.
     * @return True if the identity verification claim exist.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public boolean isIdVClaimExist(String claimId, int tenantId) throws IdVClaimMgtException {

        System.out.println("is it");
        return false;
    }

    private byte[] getMetadata(IdVClaim idVClaim) {

        String metadataString = idVClaim.getMetadata().toString();
        return metadataString.getBytes(StandardCharsets.UTF_8);
    }

    private JSONObject getMetadataJsonObject(byte[] metadata) {

        String metadataString = new String(metadata, StandardCharsets.UTF_8);
        return new JSONObject(metadataString);
    }
}
