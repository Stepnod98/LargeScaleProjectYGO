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
public class AdminLayout {
    private Label adminLabel;
    private TextField deckBrowse;
    private Label add;
    private Label remove;
    private Button addCard;
    private TextField deckToRemove;
    private Button removeCard;
    private Label title;
    private TextField topCardNumber;
    private Label set;
    private TextField topECardNumber;
    private TextField deckToFind;
    private Button logout;
    
    public AdminLayout(){
        adminLabel = new Label("Browse Decks:");
        adminLabel.setLayoutX(50);
        adminLabel.setLayoutY(40);
        deckBrowse = new TextField();
        deckBrowse.setLayoutX(150);
        deckBrowse.setLayoutY(40);
        deckBrowse.setFocusTraversable(false);
        deckBrowse.setMaxWidth(200);
        add = new Label("Card to add:");
        add.setLayoutX(50);
        add.setLayoutY(80);
        remove = new Label("Card to remove:");
        remove.setLayoutX(50);
        remove.setLayoutY(80);
        deckToRemove = new TextField();
        deckToRemove.setLayoutX(150);
        deckToRemove.setLayoutY(80);
        deckToRemove.setFocusTraversable(false);
        deckToRemove.setMaxWidth(200);
        removeCard = new Button("REMOVE");
    	removeCard.setLayoutY(80);
    	removeCard.setLayoutX(320);
    	removeCard.setMaxWidth(300);
        title = new Label("Find top X Cards");
        title.setLayoutX(80);
        title.setLayoutY(220);
        topCardNumber = new TextField("X");
        topCardNumber.setLayoutX(80);
        topCardNumber.setLayoutY(260);
        topCardNumber.setFocusTraversable(false);
        topCardNumber.setMaxWidth(50);
        set = new Label("Find top X Extra Cards");
        set.setLayoutX(280);
        set.setLayoutY(220);
        topECardNumber = new TextField("X");
        topECardNumber.setLayoutX(280);
        topECardNumber.setLayoutY(260);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        addCard = new Button("FIND");
    	addCard.setLayoutY(260);
    	addCard.setLayoutX(340);
    	addCard.setMaxWidth(300);
        logout = new Button("BACK");
    	logout.setLayoutX(520);
        logout.setLayoutY(450);
    	logout.setMaxWidth(300);
        //browseDeck.setOnAction((ActionEvent ev)->{DeckManager.addDeck();});
        removeCard.setOnAction((ActionEvent ev)->{DeckManager.removeDeck();});	
        addCard.setOnAction((ActionEvent ev)->{DeckManager.findTopXECard();}); 
        logout.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { adminLabel, deckBrowse, add, remove, deckToRemove, removeCard, 
                            title, topCardNumber, set ,topECardNumber, addCard, logout};
    	return returnNode;
    }
}
