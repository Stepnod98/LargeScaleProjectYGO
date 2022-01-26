/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.List;

public class AdminLayout {
    private Label adminLabel;
    private Label add;
    private Label remove;
    private Label removeU;
    private Label removeD;
    private TextField cardToAddTitle;
    private TextField cardToAddImage;
    private TextField cardToAddAtk;
    private TextField cardToAddDef;
    private TextField cardToAddLevel;
    private TextField cardToAddDesc;
    private TextField cardToAddType;
    private TextField cardToAddArchetype;
    private TextField cardToAddAttribute;
    private TextField cardToAddEffectTypes;
    private TextField cardToRemoveTitle;
    private TextField deckToRemove;
    private TextField userToRemove;
    private TextField logText;
    protected Button removeCard;
    protected Button addCard;
    protected Button removeUser;
    protected Button removeDeck;
    protected Button logout;
    private VBox vbox;
    private VBox userVbox;
    private VBox cardVbox;
    private VBox deckVbox;
    private ListView<String> browseCardResults;
    private ListView<String> browseUserResults;
    private ListView<String> browseDeckResults;


    public AdminLayout(){
        adminLabel = new Label("Admin Control Panel");
        adminLabel.setLayoutX(40);
        adminLabel.setLayoutY(10);
        add = new Label("Add Card:");
        add.setLayoutX(40);
        add.setLayoutY(50);
        cardToAddTitle = new TextField();
        cardToAddTitle.setPromptText("Insert title");
        cardToAddTitle.setLayoutX(40);
        cardToAddTitle.setLayoutY(80);
        cardToAddTitle.setFocusTraversable(false);
        cardToAddTitle.setMaxWidth(200);
        cardToAddImage = new TextField();
        cardToAddImage.setPromptText("Insert image url");
        cardToAddImage.setLayoutX(40);
        cardToAddImage.setLayoutY(110);
        cardToAddImage.setFocusTraversable(false);
        cardToAddImage.setMaxWidth(200);
        cardToAddAttribute = new TextField();
        cardToAddAttribute.setPromptText("Insert attribute");
        cardToAddAttribute.setLayoutX(40);
        cardToAddAttribute.setLayoutY(140);
        cardToAddAttribute.setFocusTraversable(false);
        cardToAddAttribute.setMaxWidth(200);
        cardToAddType = new TextField();
        cardToAddType.setPromptText("Insert type(s)");
        cardToAddType.setLayoutX(40);
        cardToAddType.setLayoutY(170);
        cardToAddType.setFocusTraversable(false);
        cardToAddType.setMaxWidth(200);
        cardToAddArchetype = new TextField();
        cardToAddArchetype.setPromptText("Insert archetype");
        cardToAddArchetype.setLayoutX(40);
        cardToAddArchetype.setLayoutY(200);
        cardToAddArchetype.setFocusTraversable(false);
        cardToAddArchetype.setMaxWidth(200);
        cardToAddLevel = new TextField();
        cardToAddLevel.setPromptText("Insert level");
        cardToAddLevel.setLayoutX(40);
        cardToAddLevel.setLayoutY(230);
        cardToAddLevel.setFocusTraversable(false);
        cardToAddLevel.setMaxWidth(200);
        cardToAddAtk = new TextField();
        cardToAddAtk.setPromptText("Insert attack value");
        cardToAddAtk.setLayoutX(40);
        cardToAddAtk.setLayoutY(260);
        cardToAddAtk.setFocusTraversable(false);
        cardToAddAtk.setMaxWidth(200);
        cardToAddDef = new TextField();
        cardToAddDef.setPromptText("Insert defense value");
        cardToAddDef.setLayoutX(40);
        cardToAddDef.setLayoutY(290);
        cardToAddDef.setFocusTraversable(false);
        cardToAddDef.setMaxWidth(200);
        cardToAddEffectTypes = new TextField();
        cardToAddEffectTypes.setPromptText("Insert effect types");
        cardToAddEffectTypes.setLayoutX(40);
        cardToAddEffectTypes.setLayoutY(320);
        cardToAddEffectTypes.setFocusTraversable(false);
        cardToAddEffectTypes.setMaxWidth(200);
        cardToAddDesc = new TextField();
        cardToAddDesc.setPromptText("Insert card description/effect");
        cardToAddDesc.setLayoutX(40);
        cardToAddDesc.setLayoutY(350);
        cardToAddDesc.setFocusTraversable(false);
        cardToAddDesc.setMinWidth(300);
        cardToAddDesc.setMinHeight(50);
        addCard = new Button("ADD");
        addCard.setLayoutX(40);
        addCard.setLayoutY(420);
        addCard.setMaxWidth(300);

        remove = new Label("Find Card:");
        remove.setLayoutX(290);
        remove.setLayoutY(50);
        cardToRemoveTitle = new TextField();
        cardToRemoveTitle.setLayoutX(290);
        cardToRemoveTitle.setLayoutY(80);
        cardToRemoveTitle.setFocusTraversable(false);
        cardToRemoveTitle.setPrefWidth(150);
        removeCard = new Button("FIND");
        removeCard.setLayoutY(80);
        removeCard.setLayoutX(450);
        removeCard.setMaxWidth(300);

        removeU = new Label("Find User:");
        removeU.setLayoutX(290);
        removeU.setLayoutY(115);
        userToRemove = new TextField();
        userToRemove.setLayoutX(290);
        userToRemove.setLayoutY(145);
        userToRemove.setFocusTraversable(false);
        userToRemove.setPrefWidth(150);
        removeUser = new Button("FIND");
        removeUser.setLayoutY(145);
        removeUser.setLayoutX(450);
        removeUser.setMaxWidth(300);

        removeD = new Label("Find Deck:");
        removeD.setLayoutX(290);
        removeD.setLayoutY(180);
        deckToRemove = new TextField();
        deckToRemove.setLayoutX(290);
        deckToRemove.setLayoutY(210);
        deckToRemove.setFocusTraversable(false);
        deckToRemove.setPrefWidth(150);
        removeDeck = new Button("FIND");
        removeDeck.setLayoutY(210);
        removeDeck.setLayoutX(450);
        removeDeck.setMaxWidth(300);

        vbox = new VBox();
        logout = new Button("LOGOUT");
        logout.setLayoutX(640);
        logout.setLayoutY(560);
        logout.setMaxWidth(300);

        browseCardResults = new ListView<>();
        browseCardResults.setLayoutY(105);
        browseCardResults.setLayoutX(290);
        browseCardResults.setMaxWidth(cardToRemoveTitle.getPrefWidth());
        browseCardResults.setMaxHeight(120);
        browseCardResults.setVisible(false);

        browseUserResults = new ListView<>();
        browseUserResults.setLayoutY(170);
        browseUserResults.setLayoutX(290);
        browseUserResults.setMaxWidth(userToRemove.getPrefWidth());
        browseUserResults.setMaxHeight(120);
        browseUserResults.setVisible(false);

        browseDeckResults = new ListView<>();
        browseDeckResults.setLayoutY(235);
        browseDeckResults.setLayoutX(290);
        browseDeckResults.setMaxWidth(userToRemove.getPrefWidth());
        browseDeckResults.setMaxHeight(120);
        browseDeckResults.setVisible(false);

        logText = new TextField();
        logText.setLayoutX(40);
        logText.setLayoutY(460);
        logText.setMinWidth(240);
        logText.setEditable(false);
    }

    public void updateBrowseCardResults(List<String> result){
        browseCardResults.getItems().clear();
        if(result.isEmpty()){
            browseCardResults.setVisible(false);
        }else{
            browseCardResults.setVisible(true);
            browseCardResults.getItems().addAll(result);
        }
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

    public void showUserFindResults(String username){

        userVbox = new VBox();

        HBox usernameBox = new HBox();
        Label usernameLabel = new Label("Username: ");
        usernameLabel.setStyle("-fx-font-weight: bold;");
        Text usernameText = new Text(username);

        usernameBox.getChildren().addAll(usernameLabel, usernameText);
        usernameBox.setStyle("-fx-font-size: 15");

        HBox commandBox = new HBox();
        Button action = new Button("ACTION");

        action.setPrefSize(100, 20);

        commandBox.getChildren().addAll(action);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));

        HBox changeBox = new HBox();
        TextField tf = new TextField();
        tf.setPromptText("Change Username");
        tf.setPrefSize(120, 20);
        changeBox.getChildren().addAll(tf);
        changeBox.setAlignment(Pos.CENTER);
        changeBox.setPadding(new Insets(10, 0,0, 0));

        HBox updateBox = new HBox();
        Button update = new Button("ACTION");

        update.setPrefSize(100, 20);

        updateBox.getChildren().addAll(update);
        updateBox.setAlignment(Pos.CENTER);
        updateBox.setPadding(new Insets(10, 0,0, 0));

        userVbox.getChildren().addAll(usernameBox,commandBox, changeBox, updateBox);


        userVbox.setLayoutY(60);
        userVbox.setLayoutX(520);
        userVbox.setMinWidth(250);
        userVbox.setMinHeight(100);
        userVbox.setStyle("-fx-background-color: DARKSLATEGRAY;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

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
        Button action = new Button("ACTION");

        action.setPrefSize(100, 20);

        commandBox.getChildren().addAll(action);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(20, 0,0, 0));


        deckVbox.getChildren().addAll(titleBox, creatorBox, commandBox);


        deckVbox.setLayoutY(60);
        deckVbox.setLayoutX(520);
        deckVbox.setMinWidth(250);
        deckVbox.setMinHeight(100);
        deckVbox.setStyle("-fx-background-color: DARKSLATEGRAY;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

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
        imageBox.setPadding(new Insets(20,0,0,0));

        HBox commandBox = new HBox();
        Button action = new Button("ACTION");

        action.setPrefSize(100, 20);

        commandBox.getChildren().addAll(action);
        commandBox.setAlignment(Pos.CENTER);
        commandBox.setPadding(new Insets(30, 0,0, 0));

        cardVbox.getChildren().addAll(titleBox,imageBox,commandBox);


        cardVbox.setLayoutY(60);
        cardVbox.setLayoutX(520);
        cardVbox.setMinWidth(250);
        cardVbox.setMinHeight(100);
        cardVbox.setStyle("-fx-background-color: DARKSLATEGRAY;" +
                " -fx-padding: 20;" +
                " -fx-border-style: solid;" +
                " -fx-border-color: black;");

    }


    public Node[] getNodes() {
        Node[] returnNode = { adminLabel, add, cardToAddTitle, cardToAddImage, cardToAddAtk, cardToAddDef, cardToAddLevel,
                cardToAddDesc, cardToAddType, cardToAddArchetype, cardToAddAttribute, cardToAddEffectTypes,
                remove, cardToRemoveTitle, removeCard, addCard, removeD, deckToRemove, removeDeck, removeU, userToRemove,
                removeUser, vbox, logout, browseCardResults, browseUserResults, browseDeckResults, logText};
        return returnNode;
    }

    public String getTitle(){
        return cardToAddTitle.getText();
    }

    public String getImageUrl(){
        return cardToAddImage.getText();
    }
    public String getAtk(){
        return cardToAddAtk.getText();
    }
    public String getDef(){
        return cardToAddDef.getText();
    }
    public String getLevel(){
        return cardToAddLevel.getText();
    }
    public String getDesc(){
        return cardToAddDesc.getText();
    }
    public String getType(){
        return cardToAddType.getText();
    }
    public String getArchetype(){
        return cardToAddArchetype.getText();
    }
    public String getAttribute(){
        return cardToAddAttribute.getText();
    }
    public String getEffectType(){
        return cardToAddEffectTypes.getText();
    }
    public String getCardToRemoveTitle(){
        return cardToRemoveTitle.getText();
    }

    public TextField getCardToRemoveTf(){
        return cardToRemoveTitle;
    }

    public String getUserToRemove(){
        return userToRemove.getText();
    }

    public TextField getUserToRemoveTf(){
        return userToRemove;
    }

    public Button getRemoveCard() {
        return removeCard;
    }

    public String getDeckToRemove(){
        return deckToRemove.getText();
    }

    public TextField getDeckToRemoveTf(){
        return deckToRemove;
    }

    public Button getRemoveDeck(){
        return removeDeck;
    }

    public Button getLogout() {
        return logout;
    }

    public Button getAddCard() {
        return addCard;
    }

    public Button getRemoveUser() {
        return removeUser;
    }

    public ListView<String> getBrowseCardResults() {
        return browseCardResults;
    }

    public ListView<String> getBrowseUserResults() {
        return browseUserResults;
    }

    public ListView<String> getBrowseDeckResults() {
        return browseDeckResults;
    }

    public VBox getUserVbox() {
        return userVbox;
    }

    public VBox getCardVbox() {
        return cardVbox;
    }

    public VBox getDeckVbox(){
        return deckVbox;
    }

    public void clearAddCard(){
        cardToAddTitle.setText("");
        cardToAddImage.setText("");
        cardToAddAttribute.setText("");
        cardToAddType.setText("");
        cardToAddArchetype.setText("");
        cardToAddLevel.setText("");
        cardToAddAtk.setText("");
        cardToAddDef.setText("");
        cardToAddEffectTypes.setText("");
        cardToAddDesc.setText("");
    }

    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }
}