package it.unipi.lsmsdb.yugiohdeckmaker;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.neo4j.driver.Values.ofBoolean;
import static org.neo4j.driver.Values.parameters;

public class Neo4jManager {

    //Uri to connect with the server on listening
    private static final String uri = new String("neo4j://localhost:7687");

    //Default username
    private static final String user = new String("neo4j");

    //Change the password to "neo4j" if it doesn't work.
    //default login credentials: User: neo4j    Pass: neo4j
    private static final String password = new String("root");
    private static final Driver driver;

    static{
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
    }

    //Used to authenticate the user to access the service **UPDATE** The class is static so this method is not needed
    /*public Neo4jManager(){
        authenticate();
    }*/

    //This method is used to authenticate the
    /*private void authenticate(){
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user,password));
    }*/


    public static void addPerson( final String name, final String from, final int age )
    {
        try ( Session session = driver.session() )
        {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run( "MERGE (p:Person {name: $name, from: $from, age: $age})",
                        parameters( "name", name, "from", from, "age", age ) );
                return null;
            });
        }
    }

    public static void add(final String name, final String label){
        try ( Session session = driver.session() )
        {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run( "MERGE (p:"+label+" {username: $name})",
                        parameters( "label",label,"name", name ) );
                return null;
            });
        }
    }

    public static void addRelation(final String name1, final String name2, final String relation, String label){
        try ( Session session = driver.session() )
        {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run( "MERGE (p:"+label+" {username: $name1})-[:"+relation+"]->(pp:"+label+" {username: $name2})",
                        parameters(  "name1" ,name1, "name2", name2));
                return null;
            });
        }
    }

    private static void runQuery(final String query){
        try ( Session session = driver.session() )
        {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run( query);
                return null;
            });
        }
    }


    //This method was used to add 50 random user into the social network and to simulate their friendships
    public static void populateGraphUser(){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        List<Document> people = collection.find().projection(projectionFields).limit(50).into(new ArrayList<Document>());

        //Add into nodeDB

        String user;
        for(int i = 0; i < people.size(); i++) {
            user = (String) ((Document) people.get(i).get("login")).get("username");
            add(user,"User");
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
    }

    //This method belong to MongoDBManager
    public static List<String> getDecks(User user) {

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("yugioh");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("title"), Projections.include("creator"));
        List<Document> decks = collection.find(Filters.eq("creator", user.username)).projection(projectionFields).into(new ArrayList<Document>());

        List<String> deckList = new ArrayList<>();
        for(int i = 0; i < decks.size(); i++)
            deckList.add((String) decks.get(i).get("title"));

        return deckList;
    }

    //Parameter has to be changed to Deck deck
    //This method is used tho share a deck given a user and its deck
    public static void shareDeck(User user,String deck) throws DeckPresentException, DeckNotExistsException{
        // TODO: 25/12/2021 implement checkDeck and insert the class user!
        //if(checkDeck(deck){
        //throw new DeckPresentException();   <-- chek on neo4j if the deck is present
        //throw new DeckNotExistsException(); <-- check in the local list of deck's
        //  Both can be implemented only on checkDeck(deck);
        //}
        if(!user.checkDeck(deck)){
         throw new DeckNotExistsException();
        }
        if(checkSharedDeck(user,deck)){
            throw new DeckPresentException();
        }



        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
        "CREATE (u)-[:HAS_SHARED]->(d:Deck {title: \""+deck+"\"})";

        runQuery(query);
        System.out.println("Deck: " + deck + " shared!");

        // TODO: 25/12/2021 Add socialLayout.showSharedComplete()
        // socialLayout.showSharedComplete();

    }

    public static void follow(User user,User userToFollow) throws UserPresentException{
        // TODO: 26/12/2021 Implement checkFriend
        //if(checkUser(userToFollow){
        //throw new UserPresentException();   <-- chek on neo4j if the friend is present
        //throw new UserNotExistsException(); <-- check on mongo if the user exists
        //  Both can be implemented only on checkUser(userToFollow);
        //}

        if(checkFriendship(user,userToFollow)){
            throw new UserPresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
                "MATCH (utf:User {username: \""+userToFollow.username+"\"})" +
                "CREATE (u)-[:FOLLOWS]->(utf)";

        runQuery(query);
        System.out.println("Friend: " + userToFollow.username + " added!");

        // TODO: 26/12/2021 Implement socialLayout.showFollowComplete()
        // socialLayout.showFollowComplete();

    }

    public static boolean findUser(User user){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        Bson filter = Filters.and(Filters.eq("login.username",user.username));

        List<Document> res = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(res.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    //This method is used to check if the user that we want to add is already our friend
    private static boolean checkFriendship(User user, User possibleFriend){
        Session session = driver.session();
        List<Integer> friendship = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:FOLLOWS]->(uu:User {username: $username2})" +
                    " RETURN count(*) AS friendship",parameters("username",user.username, "username2", possibleFriend.username)  );
            if(result.hasNext()){
                Record r = result.next();
                friendship.add(r.get("friendship").asInt());
            }
            return null;
        });
        if(friendship.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    //This method is used to check if the user that we want to add is already our friend
    // TODO: 26/12/2021 Change deck with Deck Object
    private static boolean checkSharedDeck(User user, String deck){
        Session session = driver.session();
        List<Integer> deckshared = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:HAS_SHARED]->(d:Deck {title: $title})" +
                    " RETURN count(*) AS deckshared",parameters("username",user.username, "title", deck)  );
            if(result.hasNext()){
                Record r = result.next();
                deckshared.add(r.get("deckshared").asInt());
            }
            return null;
        });
        if(deckshared.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    //This method is used to retrieve the friends of a given user
    public static List<String> getFriends(User user){
        Session session = driver.session();
        List<String> friends = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:FOLLOWS]->(uu)" +
                    " RETURN uu.username AS username",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                friends.add(r.get("username").asString());
            }
            return null;
        });
        return friends;
    }


    //Main to test the functionalities
    public static void main(String[] args){

    }
}
