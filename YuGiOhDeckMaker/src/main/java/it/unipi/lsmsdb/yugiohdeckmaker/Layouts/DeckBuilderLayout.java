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
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 *
 * @author Stefano
 */
public class DeckBuilderLayout {
    private Label title;
    private TextField deckTitle;
    private Label add;
    private TextField cardToAdd;
    protected Button addCardByTitle;
    protected Button addCardByAtk;
    protected Button addCardByDef;
    protected Button magicTraps;
    private Label remove;
    private TextField cardToRemove;
    protected Button removeCard;
    private Label findCard;
    private Label cardfound;
    protected Button findStrongest;
    private Label topCards;
    private TextField topCardNumber;
    protected Button findTopCards;
    private Label topECards;
    private TextField topECardNumber;
    protected Button findTopECards;
    /*private Label rarest;
    protected Button findRarest;*/
    protected Button save;
    protected Button back;
    private Label err;
    private TableView<String> table = new TableView<String>();
    private ListView<String> browseCardsToRemove;
    private ListView<String> browseCardsToAdd;
    private ObservableList<String> observableList;
    private VBox vbox;
    public DeckBuilderLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(40);
        title.setLayoutY(30);
        deckTitle = new TextField();
        deckTitle.setLayoutX(130);
        deckTitle.setLayoutY(30);
        deckTitle.setFocusTraversable(false);
        deckTitle.setMaxWidth(200);
        add = new Label("Add Card:");
        add.setLayoutX(520);
        add.setLayoutY(40);
        cardToAdd = new TextField();
        cardToAdd.setLayoutX(520);
        cardToAdd.setLayoutY(80);
        cardToAdd.setFocusTraversable(false);
        cardToAdd.setMaxWidth(180);
        addCardByTitle = new Button("ADD");
        addCardByTitle.setLayoutY(80);
        addCardByTitle.setLayoutX(680);
        addCardByTitle.setMaxWidth(100);
        addCardByAtk = new Button("FILTER BY ATK");
        addCardByAtk.setLayoutY(120);
        addCardByAtk.setLayoutX(520);
        addCardByAtk.setMaxWidth(300);
        addCardByDef = new Button("FILTER BY DEF");
        addCardByDef.setLayoutY(160);
        addCardByDef.setLayoutX(520);
        addCardByDef.setMaxWidth(300);
        magicTraps = new Button("FILTER MAGIC AND TRAPS");
        magicTraps.setLayoutY(200);
        magicTraps.setLayoutX(520);
        magicTraps.setMaxWidth(300);
        remove = new Label("Remove Card:");
        remove.setLayoutX(520);
        remove.setLayoutY(240);
        cardToRemove = new TextField();
        cardToRemove.setLayoutX(520);
        cardToRemove.setLayoutY(280);
        cardToRemove.setFocusTraversable(false);
        cardToRemove.setMaxWidth(180);
        removeCard = new Button("REMOVE");
        removeCard.setLayoutY(280);
        removeCard.setLayoutX(680);
        removeCard.setMaxWidth(300);
        topCards = new Label("Find top X Cards");
        topCards.setLayoutX(520);
        topCards.setLayoutY(320);
        topCardNumber = new TextField("5");
        topCardNumber.setLayoutX(520);
        topCardNumber.setLayoutY(360);
        topCardNumber.setFocusTraversable(false);
        topCardNumber.setMaxWidth(50);
        findTopCards = new Button("FIND");
        findTopCards.setLayoutY(360);
        findTopCards.setLayoutX(580);
        findTopCards.setMaxWidth(300);
        topECards = new Label("Find top X Extra Cards");
        topECards.setLayoutX(520);
        topECards.setLayoutY(400);
        topECardNumber = new TextField("5");
        topECardNumber.setLayoutX(520);
        topECardNumber.setLayoutY(440);
        topECardNumber.setFocusTraversable(false);
        topECardNumber.setMaxWidth(50);
        findTopECards = new Button("FIND");
        findTopECards.setLayoutY(440);
        findTopECards.setLayoutX(580);
        findTopECards.setMaxWidth(300);
        browseCardsToRemove = new ListView<>();
        browseCardsToRemove.setLayoutY(305);
        browseCardsToRemove.setLayoutX(520);
        browseCardsToRemove.setMaxWidth(cardToRemove.getMaxWidth());
        browseCardsToRemove.setMaxHeight(120);
        browseCardsToRemove.setVisible(false);
        browseCardsToAdd = new ListView<>();
        browseCardsToAdd.setLayoutY(105);
        browseCardsToAdd.setLayoutX(520);
        browseCardsToAdd.setMaxWidth(cardToAdd.getMaxWidth());
        browseCardsToAdd.setMaxHeight(120);
        browseCardsToAdd.setVisible(false);
        save = new Button("SAVE");
        save.setLayoutX(340);
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
        vbox = new VBox();
        /*findStrongest.setOnAction((ActionEvent ev)->{LoginManager.login();});
        addCard.setOnAction((ActionEvent ev)->{LoginManager.signup();});
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});*/
    }

    public Node[] getNodes() {
        Node[] returnNode = { title, deckTitle, add, cardToAdd, addCardByTitle, addCardByAtk, addCardByDef,
                topCards, topCardNumber, findTopCards, topECards ,topECardNumber, findTopECards,
                browseCardsToAdd, browseCardsToRemove,
                magicTraps, remove, cardToRemove, removeCard, save, back, err, cardfound, vbox};
        return returnNode;
    }

    public String getCardToAdd(){
        return cardToAdd.getText();
    }

    public TextField getCardToAddTf(){
        return cardToAdd;
    }

    public String getCardToRemove(){
        return cardToRemove.getText();
    }

    public TextField getCardToRemoveTf(){
        return cardToRemove;
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

    public Button getAddCardByAtk() {
        return addCardByAtk;
    }

    public Button getAddCardByTitle() {
        return addCardByTitle;
    }

    public Button getMagicTraps(){
        return magicTraps;
    }

    public Button getBack() {
        return back;
    }

    public Button getAddCardByDef() {
        return addCardByDef;
    }

    public Button getRemoveCard() {
        return removeCard;
    }

    public Button getFindStrongest() {
        return findStrongest;
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

    public ListView<String> getBrowseCardsToRemove() {
        return browseCardsToRemove;
    }

    public void clearLayout(){
        err.setText("");
        cardfound.setText("");
        vbox.getChildren().clear();
        table.getColumns().clear();
        GUIManager.removeNode(vbox);
    }



    public void updateBrowseCardsToRemove(List<String> result){
        browseCardsToRemove.getItems().clear();
        if(result.isEmpty()){
            browseCardsToRemove.setVisible(false);
        }else{
            browseCardsToRemove.setVisible(true);
            browseCardsToRemove.getItems().addAll(result);
        }
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
        TableColumn<String, String> column = new TableColumn("Card Title");
        column.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(column);
        observableList = FXCollections.observableArrayList(list);
        table.setItems(observableList);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //vbox = new VBox();
        /*table.getSelectionModel().getSelectedItem();
        table.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                cardToAdd.setText(table.selectionModelProperty().getValue().getSelectedItem());
        });*/
        vbox.setLayoutY(360);
        vbox.setLayoutX(50);
        vbox.setMaxHeight(120);
        vbox.setMinWidth(300);
        vbox.getChildren().addAll(table);
        GUIManager.addNode(vbox);
    }
}
