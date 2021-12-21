/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Stefano
 */
public class GUIManager extends Application {
    
    private static AppLayout appLayout;
    private static DeckManagerLayout deckMLayout;
    private static DeckBuilderLayout deckBLayout;
    private static SocialLayout socialLayout;
    private static LoginLayout loginLayout;
    private static DeckLayout deckLayout;
    public static Scene scene;
    public static Group root;
    public static Pane p;
    public static Node[] newNode; //for cleaning issues

    @Override
    public void start(Stage stage){
        root = setUI();
        scene = new Scene(root, 800, 600);
        stage.setTitle("YGODeckMaker");
        stage.setScene(scene);
       /* stage.setOnCloseRequest(
            (WindowEvent ev)->{
                GameDataManager.saveGame(); 
                try {
                   GameDataManager.sendLog(TopLayout.getUsername(), "GAME CLOSED");
                } catch (IOException ie) {}
                }
            );*/
        stage.show();
    }
    public static void openAppManager(){
        root.getChildren().clear();
        appLayout = new AppLayout();
        Node[] tmp;
        tmp = appLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
    }
    public static void openSocialManager(){
        root.getChildren().clear();
        socialLayout = new SocialLayout();
        Node[] tmp;
        tmp = socialLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
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
    }
    public static void openDeckManager(){
        root.getChildren().clear();
        deckMLayout = new DeckManagerLayout();
        Node[] tmp;
        tmp = deckMLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
    }
    public static void openLoginManager(){
        root.getChildren().clear();
        loginLayout = new LoginLayout();
        Node[] tmp;
        tmp = loginLayout.getNodes();
        for (Node n: tmp) {
            root.getChildren().add(n);
        }
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
    public static void main(String args[]){
        Application.launch(args);
    }
    
}
