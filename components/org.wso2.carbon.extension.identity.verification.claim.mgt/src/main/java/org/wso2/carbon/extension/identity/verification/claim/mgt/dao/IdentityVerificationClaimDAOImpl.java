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
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.model.Filters;
import org.wso2.carbon.extension.identity.verification.claim.mgt.IdVClaimMgtException;
import org.wso2.carbon.extension.identity.verification.claim.mgt.model.IdVClaim;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.core.util.IdentityUtil;

/**
 * Identity verification claim DAO class.
 */
public class IdentityVerificationClaimDAOImpl implements IdentityVerificationClaimDAO {

    private final String DATABASE_URL_REGEX = "datasource.configuration.url";
    private final String DATABASE_NAME_REGEX = "datasource.configuration.databaseName";
    private final String DATABASE_COLLECTION_REGEX = "datasource.configuration.collectionName";

    /**
     * Add the identity verification claim.
     *
     * @param idVClaim IdentityVerificationClaim.
     * @param tenantId Tenant id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public void addIdVClaim(IdVClaim idVClaim, int tenantId) throws IdVClaimMgtException {

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);

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
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);
            collection.insertOne(doc);
        }
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

        //question
        //"UPDATE IDV_CLAIM SET IS_VERIFIED = ?, METADATA = ? WHERE USER_ID = ? AND UUID = ? AND TENANT_ID = ?";

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

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        //question
        //idVClaimId = claimId = uuid ?

//        "SELECT UUID, USER_ID, CLAIM_URI, TENANT_ID, IDVP_ID, IS_VERIFIED, METADATA FROM IDV_CLAIM WHERE USER_ID = ? AND UUID = ? AND TENANT_ID = ?"

        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);
            ObjectMapper mapper = new ObjectMapper();
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);
            Document doc = collection.find(Filters.and(
                    Filters.eq("id", idVClaimId),
                    Filters.eq("tenantId", tenantId)))
                    .first();

            try {
                return doc != null ? mapper.readValue(doc.toJson(), IdVClaim.class) : null;
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
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

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        // question
        // do we need these fields exactly?
        // "SELECT UUID, USER_ID, CLAIM_URI, IS_VERIFIED, METADATA FROM IDV_CLAIM WHERE USER_ID = ? AND TENANT_ID = ?";

        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);
            ObjectMapper mapper = new ObjectMapper();
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);

            FindIterable<Document>  doc = collection.find(Filters.and(
                    Filters.eq("userId", userId),
                    Filters.eq("tenantId", tenantId)));

            List<IdVClaim> idVClaims = new ArrayList<>();
            for (Document result : doc) {
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
    }

    /**
     * Delete the identity verification claim.
     *
     * @param idVClaimId Identity verification claim id.
     * @throws IdVClaimMgtException Identity verification claim management exception.
     */
    @Override
    public void deleteIdVClaim(String idVClaimId, int tenantId) throws IdVClaimMgtException {

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);

            Document query = new Document();
            query.append("id", idVClaimId);
            query.append("tenantId", tenantId);

            DeleteResult result = collection.deleteMany(Filters.and(
                    Filters.eq("id", idVClaimId),
                    Filters.eq("tenantId", tenantId)));
            System.out.println("Deleted document count: " + result.getDeletedCount());

        }
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

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        // question
        // paras? --> idvp id == idv id?
//        "SELECT ID FROM IDV_CLAIM WHERE USER_ID = ? AND IDVP_ID = ? AND CLAIM_URI = ? AND TENANT_ID = ?";



        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);
            Document doc = collection.find(Filters.and(
                    Filters.eq("userId", userId),
                    Filters.eq("tenantId", tenantId),
                    Filters.eq("idvId", idvId),
                    Filters.eq("claimUri", uri)))
                    .first();

            if (doc != null){
                return true;
            }

            return false;

        }
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

        String url = IdentityUtil.getProperty(DATABASE_URL_REGEX);
        String db = IdentityUtil.getProperty(DATABASE_NAME_REGEX);
        String collectionName = IdentityUtil.getProperty(DATABASE_COLLECTION_REGEX);

        //question
        //only id returns ?
//        "SELECT ID FROM IDV_CLAIM WHERE UUID = ? AND TENANT_ID = ?";

        try (MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase dbObj = mongoClient.getDatabase(db);
            MongoCollection<Document> collection = dbObj.getCollection(collectionName);

            Document doc = collection.find(Filters.and(
                    Filters.eq("uuid", claimId),
                    Filters.eq("tenantId", tenantId))).first();

            if (doc != null) {
                return true;
            }

            return false;

        }
    }

}
