package it.unipi.lsmsdb.yugiohdeckmaker.DBManagers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import it.unipi.lsmsdb.yugiohdeckmaker.Exceptions.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.neo4j.driver.Values.parameters;

public class Neo4jManager {

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

    // TODO: 05/01/2022 ADD to mongodb when delete an user
    public static void delete(User user){
        String query = "MATCH(u:User {username: \""+user.username+"\"}) DETACH DELETE u";
        runQuery(query);
        System.out.println(user.username + " deleted on the social!");
    }

    // TODO: 05/01/2022 ADD to mongodb when delete a deck
    public static void delete(Deck deck){
        String query = "MATCH(u:Deck {title: \""+deck.getTitle()+"\"}) DETACH DELETE u";
        runQuery(query);
        System.out.println(deck.getTitle() + " deleted on the social!");
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
        List<Document> people = collection.find().projection(projectionFields).limit(50).into(new ArrayList<>());

        //Add into nodeDB

        String user;
        for (Document person : people) {
            user = (String) ((Document) person.get("login")).get("username");
            add(user, "User");
        }

        for(int i = 0; i < people.size(); i++){
            user = (String) ((Document) people.get(i).get("login")).get("username");
            StringBuilder matchQuery = new StringBuilder("MATCH(u:User {username: \"" + user + "\"})");
            StringBuilder mergeQuery = new StringBuilder("CREATE ");
            int randomFriendsNumb = new Random().nextInt(9) + 1;
            System.out.println(user + " has " + randomFriendsNumb + " friends");
            List<String> friends = new ArrayList<>();
            for( int j = 0; j < randomFriendsNumb; j++){
                String friend =  (String) ((Document) people.get(new Random().nextInt(people.size())).get("login")).get("username");
                if(friend.equals(user) || friends.contains(friend))
                    continue;
                friends.add(friend);
                matchQuery.append(",\n(u").append(j).append(":User {username: \"").append(friend).append("\"})");

                if(j == 0) {
                    mergeQuery.append("(u)-[:FOLLOWS]->(u").append(j).append(")");
                }else{
                    if(mergeQuery.toString().equals("CREATE ")) {
                        mergeQuery.append("(u)-[:FOLLOWS]->(u").append(j).append(")");
                    }else{
                        mergeQuery.append(",\n(u)-[:FOLLOWS]->(u").append(j).append(")");
                    }
                }
            }
            System.out.println(matchQuery+"\n"+mergeQuery);
            runQuery(matchQuery+"\n"+mergeQuery);
        }
    }


    //This method is used tho share a deck given a user and its deck
    public static void shareDeck(User user,Deck deck) throws DeckPresentException, DeckNotExistsException {

        if(!user.checkDeck(deck.getTitle())){
         throw new DeckNotExistsException();
        }
        if(checkSharedDeck(user,deck)){
            throw new DeckPresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
        "CREATE (u)-[:HAS_SHARED]->(d:Deck {title: \""+deck.getTitle()+"\", creator: \""+user.username+"\"}) ";

        runQuery(query);

    }


    public static void likeDeck(User user,Deck deck) throws LikePresentException, DeckYoursException {

        if(user.checkDeck(deck.getTitle())){
            throw new DeckYoursException();
        }
        if(checkLikedDeck(user,deck)){
            throw new LikePresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
                "MATCH(d:Deck {title: \""+deck.getTitle()+"\"})" +
                "CREATE (u)-[:LIKES]->(d) ";

        runQuery(query);

    }

    public static void unlikeDeck(User user,Deck deck) throws LikeNotPresentException {

        if(!checkLikedDeck(user,deck)){
            throw new LikeNotPresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
                "MATCH(d:Deck {title: \""+deck.getTitle()+"\"})" +
                "MATCH (u)-[l:LIKES]->(d)\n" +
                "DELETE l";

        runQuery(query);

    }

    public static void follow(User user,User userToFollow) throws UserPresentException, UserNotExistsException{

        if(!MongoDBManager.findUser(userToFollow)){
            throw new UserNotExistsException();
        }

        if(checkFriendship(user,userToFollow)){
            throw new UserPresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
                "MATCH (utf:User {username: \""+userToFollow.username+"\"})" +
                "CREATE (u)-[:FOLLOWS]->(utf)";

        runQuery(query);

    }

    //This method is used to remove the relationship FOLLOWS between two users
    public static void unfollow(User user, User userToUnfollow) throws FriendshipNotFoundException {


        //Check on the graphDB if there is a friendship relation
        if(!checkFriendship(user,userToUnfollow)){
            throw new FriendshipNotFoundException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
                "MATCH (utu:User {username: \""+userToUnfollow.username+"\"})" +
                "MATCH (u)-[f:FOLLOWS]->(utu)\n"+
                "DELETE f";

        runQuery(query);

    }



    //This method search the given deck into the social! Then return a true if is present
    //to give the possibility to like the deck!
    public static boolean findDeckOnSocial(User user,Deck deck){
        Session session = driver.session();
        List<String> res = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(d:Deck {title: $title}) WHERE d.creator <> $username " +
                    " RETURN d.title AS title",parameters("title",deck.getTitle(), "username", user.username)  );
            if(result.hasNext()){
                Record r = result.next();
                res.add(r.get("title").asString());
            }
            return null;
        });
        return res.size() == 1;
    }


    //This method is used to check if the user that we want to add is already our friend
    public static boolean checkFriendship(User user, User possibleFriend){
        Session session = driver.session();
        List<String> friendship = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:FOLLOWS]->(uu:User {username: $username2})" +
                    " RETURN uu.username AS friend",parameters("username",user.username, "username2", possibleFriend.username)  );
            if(result.hasNext()){
                Record r = result.next();
                friendship.add(r.get("friend").asString());
            }
            return null;
        });
        return friendship.size() == 1;
    }


    //This method is used to check if the user that we want to add is already our friend
    private static boolean checkSharedDeck(User user, Deck deck){
        Session session = driver.session();
        List<String> deckShared = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:HAS_SHARED]->(d:Deck {title: $title})" +
                    " RETURN d.title AS deckShared",parameters("username",user.username, "title", deck.getTitle())  );
            if(result.hasNext()){
                Record r = result.next();
                deckShared.add(r.get("deckShared").asString());
            }
            return null;
        });
        return deckShared.size() == 1;
    }


    //This method is used to check if the user that we want to add is already our friend
    public static boolean checkLikedDeck(User user, Deck deck){
        Session session = driver.session();
        List<String> deckLiked = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(" MATCH(u:User {username: $username})-[:LIKES]->(d:Deck {title: $title})" +
                    " RETURN d.title AS deckLiked",parameters("username",user.username, "title", deck.getTitle())  );
            if(result.hasNext()){
                Record r = result.next();
                deckLiked.add(r.get("deckLiked").asString());
            }
            return null;
        });
        return deckLiked.size() == 1;
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


    //Return a list that contains the recommended users based on the strength defined as the number of your
    //friends that follows a user, so return the 5 most followed users among your friends. If the list is empty return
    //the 5 most famous users in all the network!
    public static List<String> getRecommendedUsers(User user){

        List<String> recommendedUsers = new ArrayList<>();
        Session session = driver.session();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run("""
                    MATCH(u1:User {username: $username})-[:FOLLOWS]->(friends)-[:FOLLOWS]->(fof)
                    WHERE NOT (u1)-[:FOLLOWS]->(fof) AND u1 <>fof
                    RETURN fof.username AS username, COUNT(*) AS Strength
                    ORDER BY Strength DESC\s
                    LIMIT 5""",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                recommendedUsers.add(r.get("username").asString());
            }
            return null;
        });

        if(recommendedUsers.size() == 0){
            return getMostFollowedUsers(user);
        }
        return recommendedUsers;
    }


    //Return a list that contains the 5 most followed users in the network
    public static List<String> getMostFollowedUsers(User user){

        List<String> recommendedUsers = new ArrayList<>();
        Session session = driver.session();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run("""
                    MATCH(u:User {username: $username})
                    MATCH(u1:User)-[f:FOLLOWS]->(u2)\s
                    WHERE NOT (u)-[:FOLLOWS]->(u2) AND u <>u2
                    RETURN u2.username AS username, COUNT(*) AS Followers
                    ORDER BY Followers DESC
                    LIMIT 5""",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                recommendedUsers.add(r.get("username").asString());
            }
            return null;
        });

        return recommendedUsers;
    }


    //Return a list that contains the recommended decks based on the strength defined as the number of
    //likes of your friends, so return the 5 most liked decks among your friends. If the list is empty return
    //the 5 most liked decks in all the network!
    public static List<String> getRecommendedDecks(User user){

        Session session = driver.session();
        List<String> recommendedDecks = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run("""
                    MATCH(u1:User {username: $username})-[:FOLLOWS]->(friends:User)
                    MATCH(friends)-[:LIKES]->(dSugg:Deck)\s
                    WHERE dSugg.creator <> u1.username AND\s
                    NOT (u1)-[:LIKES]->(dSugg)
                    RETURN  dSugg.title AS title, count(*) AS Strength\s
                    ORDER BY Strength DESC
                    LIMIT 5""",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                recommendedDecks.add(r.get("title").asString());
            }
            return null;
        });

        if(recommendedDecks.size() == 0){
            return getMostLikedDecks(user);
        }
        return recommendedDecks;
    }

    public static List<String> getMostLikedDecks(User user){
        Session session = driver.session();
        List<String> recommendedDecks = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run("""
                    MATCH(u:User {username: $username})
                    MATCH(u1:User)-[:LIKES]->(d)\s
                    WHERE d.creator <> u.username AND NOT (u)-[:LIKES]->(d)
                    RETURN d.title AS title, COUNT(*) AS likes
                    ORDER BY likes DESC
                    LIMIT 5""",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                recommendedDecks.add(r.get("title").asString());
            }
            return null;
        });

        return recommendedDecks;
    }

}
