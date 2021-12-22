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
    public static boolean existsDeck(String t){
        return false;
    }
    
    public static Deck findDeck(String t){
        Deck d = new Deck("");
        return d;
    }
    
    public static Card findCard(String t){
        Card c = new Card();
        return c;
    }
    
    public static boolean findUser(String name, String pwd){
        return false;
    }
    
    public static void add(String t, ArrayList<Card> cards){
        
    }
    
    public static void add(String username, String fName, String lName, String email, String pwd){
        
    }
    
    public static void remove(String t){
        
    }
    
    public static String findMostAtk(String setName){
        String t = "";
        return t;
    }
    
    public static String findRarest(String setName){
        String t = "";
        return t;
    }
    
    public static String findMostAtk(int x){
        String t = "";
        return t;
    }
    
    public static String findMostAvgAtk(int x){
        String t = "";
        return t;
    }
    
    public static List<String> findTopXCards(int x){
        List<String> cardlist = new ArrayList<String>();
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
