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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class DeckManagerLayout {
    private Label find;
    private TextField deckToFind;
    protected Button findDeck;
    private Label magicTrapDecks;
    protected Button findMagicTrapDecks;
    private Label archetypeDecks;
    protected Button findArchetypeDecks;
    protected Button mostAtkDecks;
    private Button showYourDecksButton;
    private Label avgAtkDecks;
    private TextField avgAtk;
    protected Button mostAvgAtkDecks;
    private TableView<String> deckResultTable;
    private ObservableList<String> observableList;
    private ListView<String> browseDeckToFind;
    private VBox deckVbox;
    protected Button back;
    public DeckManagerLayout(){
        find = new Label("Find a Deck:");
        find.setLayoutX(50);
        find.setLayoutY(40);
        deckToFind = new TextField();
        deckToFind.setLayoutX(180);
        deckToFind.setLayoutY(40);
        deckToFind.setFocusTraversable(false);
        deckToFind.setPrefWidth(160);
        findDeck = new Button("FIND");
        findDeck.setLayoutY(40);
        findDeck.setLayoutX(350);
        findDeck.setMaxWidth(300);
        showYourDecksButton = new Button("SHOW YOUR DECKS");
        showYourDecksButton.setLayoutX(50);
        showYourDecksButton.setLayoutY(80);
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
        avgAtk = new TextField();
        avgAtk.setLayoutX(50);
        avgAtk.setLayoutY(240);
        avgAtk.setFocusTraversable(false);
        avgAtk.setMaxWidth(100);
        avgAtk.setPromptText("Value");
        mostAvgAtkDecks = new Button("FIND");
        mostAvgAtkDecks.setLayoutY(240);
        mostAvgAtkDecks.setLayoutX(190);
        mostAvgAtkDecks.setMaxWidth(300);
        browseDeckToFind = new ListView<>();
        browseDeckToFind.setLayoutY(65);
        browseDeckToFind.setLayoutX(180);
        browseDeckToFind.setMaxWidth(deckToFind.getPrefWidth());
        browseDeckToFind.setMaxHeight(120);
        browseDeckToFind.setVisible(false);
        back = new Button("BACK");
        back.setLayoutX(640);
        back.setLayoutY(560);
        back.setMaxWidth(300);
    }

    public Node[] getNodes() {
        Node[] returnNode = { find, deckToFind, findDeck, magicTrapDecks,
                findMagicTrapDecks, archetypeDecks, findArchetypeDecks,
                avgAtkDecks, avgAtk,showYourDecksButton, mostAvgAtkDecks, browseDeckToFind, back};
        return returnNode;
    }

    public String getDeckToBrowse(){
        return deckToFind.getText();
    }

    public  TextField getDeckToBrowseTf(){
        return deckToFind;
    }

    public String getAvgAtk(){
        return avgAtk.getText();
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

    public Button getShowYourDecksButton() {
        return showYourDecksButton;
    }

    public VBox getDeckVbox() {
        return deckVbox;
    }

    public TableView<String> getDeckResultTable() {
        return deckResultTable;
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

    public void updateBrowseDeckToFind(List<String> result){
        browseDeckToFind.getItems().clear();
        if(result.isEmpty()){
            browseDeckToFind.setVisible(false);
        }else{
            browseDeckToFind.setVisible(true);
            browseDeckToFind.getItems().addAll(result);
        }
    }

    public void showDeckFindResults(String title, String creator){

        deckVbox = new VBox();

        HBox titleBox = new HBox();
        Label titleLabel = new Label("Title: ");
        titleLabel.setStyle("-fx-font-weight: bold;");
        Text titleText = new Text(title);

        titleBox.getChildren().addAll(titleLabel, titleText);
        titleBox.setStyle("-fx-font-size: 15");

        HBox creatorBox = new HBox();
        Label creatorLabel = new Label("Creator: ");
        creatorLabel.setStyle("-fx-font-weight: bold;");
        Text creatorText = new Text(creator);

        creatorBox.getChildren().addAll(creatorLabel, creatorText);
        creatorBox.setStyle("-fx-font-size: 15");


        HBox commandBox = new HBox();
        Button action = new Button("VIEW");
        Button action2 = new Button("REMOVE");
        action.setPrefSize(100, 20);
        action2.setPrefSize(100, 20);
        commandBox.getChildren().addAll(action, action2);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));


        deckVbox.getChildren().addAll(titleBox, creatorBox, commandBox);


        deckVbox.setLayoutY(240);
        deckVbox.setLayoutX(500);
        deckVbox.setMinWidth(250);
        deckVbox.setMinHeight(100);
        deckVbox.setStyle("-fx-background-color: DARKSLATEGRAY;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }

    public void showDeckResults(List<String> list){

        deckResultTable = new TableView<>();
        TableColumn<String, String> column = new TableColumn("Deck Title");
        column.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));
        deckResultTable.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);
        deckResultTable.setItems(observableList);
        deckResultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        deckResultTable.setLayoutY(40);
        deckResultTable.setLayoutX(500);
        deckResultTable.setMaxHeight(180);
    }

}
