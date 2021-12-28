/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Stefano
 */
public class DeckManagerLayout {
    private Label find;
    private static TextField deckToFind;
    protected Button findDeck;
    private Label remove;
    private static TextField deckToRemove;
    protected Button removeDeck;
    private Label topCards;
    private static TextField topCardNumber;
    protected Button findTopCards;
    private Label topECards;
    private static TextField topECardNumber;
    protected Button findTopECards;
    private Label magicTrapDecks;
    protected Button findMagicTrapDecks;
    private Label archetypeDecks;
    protected Button findArchetypeDecks;
    private TableView<String> table = new TableView<String>();
    private ObservableList<String> observableList;
    private VBox vbox;
    private Label result;
    private Label findD;
    private Label viewD;
    protected Button back;
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
        topCardNumber = new TextField("5");
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
        topECardNumber = new TextField("5");
        topECardNumber.setLayoutX(280);
        topECardNumber.setLayoutY(160);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        findTopECards = new Button("FIND");
    	findTopECards.setLayoutY(160);
    	findTopECards.setLayoutX(340);
    	findTopECards.setMaxWidth(300);
        magicTrapDecks = new Label("Find magic and trap cards oriented Decks");
        magicTrapDecks.setLayoutX(460);
        magicTrapDecks.setLayoutY(120);
        findMagicTrapDecks = new Button("FIND");
    	findMagicTrapDecks.setLayoutY(120);
    	findMagicTrapDecks.setLayoutX(700);
    	findMagicTrapDecks.setMaxWidth(300);
        archetypeDecks =  new Label("Find archetype oriented Decks");
        archetypeDecks.setLayoutX(460);
        archetypeDecks.setLayoutY(160);
        findArchetypeDecks = new Button("FIND");
    	findArchetypeDecks.setLayoutY(160);
    	findArchetypeDecks.setLayoutX(700);
    	findArchetypeDecks.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        /*findDeck.setOnAction((ActionEvent ev)->{DeckManager.findDeck();});
        removeDeck.setOnAction((ActionEvent ev)->{DeckManager.removeDeck();});
        findTopCards.setOnAction((ActionEvent ev)->{DeckManager.findTopXCard();});	
        findTopECards.setOnAction((ActionEvent ev)->{DeckManager.findTopXECard();}); 
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});*/
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = { find, deckToFind, findDeck, remove, deckToRemove, removeDeck, 
                            topCards, topCardNumber, findTopCards, topECards ,topECardNumber, findTopECards, 
                            magicTrapDecks, findMagicTrapDecks, archetypeDecks, findArchetypeDecks, back};
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
    
    public void showCardResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("Card Title");
        column.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);	
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(240);
        vbox.setLayoutX(60);
        vbox.setMaxHeight(148);
        vbox.getChildren().addAll(table);
    }
    
    public void showDeckResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("Deck Title");
        column.setCellValueFactory(cellData -> 
            new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);	
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox = new VBox();
        vbox.setLayoutY(240);
        vbox.setLayoutX(440);
        vbox.setMaxHeight(180);
        vbox.getChildren().addAll(table);
    }
    
    public Node getTableNodes() {
    	return vbox;
    }
}
