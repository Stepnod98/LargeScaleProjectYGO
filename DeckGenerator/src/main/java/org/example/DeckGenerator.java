package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.neo4j.driver.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.neo4j.driver.Values.parameters;

public class DeckGenerator
{
    //Uri to connect with the server on listening
    private static final String uri = "neo4j://localhost:7687";

    //Default username
    private static final String user = "neo4j";

    //Change the password to "neo4j" if it doesn't work.
    //default login credentials: User: neo4j    Pass: neo4j
    private static final String password = "root";
    private static final Driver driver;

    static{
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
    }

    public static void main( String[] args ) throws IOException {

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("yugioh");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        List<Document> people = collection.find().projection(projectionFields).skip(51).limit(74948).into(new ArrayList<Document>());

        System.out.println(people.size());


        for(int i = 0; i < people.size(); i++){
            String username = ((Document)people.get(i).get("login")).get("username").toString();
            add(username);
            System.out.println(username + i + " Added");
        }

        System.out.println(((Document)people.get(74947).get("login")).get("username"));




        /*MongoCollection<Document> yugioh = database.getCollection("yugioh");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        Bson projectionFields2 = Projections.fields(Projections.excludeId(),Projections.include("title","imageUrl","atk","def","types","archetypes"));

        List<Document> people = collection.find(Filters.exists("name")).projection(projectionFields).into(new ArrayList<Document>());

        //******Extract a list of document containing the special cards.******

        //Build the following query:
        //db.cards.find({$or:[{types:{$all:["Fusion"]}},{types:{$all:["Link"]}},{types:{$all:["Synchro"]}},{types:{$all:["Xyz"]}}]})

        //Filters for types
        Bson filterFusion = Filters.all("types", "Fusion");
        Bson filterLink = Filters.all("types", "Link");
        Bson filterSynchro = Filters.all("types", "Synchro");
        Bson filterXyz = Filters.all("types", "Xyz");

        //Merge all the filters
        Bson filterForSpecial = Filters.or(filterFusion,filterLink,filterSynchro,filterXyz);

        //Create a list of special cards running the query: the list contains all the titles of the card
        List<Document> specialCards = collection.find(filterForSpecial).projection(projectionFields2).into(new ArrayList<Document>());

        //Test to see if the query works: the expected result is 882 --> UPDATE --> The query is correct!
        //System.out.println(specialCards.size());

        //******Extract a list of documents containing all the remaining cards.*******
        //Build the following query:
        //db.cards.find({$and: [
        //  {title: {$exists: true}},
        //  {types: {$not: {$all: ["Fusion"]}}},
        //  {types: {$not: {$all: ["Link"]}}},
        //  {types: {$not: {$all: ["Synchro"]}}},
        //  {types: {$not: {$all: ["Xyz"]}}}
        // ]})

        //Filters for types
        Bson filterNotFusion = Filters.not(filterFusion);
        Bson filterNotLink = Filters.not(filterLink);
        Bson filterNotSynchro = Filters.not(filterSynchro);
        Bson filterNotXyz = Filters.not(filterXyz);
        Bson filterExistingTitle = Filters.exists("title");

        //Merge all the filters
        Bson filterForNormal = Filters.and(filterExistingTitle,filterNotFusion,filterNotLink,filterNotXyz,filterNotSynchro);


        List<Document> normalCards = collection.find(filterForNormal).projection(projectionFields2).into(new ArrayList<Document>());

        //Test to see if the query works: the expected result is 7086 --> UPDATE --> The query is correct!
        //System.out.println(normalCards.size());


        //Extract a random number between 0 and people size to extract a random person!
        List<Document> insertionList = new ArrayList<Document>();

        Random random = new Random();

        for(int j = 0; j < 200; j++) {

            int randomPeopleIndex = random.nextInt(people.size());
            System.out.println("Extracted the person #" + randomPeopleIndex);

            JSONObject jsonObject = new JSONObject(people.get(randomPeopleIndex).toJson());
            String username = jsonObject.getJSONObject("login").getString("username");
            String deckTitle = username + "'s deck";

            //Create a list that contains the card of the major deck
            List<Document> listOfCards = new ArrayList<>();
            for (int i = 0; i < 40; i++) {
                int randomCardsIndex = random.nextInt(normalCards.size());
                listOfCards.add(normalCards.get(randomCardsIndex));
            }

            //Create the list of the extra cards
            List<Document> extraDeck = new ArrayList<>();
            int randomExtraDeckSize = random.nextInt(10);
            for(int i = 0; i < randomExtraDeckSize; i++){
                int randomCardsIndex = random.nextInt(specialCards.size());
                extraDeck.add(specialCards.get(randomCardsIndex));
            }

            Document doc = new Document("title", deckTitle).append("creator", username).append("cards", listOfCards).append("extra_deck", extraDeck);

            insertionList.add(doc);
        }

        yugioh.insertMany(insertionList);
        myClient.close();
    */


    }

    /*public static void populateGraphUser(){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        List<Document> people = collection.find().projection(projectionFields).limit(50).into(new ArrayList<>());

        //Add into nodeDB

        String user;
        for (Document person : people) {
            user = (String) ((Document) person.get("login")).get("username");
            add(user, "User");
        }

        for(int i = 0; i < people.size(); i++){
            user = (String) ((Document) people.get(i).get("login")).get("username");
            String matchQuery = "MATCH(u:User {username: \""+ user +"\"})";
            String mergeQuery = "CREATE ";
            int randomFriendsNumb = new Random().nextInt(9) + 1;
            System.out.println(user + " has " + randomFriendsNumb + " friends");
            List<String> friends = new ArrayList<>();
            for( int j = 0; j < randomFriendsNumb; j++){
                String friend =  (String) ((Document) people.get(new Random().nextInt(people.size())).get("login")).get("username");
                if(friend.equals(user) || friends.contains(friend))
                    continue;
                friends.add(friend);
                matchQuery +=  ",\n(u"+j+":User {username: \""+friend+"\"})";

                if(j == 0) {
                    mergeQuery += "(u)-[:FOLLOWS]->(u" + j + ")";
                }else{
                    if(mergeQuery.equals("CREATE ")) {
                        mergeQuery += "(u)-[:FOLLOWS]->(u" + j + ")";
                    }else{
                        mergeQuery += ",\n(u)-[:FOLLOWS]->(u"+j+")";
                    }
                }
            }
            System.out.println(matchQuery+"\n"+mergeQuery);
            runQuery(matchQuery+"\n"+mergeQuery);
        }
    }*/

    public static void add(final String name){
        try ( Session session = driver.session() )
        {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run( "MERGE (p:User {username: $name})",
                        parameters( "name", name ) );
                return null;
            });
        }
    }

}
