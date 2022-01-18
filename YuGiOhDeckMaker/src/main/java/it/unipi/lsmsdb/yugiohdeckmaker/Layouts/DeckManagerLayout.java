/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class DeckManagerLayout {
    private Label find;
    private TextField deckToFind;
    protected Button findDeck;
    private Label remove;
    private  TextField deckToRemove;
    protected Button removeDeck;
    private Label magicTrapDecks;
    protected Button findMagicTrapDecks;
    private Label archetypeDecks;
    protected Button findArchetypeDecks;
    private Label atkDecks;
    protected Button mostAtkDecks;
    private Label avgAtkDecks;
    private TextField avgAtk;
    protected Button mostAvgAtkDecks;
    private TableView<String> table = new TableView<>();
    private ObservableList<String> observableList;
    private ListView<String> browseDeckToFind;
    private ListView<String> browseDeckToRemove;
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
        deckToFind.setLayoutX(180);
        deckToFind.setLayoutY(40);
        deckToFind.setFocusTraversable(false);
        deckToFind.setMaxWidth(200);
        findDeck = new Button("FIND");
        findDeck.setLayoutY(40);
        findDeck.setLayoutX(350);
        findDeck.setMaxWidth(300);
        remove = new Label("Remove a Deck:");
        remove.setLayoutX(50);
        remove.setLayoutY(80);
        deckToRemove = new TextField();
        deckToRemove.setLayoutX(180);
        deckToRemove.setLayoutY(80);
        deckToRemove.setFocusTraversable(false);
        deckToRemove.setMaxWidth(200);
        removeDeck = new Button("REMOVE");
        removeDeck.setLayoutY(80);
        removeDeck.setLayoutX(350);
        removeDeck.setMaxWidth(300);
        magicTrapDecks = new Label("Magic and Trap Decks");
        magicTrapDecks.setLayoutX(50);
        magicTrapDecks.setLayoutY(120);
        findMagicTrapDecks = new Button("FIND");
        findMagicTrapDecks.setLayoutY(120);
        findMagicTrapDecks.setLayoutX(230);
        findMagicTrapDecks.setMaxWidth(300);
        archetypeDecks =  new Label("Archetype themed Decks");
        archetypeDecks.setLayoutX(50);
        archetypeDecks.setLayoutY(160);
        findArchetypeDecks = new Button("FIND");
        findArchetypeDecks.setLayoutY(160);
        findArchetypeDecks.setLayoutX(230);
        findArchetypeDecks.setMaxWidth(300);
        avgAtkDecks =  new Label("Find Decks with Average ATK more than");
        avgAtkDecks.setLayoutX(50);
        avgAtkDecks.setLayoutY(200);
        avgAtk = new TextField("5");
        avgAtk.setLayoutX(50);
        avgAtk.setLayoutY(240);
        avgAtk.setFocusTraversable(false);
        avgAtk.setMaxWidth(100);
        mostAvgAtkDecks = new Button("FIND");
        mostAvgAtkDecks.setLayoutY(240);
        mostAvgAtkDecks.setLayoutX(190);
        mostAvgAtkDecks.setMaxWidth(300);
        browseDeckToFind = new ListView<>();
        browseDeckToFind.setLayoutY(65);
        browseDeckToFind.setLayoutX(180);
        browseDeckToFind.setMaxWidth(deckToFind.getMaxWidth());
        browseDeckToFind.setMaxHeight(120);
        browseDeckToFind.setVisible(false);
        browseDeckToRemove = new ListView<>();
        browseDeckToRemove.setLayoutY(105);
        browseDeckToRemove.setLayoutX(180);
        browseDeckToRemove.setMaxWidth(deckToRemove.getMaxWidth());
        browseDeckToRemove.setMaxHeight(120);
        browseDeckToRemove.setVisible(false);
        vbox = new VBox();
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
        Node[] returnNode = { find, deckToFind, findDeck, remove, deckToRemove, removeDeck, magicTrapDecks,
                findMagicTrapDecks, archetypeDecks, findArchetypeDecks,
                avgAtkDecks, avgAtk, mostAvgAtkDecks, browseDeckToFind,
                browseDeckToRemove, vbox, back};
        return returnNode;
    }

    public String getDeckToBrowse(){
        return deckToFind.getText();
    }

    public  TextField getDeckToBrowseTf(){
        return deckToFind;
    }

    public String getDeckToRemove(){
        return deckToRemove.getText();
    }

    public TextField getDeckToRemoveTf(){
        return deckToRemove;
    }

    public String getAvgAtk(){
        return avgAtk.getText();
    }

    public Button getRemoveDeck() {
        return removeDeck;
    }

    public Button getFindDeck() {
        return findDeck;
    }

    public Button getFindArchetypeDecks() {
        return findArchetypeDecks;
    }

    public Button getBack() {
        return back;
    }

    public Button getFindMagicTrapDecks() {
        return findMagicTrapDecks;
    }

    public Button getMostAvgAtkDecks() {
        return mostAvgAtkDecks;
    }

    public Button getMostAtkDecks() {
        return mostAtkDecks;
    }

    public ListView<String> getBrowseDeckToFind() {
        return browseDeckToFind;
    }

    public ListView<String> getBrowseDeckToRemove() {
        return browseDeckToRemove;
    }

    public void updateBrowseDeckToRemove(List<String> result){
        browseDeckToRemove.getItems().clear();
        if(result.isEmpty()){
            browseDeckToRemove.setVisible(false);
        }else{
            browseDeckToRemove.setVisible(true);
            browseDeckToRemove.getItems().addAll(result);
        }
    }

    public void updateBrowseDeckToFind(List<String> result){
        browseDeckToFind.getItems().clear();
        if(result.isEmpty()){
            browseDeckToFind.setVisible(false);
        }else{
            browseDeckToFind.setVisible(true);
            browseDeckToFind.getItems().addAll(result);
        }
    }

    public void showCardResults(List<String> list){
        TableColumn<String, String> column = new TableColumn("Card Title");
        column.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        vbox.setLayoutY(40);
        vbox.setLayoutX(500);
        vbox.setMaxHeight(180);
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
        vbox.setLayoutY(40);
        vbox.setLayoutX(500);
        vbox.setMaxHeight(180);
        vbox.getChildren().addAll(table);
    }


    public void clearErrors(){
        vbox.getChildren().clear();
        table.getColumns().clear();
    }

    public Node getTableNodes() {
        return vbox;
    }
}
