/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import javafx.event.ActionEvent;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabiano
 */
public class LoginManager {

    LoginLayout loginLayout;

    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
        setEvents();
    }

    public void login(){

        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());
        if(!checkUser(username,password)){
            //LoginLayout.showLoginError();
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
        Bson update = Updates.set("login.sha1",encrypt("thedude"));
        Bson filter = Filters.eq("login.username","organicwolf613");
        UpdateResult result = collection.updateOne(filter,update);
        System.out.println("Modified document count: " + result.getModifiedCount());
    }
}
