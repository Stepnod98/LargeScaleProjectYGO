/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class SocialLayout {
    private Label share;
    private TextField toShare;
    private Button shareDeck;
    private Label findU;
    private TextField userToFind;
    private Button findUser;
    private Label findD;
    private TextField deckToFind;
    private Button findDeck;
    private Label viewD;
    private Button viewRecDeck;
    private Label viewU;
    private Button viewRecUser;
    private Label mostLiked;
    private Label mostPopular;
    private VBox visualize;
    private Button back;
    public ObservableList<Row> observableList;
    private TableView findDeckTable;
    private TableView findUserTable;
    private TableView findDeckRecTable;
    private TableView findUserRecTable;
    private Label log;
    private TextField logText;

    public SocialLayout(){

        logText = new TextField();
        logText.setLayoutX(520);
        logText.setLayoutY(120);
        logText.setMinWidth(240);
        logText.setEditable(false);
        log = new Label("Log:");
        log.setLayoutX(520);
        log.setLayoutY(100);
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
        viewU.setLayoutX(260);
        viewU.setLayoutY(120);
        viewRecUser = new Button("VIEW");
    	viewRecUser.setLayoutY(120);
    	viewRecUser.setLayoutX(420);
    	viewRecUser.setMaxWidth(100);
        back = new Button("BACK");
    	back.setLayoutX(520);
        back.setLayoutY(550);
    	back.setMaxWidth(300);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {share, toShare, shareDeck, findU, userToFind, findUser, findD, deckToFind, 
            findDeck, viewD, viewRecDeck, viewU, viewRecUser, back, log, logText};
    	return returnNode;
    }

    public Button getFindUser() {
        return findUser;
    }

    public Button getFindDeck() {
        return findDeck;
    }

    public Button getBack() {
        return back;
    }

    public Button getShareDeck() {
        return shareDeck;
    }


    public void showDeckRecResults(List<String> list){
        findDeckRecTable = new TableView();

        TableColumn column = new TableColumn("Deck title");
        TableColumn columnLike = new TableColumn("Action");

        findDeckRecTable.getColumns().addAll(column, columnLike);

        List<Row> recResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            recResult.add(new Row(list.get(i)));
        }

        observableList = FXCollections.observableArrayList(recResult);

        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findDeckRecTable.setItems(observableList);
        findDeckRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        visualize = new VBox();
        visualize.setLayoutY(300);
        visualize.setLayoutX(20);
        visualize.setFillWidth(true);
        visualize.setMaxHeight(182);
        visualize.getChildren().addAll(findDeckRecTable);

    }


    public void showUserRecResults(List<String> list){
        findUserRecTable = new TableView();

        TableColumn column = new TableColumn("Username");
        TableColumn columnLike = new TableColumn("Action");

        findUserRecTable.getColumns().addAll(column, columnLike);

        List<Row> recResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            recResult.add(new Row(list.get(i)));
        }

        observableList = FXCollections.observableArrayList(recResult);


        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findUserRecTable.setItems(observableList);
        findUserRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        visualize = new VBox();
        visualize.setLayoutY(300);
        visualize.setLayoutX(440);
        visualize.setMaxHeight(182);
        visualize.getChildren().addAll(findUserRecTable);
    }

    public void showDeckFindResults(String deck){
        findDeckTable = new TableView();

        TableColumn column = new TableColumn("Deck title");
        TableColumn columnLike = new TableColumn("Action");

        findDeckTable.getColumns().addAll(column, columnLike);

        Row findResult = new Row(deck);

        findResult.getButton().setAlignment(Pos.CENTER);


        observableList = FXCollections.observableArrayList(findResult);

        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findDeckTable.setItems(observableList);
        findDeckTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        visualize = new VBox();
        visualize.setLayoutY(240);
        visualize.setLayoutX(20);
        visualize.setFillWidth(true);
        visualize.setMaxHeight(58);
        visualize.getChildren().addAll(findDeckTable);

    }

    public void showUserFindResults(String username){
        findUserTable = new TableView();

        TableColumn column = new TableColumn("Username");
        TableColumn columnLike = new TableColumn("Action");

        findUserTable.getColumns().addAll(column, columnLike);

        Row findResult = new Row(username);

        findResult.getButton().setAlignment(Pos.CENTER);


        observableList = FXCollections.observableArrayList(findResult);

        column.setCellValueFactory(
                new PropertyValueFactory<Row,String>("info")
        );

        columnLike.setCellValueFactory(
                new PropertyValueFactory<Row,String>("button")
        );


        findUserTable.setItems(observableList);
        findUserTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        visualize = new VBox();
        visualize.setLayoutY(240);
        visualize.setLayoutX(440);
        visualize.setMaxHeight(58);
        visualize.getChildren().addAll(findUserTable);

    }


    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }

    public Node getTableNodes() {
        return visualize;
    }


    public TextField getToShare() {
        return toShare;
    }

    public TextField getUserToFind() {
        return userToFind;
    }

    public TextField getLogText() {
        return logText;
    }

    public TextField getDeckToFind() {
        return deckToFind;
    }

    public TableView getFindUserRecTable() {
        return findUserRecTable;
    }

    public TableView getFindDeckRecTable() {
        return findDeckRecTable;
    }

    public TableView getFindDeckTable() {
        return findDeckTable;
    }

    public TableView getFindUserTable() {
        return findUserTable;
    }

    public Button getViewRecDeck() {
        return viewRecDeck;
    }

    public Button getViewRecUser() {
        return viewRecUser;
    }
}
