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
    private static TextField cardToRemoveTitle;
    private static TextField cardToRemoveSet;
    protected Button removeCard;
    protected Button addCard;
    protected Button logout;
    
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
        cardToRemoveTitle = new TextField("Insert card title");
        cardToRemoveTitle.setLayoutX(520);
        cardToRemoveTitle.setLayoutY(80);
        cardToRemoveTitle.setFocusTraversable(false);
        cardToRemoveTitle.setMaxWidth(200);
        cardToRemoveSet = new TextField("Insert card set");
        cardToRemoveSet.setLayoutX(520);
        cardToRemoveSet.setLayoutY(120);
        cardToRemoveSet.setFocusTraversable(false);
        cardToRemoveSet.setMaxWidth(200);
        removeCard = new Button("REMOVE");
    	removeCard.setLayoutY(150);
    	removeCard.setLayoutX(520);
    	removeCard.setMaxWidth(300);
        logout = new Button("LOGOUT");
    	logout.setLayoutX(640);
        logout.setLayoutY(560);
    	logout.setMaxWidth(300);
    }
    
     public Node[] getNodes() {
    	Node[] returnNode = { adminLabel, add, cardToAddTitle, cardToAddImage, cardToAddAtk, cardToAddDef, cardToAddLevel,
                            cardToAddDesc, cardToAddType, cardToAddArchetype, cardToAddAttribute, cardToAddEffectTypes,
                            remove, cardToRemoveTitle, cardToRemoveSet, removeCard, addCard, logout};
    	return returnNode;
    }
}
