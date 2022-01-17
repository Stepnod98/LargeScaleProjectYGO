/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Stefano
 */
public class AppLayout {
    private Label title;
    private Button bDeckBuilder;
    private Button bDeckManager;
    private Button bDeckOpener;
    private Button bSocial;
    private Button bLogout;
    public AppLayout(){
        title = new Label("Welcome in YugiOhDeckMaker!");
        title.setLayoutX(190);
        title.setLayoutY(20);
        title.setId("title");
        bDeckBuilder = new Button("CREATE A DECK");
        bDeckBuilder.setLayoutX(300);
        bDeckBuilder.setLayoutY(80);
        bDeckManager = new Button("BROWSE YOUR DECKS");
        bDeckManager.setLayoutX(300);
        bDeckManager.setLayoutY(130);
        bDeckOpener = new Button("OPEN A DECK");
        bDeckOpener.setLayoutX(300);
        bDeckOpener.setLayoutY(180);
        bSocial = new Button("SEARCH NEW DECKS");
        bSocial.setLayoutX(300);
        bSocial.setLayoutY(230);
        bLogout = new Button("LOGOUT");
        bLogout.setLayoutX(300);
        bLogout.setLayoutY(280);
        bDeckBuilder.setOnAction((ActionEvent ev)->{GUIManager.openDeckBuilder();}); 
        bDeckManager.setOnAction((ActionEvent ev)->{GUIManager.openDeckManager();}); 
        bDeckOpener.setOnAction((ActionEvent ev)->{GUIManager.openDeckOpener();});
        bSocial.setOnAction((ActionEvent ev)->{GUIManager.openSocialManager();});
    	//bLogout.setOnAction((ActionEvent ev)->{LoginManager.logout();});
        bLogout.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});           
    }
    
    public static void createDeck(){
        
    }
    
    public static void openSocial(){
        
    }
    
    public static void logout(){
        
    }
    
    public static void openDeckManager(){
        
    }
    public Node[] getNodes() {
    	Node[] returnNode = {title, bDeckBuilder, bDeckManager, bDeckOpener, bSocial, bLogout};
    	return returnNode;
    }
}
