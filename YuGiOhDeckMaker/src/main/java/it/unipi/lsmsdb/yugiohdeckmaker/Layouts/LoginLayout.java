/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;
import javafx.scene.Node;
import javafx.scene.control.*;

public class LoginLayout {
    private Label user;
    private TextField username;
    private Label pwd;
    private TextField password;
    private Button login;
    private Button signUp;
    private TextField logText;

    public LoginLayout(){
        user = new Label("Insert Username");
        user.setLayoutX(330);
        user.setLayoutY(40);
        username = new TextField();
        username.setLayoutX(310);
        username.setLayoutY(80);
        username.setFocusTraversable(false);
        username.setMaxWidth(200);

        pwd = new Label("Insert Password");
        pwd.setLayoutX(330);
        pwd.setLayoutY(120);

        password = new PasswordField();
        password.setLayoutX(310);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);

        login = new Button("LOGIN");
        login.setLayoutY(250);
        login.setLayoutX(300);
        login.setMinWidth(80);
        signUp = new Button("SIGN");
        signUp.setLayoutY(250);
        signUp.setLayoutX(400);
        signUp.setMinWidth(80);

        logText = new TextField();
        logText.setLayoutX(310);
        logText.setLayoutY(310);
        logText.setMaxWidth(200);
        logText.setEditable(false);
    }
    
     public Node[] getNodes() {
    	Node[] returnNode = {user, username, pwd, password, login, signUp, logText};
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
