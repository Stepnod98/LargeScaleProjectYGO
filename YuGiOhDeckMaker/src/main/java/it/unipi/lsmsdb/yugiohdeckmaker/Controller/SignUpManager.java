package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.SignUpLayout;
import javafx.event.ActionEvent;

import java.security.NoSuchAlgorithmException;

public class SignUpManager {

    private SignUpLayout signUpLayout;
    public SignUpManager(SignUpLayout signUpLayout){
        this.signUpLayout = signUpLayout;
        setEvents();
    }

    private void setEvents(){
        signUpLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
        signUpLayout.getSign().setOnAction((ActionEvent ev)->{checkCredentials();});
    }

    public void checkCredentials(){

        if(!signUpLayout.getPassword().getText().equals(signUpLayout.getConfirmPassword().getText())){
            signUpLayout.printError("The passwords are different!");
        }else if(signUpLayout.getUsername().getText().isEmpty() || checkUsername()){
            signUpLayout.printError("The username is not valid!");
        }else if(signUpLayout.getEmailAddress().getText().isEmpty() || checkEmail()){
            signUpLayout.printError("The email is not valid!");
        }else {
            addUser();
            signUpLayout.printLog("Registration completed!");
        }
    }

    private void addUser(){
        String username = signUpLayout.getUsername().getText();
        String email = signUpLayout.getEmailAddress().getText();
        String pwd = encrypt(signUpLayout.getPassword().getText());
        String firstName = signUpLayout.getFirstName().getText();
        String lastName = signUpLayout.getLastName().getText();
        User user = new User(username, pwd, firstName, lastName, email);
        MongoDBManager.addUser(user);
        Neo4jManager.add(user.username, "User");
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

    private boolean checkUsername(){
        return MongoDBManager.checkUser(signUpLayout.getUsername().getText());
    }

    private boolean checkEmail(){
        return MongoDBManager.checkEmail(signUpLayout.getEmailAddress().getText());
    }
}
