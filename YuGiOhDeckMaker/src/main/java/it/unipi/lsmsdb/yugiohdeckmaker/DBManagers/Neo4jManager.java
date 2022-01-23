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


    public static void delete(User user){
        String query = "MATCH(u:User {username: \""+user.username+"\"}) DETACH DELETE u";
        runQuery(query);
        //System.out.println(user.username + " deleted on the social!");
    }

    public static void delete(Deck deck){
        String query = "MATCH(u:Deck {title: \""+deck.getTitle()+"\"}) DETACH DELETE u";
        runQuery(query);
        //System.out.println(deck.getTitle() + " deleted on the social!");
    }

    public static void updateUser(String oldUsername, String newUsername){
        String query = "MATCH(u:User {username: \""+oldUsername+"\"})" +
                " SET u.username = \""+newUsername+"\"";
        System.out.println(query);
        runQuery(query);
    }

    public static void updateUserDecks(String oldUsername, String newUsername){
        String query = "MATCH(u:User {username: \""+oldUsername+"\"})-[:HAS_SHARED]->(d)" +
            " SET d.creator = \""+newUsername+"\"";
        System.out.println(query);
        runQuery(query);
    }

    public static void updateDeck(String oldTitle, String newTitle){
        String query = "MATCH(d:Deck {title: \""+oldTitle+"\"})" +
                " SET d.title = \""+newTitle+"\"";
        runQuery(query);
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

        if(MongoDBManager.findYourDecks(deck.getTitle()).isEmpty()){
         throw new DeckNotExistsException();
        }
        if(checkSharedDeck(user,deck)){
            throw new DeckPresentException();
        }

        String query = "MATCH (u:User {username: \""+user.username+"\"})\n" +
        "CREATE (u)-[:HAS_SHARED]->(d:Deck " +
                "{title: \""+deck.getTitle()+"\", " +
                "creator: \""+user.username+"\", " +
                "publicationDate: date.statement()}) ";

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
    public static boolean checkSharedDeck(User user, Deck deck){
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

    public static List<String> getRecentSharedDecks(User user){
        Session session = driver.session();
        List<String> recentDecks = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run("""
                    MATCH(d:Deck {publicationDate: date.statement()})
                    <-[:HAS_SHARED]-()<-[:FOLLOWS]-(u:User {username: $username})
                     RETURN d.title AS title""",parameters("username",user.username)  );
            while(result.hasNext()){
                Record r = result.next();
                recentDecks.add(r.get("title").asString());
            }
            return null;
        });

        return recentDecks;
    }



    public static List<String> browseUsers(String str){
        Session session = driver.session();
        List<String> users = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                      "MATCH(u:User) " +
                            "WHERE u.username CONTAINS \""+str+"\" " +
                            "RETURN u.username AS username " +
                              "ORDER BY username LIMIT 10");
            while(result.hasNext()){
                Record r = result.next();
                users.add(r.get("username").asString());
            }
            return null;
        });

        return users;
    }

    public static List<String> browseDecks(String str){
        Session session = driver.session();
        List<String> users = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "MATCH(d:Deck) " +
                            "WHERE d.title CONTAINS \""+str+"\" " +
                            "RETURN d.title AS title LIMIT 5");
            while(result.hasNext()){
                Record r = result.next();
                users.add(r.get("title").asString());
            }
            return null;
        });

        return users;
    }

    public static List<String> browseYourDecks(String username, String str){
        Session session = driver.session();
        List<String> users = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "MATCH(d:Deck) " +
                            "WHERE d.creator = \""+ username +"\" AND d.title CONTAINS \""+str+"\" " +
                            "RETURN d.title AS title LIMIT 5");
            while(result.hasNext()){
                Record r = result.next();
                users.add(r.get("title").asString());
            }
            return null;
        });

        return users;
    }

    public static List<String> getFollowers(String username){

        Session session = driver.session();
        List<String> followers = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User)-[:FOLLOWS]->(u2:User {username: $username }) " +
                            "RETURN u.username AS username"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                followers.add( r.get("username").asString());
            }
            return null;
        });

        return followers;
    }

    public static int getCountFollowers(String username){

        Session session = driver.session();
        final Integer[] followers = {0};
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User)-[:FOLLOWS]->(u2:User {username: $username }) " +
                            "RETURN COUNT(*) AS followers"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                followers[0] = r.get("followers").asInt();
            }
            return null;
        });

        return followers[0];
    }


    public static List<String> getSharedDecks(String username){

        Session session = driver.session();
        List<String> sharedDecks = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User {username: $username })-[:HAS_SHARED]->(d:Deck) " +
                            "RETURN d.title AS title"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                sharedDecks.add(r.get("title").asString());
            }
            return null;
        });

        return sharedDecks;
    }

    public static int getCountSharedDecks(String username){

        Session session = driver.session();
        final Integer[] sharedDecks = {0};
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User)-[:HAS_SHARED]->(d:Deck {creator: $username }) " +
                            "RETURN COUNT(*) AS sharedDecks"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                sharedDecks[0] = r.get("sharedDecks").asInt();
            }
            return null;
        });

        return sharedDecks[0];
    }

    public static List<String> getLikedDecks(String username){

        Session session = driver.session();
        List<String> likedDecks = new ArrayList<>();
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User {username: $username })-[:LIKES]->(d:Deck) " +
                            "RETURN d.title AS title"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                likedDecks.add(r.get("title").asString());
            }
            return null;
        });

        return likedDecks;
    }

    public static int getTotalLikes(String username){

        Session session = driver.session();
        final Integer[] totalLikes = {0};
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match(u:User)-[:LIKES]->(d:Deck {creator: $username}) " +
                            "RETURN COUNT(*) AS totalLikes"
                    ,parameters("username",username)
            );
            while(result.hasNext()){
                Record r = result.next();
                totalLikes[0] = r.get("totalLikes").asInt();
            }
            return null;
        });

        return totalLikes[0];
    }

    public static String getCreator(String title){
        Session session = driver.session();
        final String[] creator = new String[1];
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "Match (d:Deck {title: $title}) " +
                            "RETURN d.creator AS creator"
                    ,parameters("title",title)
            );
            while(result.hasNext()){
                Record r = result.next();
                creator[0] = r.get("creator").asString();
            }
            return null;
        });

        return creator[0];

    }

    public static int getLikes(String title){

        Session session = driver.session();
        final Integer[] likes = {0};
        session.readTransaction((TransactionWork<List<String>>) tx ->{
            Result result = tx.run(
                    "MATCH(d:Deck {title: $title})<-[:LIKES]-(u:User) " +
                            "RETURN count(*) AS likes"
                    ,parameters("title",title)
            );
            while(result.hasNext()){
                Record r = result.next();
                likes[0] = r.get("likes").asInt();
            }
            return null;
        });

        return likes[0];
    }

    public static void main(String[] args){

        //System.out.println(Neo4jManager.getRecentSharedDecks(new User("fabi8")));
        Neo4jManager.delete(new Deck("prova2"));
    }
}
