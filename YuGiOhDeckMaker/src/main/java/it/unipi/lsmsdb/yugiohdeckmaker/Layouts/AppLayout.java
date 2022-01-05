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
    private Button bDeckOpener;
    private Button bSocial;
    private Button bLogout;
    public AppLayout(){
        title = new Label("Welcome in YugiOhDeckMaker!");
        title.setLayoutX(280);
        title.setLayoutY(20);
        bDeckBuilder = new Button("CREATE A DECK");
        bDeckBuilder.setLayoutX(280);
        bDeckBuilder.setLayoutY(80);
        bDeckManager = new Button("BROWSE YOUR DECKS");
        bDeckManager.setLayoutX(280);
        bDeckManager.setLayoutY(130);
        bDeckOpener = new Button("OPEN A DECK");
        bDeckOpener.setLayoutX(280);
        bDeckOpener.setLayoutY(180);
        bSocial = new Button("SEARCH NEW DECKS");
        bSocial.setLayoutX(280);
        bSocial.setLayoutY(230);
        bLogout = new Button("LOGOUT");
        bLogout.setLayoutX(280);
        bLogout.setLayoutY(280);

    }

    public static void createDeck(){

    }

    public static void openSocial(){

    }

    public static void logout(){

    }

    public static void openDeckManager(){

    }
    public Node[] getNodes() {
        Node[] returnNode = {title, bDeckBuilder, bDeckOpener,bDeckManager, bSocial, bLogout};
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

    public Button getbDeckOpener() {
        return bDeckOpener;
    }

    public Button getbSocial() {
        return bSocial;
    }
}