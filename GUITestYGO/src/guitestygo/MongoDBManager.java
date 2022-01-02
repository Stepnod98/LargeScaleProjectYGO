/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;
/*
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lte;
import org.bson.Document;
import java.util.function.Consumer;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.*;*/
import java.util.*;
//import org.bson.conversions.*;


/**
 *
 * @author Stefano
 */
public class MongoDBManager {
   /* private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    public MongoDBManager(){
        mongoClient=MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("cards");
    }*/
    /*public void connectMongo(){
        System.out.println(collection.countDocuments());
        Consumer<Document> printDocuments = doc ->
            {System.out.println(doc.toJson());};
        myColl.find().forEach(printDocuments);
       
    }*/
   /* public Consumer<Document> printDocuments(){
        Consumer<Document> printDocuments = doc ->
        {System.out.println(doc.toJson());};
        collection.find().forEach(printDocuments);
        return printDocuments;
    }*/ 
    
    public static List<String> browse(String t){
        List list = new ArrayList<String>();
        return list;
    }
    public static boolean existsDeck(String inTitle){
       /* JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({title:inTitle}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }
        if(itemsMatched != null){
            return true;
        }
        */
        return false;
    }
    
    public static Deck findDeck(String t){
        Deck d = new Deck("");
        /*JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({title:inTitle}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }
        if (itemsMatched != null) { 
           for (int i=0;i<jArray.length();i++){ 
                JsonObject decks = (JsonObject) jArray.get(i); 
                 String issues_key = (String) issues.get("key").toString();
                String project_name = (String) issues.get("name").toString(); 
                d.add(jArray.getString(i));
           } 
        }
        */
        /*
        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("title"));
        Bson filterNotFusion = Filters.not(filterFusion);
        Bson filterNotLink = Filters.not(filterLink);
        Bson filterNotSynchro = Filters.not(filterSynchro);
        Bson filterNotXyz = Filters.not(filterXyz);
        Bson filterExistingTitle = Filters.exists("title");

        //Merge all the filters
        //Bson filterForNormal = Filters.and(filterExistingTitle,filterNotFusion,filterNotLink,filterNotXyz,filterNotSynchro);
        List<Document> deckList = collection.find(filterExistingTitle).projection(projectionFields).into(new ArrayList<Document>());
        */return d;
    }
    
    public static Card findCard(String t){
        Card c = new Card();
        return c;
    }
    
    public static Card findCard(int value, boolean type){ //false: def, true: atk
        Card c = new Card();
        return c;
    }
    
    public static boolean findUser(String name, String pwd){
        return false;
    }
    
    public static boolean checkCardType(String t){
        return false;
    }
    
    public static void add(String t, ArrayList<Card> cards){
        
    }
    
    public static void add(String username, String fName, String lName, String email, String pwd){
        
    }
    
    public static void remove(String inTitle){
        /*try (MongoCursor<Document> removeRes= collection.remove({title: inTitle})
        {
            return remoteRes.nRemoved;
        }
        catch(Exception e)
        {
            return -1;
        }*/
    }
    
    public static String findMostAtk(String setName){
        String t = "";
        return t;
    }
    
    /*public static String findRarest(String setName){
        String t = "";
        
        return t;
    }*/
    
    public static String findMostAtk(int th){
        String t = "";
        
        return t;
    }
    
    public static List findMostAvgAtk(int th){
        List<String> cardlist = new ArrayList<String>();
        
        /*
        if (itemsMatched != null) { 
           for (int i=0;i<jArray.length();i++){ 
            cardlist.add(jArray.getString(i));
           } 
        } */
        return cardlist;
    }
    
    public static List<String> findTopXCards(int x){
        List<String> cardlist = new ArrayList<String>();
        
        /*
        if (itemsMatched != null) { 
           for (int i=0;i<jArray.length();i++){ 
            cardlist.add(jArray.getString(i));
           } 
        } */
        return cardlist;
    }
    
    public static List<String> findTopXECards(int x){
        List<String> cardlist = new ArrayList<String>();
        
        return cardlist;
    }
    
    public static List<String> findMagicTrapDeck(){
        List<String> decklist = new ArrayList<String>();
        
        return decklist;
    }
    
    public static List<String> findArchetypeDeck(){
        List<String> decklist = new ArrayList<String>();
        
        return decklist;
    }
    
    public static void saveDeck(Deck d){
        String title = d.getTitle();
        List<Card> cardlist = new ArrayList<Card>();
        List<Card> ecardlist = new ArrayList<Card>();
        cardlist = d.getCards();
        ecardlist = d.getECards();
        String creator = d.getCreator();
    }
    
     /*
    Card Mongo operations
     */

   /* public static JSONArray findCardByTitle(String inTitle) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({title:inTitle}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static JSONArray findCardByStats(int valueAtk, int valueDef) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({$and: [{atk: valueAtk},{def: valueDef}]}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static JSONArray findCardBySetName(String inSetName) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({"sets.setName":inSetName}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static boolean RemoveCardByTitle(String inCardTitle) {
        try (MongoCursor<Document> cursor = collection.deleteMany({"cards.title": inCardTitle) }))
        {
            collection.remove({title: inCardTitle) })
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }*/

    /*
    Deck Mongo operations
     */

   /* public JSONArray findDeckByCard(String inCardTitle) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({"cards.title": inCardTitle}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static JSONArray findDeckByTitle(String inDeckTitle) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({creator:inDeckTitle}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static JSONArray findDeckByCreator(String inCreatorName) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = collection.find({creator:inCreatorName}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }

    public static int RemoveDeckByTitle(String inDeckTitle) {
        try (MongoCursor<Document> removeRes= collection.remove({title: inDeckTitle})
        {
            return remoteRes.nRemoved;
        }
        catch(Exception e)
        {
            return -1;
        }
    }

    /*
    User Mongo operations
     */

   /* public static JSONArray findUserByUsername(String inUsername) {
        JSONArray itemsMatched = new JSONArray();
        try (MongoCursor<Document> cursor = loginCollection.find({"login.username": "inUserName"}).iterator())
        {
            while (cursor.hasNext())
            {
                itemsMatched.put(cursor.next().toJson());
            }
        }

        return itemsMatched;
    }*/
    
    /*
    Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        Bson projectionFields2 = Projections.fields(Projections.excludeId(),Projections.include("title"));

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
    
    */
    
   /* public void executeQuery(){
        if(query1){
            try (MongoCursor<Document> cursor = 
            collection.find(and(gt("i", 50), lte("i", 100))).iterator())
            {
                while (cursor.hasNext())
                {
                System.out.println(cursor.next().toJson());
                }
            }
        }
        else if(query2){
            try (MongoCursor<Document> cursor = 
            collection.find(and(gt("i", 50), lte("i", 100))).iterator())
            {
                while (cursor.hasNext())
                {
                System.out.println(cursor.next().toJson());
                }
            }
        }
        else if(aggr1){
            Bson myMatch = match(eq("city", "NEW YORK"));
            Bson myGroup = group("$city", sum("count", 1L));
            collection.aggregate(Arrays.asList(myMatch, myGroup))
            .forEach(printDocuments());
        }
         else if(aggr1){
             Bson myMatch = Aggregates.match(Filters.eq("city", "NEW YORK"));
             Bson myGroup = Aggregates.group("$city", Accumulators.sum("count", 1));
        }
         else if(aggr2){
             Bson groupSingle = group("$city", sum("totPop", "$pop"));
             Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("city", "$city")
            .append("state", "$state"))
            .append("totalPop", new Document("$sum", "$pop")));
             Bson p1 = project(fields(include("city")));
            Bson p2 = project(fields(
            excludeId(),include("city", "state")));
            Bson p3 = project(fields(
            include("city"), exclude("state")));
            Bson p4 = project(fields(
            computed("myID", "_id"),include("city")));
            Bson s1 = sort(descending("pop"));
            collection.aggregate(Arrays.asList(s1))
            .forEach(printDocuments());
            //Find the 2nd and 3rd biggest cities
            Bson mySort = sort(descending("pop"));
            Bson myLimit = limit(2);
            Bson mySkip = skip(1);
            collection.aggregate(Arrays.asList(mySort, mySkip, myLimit))
            .forEach(printDocuments());
            //Find the average score for the American cusine
            Bson m = match(eq("cusine", "American"));
            Bson u = unwind("grades");
            Bson g = group("$cusine", avg("avgGrade","$grades.score"));
            collection.aggregate(Arrays.asList(m, u, g))
            .forEach(printDocuments());*/
            /*
            To create an index on a field or fields, pass an index specification document to the 
            createIndex() method.
            An index key specification document contains the fieldsto index and the index 
            type for each field:
            new Document(<field1>, <type1>).append(<field2>, <type2>) ...
            For an ascending index type, specify 1 for <type>.
            For a descending index type, specify -1 for <type>.
            The following example creates an ascending index on the i field:
            collection.createIndex(new Document("city", 1));
            */
        // }
}
