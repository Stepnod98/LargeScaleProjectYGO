/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import it.unipi.lsmsdb.yugiohdeckmaker.Controller.GUIManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class DeckBuilderLayout {
    private Label title;
    private Label add;
    private Label cardfound;
    private Label topCards;
    private Label topECards;
    private Label err;
    private Label log;
    private TextField numberOfCards;
    private TextField numberOfECards;
    private TextField deckTitle;
    private TextField cardToAdd;
    private TextField topCardNumber;
    private TextField topECardNumber;
    private TextField logText;
    private TextArea tips;
    protected Button findCardButton;
    protected Button magicTraps;
    protected Button findTopECards;
    protected Button findTopCards;
    protected Button save;
    protected Button back;
    private RadioButton byTitleRadioButton;
    private RadioButton byAtkRadioButton;
    private RadioButton byDefRadioButton;
    private TableView<String> cardFindResultTable;
    private TableView<String> deckCreationTable;
    private TableView<String> extraDeckCreationTable;
    private ListView<String> browseCardsToAdd;
    private VBox cardVbox;


    public DeckBuilderLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(20);
        title.setLayoutY(30);
        title.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        deckTitle = new TextField();
        deckTitle.setLayoutX(110);
        deckTitle.setLayoutY(30);
        deckTitle.setFocusTraversable(false);
        deckTitle.setMaxWidth(200);
        add = new Label("FIND A CARD:");
        add.setLayoutX(550);
        add.setLayoutY(40);
        add.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        byTitleRadioButton = new RadioButton("Title");
        byTitleRadioButton.setLayoutX(550);
        byTitleRadioButton.setLayoutY(100);
        byTitleRadioButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        byTitleRadioButton.setSelected(true);
        byAtkRadioButton = new RadioButton("Atk");
        byAtkRadioButton.setLayoutX(600);
        byAtkRadioButton.setLayoutY(100);
        byAtkRadioButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        byDefRadioButton = new RadioButton("Def");
        byDefRadioButton.setLayoutX(650);
        byDefRadioButton.setLayoutY(100);
        byDefRadioButton.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        cardToAdd = new TextField();
        cardToAdd.setLayoutX(550);
        cardToAdd.setLayoutY(75);
        cardToAdd.setFocusTraversable(false);
        cardToAdd.setPrefWidth(150);
        findCardButton = new Button("FIND");
        findCardButton.setLayoutY(75);
        findCardButton.setLayoutX(710);
        findCardButton.setMaxWidth(100);
        magicTraps = new Button("FILTER MAGIC AND TRAPS");
        magicTraps.setLayoutY(135);
        magicTraps.setLayoutX(550);
        magicTraps.setMaxWidth(300);
        topCards = new Label("Find top X Cards");
        topCards.setLayoutX(550);
        topCards.setLayoutY(170);
        topCards.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        topCardNumber = new TextField("5");
        topCardNumber.setLayoutX(550);
        topCardNumber.setLayoutY(195);
        topCardNumber.setFocusTraversable(false);
        topCardNumber.setMaxWidth(50);
        findTopCards = new Button("FIND");
        findTopCards.setLayoutY(195);
        findTopCards.setLayoutX(610);
        findTopCards.setMaxWidth(300);
        topECards = new Label("Find top X Extra Cards");
        topECards.setLayoutX(550);
        topECards.setLayoutY(235);
        topECards.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        topECardNumber = new TextField("5");
        topECardNumber.setLayoutX(550);
        topECardNumber.setLayoutY(260);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        findTopECards = new Button("FIND");
        findTopECards.setLayoutY(260);
        findTopECards.setLayoutX(610);
        findTopECards.setMaxWidth(300);
        browseCardsToAdd = new ListView<>();
        browseCardsToAdd.setLayoutY(95);
        browseCardsToAdd.setLayoutX(550);
        browseCardsToAdd.setMaxWidth(cardToAdd.getPrefWidth());
        browseCardsToAdd.setMaxHeight(120);
        browseCardsToAdd.setVisible(false);
        save = new Button("SAVE");
        save.setLayoutX(260);
        save.setLayoutY(30);
        save.setMaxWidth(300);
        err = new Label();
        err.setLayoutX(40);
        err.setLayoutY(500);
        back = new Button("BACK");
        back.setLayoutX(640);
        back.setLayoutY(560);
        back.setMaxWidth(300);
        cardfound = new Label();
        logText = new TextField();
        logText.setLayoutX(20);
        logText.setLayoutY(490);
        logText.setMinWidth(248);
        logText.setEditable(false);
        initializeDeckCreationTable();
    }

    public Node[] getNodes() {
        Node[] returnNode = { title, deckTitle, add, cardToAdd,
                topCards, topCardNumber, findTopCards, topECards ,topECardNumber, findTopECards, magicTraps, save, back,
                err, cardfound, deckCreationTable, numberOfCards, extraDeckCreationTable,
                numberOfECards, byTitleRadioButton, byAtkRadioButton, byDefRadioButton, findCardButton, browseCardsToAdd,
                logText};
        return returnNode;
    }

    public TableView<String> getDeckCreationTable() {
        return deckCreationTable;
    }

    public TableView<String> getExtraDeckCreationTable() {
        return extraDeckCreationTable;
    }

    public TableView<String> getCardFindResultTable() {
        return cardFindResultTable;
    }

    public VBox getCardVbox() {
        return cardVbox;
    }

    public String getCardToAdd(){
        return cardToAdd.getText();
    }

    public TextField getCardToAddTf(){
        return cardToAdd;
    }

    public TextField getNumberOfCards() {
        return numberOfCards;
    }

    public TextField getNumberOfECards() {
        return numberOfECards;
    }

    public TextArea getTips() {
        return tips;
    }

    public String getDeckTitle(){
        return deckTitle.getText();
    }

    public String getCardsRank(){
        return topCardNumber.getText();
    }

    public String getECardsRank(){
        return topECardNumber.getText();
    }

    public Button getFindCardButton() {
        return findCardButton;
    }

    public Button getMagicTraps(){
        return magicTraps;
    }

    public Button getBack() {
        return back;
    }

    public Button getSave() {
        return save;
    }

    public void showErrors(String text){
        err.setText(text);
    }

    public Button getFindTopCards() {
        return findTopCards;
    }

    public Button getFindTopECards() {
        return findTopECards;
    }

    public ListView<String> getBrowseCardsToAdd() {
        return browseCardsToAdd;
    }

    public RadioButton getByAtkRadioButton() {
        return byAtkRadioButton;
    }

    public RadioButton getByDefRadioButton() {
        return byDefRadioButton;
    }

    public RadioButton getByTitleRadioButton() {
        return byTitleRadioButton;
    }


    public void updateBrowseCardsToAdd(List<String> result){
        browseCardsToAdd.getItems().clear();
        if(result.isEmpty()){
            browseCardsToAdd.setVisible(false);
        }else{
            browseCardsToAdd.setVisible(true);
            browseCardsToAdd.getItems().addAll(result);
        }
    }

    public void showCardResults(List<String> list){

        cardFindResultTable = new TableView<>();

        ObservableList<String> observableList = FXCollections.observableArrayList(list);

        TableColumn<String, String> column = new TableColumn("Card Title");

        column.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        cardFindResultTable.getColumns().add(column);

        cardFindResultTable.setItems(observableList);

        cardFindResultTable.setLayoutY(290);
        cardFindResultTable.setLayoutX(550);
        cardFindResultTable.setMaxHeight(182);
        cardFindResultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        GUIManager.addNode(cardFindResultTable);
    }

    private void initializeDeckCreationTable(){

        deckCreationTable = new TableView();
        extraDeckCreationTable = new TableView();
        numberOfCards = new TextField("Cards: 0/40");
        numberOfECards = new TextField("Extra Cards: 0/10");

        TableColumn<String,String> titleColumn = new TableColumn("Deck");
        TableColumn<String,String> titleColumn2 = new TableColumn("Extra Deck");

        deckCreationTable.getColumns().addAll(titleColumn);
        extraDeckCreationTable.getColumns().addAll(titleColumn2);

        titleColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        titleColumn2.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        numberOfCards.setLayoutX(20);
        numberOfCards.setLayoutY(70);
        numberOfCards.setEditable(false);
        numberOfCards.setMaxWidth(100);
        numberOfCards.setAlignment(Pos.CENTER);
        numberOfCards.setStyle("-fx-font-weight: bold;");

        numberOfECards.setLayoutX(125);
        numberOfECards.setLayoutY(70);
        numberOfECards.setEditable(false);
        numberOfECards.setMaxWidth(120);
        numberOfECards.setAlignment(Pos.CENTER);
        numberOfECards.setStyle("-fx-font-weight: bold;");

        deckCreationTable.setLayoutY(100);
        deckCreationTable.setLayoutX(20);
        deckCreationTable.setMaxHeight(182);
        deckCreationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        extraDeckCreationTable.setLayoutY(290);
        extraDeckCreationTable.setLayoutX(20);
        extraDeckCreationTable.setMaxHeight(182);
        extraDeckCreationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void updateDeckCreationTable(List<String> list){

        ObservableList<String> cards = FXCollections.observableArrayList(list);
        deckCreationTable.setItems(cards);

    }

    public void updateExtraDeckCreationTable(List<String> list){

        ObservableList<String> cards = FXCollections.observableArrayList(list);
        extraDeckCreationTable.setItems(cards);

    }

    public void showCardFindResults(String title, Image image){

        cardVbox = new VBox();

        HBox titleBox = new HBox();
        Label titleLabel = new Label("Title: ");
        titleLabel.setStyle("-fx-font-weight: bold;");
        Text titleText = new Text(title);


        titleBox.getChildren().addAll(titleLabel, titleText);
        titleBox.setStyle("-fx-font-size: 15");

        HBox imageBox = new HBox();
        ImageView cardImage = new ImageView(image);
        imageBox.getChildren().add(cardImage);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(10,0,0,0));

        HBox commandBox = new HBox();
        Button action = new Button("ACTION");

        action.setPrefSize(100, 20);

        commandBox.getChildren().addAll(action);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));


        cardVbox.getChildren().addAll(titleBox,imageBox,commandBox);


        cardVbox.setLayoutY(100);
        cardVbox.setLayoutX(275);
        cardVbox.setMaxWidth(250);
        cardVbox.setMinHeight(100);
        cardVbox.setStyle("-fx-background-color: DARKSLATEGRAY;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }

    public void setTips(String text){
        tips = new TextArea(text);
        tips.setLayoutX(275);
        tips.setLayoutY(490);
        tips.setPrefSize(240, 100);
        tips.setEditable(false);
        tips.setWrapText(true);
    }

    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }

    public void clearDecks(){
        updateExtraDeckCreationTable(new ArrayList<>());
        updateDeckCreationTable(new ArrayList<>());
        deckTitle.setText("");
        numberOfCards.setText("Cards: 0/40");
        numberOfCards.setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
        numberOfECards.setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
        numberOfECards.setText("Extra Cards: 0/10");
    }
}
