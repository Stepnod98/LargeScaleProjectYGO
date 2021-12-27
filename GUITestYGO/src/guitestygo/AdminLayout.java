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
    private Label cardTitle;
    private Label cardSet;
    private static TextField cardToRemoveTitle;
    private static TextField cardToRemoveSet;
    protected Button removeCard;
    protected Button addCard;
    protected Button logout;
    
    public AdminLayout(){
        adminLabel = new Label("Admin Control Panel");
        adminLabel.setLayoutX(50);
        adminLabel.setLayoutY(40);
        add = new Label("Add Card:");
        add.setLayoutX(520);
        add.setLayoutY(40);
        cardToAddTitle = new TextField();
        cardToAddTitle.setLayoutX(520);
        cardToAddTitle.setLayoutY(80);
        cardToAddTitle.setFocusTraversable(false);
        cardToAddTitle.setMaxWidth(200);
        remove = new Label("Remove Card:");
        remove.setLayoutX(520);
        remove.setLayoutY(240);
        cardToRemoveTitle = new TextField();
        cardToRemoveTitle.setLayoutX(520);
        cardToRemoveTitle.setLayoutY(280);
        cardToRemoveTitle.setFocusTraversable(false);
        cardToRemoveTitle.setMaxWidth(200);
        removeCard = new Button("REMOVE");
    	removeCard.setLayoutY(320);
    	removeCard.setLayoutX(520);
    	removeCard.setMaxWidth(300);
        addCard = new Button("ADD");
    	addCard.setLayoutX(50);
        addCard.setLayoutY(400);
    	addCard.setMaxWidth(300);
        logout = new Button("BACK");
    	logout.setLayoutX(640);
        logout.setLayoutY(560);
    	logout.setMaxWidth(300);
    }
    
     public Node[] getNodes() {
    	Node[] returnNode = { adminLabel, add, cardToAddTitle,
                            remove, cardToRemoveTitle, removeCard, addCard, logout};
    	return returnNode;
    }
}
