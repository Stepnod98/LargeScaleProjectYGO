/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.*;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class GUIManager extends Application {
    
    private static AppLayout appLayout;
    private static AppLayoutManager appLayoutManager;
    private static DeckManager deckManager;
    private static DeckManagerLayout deckMLayout;
    private static DeckBuilder deckBuilder;
    private static DeckBuilderLayout deckBLayout;
    private static DeckOpenerLayout deckOLayout;
    private static DeckOpener deckOpener;
    private static SocialLayout socialLayout;
    private static SocialManager socialManager;
    private static LoginLayout loginLayout;
    private static LoginManager loginManager;
    private static DeckLayout deckLayout;
    private static AdminLayout adminLayout;
    private static AdminPanel adminPanel;
    private static SignUpLayout signUpLayout;
    private static SignUpManager signUpManager;
    public static Scene scene;
    public static Group root;
    public static Pane p;
    public static Node[] newNode; //for cleaning issues
    public static User currentUser;
    private static Scene card;

    @Override
    public void start(Stage stage){
        root = setUI();
        scene = new Scene(root, 800, 600);
        stage.setTitle("YGODeckMaker");
        stage.setScene(scene);
        scene.getStylesheets().add("file:style/loginStyle.css");
        scene.setFill(Color.DARKSLATEGRAY);
        loginManager = new LoginManager(loginLayout);
        stage.show();
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    //Modified to add the currentUser information
    public static void openAppManager(){
        root.getChildren().clear();
        appLayout = new AppLayout();
        Node[] tmp;
        tmp = appLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        scene.setFill(new ImagePattern(new Image("file:img/backgroundapp.jpg")));
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:style/builderStyle.css");
        appLayoutManager = new AppLayoutManager(appLayout,currentUser);
    }
    public static void openSocialManager(User currentUser){
        root.getChildren().clear();
        socialLayout = new SocialLayout();
        Node[] tmp;
        tmp = socialLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        socialManager = new SocialManager(socialLayout,currentUser);
    }
    public static void openDeckBuilder(){
        root.getChildren().clear();
        deckBLayout = new DeckBuilderLayout();
        deckLayout = new DeckLayout();
        Node[] tmp;
        tmp = deckBLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        p = (Pane)deckLayout.getGameParent();
        p.setLayoutX(40);
        p.setLayoutY(120);
        root.getChildren().add(p);

        scene.setFill(new ImagePattern(new Image("file:img/backgroundyugioh.png")));
        deckBuilder = new DeckBuilder(deckBLayout, deckLayout);
        deckBuilder.setEvents();
    }

    public static void openDeckBuilder(Deck d){
        root.getChildren().clear();
        deckBLayout = new DeckBuilderLayout();
        deckLayout = new DeckLayout(d);

        Node[] tmp;
        tmp = deckBLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        p = (Pane)deckLayout.getGameParent();
        p.setLayoutX(40);
        p.setLayoutY(120);
        root.getChildren().add(p);
        scene.setFill(new ImagePattern(new Image("file:img/backgroundyugioh.png")));
        deckBuilder = new DeckBuilder(deckBLayout, deckLayout, d);
        deckBuilder.setEvents();
    }

    public static void openDeckBuilder(Deck d, DeckLayout deckLayout){
        root.getChildren().clear();
        deckBLayout = new DeckBuilderLayout();
        GUIManager.deckLayout = deckLayout;
        Node[] tmp;
        tmp = deckBLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        p = (Pane)deckLayout.getGameParent();
        p.setLayoutX(40);
        p.setLayoutY(120);
        root.getChildren().add(p);
        deckBuilder = new DeckBuilder(deckBLayout, deckLayout);
        deckBuilder.setEvents();
    }

    public static void openDeckOpener(){
        root.getChildren().clear();
        deckOLayout = new DeckOpenerLayout();
        deckLayout = new DeckLayout();
        Node[] tmp;
        tmp = deckOLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        p = (Pane)deckLayout.getGameParent();
        p.setLayoutX(40);
        p.setLayoutY(120);
        root.getChildren().add(p);
        scene.setFill(new ImagePattern(new Image("file:img/backgroundyugioh.png")));
        deckOpener = new DeckOpener(deckOLayout);
        deckOpener.setEvents();
    }

    public static void openDeckManager(){
        root.getChildren().clear();
        deckMLayout = new DeckManagerLayout();
        Node[] tmp;
        tmp = deckMLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        deckManager = new DeckManager(deckMLayout);
        deckManager.setEvents();
        scene.setFill(new ImagePattern(new Image("file:img/backgroundyugioh.png")));
    }

    public static void openDeckManagerResults(DeckManagerLayout dm){
        root.getChildren().clear();
        deckMLayout = new DeckManagerLayout();
        Node[] tmp;
        tmp = deckMLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        root.getChildren().add(dm.getTableNodes());

        deckManager = new DeckManager(deckMLayout);
        deckManager.setEvents();
    }

    public static void openLoginManager(){
        root.getChildren().clear();
        loginLayout = new LoginLayout();
        Node[] tmp;
        tmp = loginLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }

        scene.setFill(Color.DARKSLATEGRAY);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:style/loginStyle.css");
        loginManager = new LoginManager(loginLayout);
    }

    public static void openSignUpManager(){
        root.getChildren().clear();
        signUpLayout = new SignUpLayout();
        Node[] tmp;
        tmp = signUpLayout.getSignUpNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        signUpManager = new SignUpManager(signUpLayout);
    }

    public static void openAdminPanel(){
        root.getChildren().clear();
        adminLayout = new AdminLayout();
        Node[] tmp;
        tmp = adminLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        scene.getStylesheets().clear();
        scene.setFill(new ImagePattern(new Image("file:img/backgroundapp.jpg")));
        scene.getStylesheets().add("file:style/builderStyle.css");
        adminPanel = new AdminPanel(adminLayout);
        adminPanel.setEvents();
    }
    
    public static Group setUI() {
        loginLayout = new LoginLayout();
        Group root = new Group();
        Node[] tmp;

        tmp = loginLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
        return root;
    }

    public static void openCard(Image image, String title){
        GridPane pane = new GridPane();
        ImageView img = new ImageView(image);
        pane.getChildren().addAll(img);
        card = new Scene(pane, 400, 600);
        Stage stage = new Stage();
        stage.setScene(card);
        stage.setTitle(title);
        stage.show();
    }

    // TODO: 29/12/2021 Added this on this day
    public static void addNode(Node node){
        root.getChildren().add(node);
    }

    public static  void removeNode(Node node){
        root.getChildren().remove(node);
    }

    public static void clearSocialLayout(){
        if(socialLayout.getUserVbox() != null)
            removeNode(socialLayout.getUserVbox());

        if(socialLayout.getDeckVbox() != null)
            removeNode(socialLayout.getDeckVbox());
    }

    public static void clearRecSocialLayout(){
        if(socialLayout.getFindUserRecTable() != null)
            removeNode(socialLayout.getFindUserRecTable());

        if(socialLayout.getFindDeckRecTable() != null)
            removeNode(socialLayout.getFindDeckRecTable());
    }

    public static void clearProfSocialLayout(){
        if(socialLayout.getUserProfileVbox() != null)
            removeNode(socialLayout.getUserProfileVbox());

        clearProfTables();

        if(socialLayout.getViewFriends() != null)
            removeNode(socialLayout.getViewFriends());

        if(socialLayout.getViewFollowers() != null)
            removeNode(socialLayout.getViewFollowers());

        if(socialLayout.getViewSharedDecks() != null)
            removeNode(socialLayout.getViewSharedDecks());

        if(socialLayout.getViewLikedDecks() != null)
            removeNode(socialLayout.getViewLikedDecks());

        if(socialLayout.getViewRecentDecks() != null)
            removeNode(socialLayout.getViewRecentDecks());

        if(socialLayout.getFollowUserButton() != null)
            removeNode(socialLayout.getFollowUserButton());
    }

    public static void clearProfTables(){
        if(socialLayout.getSharedDecksTable() != null)
            removeNode(socialLayout.getSharedDecksTable());

        if(socialLayout.getFriendsTable() != null)
            removeNode(socialLayout.getFriendsTable());

        if(socialLayout.getFollowersTable() != null)
            removeNode(socialLayout.getFollowersTable());

        if(socialLayout.getLikedDecksTable() != null)
            removeNode(socialLayout.getLikedDecksTable());

        if(socialLayout.getRecentDecksTable() != null)
            removeNode(socialLayout.getRecentDecksTable());
    }



    public static String getCurrentUser() {
        return currentUser.username;
    }

    public static void main(String args[]){
        Application.launch(args);
    }

    
}
