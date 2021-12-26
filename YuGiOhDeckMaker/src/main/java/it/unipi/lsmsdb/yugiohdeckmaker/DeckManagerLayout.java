/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class DeckManagerLayout {
    private Label browse;
    private TextField deckBrowse;
    private Label remove;
    private TextField deckToRemove;
    private Button removeDeck;
    private Label topCards;
    private TextField topCardNumber;
    private Button findTopCards;
    private Label topECards;
    private TextField topECardNumber;
    private Button findTopECards;
    private Label findD;
    private TextField deckToFind;
    private Button findDeck;
    private Label viewD;
    private Button back;
    
    public DeckManagerLayout(){
        browse = new Label("Browse Decks:");
        browse.setLayoutX(50);
        browse.setLayoutY(40);
        deckBrowse = new TextField();
        deckBrowse.setLayoutX(150);
        deckBrowse.setLayoutY(40);
        deckBrowse.setFocusTraversable(false);
        deckBrowse.setMaxWidth(200);
        remove = new Label("Remove a Deck:");
        remove.setLayoutX(50);
        remove.setLayoutY(80);
        deckToRemove = new TextField();
        deckToRemove.setLayoutX(150);
        deckToRemove.setLayoutY(80);
        deckToRemove.setFocusTraversable(false);
        deckToRemove.setMaxWidth(200);
        removeDeck = new Button("REMOVE");
    	removeDeck.setLayoutY(80);
    	removeDeck.setLayoutX(320);
    	removeDeck.setMaxWidth(300);
        topCards = new Label("Find top X Cards");
        topCards.setLayoutX(80);
        topCards.setLayoutY(220);
        topCardNumber = new TextField("X");
        topCardNumber.setLayoutX(80);
        topCardNumber.setLayoutY(260);
        topCardNumber.setFocusTraversable(false);
        topCardNumber.setMaxWidth(50);
        findTopCards = new Button("FIND");
    	findTopCards.setLayoutY(260);
    	findTopCards.setLayoutX(140);
    	findTopCards.setMaxWidth(300);
        topECards = new Label("Find top X Extra Cards");
        topECards.setLayoutX(280);
        topECards.setLayoutY(220);
        topECardNumber = new TextField("X");
        topECardNumber.setLayoutX(280);
        topECardNumber.setLayoutY(260);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        findTopECards = new Button("FIND");
    	findTopECards.setLayoutY(260);
    	findTopECards.setLayoutX(340);
    	findTopECards.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(520);
        back.setLayoutY(450);
    	back.setMaxWidth(300);
        findTopCards.setOnAction((ActionEvent ev)->{});
        findTopECards.setOnAction((ActionEvent ev)->{LoginManager.signup();}); 
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { browse, deckBrowse, remove, deckToRemove, removeDeck, 
                            topCards, topCardNumber, findTopCards, topECards ,topECardNumber, findTopECards, back};
    	return returnNode;
    }
}
