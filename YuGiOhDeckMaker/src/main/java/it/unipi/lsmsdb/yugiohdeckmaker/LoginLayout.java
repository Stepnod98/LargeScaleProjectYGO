/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 *
 * @author Stefano
 */
public class LoginLayout {
    private Label user;
    private TextField username;
    private Label pwd;
    private TextField password;
    private Button login;
    private Button signUp;
    private Label log;
    private TextField logText;

    public LoginLayout(){
        user = new Label("Insert Username");
        user.setLayoutX(280);
        user.setLayoutY(40);
        username = new TextField();
        username.setLayoutX(280);
        username.setLayoutY(80);
        username.setFocusTraversable(false);
        username.setMaxWidth(200);
        pwd = new Label("Insert Password");
        pwd.setLayoutX(280);
        pwd.setLayoutY(120);
        password = new TextField();
        password.setLayoutX(280);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);
        login = new Button("LOGIN");
    	login.setLayoutY(250);
    	login.setLayoutX(280);
    	login.setMaxWidth(300);
        signUp = new Button("SIGN UP");
    	signUp.setLayoutY(250);
    	signUp.setLayoutX(380);
    	signUp.setMaxWidth(300);
        logText = new TextField();
        logText.setLayoutX(280);
        logText.setLayoutY(310);
        logText.setMaxWidth(300);
        logText.setEditable(false);
        log = new Label("Log:");
        log.setLayoutX(280);
        log.setLayoutY(290);
    }
    
     public Node[] getNodes() {
    	Node[] returnNode = {user, username, pwd, password, login, signUp, log, logText};
    	return returnNode;
    }
     
    public String getUsername(){
        return username.getText();
    }
    
    public String getPassword(){
        return password.getText();
    }

    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }


    public Button getLogin(){ return login;};
    public Button getSignUp(){ return signUp;};
}
