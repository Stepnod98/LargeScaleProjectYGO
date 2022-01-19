/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.DBManagers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;

import it.unipi.lsmsdb.yugiohdeckmaker.Controller.GUIManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import org.bson.Document;
import static com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.*;
import com.mongodb.client.model.Projections;
import java.util.*;
import org.bson.conversions.Bson;


public class MongoDBManager {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static{
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("test");
    }
    /* public Consumer<Document> printDocuments(){
         Consumer<Document> printDocuments = doc ->
         {System.out.println(doc.toJson());};
         collection.find().forEach(printDocuments);
         return printDocuments;
     }*/

    // TODO: 07/01/2022 testare in login
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

    public static boolean checkUser(String username){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.eq("login.username",username);

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(user.size() >= 1){
            return true;
        }else{
            return false;
        }
    }


    public static boolean checkEmail(String email){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.email"));
        Bson filter = Filters.eq("login.email",email);

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(user.size() >= 1){
            return true;
        }else{
            return false;
        }
    }



    public static String getImageUrl(Card card){

        MongoCollection<Document> cards = database.getCollection("cards");

        List<Document> cardUrldoc = cards.find(Filters.eq("title", card.getTitle())).into(new ArrayList<Document>());

        return cardUrldoc.get(0).getString("imageUrl");
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


    public static boolean existsDeck(String inTitle){
        MongoCollection<Document> deck = database.getCollection("yugioh");
        Bson filter = eq("title", inTitle);

        List<Document> decklist = deck.find(filter).into(new ArrayList<Document>());
        return decklist.size() == 1;
    }

    public static Deck findDeck(String t){
        MongoCollection<Document> deck = database.getCollection("yugioh");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {
            while(cursor.hasNext()) {
                Document doc = cursor.next();
                return new Deck(doc);
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    public static List<String> findCards(String inTitle){
        MongoCollection<Document> card = database.getCollection("cards");
        List<String> cardList = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            while(cursor.hasNext()){
                Document doc = cursor.next();
                cardList.add(new Card(doc).getTitle());
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return cardList;
    }

    public static List<String> findDecks(String inTitle){
        MongoCollection<Document> deck = database.getCollection("decks");
        List<String> deckList = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {

            while(cursor.hasNext()){
                Document doc = cursor.next();
                deckList.add(new Deck(doc).getTitle());
            }

        } catch (Exception ex) {ex.printStackTrace();}
        return deckList;
    }

    public static List<String> findMagicTraps(){
        // db.decks.find({types: {$exists:0}})
        MongoCollection<Document> card = database.getCollection("cards");
        List<String> cardList = new ArrayList<>();
        Bson filter = Filters.not(Filters.exists("types"));
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            while(cursor.hasNext()){
                Document doc = cursor.next();
                cardList.add(new Card(doc).getTitle());
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return cardList;
    }

    public static Card findCard(String t){
        MongoCollection<Document> card = database.getCollection("cards");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                return new Card(doc);
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    public static List findCard(int value, boolean type){ //false: def, true: atk
        MongoCollection<Document> card = database.getCollection("yugioh");
        List<String> cardlist = new ArrayList<>();
        if(type){
            Bson filter = eq("atk", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    cardlist.add(new Card(doc).getTitle());
                }
            } catch (Exception ex) {ex.printStackTrace();}
        }
        else{
            Bson filter = eq("def", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    cardlist.add(new Card(doc).getTitle());
                }
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
        List<Document> normalCards = card.find(filterForNormal).projection(projectionFields).into(new ArrayList<Document>());
        if(!normalCards.isEmpty()){
            return true;
        }
        return false;
    }

    public static void insertCard(String title, String imgurl, int atk, int def, int level, String desc, String type, String archetype, String attribute, String effectType){

        MongoCollection<Document> cards = database.getCollection("cards");
        Document card = new Document("title", title)
                .append("imgUrl", imgurl).append("atk", atk).append("level", level)
                .append("lore", desc).append("types", type)
                .append("archetypes", archetype).append("attribute", attribute)
                .append("effectTypes", effectType);
        cards.insertOne(card);
    }

    public static void addUser(User user){

        MongoCollection<Document> collection = database.getCollection("login");

        Document login = new Document("username", user.username).append("sha1", user.pwd);
        Document name = new Document("first", user.firstName).append("last", user.lastName);

        Document doc = new Document("name", name).append("email", user.email).append("login", login);

        collection.insertOne(doc);

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
        MongoCollection<Document> decks = database.getCollection("yugioh");
        Document deck = new Document("title", title)
                .append("creator", creator).append("cards", cardlist).append("extra_deck", ecardlist);
        decks.insertOne(deck);
    }

    // !!!!!!Aggregations!!!!!!!

    public static String findMostAtk(String setName){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        String result  = "";
        Bson u = unwind("$sets");
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
        Bson myMatch2 = Aggregates.match(Filters.eq("_id.set",setName));
        Bson p1 = project(fields(computed("_id", "$_id.set"),
                computed("cards", new Document("title", "$cards.card_Name")
                        .append("atk", "$cards.atk_Value")),
                computed("highestAtk",new BasicDBObject("$cmp", Arrays.asList( "$cards.atk_Value","$max"))))); //<------DA FINIRE?
        Bson filter = Filters.eq("highestAtk",0);
        Bson u2 = unwind("$cards");
        Bson myMatch3 = Aggregates.match(filter);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u,myMatch1,groupMultiple,myMatch2,u2,p1,myMatch3))
                .forEach(doc -> doclist.add(doc));

        for(int i = 0; i < doclist.size(); i++){
            result = ((Document)doclist.get(i).get("cards")).getString("title");
        }
        System.out.println(result);
        return result;
    }
    /*
    * db.decks.aggregate([{$unwind:"$sets"},
    * {$match: {$and: [{"atk": {$exists:true}},{"atk": {$ne:"?"}},{"atk": {$ne:"X000"}},{"atk": {$ne:null}}]}} ,
    * {$group:{_id:{ set: "$sets.setName"},cards:{$push: {card_Name:"$title", atk_Value:"$atk"}},max: { $max : "$atk" }}},
    * {$match: {"_id.set" : "Structure Deck: Invincible Fortress"}},
    * {$unwind:"$cards"},
    * {$project: {_id: "$_id.set", card:{title: "$cards.card_Name", atk:"$cards.atk_Value", highestAtk : {$cmp:["$cards.atk_Value", "$max"]}}}},
    * {$match:{ "card.highestAtk":{$eq:0}}}])
    * */



    public static List findMostAvgAtk(int th){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("$cards");
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
        decks.aggregate(Arrays.asList(u,myMatch1,groupMultiple,myMatch2))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(((Document)doclist.get(i).get("_id")).getString("deck"));
        }
        return decklist;
    }

    public static List<String> findTopXCards(int x){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        List<String> cardlist = new ArrayList<String>();
        Bson cards = unwind("$cards");
        Bson sortCards = sortByCount("$cards.title");
        Bson limit = limit(x);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(cards, sortCards, limit))
                .forEach(doc -> doclist.add(doc));
        System.out.println(doclist);
        for(int i = 0; i < doclist.size(); i++){
            cardlist.add(doclist.get(i).getString("_id"));
        }
        return cardlist;
    }

    public static List<String> findTopXECards(int x){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        List<String> cardlist = new ArrayList<String>();
        Bson cards = unwind("$extra_deck");
        Bson sortCards = sortByCount("$extra_deck.title");
        Bson limit = limit(x);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(cards, sortCards, limit))
                .forEach(doc -> doclist.add(doc));
        System.out.println(doclist);
        for(int i = 0; i < doclist.size(); i++){
            cardlist.add(doclist.get(i).getString("_id"));
        }
        return cardlist;
    }

    public static List<String> findMagicTrapDeck(){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.types");
        Bson myMatch = match(filterExistsType);
        Bson groupMultiple = new Document("$group",
                new Document("_id", new Document("deck", "$title"))
                        .append("cards", new Document("$push", new Document("card_Name", "$title")
                                .append("type", "$cards.types"))).append("total_count", new Document("$sum", 1)));
        Bson filterAtk = Filters.lt("total_count", 20);
        Bson myMatch2 = match(filterAtk);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u, myMatch, groupMultiple, myMatch2))
                .forEach(doc -> doclist.add(doc));
        System.out.println(doclist);
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(((Document)doclist.get(i).get("_id")).getString("deck"));
        }
        return decklist;
        //db.decks.aggregate([{$unwind: "$cards"},{$match:{"cards.types":{$exists:1}}},
        //{$group:{_id:{deck:"$title"}, cards:{$push:{card_name:"$cards.title", type: "$cards.types"}},
        //total_count:{$sum:1}}}, {$match:{total_count:{$lt:20}}}])
    }

    public static List<String> findArchetypeDeck(){
        MongoCollection<Document> decks = database.getCollection("yugioh");
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.archetypes");
        Bson myMatch = match(filterExistsType);
        Bson groupMultiple = new Document("$group",
                new Document("_id", new Document("title", "$title")
                        .append("creator", "$creator")).append("total_count", new Document("$sum", 1)));
        Bson mySort = sort(ascending("total_count"));
        Bson myLimit = limit(1);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u, myMatch, groupMultiple, mySort, myLimit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(((Document)doclist.get(i).get("_id")).getString("title"));
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
