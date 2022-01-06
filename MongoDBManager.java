/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.dii.ygodeckmaker;

import com.mongodb.BasicDBObject;
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
import static com.mongodb.client.model.Indexes.*;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.UpdateResult;
import java.util.*;
import static java.util.Locale.filter;
import org.bson.conversions.Bson;
//import org.bson.conversions.*;


/**
 *
 * @author Stefano
 */
public class MongoDBManager {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;
    private static MongoCollection<Document> userCollection;
    public MongoDBManager(){
        mongoClient=MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("decks");
    }
    public void connectMongo(){
        mongoClient=MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("yugioh");
        userCollection = database.getCollection("login");
       
    }
   /* public Consumer<Document> printDocuments(){
        Consumer<Document> printDocuments = doc ->
        {System.out.println(doc.toJson());};
        collection.find().forEach(printDocuments);
        return printDocuments;
    }*/ 
    public static boolean findUser(User user){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        Bson filter = Filters.and(Filters.eq("login.username",user.getUsername()));

        List<Document> res = collection.find(filter).projection(projectionFields).into(new ArrayList<>());

        if(user.getUsername().equals(GUIManager.getCurrentUser())){
            return false;
        }


        return res.size() == 1;
    }

    public static boolean checkUser(String username, String pwd){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.and(Filters.eq("login.username",username),Filters.eq("login.sha1",pwd));

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(user.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    public static List<String> getDecks(String username) {

        MongoCollection<Document> collection = database.getCollection("yugioh");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("title"), Projections.include("creator"));
        List<Document> decks = collection.find(Filters.eq("creator", username)).projection(projectionFields).into(new ArrayList<Document>());

        List<String> deckList = new ArrayList<>();
        for(int i = 0; i < decks.size(); i++)
            deckList.add((String) decks.get(i).get("title"));

        return deckList;
    }
    
    public static User findUser(String username){
        MongoCollection<Document> users = database.getCollection("profiles");
        Bson filter = eq("username", username);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            Document doc = cursor.next();
            return new User(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    
    public static boolean existsDeck(String inTitle){
       MongoCollection<Document> deck = database.getCollection("yugioh");
        Bson filter = eq("title", inTitle);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {
            Document doc = cursor.next();
            return true;
        } catch (Exception ex) {ex.printStackTrace();}
        return false;
    }
    
    public static Deck findDeck(String t){
        MongoCollection<Document> deck = database.getCollection("yugioh");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Deck(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }
    
    public static Card findCard(String t){
        MongoCollection<Document> card = database.getCollection("yugioh");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Card(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }
    
    public static List findCard(int value, boolean type){ //false: def, true: atk
        MongoCollection<Document> card = database.getCollection("yugioh");
        List<String> cardlist = new ArrayList<>();
        if(type){
            Bson filter = eq("atk", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                Document doc = cursor.next();
                cardlist.add(new Card(doc).getTitle());
            } catch (Exception ex) {ex.printStackTrace();}
        }
        else{
            Bson filter = eq("def", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                Document doc = cursor.next();
                cardlist.add(new Card(doc).getTitle());
            } catch (Exception ex) {ex.printStackTrace();}
        }
        return cardlist;
    }
    
    
    public static boolean checkCardType(String t){
        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("title"));
        MongoCollection<Document> card = database.getCollection("yugioh");
        Bson filterFusion = Filters.all("types", "Fusion");
        Bson filterLink = Filters.all("types", "Link");
        Bson filterSynchro = Filters.all("types", "Synchro");
        Bson filterXyz = Filters.all("types", "Xyz");

        //Filters for types
        Bson filterNotFusion = Filters.not(filterFusion);
        Bson filterNotLink = Filters.not(filterLink);
        Bson filterNotSynchro = Filters.not(filterSynchro);
        Bson filterNotXyz = Filters.not(filterXyz);
        Bson filterExistingTitle = Filters.exists("title");
        Bson filter = eq("title", t);

        //Merge all the filters
        Bson filterForNormal = Filters.and(filter, filterExistingTitle,filterNotFusion,filterNotLink,filterNotXyz,filterNotSynchro);
        List<Document> normalCards = collection.find(filterForNormal).projection(projectionFields).into(new ArrayList<Document>());
        if(normalCards != null){
            return true;
        }
        return false;
    }
    
    public static void insertCard(String title, String imgurl, int atk, int def, int level, String desc, String type, String archetype, String attribute, String effectType){
        
        MongoCollection<Document> cards = database.getCollection("decks");
        Document card = new Document("title", title)
                .append("imgUrl", imgurl).append("atk", atk).append("level", level)
                .append("lore", desc).append("types", type)
                .append("archetypes", archetype).append("attribute", attribute)
                .append("effectTypes", effectType);
        cards.insertOne(card);
    }
    
    public static void addUser(String username, String fName, String lName, String email, String pwd){
        MongoCollection<Document> users = database.getCollection("login");
        Document user = new Document("username", username)
                    .append("firstName", fName).append("lastName", lName).append("email", email)
                .append("password", pwd);
        users.insertOne(user);
    }
    
    public static void remove(String inTitle){
       MongoCollection<Document> decks = database.getCollection("yugioh");
       Bson filter = eq("title", inTitle);
       decks.deleteOne(filter);
       //decks.deleteOne(Filters.eq("title", inTitle));
    }
    
     public static void remove(String inTitle, String set){
       MongoCollection<Document> decks = database.getCollection("yugioh");
       Bson filterTitle = eq("title", inTitle);
       Bson filterSet = eq("title", set);
       decks.deleteOne(Filters.and(filterTitle, filterSet));
       //decks.deleteOne(Filters.eq("title", inTitle));
    }
    
    public static void saveDeck(Deck d){
        String title = d.getTitle();
        List<Card> cardlist = new ArrayList<Card>();
        List<Card> ecardlist = new ArrayList<Card>();
        cardlist = d.getCards();
        ecardlist = d.getECards();
        String creator = d.getCreator();
        MongoCollection<Document> decks = database.getCollection("decks");
        Document deck = new Document("title", title)
                    .append("creator", creator).append("cards", cardlist).append("extra_deck", ecardlist);
        Bson setter = set("deck", deck);
        UpdateResult result = decks.updateOne(null, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Deck update operation failed");
        }
    }
    
    // !!!!!!Aggregations!!!!!!!
    
    public static String findMostAtk(String setName){
        String result  = "";
        Bson u = unwind("sets");
        Bson existsAtk = Filters.exists("atk");
        Bson filterQM = Filters.eq("atk","?");
        Bson notQM = Filters.not(filterQM);
        Bson filterX = Filters.eq("atk","X000");
        Bson notX = Filters.not(filterX);
        Bson filterNull = Filters.eq("atk","null");
        Bson notNull = Filters.not(filterNull);
        Bson myMatch1 = Aggregates.match(Filters.and(existsAtk, notQM, notX, notNull));
        Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("set", "$sets.setName"))
            .append("cards", new Document("$push", new Document("card_Name", "$title")
            .append("atk_Value", "$atk"))).append("max", new Document("$max", "$atk")));
        Bson p1 = project(fields(computed("_id", "$_id.set"),
                computed("cards", new Document("title", "$cards.card_Name")
                .append("atk", "$cards.atk_Value")),
                computed("highestAtk",new BasicDBObject("$cmp", Arrays.asList( "$cards.atk_Value","$max"))))); //<------DA FINIRE?
        Bson filter = Filters.eq("highestAtk",0);
        Bson myMatch2 = Aggregates.match(filter);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(u,myMatch1,groupMultiple,myMatch2))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Card c = new Card(doclist.get(i));
            result = c.getTitle();
        }
        return result;
    }
    
    public static List findMostAvgAtk(int th){
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("cards");
        Bson existsAtk = Filters.exists("cards.atk");
        Bson filterQM = Filters.eq("atk","?");
        Bson notQM = Filters.not(filterQM);
        Bson filterX = Filters.eq("atk","X000");
        Bson notX = Filters.not(filterX);
        Bson filterNull = Filters.eq("atk","null");
        Bson notNull = Filters.not(filterNull);
        Bson myMatch1 = Aggregates.match(Filters.or(existsAtk, notQM, notX, notNull));
        Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("deck", "$title"))
            .append("avgAtk", new Document("$avg", "$cards.atk")));
        Bson filterAtk = Filters.gte("avgAtk", th);
        Bson myMatch2 = Aggregates.match(filterAtk);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(u,myMatch1,groupMultiple,myMatch2))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Deck d = new Deck(doclist.get(i));
            decklist.add(d.getTitle());
        }
        return decklist;
    }
    
    public static List<String> findTopXCards(int x){
        List<String> cardlist = new ArrayList<String>();
        Bson cards = unwind("cards");
        Bson sortCards = sortByCount("cards.title");
        Bson limit = limit(x);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(cards, sortCards, limit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Card c = new Card(doclist.get(i));
            cardlist.add(c.getTitle());
        }
        return cardlist;
    }
    
    public static List<String> findTopXECards(int x){
        List<String> cardlist = new ArrayList<String>();
        Bson u = unwind("extra_deck");
        Bson u2 = unwind("extra_deck.cards");
        Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("type", "$extra_deck.types")
            .append("title", "$extra_deck.title")));
        Bson filterFusion = Filters.all("types", "Fusion");
        Bson filterLink = Filters.all("types", "Link");
        Bson filterSynchro = Filters.all("types", "Synchro");
        Bson filterXyz = Filters.all("types", "Xyz");
        Bson filterForSpecial = Filters.or(filterFusion,filterLink,filterSynchro,filterXyz);
        Bson myMatch = match(filterForSpecial);
        Bson mySort = sortByCount("_id.title");
        Bson myLimit = limit(x);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(u, u2, groupMultiple, myMatch, mySort, myLimit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Card c = new Card(doclist.get(i));
            cardlist.add(c.getTitle());
        }
        //db.decks.aggregate([{$unwind: "$extra_deck"},{$unwind: "$extra_deck.types"},
        //{$group:{_id:{type:"$extra_deck.types", title: "$extra_deck.title"}}},
        //{$match:{$or:[ {"_id.type":"Synchro"}, {"_id.type":"Fusion"}, {"_id.type":"Xyz"},{"_id.type":"Link"}]}},
        //{$sortByCount:"$_id.title"},{$limit:10}]) 
        return cardlist;
    }
    
    public static List<String> findMagicTrapDeck(){
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("cards");
        Bson filterExistsType = Filters.exists("cards.types");
        Bson myMatch = match(filterExistsType);
        Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("deck", "$title"))
            .append("cards", new Document("$push", new Document("card_Name", "$title")
            .append("type", "$cards.types"))).append("total_count", new Document("$sum", 1)));
        Bson filterAtk = Filters.lt("total_count", 20);
        Bson myMatch2 = match(filterAtk);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(u, myMatch, groupMultiple, myMatch2))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Deck d = new Deck(doclist.get(i));
            decklist.add(d.getTitle());
        }
        return decklist;
        //db.decks.aggregate([{$unwind: "$cards"},{$match:{"cards.types":{$exists:1}}},
        //{$group:{_id:{deck:"$title"}, cards:{$push:{card_name:"$cards.title", type: "$cards.types"}},
        //total_count:{$sum:1}}}, {$match:{total_count:{$lt:20}}}])
    }
    
    public static List<String> findArchetypeDeck(){
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("cards");
        Bson filterExistsType = Filters.exists("cards.archetypes");
        Bson myMatch = match(filterExistsType);
        Bson groupMultiple = new Document("$group",
            new Document("_id", new Document("title", "$title"))
            .append("creator", "$creator").append("total_count", new Document("$sum", 1)));
        Bson mySort = sort(ascending("total_count"));
        Bson myLimit = limit(1);
        List<Document> doclist = new ArrayList<>();
        collection.aggregate(Arrays.asList(u, myMatch, groupMultiple, mySort, myLimit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            Deck d = new Deck(doclist.get(i));
            decklist.add(d.getTitle());
        }
        return decklist;
        //db.decks.aggregate([{$unwind:"$cards"},{$unwind: "$cards.archetypes"},
        //{$group:{_id:{title: "$title", creator:"$creator"}, total_count: {$sum:1}}},
        //{$sort:{total_count:1}},{$limit:1}])
    }
    
    public static void connectionCloser(){
        mongoClient.close();
    }
}
