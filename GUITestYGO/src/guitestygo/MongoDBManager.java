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
    /*private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoCollection<Document> userCollection;
    public MongoDBManager(){
        mongoClient=MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("decks");
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
    /*public static boolean findUser(User user){

        MongoCollection<Document> collection = database.getCollection("login");

        Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("login.username"));
        Bson filter = Filters.and(Filters.eq("login.username",user.username));

        List<Document> res = collection.find(filter).projection(projectionFields).into(new ArrayList<>());

        if(user.username.equals(GUIManager.currentUser.username)){
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
    }*/
    
    public static List<String> browse(String t){
        List list = new ArrayList<String>();
        return list;
    }
    
    public static List findCard(int value, boolean type){ //false: def, true: atk
        //MongoCollection<Document> card = database.getCollection("yugioh");
        List<String> cardlist = new ArrayList<>();
       /* if(type){
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
        }*/
        return cardlist;
    }
    
     public static void insertCard(String title, String imgurl, int atk, int def, int level, String desc, String type, String archetype, String attribute, String effectType){
       /* 
        MongoCollection<Document> cards = database.getCollection("decks");
        Document card = new Document("title", title)
                .append("imgUrl", imgurl).append("atk", atk).append("level", level)
                .append("lore", desc).append("types", type)
                .append("archetypes", archetype).append("attribute", attribute)
                .append("effectTypes", effectType);
        cards.insertOne(card);*/
    }
     
     public static void remove(String inTitle, String set){
       /*MongoCollection<Document> decks = database.getCollection("yugioh");
       Bson filterTitle = eq("title", inTitle);
       Bson filterSet = eq("title", set);
       decks.deleteOne(Filters.and(filterTitle, filterSet));
       //decks.deleteOne(Filters.eq("title", inTitle));*/
    }
   /* public static User findUser(MongoDatabase db, String username){
        MongoCollection<Document> users = db.getCollection("profiles");
        Bson filter = eq("username", username);
        try (MongoCursor<Document> cursor = users.find(filter).iterator()) {
            Document doc = cursor.next();
            return new User(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that find first 50 anime
    public static ArrayList<Anime> browseAnime(MongoDatabase db){
        MongoCollection<Document> anime = db.getCollection("animes");
        ArrayList<Anime> foundAnime = new ArrayList<>();
        try (MongoCursor<Document> cursor = anime.find().limit(50).iterator()) {
            while (cursor.hasNext()) {//iterates between all reviews (A)
                Document doc = cursor.next();
                Anime a = new Anime(doc);
                foundAnime.add(a);
                String username = doc.getString("profile");
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return foundAnime;
    }

    //Method that finds an anime, given it's title
    public static Anime findAnime(MongoDatabase db, String title){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = eq("title", title);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that finds an anime, given it's uid
    public static Anime findAnime(MongoDatabase db, int uid){
        MongoCollection<Document> anime = db.getCollection("animes");
        Bson filter = eq("uid", uid);
        try (MongoCursor<Document> cursor = anime.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Anime(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
    }

    //Method that stores a new animelist element to the corresponding user
    public static boolean storeAnimeListElement(MongoDatabase db, String userName, String animeTitle) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(db, userName);
        if(duplicatesChecker(target, animeTitle)){
            System.out.println("Anime already present");
            return false;
        }
        ArrayList<Document> updatedAnimeList = target.addNewAnimeListElementFromList(animeTitle);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(eq("profile", userName), set("animelist", updatedAnimeList));
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that deletes an animelist element from the corresponding user
    public static boolean removeAnimeListElement(MongoDatabase db, String userName, String animeTitle) {
        MongoCollection<Document> users = db.getCollection("profiles");
        User target = findUser(db, userName);
        ArrayList<Document> updatedAnimeList = target.removeAnimeListElement(animeTitle);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that update an anime score from a user animelist
    public static boolean updateAnimeScore(MongoDatabase db, String userName, String animeTitle, int score) {
        MongoCollection<Document> users = db.getCollection("decks");
        User target = findUser(db, userName);
        ArrayList<Document> updatedAnimeList = target.setAnimeScore(animeTitle, score);
        Bson filter = eq("profile", userName);
        Bson setter = set("animelist", updatedAnimeList);
        UpdateResult result = users.updateOne(filter, setter);
        if (result.getModifiedCount() == 0) {
            System.out.println("Customer update operation failed");
            return false;
        }
        return true;
    }

    //Method that checks if an anime is aldready present inside a user animelist
    public static boolean duplicatesChecker(User target, String animeTitle){
        for(AnimeListElem e : target.getAnimeList()){
            if(e.getTitle().equals(animeTitle))
                return true;
        }
        return false;
    }*/
     
     
    //NUOVE:
    
    public static List findCards(String inTitle){
        /*
        MongoCollection<Document> card = database.getCollection("cards");
        List<String> cardlist = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            cardlist.add(new Card(doc).getTitle());
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
        */
        return null;
    }
    
    public static List findMagicTraps(){
        List<String> l = new ArrayList<>();
        /*
        MongoCollection<Document> card = database.getCollection("cards");
        List<String> cardlist = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            cardlist.add(new Card(doc).getTitle());
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
        */
        return l;
    }
    
    public static List findDecks(String inTitle){
        /*
        MongoCollection<Document> card = database.getCollection("decks");
        List<String> cardlist = new ArrayList<>();
        Bson filter = regex("title", inTitle);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            cardlist.add(new Card(doc).getTitle());
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
        */
        return null;
    }
    
    public static List findUsers(String inUsername){
        /*
        MongoCollection<Document> card = database.getCollection("users");
        List<String> cardlist = new ArrayList<>();
        Bson filter = regex("username", inUsername);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            cardlist.add(new Card(doc).getTitle());
        } catch (Exception ex) {ex.printStackTrace();}
        return null;
        */
        return null;
    }
    
    //FINE!

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
        Deck d = new Deck();
        /*MongoCollection<Document> deck = db.getCollection("decks");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = deck.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Card(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;*/
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
                String title = (String) decks.get("title").toString();
                String creator = (String) decks.get("creator").toString(); 
                Object cards = (Object) decks.get("cards").toString();
                Object ecards = (String) decks.get("extra_deck").toString(); 
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
        /*MongoCollection<Document> card = db.getCollection("decks");
        Bson filter = eq("title", t);
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            return new Card(doc);
        } catch (Exception ex) {ex.printStackTrace();}
        return null;*/
        Card c = new Card();
        return c;
    }
    
    /*public static Card findCard(int value, boolean type){ //false: def, true: atk
        MongoCollection<Document> card = db.getCollection("decks");
        if(type){
            Bson filter = eq("atk", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                Document doc = cursor.next();
                return new Card(doc);
            } catch (Exception ex) {ex.printStackTrace();}
        }
        else{
            Bson filter = eq("def", value);
            try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
                Document doc = cursor.next();
                return new Card(doc);
            } catch (Exception ex) {ex.printStackTrace();}
        }
        return null;
        Card c = new Card();
        return c;
    }*/
    
    public static boolean findUser(String name, String pwd){
        return false;
    }
    
    public static boolean checkCardType(String t){
        /*Bson projectionFields = Projections.fields(Projections.excludeId(),Projections.include("title"));
        MongoCollection<Document> card = db.getCollection("decks");
        Bson filterFusion = Filters.all("types", "Fusion");
        Bson filterLink = Filters.all("types", "Link");
        Bson filterSynchro = Filters.all("types", "Synchro");
        Bson filterXyz = Filters.all("types", "Xyz");

        //Merge all the filters
        Bson filterForSpecial = Filters.or(filterFusion,filterLink,filterSynchro,filterXyz);

        //Filters for types
        Bson filterNotFusion = Filters.not(filterFusion);
        Bson filterNotLink = Filters.not(filterLink);
        Bson filterNotSynchro = Filters.not(filterSynchro);
        Bson filterNotXyz = Filters.not(filterXyz);
        Bson filterExistingTitle = Filters.exists("title");

        //Merge all the filters
        Bson filterForNormal = Filters.and(filterExistingTitle,filterNotFusion,filterNotLink,filterNotXyz,filterNotSynchro);
        List<Document> normalCards = collection.find(filterForNormal).projection(projectionFields).into(new ArrayList<Document>());

        Bson filter = eq("title", t);
        Bson filterFusion = Filters.all("types", "Fusion");
        try (MongoCursor<Document> cursor = card.find(filter).iterator()) {
            Document doc = cursor.next();
            Card c = new Card(doc);
        } catch (Exception ex) {ex.printStackTrace();}*/
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
    
    /*public static String findMostAtk(int th){
        String t = "";
        
        return t;
    }*/
    
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
