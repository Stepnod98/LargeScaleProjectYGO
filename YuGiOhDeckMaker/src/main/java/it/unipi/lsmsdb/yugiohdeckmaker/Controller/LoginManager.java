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

        if(username.equals("admin") && password.equals(encrypt("admin"))){
            GUIManager.setCurrentUser(new User("admin"));
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


    public static String encrypt(String pass){
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


}
