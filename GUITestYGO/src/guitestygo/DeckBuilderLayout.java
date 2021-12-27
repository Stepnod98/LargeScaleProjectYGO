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
    protected Button addCardByTitle;
    protected Button addCardByAtk;
    protected Button addCardByDef;
    private Label remove;
    private static TextField cardToRemove;
    protected Button removeCard;
    private Label findCard;
    private Label cardfound;
    private static TextField setName;
    private Label mostAtk;
    protected Button findStrongest;
    private Label rarest;
    protected Button findRarest;
    protected Button save;
    protected Button back;
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
        addCardByTitle = new Button("ADD BY TITLE");
    	addCardByTitle.setLayoutY(120);
    	addCardByTitle.setLayoutX(520);
    	addCardByTitle.setMaxWidth(100);
        addCardByAtk = new Button("ADD BY ATK");
    	addCardByAtk.setLayoutY(160);
    	addCardByAtk.setLayoutX(520);
    	addCardByAtk.setMaxWidth(100);
        addCardByDef = new Button("ADD BY DEF");
    	addCardByDef.setLayoutY(200);
    	addCardByDef.setLayoutX(520);
    	addCardByDef.setMaxWidth(100);
        remove = new Label("Remove Card:");
        remove.setLayoutX(520);
        remove.setLayoutY(240);
        cardToRemove = new TextField();
        cardToRemove.setLayoutX(520);
        cardToRemove.setLayoutY(280);
        cardToRemove.setFocusTraversable(false);
        cardToRemove.setMaxWidth(200);
        removeCard = new Button("REMOVE BY TITLE");
    	removeCard.setLayoutY(320);
    	removeCard.setLayoutX(520);
    	removeCard.setMaxWidth(300);
        findCard = new Label("Find Card in set:");
        findCard.setLayoutX(480);
        findCard.setLayoutY(400);
        setName = new TextField();
        setName.setLayoutX(480);
        setName.setLayoutY(440);
        setName.setFocusTraversable(false);
        setName.setMaxWidth(300);
        mostAtk = new Label("Most ATK");
        mostAtk.setLayoutX(500);
        mostAtk.setLayoutY(480);
        findStrongest = new Button("FIND");
    	findStrongest.setLayoutY(480);
    	findStrongest.setLayoutX(560);
    	findStrongest.setMaxWidth(300);
        rarest = new Label("Rarest");
        rarest.setLayoutX(500);
        rarest.setLayoutY(520);
        findRarest = new Button("FIND");
    	findRarest.setLayoutY(520);
    	findRarest.setLayoutX(560);
    	findRarest.setMaxWidth(300);
        save = new Button("SAVE");
    	save.setLayoutX(50);
        save.setLayoutY(400);
    	save.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        /*findStrongest.setOnAction((ActionEvent ev)->{LoginManager.login();});	
        addCard.setOnAction((ActionEvent ev)->{LoginManager.signup();});
        back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});*/
    }
    public Node[] getNodes() {
    	Node[] returnNode = { title, deckTitle, add, cardToAdd, addCardByTitle, addCardByAtk, addCardByDef,
                            remove, cardToRemove, removeCard, mostAtk, findStrongest, rarest, findRarest,
                            findCard ,setName, save, back};
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
    
    public static String getDeckTitle(){
        return deckTitle.getText();
    }
    
    public void showCard(String c){
        cardfound = new Label(c);
        cardfound.setLayoutX(580);
        cardfound.setLayoutY(400);
    }
}
