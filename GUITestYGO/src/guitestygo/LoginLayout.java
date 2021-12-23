/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

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
       // login.setOnAction((ActionEvent ev)->{LoginManager.login();});
        login.setOnAction((ActionEvent ev)->{LoginManager.login();});	
        signUp.setOnAction((ActionEvent ev)->{LoginManager.signup();}); 
    }
    
     public Node[] getNodes() {
    	Node[] returnNode = {user, username, pwd, password, login, signUp};
    	return returnNode;
    }
     
    public String getUsername(){
        return username.getText();
    }
    
    public String getPassword(){
        return password.getText();
    }
}
