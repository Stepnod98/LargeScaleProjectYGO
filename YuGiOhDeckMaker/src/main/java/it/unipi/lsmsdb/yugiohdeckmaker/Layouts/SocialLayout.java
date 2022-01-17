/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
    private Button viewProfile;
    private Button viewRecUser;
    private Button viewSharedDecks;
    private Button viewFollowers;
    private Button viewFriends;
    private Button viewLikedDecks;
    private Button viewRecentDecks;
    private Button followUserButton;
    private VBox userVbox;
    private VBox deckVbox;
    private VBox userProfileVbox;
    private Button back;
    private TableView<String> findDeckRecTable;
    private TableView<String> findUserRecTable;
    private TableView<String> sharedDecksTable;
    private TableView<String> friendsTable;
    private TableView<String> followersTable;
    private TableView<String> likedDecksTable;
    private TableView<String> recentDecksTable;
    private Label log;
    private TextField logText;
    private ListView<String> browseUserResults;
    private ListView<String> browseDeckResults;
    private ListView<String> browseYourDeckResults;

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
    	back.setLayoutX(690);
        back.setLayoutY(510);
    	back.setPrefWidth(50);
        back.setTextAlignment(TextAlignment.CENTER);
        viewProfile = new Button("View your profile");
        viewProfile.setLayoutX(20);
        viewProfile.setLayoutY(180);
        browseUserResults = new ListView<>();
        browseUserResults.setLayoutY(65);
        browseUserResults.setLayoutX(260);
        browseUserResults.setMaxWidth(userToFind.getMaxWidth());
        browseUserResults.setMaxHeight(120);
        browseUserResults.setVisible(false);

        browseDeckResults = new ListView<>();
        browseDeckResults.setLayoutY(65);
        browseDeckResults.setLayoutX(520);
        browseDeckResults.setMaxWidth(deckToFind.getMaxWidth());
        browseDeckResults.setMaxHeight(120);
        browseDeckResults.setVisible(false);

        browseYourDeckResults = new ListView<>();
        browseYourDeckResults.setLayoutY(65);
        browseYourDeckResults.setLayoutX(20);
        browseYourDeckResults.setMaxWidth(toShare.getMaxWidth());
        browseYourDeckResults.setMaxHeight(120);
        browseYourDeckResults.setVisible(false);
    }
    
    public Node[] getNodes() {
    	Node[] returnNode = {share, toShare, shareDeck, findU, userToFind, findUser, findD, deckToFind, 
            findDeck, viewD, viewRecDeck, viewU, viewRecUser, back, viewProfile, log, logText, browseUserResults,
                browseDeckResults, browseYourDeckResults};
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

    public ListView<String> getBrowseUserResults() {
        return browseUserResults;
    }

    public ListView<String> getBrowseDeckResults() {
        return browseDeckResults;
    }

    public ListView<String> getBrowseYourDeckResults() {
        return browseYourDeckResults;
    }

    public void updateBrowseUserResults(List<String> result){
        browseUserResults.getItems().clear();
        if(result.isEmpty()){
            browseUserResults.setVisible(false);
        }else{
            browseUserResults.setVisible(true);
            browseUserResults.getItems().addAll(result);
        }
    }

    public void updateBrowseDeckResults(List<String> result){
        browseDeckResults.getItems().clear();
        if(result.isEmpty()){
            browseDeckResults.setVisible(false);
        }else{
            browseDeckResults.setVisible(true);
            browseDeckResults.getItems().addAll(result);
        }
    }

    public void updateBrowseYourDeckResults(List<String> result){
        browseYourDeckResults.getItems().clear();
        if(result.isEmpty()){
            browseYourDeckResults.setVisible(false);
        }else{
            browseYourDeckResults.setVisible(true);
            browseYourDeckResults.getItems().addAll(result);
        }
    }

    public void showDeckRecResults(List<String> list){

        ObservableList<String> deckTitles = FXCollections.observableArrayList(list);


        findDeckRecTable = new TableView();

        TableColumn<String,String> titleColumn = new TableColumn("Deck title");

        findDeckRecTable.getColumns().addAll(titleColumn);

        titleColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        findDeckRecTable.setLayoutY(240);
        findDeckRecTable.setLayoutX(20);
        findDeckRecTable.setMaxHeight(182);

        findDeckRecTable.setItems(deckTitles);
        findDeckRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void showUserRecResults(List<String> list){
        ObservableList<String> usernames = FXCollections.observableArrayList(list);


        findUserRecTable = new TableView();

        TableColumn<String,String> usernameColumn = new TableColumn("Username");

        findUserRecTable.getColumns().addAll(usernameColumn);

        usernameColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        findUserRecTable.setLayoutY(240);
        findUserRecTable.setLayoutX(20);
        findUserRecTable.setMaxHeight(182);

        findUserRecTable.setItems(usernames);
        findUserRecTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void showFriends(List<String> list){
        ObservableList<String> friends = FXCollections.observableArrayList(list);

        friendsTable = new TableView();

        TableColumn<String,String> friendsColumn = new TableColumn("Friends");

        friendsTable.getColumns().addAll(friendsColumn);

        friendsColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        setProfileTablesLayout(friendsTable);

        friendsTable.setItems(friends);

    }

    public void showFollowers(List<String> list){
        ObservableList<String> followers = FXCollections.observableArrayList(list);

        followersTable = new TableView();

        TableColumn<String,String> followersColumn = new TableColumn("Followers");

        followersTable.getColumns().addAll(followersColumn);

        followersColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        setProfileTablesLayout(followersTable);

        followersTable.setItems(followers);

    }

    public void showSharedDecksResults(List<String> list){
        ObservableList<String> decks = FXCollections.observableArrayList(list);

        sharedDecksTable = new TableView();

        TableColumn<String,String> titleColumn = new TableColumn("Title");


        sharedDecksTable.getColumns().addAll(titleColumn);

        titleColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        setProfileTablesLayout(sharedDecksTable);


        sharedDecksTable.setItems(decks);
    }

    public void showLikedDecksResults(List<String> list){
        ObservableList<String> decks = FXCollections.observableArrayList(list);

        likedDecksTable = new TableView();

        TableColumn<String,String> titleColumn = new TableColumn("Title");


        likedDecksTable.getColumns().addAll(titleColumn);

        titleColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        setProfileTablesLayout(likedDecksTable);


        likedDecksTable.setItems(decks);
    }

    public void showRecentDecksResults(List<String> list){
        ObservableList<String> decks = FXCollections.observableArrayList(list);

        recentDecksTable = new TableView();

        TableColumn<String,String> titleColumn = new TableColumn("Title");


        recentDecksTable.getColumns().addAll(titleColumn);

        titleColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue()));

        setProfileTablesLayout(recentDecksTable);


        recentDecksTable.setItems(decks);
    }

    private void setProfileTablesLayout(TableView<String> tw){

        tw.setLayoutY(240);
        tw.setLayoutX(260);
        tw.setMaxHeight(182);
        tw.setMaxWidth(180);
        //tw.setPrefSize(250, 182);

        tw.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }


    public void showDeckFindResults(String title, String creator, String likes, double x, double y){

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


        HBox likesBox = new HBox();
        Label likesLabel = new Label("Likes: ");
        likesLabel.setStyle("-fx-font-weight: bold;");
        Text likesText= new Text(likes);

        likesBox.getChildren().addAll(likesLabel, likesText);
        likesBox.setStyle("-fx-font-size: 15");

        HBox commandBox = new HBox();
        Button action = new Button("ACTION");
        Button action2 = new Button("ACTION");
        action.setPrefSize(100, 20);
        action2.setPrefSize(100, 20);
        commandBox.getChildren().addAll(action, action2);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));


        deckVbox.getChildren().addAll(titleBox,creatorBox,likesBox,commandBox);


        deckVbox.setLayoutY(y);
        deckVbox.setLayoutX(x);
        deckVbox.setMinHeight(100);
        deckVbox.setMinWidth(300);
        deckVbox.setStyle("-fx-background-color: cornsilk;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");
    }

    public void showUserFindResults(String username, String followers, String sharedDecks, String totalLikes, double x, double y){

        userVbox = new VBox();

        HBox usernameBox = new HBox();
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-weight: bold;");
        Text usernameText = new Text(username);

        usernameBox.getChildren().addAll(usernameLabel, usernameText);
        usernameBox.setStyle("-fx-font-size: 15");

        HBox followersBox = new HBox();
        Label labelFollowers = new Label("Followers: ");
        labelFollowers.setStyle("-fx-font-weight: bold;");
        Text followersText = new Text(followers);

        followersBox.getChildren().addAll(labelFollowers, followersText);
        followersBox.setStyle("-fx-font-size: 15");

        HBox deckSharedBox = new HBox();
        Label labelDeckShared = new Label("Deck shared: ");
        labelDeckShared.setStyle("-fx-font-weight: bold;");
        Text deckSharedText= new Text(sharedDecks);

        deckSharedBox.getChildren().addAll(labelDeckShared, deckSharedText);
        deckSharedBox.setStyle("-fx-font-size: 15");

        HBox likesBox = new HBox();
        Label labelLikes = new Label("Total likes: ");
        labelLikes.setStyle("-fx-font-weight: bold;");
        Text likesText = new Text(totalLikes);
        likesBox.getChildren().addAll(labelLikes, likesText);
        likesBox.setStyle("-fx-font-size: 15");

        HBox commandBox = new HBox();
        Button action = new Button("ACTION");
        Button action2 = new Button("ACTION");
        action.setPrefSize(100, 20);
        action2.setPrefSize(100, 20);
        commandBox.getChildren().addAll(action, action2);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));


        userVbox.getChildren().addAll(usernameBox,followersBox,deckSharedBox,likesBox,commandBox);


        userVbox.setLayoutY(y);
        userVbox.setLayoutX(x);
        userVbox.setMinWidth(300);
        userVbox.setMinHeight(100);
        userVbox.setStyle("-fx-background-color: cornsilk;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }

    public void showUserInfo(String username, String followers, String sharedDecks, String totalLikes){

        userProfileVbox = new VBox();

        VBox usernameBox = new VBox();
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-weight: bold;");
        Text usernameText = new Text(username);

        usernameBox.getChildren().addAll(usernameLabel, usernameText);
        usernameBox.setStyle("-fx-font-size: 15");

        HBox followersBox = new HBox();
        Label labelFollowers = new Label("Followers: ");
        labelFollowers.setStyle("-fx-font-weight: bold;");
        Text followersText = new Text(followers);

        followersBox.getChildren().addAll(labelFollowers, followersText);
        followersBox.setStyle("-fx-font-size: 15");

        HBox deckSharedBox = new HBox();
        Label labelDeckShared = new Label("Deck shared: ");
        labelDeckShared.setStyle("-fx-font-weight: bold;");
        Text deckSharedText= new Text(sharedDecks);

        deckSharedBox.getChildren().addAll(labelDeckShared, deckSharedText);
        deckSharedBox.setStyle("-fx-font-size: 15");

        HBox likesBox = new HBox();
        Label labelLikes = new Label("Total likes: ");
        labelLikes.setStyle("-fx-font-weight: bold;");
        Text likesText = new Text(totalLikes);
        likesBox.getChildren().addAll(labelLikes, likesText);
        likesBox.setStyle("-fx-font-size: 15");


        userProfileVbox.getChildren().addAll(usernameBox,followersBox,deckSharedBox,likesBox);


        userProfileVbox.setLayoutY(240);
        userProfileVbox.setLayoutX(20);
        userProfileVbox.setMinWidth(200);
        userProfileVbox.setMinHeight(100);
        userProfileVbox.setStyle("-fx-background-color: cornsilk;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }

    public void showProfileActions(){

        viewFriends = new Button("View your friends");

        viewFriends.setLayoutX(45);
        viewFriends.setLayoutY(390);
        viewFriends.setPrefWidth(150);
        viewFriends.setTextAlignment(TextAlignment.CENTER);


        viewFollowers = new Button("View your followers");

        viewFollowers.setLayoutX(45);
        viewFollowers.setLayoutY(420);
        viewFollowers.setPrefWidth(150);
        viewFollowers.setTextAlignment(TextAlignment.CENTER);

        viewSharedDecks = new Button("View shared decks");

        viewSharedDecks.setLayoutX(45);
        viewSharedDecks.setLayoutY(450);
        viewSharedDecks.setPrefWidth(150);
        viewSharedDecks.setTextAlignment(TextAlignment.CENTER);

        viewLikedDecks = new Button("View liked decks");

        viewLikedDecks.setLayoutX(45);
        viewLikedDecks.setLayoutY(480);
        viewLikedDecks.setPrefWidth(150);
        viewLikedDecks.setTextAlignment(TextAlignment.CENTER);

        viewRecentDecks = new Button("View recent decks");

        viewRecentDecks.setLayoutX(45);
        viewRecentDecks.setLayoutY(510);
        viewRecentDecks.setPrefWidth(150);
        viewRecentDecks.setTextAlignment(TextAlignment.CENTER);
    }

    public void showUserFoundActions(){
        viewFollowers = new Button("View followers");

        viewFollowers.setLayoutX(45);
        viewFollowers.setLayoutY(420);
        viewFollowers.setPrefWidth(150);
        viewFollowers.setTextAlignment(TextAlignment.CENTER);

        viewSharedDecks = new Button("View shared decks");

        viewSharedDecks.setLayoutX(45);
        viewSharedDecks.setLayoutY(450);
        viewSharedDecks.setPrefWidth(150);
        viewSharedDecks.setTextAlignment(TextAlignment.CENTER);

        followUserButton = new Button("Action");

        followUserButton.setLayoutX(45);
        followUserButton.setLayoutY(480);
        followUserButton.setPrefWidth(150);
        followUserButton.setTextAlignment(TextAlignment.CENTER);
    }


    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }

    public VBox getUserVbox() {
        return userVbox;
    }

    public VBox getDeckVbox() {
        return deckVbox;
    }

    public VBox getUserProfileVbox() {
        return userProfileVbox;
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

    public TableView<String> getSharedDecksTable() {
        return sharedDecksTable;
    }

    public TableView<String> getFriendsTable() {
        return friendsTable;
    }

    public TableView<String> getFollowersTable() {
        return followersTable;
    }

    public TableView<String> getLikedDecksTable() {
        return likedDecksTable;
    }

    public TableView<String> getRecentDecksTable() {
        return recentDecksTable;
    }

    public Button getViewRecDeck() {
        return viewRecDeck;
    }

    public Button getViewRecUser() {
        return viewRecUser;
    }

    public Button getViewFriends() {
        return viewFriends;
    }

    public Button getViewFollowers() {
        return viewFollowers;
    }

    public Button getViewLikedDecks() {
        return viewLikedDecks;
    }

    public Button getViewSharedDecks() {
        return viewSharedDecks;
    }

    public Button getViewRecentDecks() {
        return viewRecentDecks;
    }

    public Button getViewProfile() {
        return viewProfile;
    }

    public Button getFollowUserButton() {
        return followUserButton;
    }
}
