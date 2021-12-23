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
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class DeckManagerLayout {
    private Label find;
    private static TextField deckToFind;
    private Button findDeck;
    private Label remove;
    private static TextField deckToRemove;
    private Button removeDeck;
    private Label topCards;
    private static TextField topCardNumber;
    private Button findTopCards;
    private Label topECards;
    private static TextField topECardNumber;
    private Button findTopECards;
    private Label findD;
    private Label viewD;
    private Button back;
    public DeckManagerLayout(){
        find = new Label("Find a Deck:");
        find.setLayoutX(50);
        find.setLayoutY(40);
        deckToFind = new TextField();
        deckToFind.setLayoutX(150);
        deckToFind.setLayoutY(40);
        deckToFind.setFocusTraversable(false);
        deckToFind.setMaxWidth(200);
        findDeck = new Button("FIND");
    	findDeck.setLayoutY(40);
    	findDeck.setLayoutX(320);
    	findDeck.setMaxWidth(300);
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
        topCards.setLayoutY(120);
        topCardNumber = new TextField("X");
        topCardNumber.setLayoutX(80);
        topCardNumber.setLayoutY(160);
        topCardNumber.setFocusTraversable(false);
        topCardNumber.setMaxWidth(50);
        findTopCards = new Button("FIND");
    	findTopCards.setLayoutY(160);
    	findTopCards.setLayoutX(140);
    	findTopCards.setMaxWidth(300);
        topECards = new Label("Find top X Extra Cards");
        topECards.setLayoutX(280);
        topECards.setLayoutY(120);
        topECardNumber = new TextField("X");
        topECardNumber.setLayoutX(280);
        topECardNumber.setLayoutY(160);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        findTopECards = new Button("FIND");
    	findTopECards.setLayoutY(160);
    	findTopECards.setLayoutX(340);
    	findTopECards.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(520);
        back.setLayoutY(450);
    	back.setMaxWidth(300);
        findDeck.setOnAction((ActionEvent ev)->{DeckManager.findDeck();});
        removeDeck.setOnAction((ActionEvent ev)->{DeckManager.removeDeck();});
        findTopCards.setOnAction((ActionEvent ev)->{DeckManager.findTopXCard();});	
        findTopECards.setOnAction((ActionEvent ev)->{DeckManager.findTopXECard();}); 
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { find, deckToFind, findDeck, remove, deckToRemove, removeDeck, 
                            topCards, topCardNumber, findTopCards, topECards ,topECardNumber, findTopECards, back};
    	return returnNode;
    }
    
    public static String getDeckToBrowse(){
        return deckToFind.getText();
    }
    
    public static String getDeckToRemove(){
        return deckToRemove.getText();
    }
    
    public static String getCardsRank(){
        return topCardNumber.getText();
    }
    
    public static String getECardsRank(){
        return topECardNumber.getText();
    }
}
