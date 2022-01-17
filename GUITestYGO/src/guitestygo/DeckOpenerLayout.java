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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Stefano
 */
public class DeckOpenerLayout {
    private Label title;
    private static TextField deckTitle;
    protected Button open;
    private VBox vbox;
    protected Button back;
    public DeckOpenerLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(40);
        title.setLayoutY(30);
        deckTitle = new TextField();
        deckTitle.setLayoutX(130);
        deckTitle.setLayoutY(30);
        deckTitle.setFocusTraversable(false);
        deckTitle.setMaxWidth(200);
        open = new Button("OPEN");
    	open.setLayoutX(340);
        open.setLayoutY(30);
    	open.setMaxWidth(300);
        vbox = new VBox();
        back = new Button("BACK");
    	back.setLayoutX(640);
        back.setLayoutY(560);
    	back.setMaxWidth(300);
        //open.setOnAction((ActionEvent ev)->{DeckOpener.openDeck();});
        //back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
    public Node[] getNodes() {
    	Node[] returnNode = { title, deckTitle, open, vbox, back};
    	return returnNode;
    }
    
    public static String getTitle(){
        return deckTitle.getText();
    }
    
    public static TextField getTitleTf(){
        return deckTitle;
    }
    
    public Button getBack() {
        return back;
    }

    public Button getOpen() {
        return open;
    }
    
    public void clearErrors(){
        vbox.getChildren().clear();
    }
    
    public void showListResults(BorderPane bp, int x, int y){
        if(bp == null){
            return;
        }
        vbox.setLayoutX(x);
        vbox.setLayoutY(y);
        vbox.setMaxHeight(60);
        vbox.setMaxWidth(150);
        vbox.getChildren().addAll(bp);
    }
}
