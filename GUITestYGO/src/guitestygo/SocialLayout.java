/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Stefano
 */
public class SocialLayout {
    private Label share;
    private static TextField toShare;
    private Button shareDeck;
    private Label findU;
    private static TextField userToFind;
    private Button findUser;
    private Label findD;
    private static TextField deckToFind;
    private Button findDeck;
    private Label viewD;
    private Button viewRecDeck;
    private Label viewU;
    private Button viewRecUser;
    private Label mostLiked;
    private Label mostPopular;
    private VBox visualize;
    private Button back;
    public SocialLayout(){
        share = new Label("Share a Deck:");
        share.setLayoutX(20);
        share.setLayoutY(20);
        toShare = new TextField();
        toShare.setLayoutX(20);
        toShare.setLayoutY(40);
        toShare.setFocusTraversable(false);
        toShare.setMaxWidth(150);
        shareDeck = new Button("SHARE");
        shareDeck.setLayoutY(40);
    	shareDeck.setLayoutX(180);
    	shareDeck.setMaxWidth(100);
        findU = new Label("Find a User");
        findU.setLayoutX(260);
        findU.setLayoutY(20);
        userToFind = new TextField();
        userToFind.setLayoutX(260);
        userToFind.setLayoutY(40);
        userToFind.setFocusTraversable(false);
        userToFind.setMaxWidth(150);
        findUser = new Button("FIND USER");
        findUser.setLayoutY(40);
    	findUser.setLayoutX(420);
    	findUser.setMaxWidth(100);
        findD = new Label("Find a Deck");
        findD.setLayoutX(520);
        findD.setLayoutY(20);
        deckToFind = new TextField();
        deckToFind.setLayoutX(520);
        deckToFind.setLayoutY(40);
        deckToFind.setFocusTraversable(false);
        deckToFind.setMaxWidth(150);
        findDeck = new Button("FIND DECK");
        findDeck.setLayoutY(40);
    	findDeck.setLayoutX(680);
    	findDeck.setMaxWidth(100);
        viewD = new Label("View Recommended Decks:");
        viewD.setLayoutX(20);
        viewD.setLayoutY(120);
        viewRecDeck = new Button("VIEW");
    	viewRecDeck.setLayoutY(120);
    	viewRecDeck.setLayoutX(180);
    	viewRecDeck.setMaxWidth(100);
        viewU = new Label("View Recommended Users:");
        viewU.setLayoutX(360);
        viewU.setLayoutY(120);
        viewRecUser = new Button("VIEW");
    	viewRecUser.setLayoutY(120);
    	viewRecUser.setLayoutX(520);
    	viewRecUser.setMaxWidth(100);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        shareDeck.setOnAction((ActionEvent ev)->{SocialManager.share();});	
        findUser.setOnAction((ActionEvent ev)->{SocialManager.findUser();}); 
        findDeck.setOnAction((ActionEvent ev)->{SocialManager.findDeck();}); 
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {share, toShare, shareDeck, findU, userToFind, findUser, findD, deckToFind, 
            findDeck, viewD, viewRecDeck, viewU, viewRecUser, back};
    	return returnNode;
    }
    
    public static String getDeckToShare(){
        return toShare.getText();
    }
    
    public static String getUserToFind(){
        return userToFind.getText();
    }
    
    public static String getDeckToFind(){
        return deckToFind.getText();
    }
}
