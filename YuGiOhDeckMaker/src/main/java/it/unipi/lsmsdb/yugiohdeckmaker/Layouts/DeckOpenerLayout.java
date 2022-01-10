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
import javafx.scene.control.TextField;

/**
 *
 * @author Stefano
 */
public class DeckOpenerLayout {
    private Label title;
    private static TextField deckTitle;
    protected Button open;
    protected Button back;
    public DeckOpenerLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(40);
        title.setLayoutY(30);
        deckTitle = new TextField();
        deckTitle.setLayoutX(100);
        deckTitle.setLayoutY(30);
        deckTitle.setFocusTraversable(false);
        deckTitle.setMaxWidth(200);
        open = new Button("OPEN");
    	open.setLayoutX(520);
        open.setLayoutY(120);
    	open.setMaxWidth(300);
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        //open.setOnAction((ActionEvent ev)->{DeckOpener.openDeck();});
        //back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    public Node[] getNodes() {
    	Node[] returnNode = { title, deckTitle, open, back};
    	return returnNode;
    }
    
    public static String getTitle(){
        return deckTitle.getText();
    }

    public Button getBack() {
        return back;
    }

    public Button getOpen() {
        return open;
    }
}
