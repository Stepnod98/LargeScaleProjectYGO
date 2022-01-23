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

/**
 *
 * @author Stefano
 */
public class AppLayout {
    private Label title;
    private Button bDeckBuilder;
    private Button bDeckManager;
    private Button bSocial;
    private Button bLogout;
    public AppLayout(){
        title = new Label("Welcome in YugiOhDeckMaker!");
        title.setLayoutX(190);
        title.setLayoutY(20);
        title.setId("title");
        bDeckBuilder = new Button("CREATE A DECK");
        bDeckBuilder.setLayoutX(280);
        bDeckBuilder.setLayoutY(80);
        bDeckBuilder.setMinWidth(200);
        bDeckManager = new Button("BROWSE YOUR DECKS");
        bDeckManager.setLayoutX(280);
        bDeckManager.setLayoutY(130);
        bDeckManager.setMinWidth(200);
        bSocial = new Button("SEARCH NEW DECKS");
        bSocial.setLayoutX(280);
        bSocial.setLayoutY(180);
        bSocial.setMinWidth(200);
        bLogout = new Button("LOGOUT");
        bLogout.setLayoutX(280);
        bLogout.setLayoutY(230);
        bLogout.setMinWidth(200);

    }
    public Node[] getNodes() {
        Node[] returnNode = {title, bDeckBuilder,bDeckManager, bSocial, bLogout};
        return returnNode;
    }

    public Button getbDeckBuilder() {
        return bDeckBuilder;
    }

    public Button getbDeckManager() {
        return bDeckManager;
    }

    public Button getbLogout() {
        return bLogout;
    }

    public Button getbSocial() {
        return bSocial;
    }
}