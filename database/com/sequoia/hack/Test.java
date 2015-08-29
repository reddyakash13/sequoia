package com.sequoia.hack;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Created by Akash on 29-08-2015.
 */
public class Test {
    public static void main(String[] args) {

        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://54.169.176.52"));
        final MongoDatabase communityDatabase = mongoClient.getDatabase("test");

        /*CommunityHeadDAO communityDAO = new CommunityHeadDAO(communityDatabase);

        System.out.println(communityDAO.validateUser("akash","Cub@4129"));*/

        CommunityDAO communityDAO = new CommunityDAO(communityDatabase);

        /*System.out.print(communityDAO.addCommunity("akash","kotapalli",1000));*/

        /*List<Document> list = communityDAO.getAllCommunitiesForHead("akash");
        System.out.println(list.get(0));*/

        System.out.println(communityDAO.getCommunityDetails("akash","kotapalli"));

       /* System.out.println(communityDAO.addFundsToCommunity("akash","kotapalli",250));*/
    }
}
