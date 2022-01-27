/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.DBManagers;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.*;

import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import it.unipi.lsmsdb.yugiohdeckmaker.Controller.GUIManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Controller.LoginManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import org.bson.Document;
import static com.mongodb.client.model.Aggregates.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.*;
import static com.mongodb.client.model.Projections.*;

import com.mongodb.client.model.Projections;
import java.util.*;
import org.bson.conversions.Bson;


public class MongoDBManager {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static{
        mongoClient = MongoClients.create("mongodb://localhost:27018,localhost:27019,localhost:27020");
        database = mongoClient.getDatabase("examtest");
    }

    // TODO: 07/01/2022 testare in login
    public static boolean findUser(User user){

        MongoCollection<Document> collection = database.getCollection("users");

        Bson projectionFields = fields(Projections.excludeId(),Projections.include("login.username"));
        Bson filter = Filters.and(Filters.eq("login.username",user.getUsername()));

        List<Document> res = collection.find(filter).projection(projectionFields).into(new ArrayList<>());

        if(user.getUsername().equals(GUIManager.getCurrentUser())){
            return false;
        }

        return res.size() >= 1;
    }

    public static boolean checkUser(String username, String pwd){

        MongoCollection<Document> collection = database.getCollection("users");

        Bson projectionFields = fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.and(Filters.eq("login.username",username),Filters.eq("login.sha1",pwd));

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(user.size() == 1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean checkUser(String username){

        MongoCollection<Document> collection = database.getCollection("users");

        Bson projectionFields = fields(Projections.excludeId(),Projections.include("login.username","login.password"));
        Bson filter = Filters.eq("login.username",username);

        List<Document> user = collection.find(filter).projection(projectionFields).into(new ArrayList<Document>());

        if(user.size() >= 1){
            return true;
        }else{
            return false;
        }
    }


    public static boolean checkEmail(String email){

        MongoCollection<Document> collection = database.getCollection("users");

        Bson projectionFields = fields(Projections.excludeId(),Projections.include("login.email"));
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

        MongoCollection<Document> users = database.getCollection("users");
        Bson filter = eq("login.username", username);
        Bson filter2 = Filters.exists("decks");
        Bson filter3 = Filters.and(filter, filter2);
        List<String> deckList = new ArrayList<>();
        try (MongoCursor<Document> cursor = users.find(filter3).iterator()) {
            if(cursor.hasNext()) {
                Document doc = cursor.next();
                deckList = doc.get("decks", List.class);
            }
            return deckList;
        } catch (Exception ex) {ex.printStackTrace();}

        return null;
    }

    public static List<String> getTips(String title){
        MongoCollection<Document> cards = database.getCollection("cards");
        Bson filter = eq("title", title);
        Bson filter2 = Filters.exists("tips");
        Bson filter3 = Filters.and(filter, filter2);
        List<String> tips = new ArrayList<>();
        try (MongoCursor<Document> cursor = cards.find(filter3).iterator()) {
            if(cursor.hasNext()) {
                Document doc = cursor.next();
                List<Document> documentList = doc.get("tips", List.class);
                for (int i = 0; i < documentList.size(); i ++) {
                    tips.add(documentList.get(i).getString("value"));
                }
                return tips;
            }

        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    // TODO: 22/01/2022 Togliere i deck dal db quando rimuovo l'utente
    /*public static List<String> getDecks(String username) {

        MongoCollection<Document> collection = database.getCollection("users");

        List<String> user;
        Bson filter = Filters.eq("login.username", username);
        try (MongoCursor<Document> cursor = collection.find(filter).iterator()) {
            if(cursor.hasNext()){
                Document doc = cursor.next();
                for(int i = 0; doc.get("decks")){

                }
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return cardList;

        List<String> deckList = new ArrayList<>();
        for(int i = 0; i < user.size(); i++)
            deckList.add((String) decks.get(i).get("title"));

        return deckList;
    }*/

    // TODO: 22/01/2022 Testare 
    public static List<String> getCards(String title) { //DA TESTARE

        Deck d = findDeck(title);
        List<String> cardTitles = new ArrayList<>();
        cardTitles.addAll(d.getCardsTitles());
        cardTitles.addAll(d.getECardsTitles());
        return cardTitles;
    }

    // TODO: 22/01/2022 Testare
    public static String getCreator(String title) { //DA TESTARE

        MongoCollection<Document> collection = database.getCollection("decks");

        Bson projectionFields = fields(Projections.excludeId(), Projections.include("title"), Projections.include("creator"));
        List<Document> decks = collection.find(Filters.eq("title", title)).projection(projectionFields).into(new ArrayList<Document>());

        return decks.get(0).getString("creator");
    }

    // TODO: 22/01/2022 Testare
    public static String getDesc(Card card){ //DA TESTARE

        MongoCollection<Document> cards = database.getCollection("cards");

        List<Document> cardUrldoc = cards.find(Filters.eq("title", card.getTitle())).into(new ArrayList<Document>());

        return cardUrldoc.get(0).getString("lore");
    }

    public static boolean existsDeck(String inTitle){
        MongoCollection<Document> deck = database.getCollection("decks");
        Bson filter = eq("title", inTitle);

        List<Document> decklist = deck.find(filter).into(new ArrayList<Document>());
        return decklist.size() == 1;
    }

    public static Deck findDeck(String t){
        MongoCollection<Document> deck = database.getCollection("decks");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {
            if(cursor.hasNext()) {
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
                //System.out.println(doc.getString("title"));
                cardList.add(new Card(doc).getTitle());
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return cardList;
    }

    public static List<String> findUsers(String username){
        MongoCollection<Document> users = database.getCollection("users");
        List<String> usernameList = new ArrayList<>();
        Bson filter = regex("login.username", username);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            while(cursor.hasNext()){
                Document doc = cursor.next();
                //System.out.println(doc.getString("login.username"));
                usernameList.add(((Document)doc.get("login")).getString("username"));
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return usernameList;
    }

    public static List<String> findYourDecks(String inTitle){
        MongoCollection<Document> deck = database.getCollection("decks");
        List<String> deckList = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        Bson filter2 = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson filter3 = Filters.and(filter, filter2);
        try (MongoCursor<Document> cursor = deck.find(filter3).iterator()) {

            while(cursor.hasNext()){
                Document doc = cursor.next();
                deckList.add(new Deck(doc).getTitle());
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return deckList;
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
        MongoCollection<Document> card = database.getCollection("cards");
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
        Bson projectionFields = fields(Projections.excludeId(),Projections.include("title"));
        MongoCollection<Document> card = database.getCollection("cards");
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

    // TODO: 22/01/2022 Testare
    public static void insertCard(String title, String imgurl, int atk, int def, int level, String desc, String type, String archetype, String attribute, String effectType){

        MongoCollection<Document> cards = database.getCollection("cards");

        List<String> typeList = new ArrayList(Arrays.asList(type.split(",")));
        for(int i = 0; i < typeList.size(); i++){
            typeList.set(i, typeList.get(i).trim());
        }
        List<String> archetypeList = new ArrayList(Arrays.asList(archetype.split(",")));
        for(int i = 0; i < archetypeList.size(); i++){
            archetypeList.set(i, archetypeList.get(i).trim());
        }
        List<String> effectTypeList = new ArrayList(Arrays.asList(effectType.split(",")));
        for(int i = 0; i < effectTypeList.size(); i++){
            effectTypeList.set(i, effectTypeList.get(i).trim());
        }
        Document card = new Document("title", title)
                .append("imgUrl", imgurl).append("atk", atk)
                .append("def", def).append("level", level)
                .append("lore", desc).append("types",typeList)
                .append("archetypes", archetypeList)
                .append("attribute", attribute)
                .append("effectTypes", effectTypeList);
        cards.insertOne(card);
    }

    public static void addUser(User user){

        MongoCollection<Document> collection = database.getCollection("users");

        Document login = new Document("username", user.username)
                .append("sha1", user.pwd);
        Document name = new Document("first", user.firstName)
                .append("last", user.lastName);

        Document doc = new Document("name", name)
                .append("email", user.email).append("login", login);

        collection.insertOne(doc);

    }

    // TODO: 22/01/2022 Testare 
    public static void removeCard(String inTitle){
        MongoCollection<Document> cards = database.getCollection("cards");
        Bson filter = eq("title", inTitle);
        Bson u = unwind("$decks");
        Bson myMatch = match(filter);
        List<Document> doclist = new ArrayList<>();
        cards.aggregate(Arrays.asList(myMatch, u)).forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            String current = ((Document)doclist.get(i)).getString("decks");
            removeCardFromDeck(current, inTitle);
        }
        cards.deleteOne(filter);
    }

    public static void removeCardFromDeck(String deckTitle, String cardTitle){
        MongoCollection<Document> decks = database.getCollection("decks");
        Document query = new Document().append("title",  deckTitle);
        Bson update = Updates.pull("cards", new Document("title", cardTitle));
        try {
            UpdateResult result = decks.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void removeDeck(String inTitle){
        MongoCollection<Document> decks = database.getCollection("decks");
        Bson filter = eq("title", inTitle);
        String creator = getCreator(inTitle);
        List<String> cards = getCards(inTitle);
        for(int i = 0; i < cards.size(); i++){
            removeDeckFromCard(inTitle, cards.get(i));
        }
        removeUserDeck(creator,inTitle);
        decks.deleteOne(filter);
    }

    /*public static void removeDecks(String username){
        List<String> decks = getUserDecks("username");
    }*/


    // TODO: 23/01/2022 Eliminare tutti i deck che appartengono a questo utente
    public static void removeUser(String inUsername){
        MongoCollection<Document> users = database.getCollection("users");
        Bson filter = eq("login.username", inUsername);
        List<String> deckList = getDecks(inUsername);
        if(deckList != null){
            for(int i = 0; i < deckList.size(); i++){
                removeDeck(deckList.get(i));
            }
        }
        users.deleteOne(filter);
    }

    public static void updateDeckTitle(String oldTitle, String newTitle){
        MongoCollection<Document> decks = database.getCollection("decks");
        Document query = new Document().append("title",  oldTitle);
        Bson update = Updates.combine(Updates.set("title", newTitle));
        try {
            UpdateResult result = decks.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateUserDeck(String username, String deckTitle){
        MongoCollection<Document> users = database.getCollection("users");
        Document query = new Document().append("login.username", username);
        Bson update = Updates.push("decks", deckTitle);
        try {
            UpdateResult result = users.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void removeUserDeck(String username, String deckTitle){
        MongoCollection<Document> users = database.getCollection("users");
        Document query = new Document().append("login.username", username);
        Bson update = Updates.pull("decks", deckTitle);
        try {
            UpdateResult result = users.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateCardTitle(String oldTitle, String newTitle){
        MongoCollection<Document> cards = database.getCollection("cards");
        Document query = new Document().append("title",  oldTitle);
        Bson update = Updates.combine(Updates.set("title", newTitle));
        try {
            UpdateResult result = cards.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateUsername(String oldUsername, String newUsername){
        MongoCollection<Document> users = database.getCollection("users");
        Document query = new Document().append("login.username",  oldUsername);
        Bson update = Updates.combine(Updates.set("login.username", newUsername));
        try {
            UpdateResult result = users.updateOne(query, update);
            List<String> deckList = getDecks(newUsername);
            if(deckList != null){
                for(int i = 0; i < deckList.size(); i++){
                    updateCreator(deckList.get(i), newUsername);
                }
            }

        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateCreator(String title, String newUsername){
        MongoCollection<Document> decks = database.getCollection("decks");
        Document query = new Document().append("title",  title);
        Bson update = Updates.combine(Updates.set("creator", newUsername));
        try {
            UpdateResult result = decks.updateOne(query, update);

        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateCardDescription(String title, String newDesc){
        MongoCollection<Document> cards = database.getCollection("cards");
        Document query = new Document().append("title",  title);
        Bson update = Updates.combine(Updates.set("lore", newDesc));
        try {
            UpdateResult result = cards.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateCardImage(String title, String newUrl){
        MongoCollection<Document> cards = database.getCollection("cards");
        Document query = new Document().append("title",  title);
        Bson update = Updates.combine(Updates.set("imageUrl", newUrl));
        try {
            UpdateResult result = cards.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void updateCards(String deckTitle){
        List<String> cardsList = getCards(deckTitle);
        System.out.println(cardsList);
        for(int i = 0; i < cardsList.size(); i++){
            updateCard(cardsList.get(i), deckTitle);
        }
    }

    public static void updateCard(String cardTitle, String deckTitle){
        MongoCollection<Document> cards = database.getCollection("cards");
        Document query = new Document().append("title",  cardTitle);
        Bson update = Updates.push("decks", deckTitle);
        try {
            UpdateResult result = cards.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static void removeDeckFromCard(String deckTitle, String cardTitle){
        MongoCollection<Document> cards = database.getCollection("cards");
        Document query = new Document().append("title",  cardTitle);
        Bson update = Updates.pull("decks", deckTitle);
        try {
            UpdateResult result = cards.updateOne(query, update);
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public static boolean saveDeck(Deck d){
        MongoCollection<Document> decks = database.getCollection("decks");
        if(existsDeck(d.getTitle())){
            return false;
        }
        if(d.toDocument() != null){
            decks.insertOne(d.toDocument());
            updateCards(d.getTitle());
            updateUserDeck(GUIManager.getCurrentUser(),d.getTitle());
            return true;
        }
        return false;
    }

    public static boolean saveDeck(Deck d, String username){
        MongoCollection<Document> decks = database.getCollection("decks");
        if(existsDeck(d.getTitle())){
            return false;
        }
        if(d.toDocument() != null){
            decks.insertOne(d.toDocument());
            updateCards(d.getTitle());
            updateUserDeck(username,d.getTitle());
            return true;
        }
        return false;
    }

    // !!!!!!Aggregations!!!!!!!

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
        MongoCollection<Document> decks = database.getCollection("decks");
        List<String> decklist = new ArrayList<String>();
        Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson u = unwind("$cards");
        Bson existsAtk = Filters.exists("cards.atk");
        Bson filterQM = Filters.eq("atk","?");
        Bson notQM = Filters.not(filterQM);
        Bson filterX = Filters.eq("atk","X000");
        Bson notX = Filters.not(filterX);
        Bson filterNull = Filters.eq("atk","null");
        Bson notNull = Filters.not(filterNull);
        Bson myMatch1 = Aggregates.match(Filters.and(filterCreator, existsAtk, Filters.or(notQM, notX, notNull)));
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
	/*
	public static List findMostAvgAtk(int th){
        MongoCollection<Document> decks = database.getCollection("decks");
        List<StatisticsElem> decklist = new ArrayList<StatisticsElem>();
		Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson u = unwind("$cards");
        Bson existsAtk = Filters.exists("cards.atk");
        Bson filterQM = Filters.eq("atk","?");
        Bson notQM = Filters.not(filterQM);
        Bson filterX = Filters.eq("atk","X000");
        Bson notX = Filters.not(filterX);
        Bson filterNull = Filters.eq("atk","null");
        Bson notNull = Filters.not(filterNull);
        Bson myMatch1 = Aggregates.match(Filters.and(filterCreator, existsAtk, Filters.or(notQM, notX, notNull)));
        Bson groupMultiple = new Document("$group",
                new Document("_id", new Document("deck", "$title"))
                        .append("avgAtk", new Document("$avg", "$cards.atk")));
        Bson filterAtk = Filters.gte("avgAtk", th);
        Bson myMatch2 = Aggregates.match(filterAtk);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u,myMatch1,groupMultiple,myMatch2))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(new StatisticsElem(((Document)doclist.get(i).get("_id")).getString("deck"),((Document)doclist.get(i).get("_id")).getDouble("total_count")));
        }
        return decklist;
    }*/

    public static List<String> findTopXCards(int x){

        MongoCollection<Document> cards = database.getCollection("cards");
        List<String> cardlist = new ArrayList<String>();
        Bson match = match(Filters.exists("decks"));
        Bson p1 = project(fields(excludeId(),include("title"),computed("cont", new BasicDBObject("$size", "$decks"))));
        Bson sortCards = sort(descending("cont"));
        Bson limit = limit(x);
        List<Document> doclist = new ArrayList<>();
        cards.aggregate(Arrays.asList(match,p1,sortCards,limit))
                .forEach(doc -> doclist.add(doc));
        System.out.println(doclist);
        for(int i = 0; i < doclist.size(); i++){
            cardlist.add(doclist.get(i).getString("title"));
        }
        return cardlist;
    }

    public static List<String> findTopXECards(int x){
        MongoCollection<Document> decks = database.getCollection("cards");
        List<String> cardlist = new ArrayList<String>();
        Bson match1 = match(Filters.exists("decks"));
        Bson filterFusion = Filters.all("types", "Fusion");
        Bson filterLink = Filters.all("types", "Link");
        Bson filterSynchro = Filters.all("types", "Synchro");
        Bson filterXyz = Filters.all("types", "Xyz");
        Bson filter = Filters.or(filterFusion, filterLink, filterSynchro, filterXyz);
        Bson sortCards = sort(descending("cont"));
        Bson match2 = match(filter);
        Bson p1 = project(fields(excludeId(),include("title"),computed("cont", new BasicDBObject("$size", "$decks"))));
        Bson limit = limit(x);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(match1, match2, p1, sortCards, limit))
                .forEach(doc -> doclist.add(doc));
        System.out.println(doclist);
        for(int i = 0; i < doclist.size(); i++){
            cardlist.add(doclist.get(i).getString("title"));
        }
        return cardlist;
    }

    public static List<String> findMagicTrapDeck(){
        MongoCollection<Document> decks = database.getCollection("decks");
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.types");
        Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson myMatch = match(Filters.and(filterExistsType, filterCreator));
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
    }

    /*
	public static List<StatisticsElem> findMagicTrapDeck(){
        MongoCollection<Document> decks = database.getCollection("decks");
        List<StatisticsElem> decklist = new ArrayList<StatisticsElem>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.types");
		Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson myMatch = match(Filters.and(filterExistsType, filterCreator));
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
            decklist.add(new StatisticsElem(((Document)doclist.get(i).get("_id")).getString("deck"),((Document)doclist.get(i).get("_id")).getDouble("total_count")));
        }
        return decklist;
    }*/

    public static List<String> findArchetypeDeck(){
        MongoCollection<Document> decks = database.getCollection("decks");
        List<String> decklist = new ArrayList<String>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.archetypes");
        Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson myMatch = match(Filters.and(filterExistsType, filterCreator));
        Bson groupMultiple = new Document("$group",
                new Document("_id", new Document("title", "$title")
                        .append("creator", "$creator")).append("total_count", new Document("$sum", 1)));
        Bson mySort = sort(ascending("total_count"));
        Bson myLimit = limit(5);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u, myMatch, groupMultiple, mySort, myLimit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(((Document)doclist.get(i).get("_id")).getString("title"));
        }
        return decklist;
		/*
		 db.decks.aggregate([{$unwind:"$cards"},{$match: {$and:[{creator: "orangeelephant480"}, {"cards.archetypes":{$exists:1}}]}},{$group:{_id:{title: "$title", creator:"$creator"}, total_count: {$sum:1}}},{$sort:{total_count:1}},{$limit:1}])
		*/
    }

    /*
	public static List<StatisticsElem> findArchetypeDeck(){
        MongoCollection<Document> decks = database.getCollection("decks");
        List<StatisticsElem> decklist = new ArrayList<StatisticsElem>();
        Bson u = unwind("$cards");
        Bson filterExistsType = Filters.exists("cards.archetypes");
		Bson filterCreator = Filters.eq("creator", GUIManager.getCurrentUser());
        Bson myMatch = match(Filters.and(filterExistsType, filterCreator));
        Bson groupMultiple = new Document("$group",
                new Document("_id", new Document("title", "$title")
                        .append("creator", "$creator")).append("total_count", new Document("$sum", 1)));
        Bson mySort = sort(ascending("total_count"));
        Bson myLimit = limit(1);
        List<Document> doclist = new ArrayList<>();
        decks.aggregate(Arrays.asList(u, myMatch, groupMultiple, mySort, myLimit))
                .forEach(doc -> doclist.add(doc));
        for(int i = 0; i < doclist.size(); i++){
            decklist.add(new StatisticsElem(((Document)doclist.get(i).get("_id")).getString("title"),((Document)doclist.get(i).get("_id")).getDouble("total_count")));
        }
        return decklist;

		 //db.decks.aggregate([{$unwind:"$cards"},{$match: {$and:[{creator: "orangeelephant480"}, {"cards.archetypes":{$exists:1}}]}},{$group:{_id:{title: "$title", //creator:"$creator"}, total_count: {$sum:1}}},{$sort:{total_count:1}},{$limit:1}])

    }
	*/

    public static void connectionCloser(){
        mongoClient.close();
    }

    private static void updateUserForTest(){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("examtest");
        MongoCollection<Document> users = database.getCollection("users");
        List<Document> people = users.find().into(new ArrayList<Document>());
        for(int i = 0; i < 50; i++) {
            Bson update = Updates.set("login.sha1", LoginManager.encrypt("12345"));
            Bson filter = Filters.eq("login.username", ((Document)people.get(i).get("login")).getString("username"));
            UpdateResult result = users.updateOne(filter, update);
            System.out.println("Modified document count: " + result.getModifiedCount());
        }
    }

}
