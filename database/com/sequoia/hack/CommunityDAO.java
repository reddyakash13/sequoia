package com.sequoia.hack;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Akash on 29-08-2015.
 */
public class CommunityDAO {

    MongoCollection<Document> communityCollections;

    public CommunityDAO(final MongoDatabase database){
        communityCollections = database.getCollection("community");
    }

    public boolean addCommunity(String communityHead, String communityName, int initialFunds){
        Document community = new Document();
        community.append("communityHead", communityHead);
        community.append("name", communityName);
        community.append("availableFunds", initialFunds);

        try{
            communityCollections.insertOne(community);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public List<Document> getAllCommunitiesForHead(String communityHead){
        final List<Document> communities = new ArrayList<Document>();

        FindIterable<Document> iterable = communityCollections.find(eq("communityHead", communityHead));

        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                communities.add(document);
            }
        });

        return communities;
    }

    public Document getCommunityDetails(String communityHead, String communityName){
        Document community = null;
        Document filter = new Document("communityHead",communityHead).append("name",communityName);
        community = communityCollections.find(filter).first();

        return  community;
    }

    public Document addFundsToCommunity(String communityHead, String communityName, int funds){
        Document community = getCommunityDetails(communityHead,communityName);
        Document filter = new Document("communityHead",communityHead).append("name",communityName);
        int currentFunds = community.getInteger("availableFunds");
        int newFunds = funds + currentFunds;

        communityCollections.updateOne(filter,new Document("$set", new Document("availableFunds",newFunds)));

        return getCommunityDetails(communityHead,communityName);
    }

    public Document releaseFundsFromCommunity(String communityHead, String communityName, int funds){
        Document community = getCommunityDetails(communityHead,communityName);
        Document filter = new Document("communityHead",communityHead).append("name",communityName);
        int currentFunds = community.getInteger("availableFunds");
        int newFunds = currentFunds - funds;

        communityCollections.updateOne(filter,new Document("$set", new Document("availableFunds",newFunds)));

        return getCommunityDetails(communityHead,communityName);
    }
}
