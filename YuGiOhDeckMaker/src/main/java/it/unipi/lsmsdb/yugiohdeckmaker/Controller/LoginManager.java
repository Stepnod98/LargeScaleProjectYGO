/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.LoginLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import javafx.event.ActionEvent;
import java.security.NoSuchAlgorithmException;

public class LoginManager {

    LoginLayout loginLayout;

    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
        setEvents();
    }

    public void login(){

        String username = loginLayout.getUsername();
        String password = encrypt(loginLayout.getPassword());

        // TODO: 28/12/2021 Add admin control 
        if(!MongoDBManager.checkUser(username,password)){
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


    //This method was used to test if the login system works
    /*private static void updateUserForTest(){
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
    }*/

}
