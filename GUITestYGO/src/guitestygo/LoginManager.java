/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class LoginManager {
    private static LoginLayout loginLayout;
    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
    }/* public void login(){

        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());

        // TODO: 28/12/2021 Add admin control 
        if(!checkUser(username,password)){
            loginLayout.printError("User\\pass not correct");
        }else{
            //GUIManager.openAppManager();
            //Currently I switch directly to SocialManager!
            GUIManager.setCurrentUser(new User(username, getDecks(username)));
            GUIManager.openAppManager();
        }
    }

    public void setEvents(){
        loginLayout.getLogin().setOnAction((ActionEvent ev)->{login();});
        loginLayout.getSignUp().setOnAction((ActionEvent ev)->{signup();});
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void signup(){
    }

    private static String encrypt(String pass){
        java.security.MessageDigest d = null;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        d.reset();
        d.update(pass.getBytes());
        return new String(d.digest());
    }

    // TODO: 28/12/2021 Add to mongoDBManager
    private static boolean checkUser(String username, String pwd){

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
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

    // TODO: 28/12/2021 spostare su mongodb 
    public static List<String> getDecks(String username) {

        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("yugioh");

        Bson projectionFields = Projections.fields(Projections.excludeId(), Projections.include("title"), Projections.include("creator"));
        List<Document> decks = collection.find(Filters.eq("creator", username)).projection(projectionFields).into(new ArrayList<Document>());

        List<String> deckList = new ArrayList<>();
        for(int i = 0; i < decks.size(); i++)
            deckList.add((String) decks.get(i).get("title"));

        return deckList;
    }

    //This method was used to test if the login system works
    private static void updateUserForTest(){
        MongoClient myClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = myClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("login");
        Bson update = Updates.set("login.sha1",encrypt("inferno"));
        Bson filter = Filters.eq("login.username","crazymouse121");
        UpdateResult result = collection.updateOne(filter,update);
        System.out.println("Modified document count: " + result.getModifiedCount());
    }

    public static void main(String[] args){
        //LoginManager.updateUserForTest();
    }
    
    Update:
    LoginLayout loginLayout;

    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
        setEvents();
    }

    public void login(){

        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());

        if(username.equals("admin") && password.equals(encrypt("admin"))){
            GUIManager.openAdminPanel();
        }else if(username.isEmpty() || password.isEmpty() || !MongoDBManager.checkUser(username,password)){
            loginLayout.printError("User\\pass not correct");
        }else{
            GUIManager.setCurrentUser(new User(username, MongoDBManager.getDecks(username)));
            GUIManager.openAppManager();
        }
    }

    public void setEvents(){
        loginLayout.getLogin().setOnAction((ActionEvent ev)-> login());
        loginLayout.getSignUp().setOnAction((ActionEvent ev)-> GUIManager.openSignUpManager());
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }


    private static String encrypt(String pass){
        java.security.MessageDigest d = null;
        try {
            d = java.security.MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        d.reset();
        d.update(pass.getBytes());
        return new String(d.digest());
    }
    
    */
    public static void login(){
        /*if(){
            GUIManager.openAdminPanel();
        }*/
        GUIManager.openAppManager();
        //GUIManager.openAdminPanel();
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void checkCredentials(){
        
    }
    
    public static void signup(){
        GUIManager.openSignUpManager();
        loginLayout.sign.setOnAction((ActionEvent ev)->{checkCredentials();}); 
    }
    
    public static void setEvents(){
        loginLayout.login.setOnAction((ActionEvent ev)->{login();});	
        loginLayout.signUp.setOnAction((ActionEvent ev)->{signup();});
    }
    
    public static void setSignUpEvents(){
         loginLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
