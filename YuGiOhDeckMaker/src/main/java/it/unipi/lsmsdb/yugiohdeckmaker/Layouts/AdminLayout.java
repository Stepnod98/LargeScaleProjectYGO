/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 *
 * @author Stefano
 */
public class AdminLayout {
    private Label adminLabel;
    private Label add;
    private static TextField cardToAddTitle;
    private static TextField cardToAddImage;
    private static TextField cardToAddAtk;
    private static TextField cardToAddDef;
    private static TextField cardToAddLevel;
    private static TextField cardToAddDesc;
    private static TextField cardToAddType;
    private static TextField cardToAddArchetype;
    private static TextField cardToAddAttribute;
    private static TextField cardToAddEffectTypes;
    private Label remove;
    private TextField cardToRemoveTitle;
    private Label removeU;
    private TextField userToRemove;
    protected Button removeCard;
    protected Button addCard;
    protected Button removeUser;
    private VBox vbox;
    protected Button logout;
    private ListView<String> browseCardResults;
    private ListView<String> browseUserResults;

    public AdminLayout(){
        adminLabel = new Label("Admin Control Panel");
        adminLabel.setLayoutX(40);
        adminLabel.setLayoutY(10);
        add = new Label("Add Card:");
        add.setLayoutX(40);
        add.setLayoutY(40);
        cardToAddTitle = new TextField("Insert title");
        cardToAddTitle.setLayoutX(40);
        cardToAddTitle.setLayoutY(80);
        cardToAddTitle.setFocusTraversable(false);
        cardToAddTitle.setMaxWidth(200);
        cardToAddImage = new TextField("Insert image url");
        cardToAddImage.setLayoutX(40);
        cardToAddImage.setLayoutY(110);
        cardToAddImage.setFocusTraversable(false);
        cardToAddImage.setMaxWidth(200);
        cardToAddAttribute = new TextField("Insert attribute");
        cardToAddAttribute.setLayoutX(40);
        cardToAddAttribute.setLayoutY(140);
        cardToAddAttribute.setFocusTraversable(false);
        cardToAddAttribute.setMaxWidth(200);
        cardToAddType = new TextField("Insert type(s)");
        cardToAddType.setLayoutX(40);
        cardToAddType.setLayoutY(170);
        cardToAddType.setFocusTraversable(false);
        cardToAddType.setMaxWidth(200);
        cardToAddArchetype = new TextField("Insert archetype");
        cardToAddArchetype.setLayoutX(40);
        cardToAddArchetype.setLayoutY(200);
        cardToAddArchetype.setFocusTraversable(false);
        cardToAddArchetype.setMaxWidth(200);
        cardToAddLevel = new TextField("Insert level");
        cardToAddLevel.setLayoutX(40);
        cardToAddLevel.setLayoutY(230);
        cardToAddLevel.setFocusTraversable(false);
        cardToAddLevel.setMaxWidth(200);
        cardToAddAtk = new TextField("Insert attack value");
        cardToAddAtk.setLayoutX(40);
        cardToAddAtk.setLayoutY(260);
        cardToAddAtk.setFocusTraversable(false);
        cardToAddAtk.setMaxWidth(200);
        cardToAddDef = new TextField("Insert defense value");
        cardToAddDef.setLayoutX(40);
        cardToAddDef.setLayoutY(290);
        cardToAddDef.setFocusTraversable(false);
        cardToAddDef.setMaxWidth(200);
        cardToAddEffectTypes = new TextField("Insert effect types");
        cardToAddEffectTypes.setLayoutX(40);
        cardToAddEffectTypes.setLayoutY(320);
        cardToAddEffectTypes.setFocusTraversable(false);
        cardToAddEffectTypes.setMaxWidth(200);
        cardToAddDesc = new TextField("Insert card description/effect");
        cardToAddDesc.setLayoutX(40);
        cardToAddDesc.setLayoutY(350);
        cardToAddDesc.setFocusTraversable(false);
        cardToAddDesc.setMinWidth(300);
        cardToAddDesc.setMinHeight(50);
        addCard = new Button("ADD");
        addCard.setLayoutX(40);
        addCard.setLayoutY(420);
        addCard.setMaxWidth(300);
        remove = new Label("Remove Card:");
        remove.setLayoutX(520);
        remove.setLayoutY(40);
        cardToRemoveTitle = new TextField();
        cardToRemoveTitle.setLayoutX(520);
        cardToRemoveTitle.setLayoutY(80);
        cardToRemoveTitle.setFocusTraversable(false);
        cardToRemoveTitle.setPrefWidth(150);
        removeCard = new Button("REMOVE");
        removeCard.setLayoutY(80);
        removeCard.setLayoutX(680);
        removeCard.setMaxWidth(300);
        removeU = new Label("Remove User:");
        removeU.setLayoutX(520);
        removeU.setLayoutY(180);
        userToRemove = new TextField();
        userToRemove.setLayoutX(520);
        userToRemove.setLayoutY(210);
        userToRemove.setFocusTraversable(false);
        userToRemove.setPrefWidth(150);
        removeUser = new Button("REMOVE");
        removeUser.setLayoutY(210);
        removeUser.setLayoutX(680);
        removeUser.setMaxWidth(300);
        vbox = new VBox();
        logout = new Button("LOGOUT");
        logout.setLayoutX(640);
        logout.setLayoutY(560);
        logout.setMaxWidth(300);

        browseCardResults = new ListView<>();
        browseCardResults.setLayoutY(105);
        browseCardResults.setLayoutX(520);
        browseCardResults.setMaxWidth(cardToRemoveTitle.getPrefWidth());
        browseCardResults.setMaxHeight(120);
        browseCardResults.setVisible(false);

        browseUserResults = new ListView<>();
        browseUserResults.setLayoutY(235);
        browseUserResults.setLayoutX(520);
        browseUserResults.setMaxWidth(userToRemove.getPrefWidth());
        browseUserResults.setMaxHeight(120);
        browseUserResults.setVisible(false);
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


    public Node[] getNodes() {
        Node[] returnNode = { adminLabel, add, cardToAddTitle, cardToAddImage, cardToAddAtk, cardToAddDef, cardToAddLevel,
                cardToAddDesc, cardToAddType, cardToAddArchetype, cardToAddAttribute, cardToAddEffectTypes,
                remove, cardToRemoveTitle, removeCard, addCard, removeU, userToRemove,
                removeUser, vbox, logout, browseCardResults, browseUserResults};
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

    public Button getLogout() {
        return logout;
    }

    public Button getAddCard() {
        return addCard;
    }

    public Button getRemoveUser() {
        return removeUser;
    }

    public void clearErrors(){
        vbox.getChildren().clear();
    }

    public ListView<String> getBrowseCardResults() {
        return browseCardResults;
    }

    public ListView<String> getBrowseUserResults() {
        return browseUserResults;
    }

}
