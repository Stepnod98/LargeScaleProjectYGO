/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class DeckBuilderLayout {
    private Label add;
    private TextField cardToAdd;
    private Button addCard;
    private Label remove;
    private TextField cardToRemove;
    private Button removeCard;
    private Label findCard;
    private TextField setName;
    private Label mostAtk;
    private Button findStrongest;
    private Label rarest;
    private Button findRarest;
    private Button save;
    private Button back;
    public DeckBuilderLayout(){
        add = new Label("Add Card:");
        add.setLayoutX(520);
        add.setLayoutY(40);
        cardToAdd = new TextField();
        cardToAdd.setLayoutX(520);
        cardToAdd.setLayoutY(80);
        cardToAdd.setFocusTraversable(false);
        cardToAdd.setMaxWidth(200);
        addCard = new Button("ADD");
    	addCard.setLayoutY(120);
    	addCard.setLayoutX(520);
    	addCard.setMaxWidth(100);
        remove = new Label("Remove Card:");
        remove.setLayoutX(520);
        remove.setLayoutY(160);
        cardToRemove = new TextField();
        cardToRemove.setLayoutX(520);
        cardToRemove.setLayoutY(200);
        cardToRemove.setFocusTraversable(false);
        cardToRemove.setMaxWidth(200);
        removeCard = new Button("REMOVE");
    	removeCard.setLayoutY(240);
    	removeCard.setLayoutX(520);
    	removeCard.setMaxWidth(300);
        findCard = new Label("Find Card in");
        findCard.setLayoutX(480);
        findCard.setLayoutY(320);
        setName = new TextField();
        setName.setLayoutX(480);
        setName.setLayoutY(340);
        setName.setFocusTraversable(false);
        setName.setMaxWidth(300);
        mostAtk = new Label("Most ATK");
        mostAtk.setLayoutX(500);
        mostAtk.setLayoutY(380);
        findStrongest = new Button("FIND");
    	findStrongest.setLayoutY(380);
    	findStrongest.setLayoutX(560);
    	findStrongest.setMaxWidth(300);
        rarest = new Label("Rarest");
        rarest.setLayoutX(500);
        rarest.setLayoutY(440);
        findRarest = new Button("FIND");
    	findRarest.setLayoutY(440);
    	findRarest.setLayoutX(560);
    	findRarest.setMaxWidth(300);
        save = new Button("SAVE");
    	save.setLayoutX(40);
        save.setLayoutY(560);
    	save.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(520);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        findStrongest.setOnAction((ActionEvent ev)->{});
        addCard.setOnAction((ActionEvent ev)->{LoginManager.signup();});
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    public Node[] getNodes() {
    	Node[] returnNode = { add, cardToAdd, addCard, remove, cardToRemove, removeCard, mostAtk, findStrongest, rarest, findRarest, findCard ,setName, save, back};
    	return returnNode;
    }
}
