package com.sequoia.hack;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Akash on 29-08-2015.
 */
public class CommunityHeadDAO {
    MongoCollection<Document> peopleCollections;

    public CommunityHeadDAO(final MongoDatabase database){

        peopleCollections = database.getCollection("people");

    }

    public boolean validateUser(String userName, String password){

        Document user;

        user = peopleCollections.find(eq("_id", userName)).first();

        if (user == null) {
            System.out.println("User not in database");
            return false;
        }
        String dbPassword = user.get("password").toString();

        if(password.equals(dbPassword)){
            return true;
        }else{
            return false;
        }
    }


}
