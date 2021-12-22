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
public class DeckBuilderLayout {
    private Label title;
    private static TextField deckTitle;
    private Label add;
    private static TextField cardToAdd;
    private Button addCard;
    private Label remove;
    private static TextField cardToRemove;
    private Button removeCard;
    private Label findCard;
    private static TextField setName;
    private Label mostAtk;
    private Button findStrongest;
    private Label rarest;
    private Button findRarest;
    private Button save;
    private Button back;
    private static DeckBuilder db;
    public DeckBuilderLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(40);
        title.setLayoutY(30);
        deckTitle = new TextField();
        deckTitle.setLayoutX(100);
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
    	save.setLayoutX(50);
        save.setLayoutY(400);
    	save.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(520);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        findStrongest.setOnAction((ActionEvent ev)->{LoginManager.login();});	
        addCard.setOnAction((ActionEvent ev)->{LoginManager.signup();});
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        db = new DeckBuilder();
    }
    public Node[] getNodes() {
    	Node[] returnNode = { title, deckTitle, add, cardToAdd, addCard, remove, cardToRemove, removeCard, mostAtk, findStrongest, rarest, findRarest, findCard ,setName, save, back};
    	return returnNode;
    }
    
    public static String getCardToAdd(){
        return cardToAdd.getText();
    }
    
    public static String getCardToRemove(){
        return cardToRemove.getText();
    }
    
    public static String getSetName(){
        return setName.getText();
    }
}
