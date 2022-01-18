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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/**
 *
 * @author Stefano
 */
public class DeckOpenerLayout {
    private Label title;
    private  TextField deckTitle;
    protected Button open;
    protected Button back;
    private ListView<String> browseDeckResults;

    public DeckOpenerLayout(){
        title = new Label("Insert Title");
        title.setLayoutX(40);
        title.setLayoutY(30);
        deckTitle = new TextField();
        deckTitle.setLayoutX(130);
        deckTitle.setLayoutY(30);
        deckTitle.setFocusTraversable(false);
        deckTitle.setPrefWidth(200);
        open = new Button("OPEN");
        open.setLayoutX(340);
        open.setLayoutY(30);
        open.setMaxWidth(300);
        back = new Button("BACK");
        back.setLayoutX(640);
        back.setLayoutY(560);
        back.setMaxWidth(300);

        browseDeckResults = new ListView<>();
        browseDeckResults.setLayoutY(55);
        browseDeckResults.setLayoutX(130);
        browseDeckResults.setMaxWidth(deckTitle.getPrefWidth());
        browseDeckResults.setMaxHeight(120);
        browseDeckResults.setVisible(false);

    }

    public void updateBrowseDeckResults(List<String> result){
        browseDeckResults.getItems().clear();
        if(result.isEmpty()){
            browseDeckResults.setVisible(false);
        }else{
            browseDeckResults.setVisible(true);
            browseDeckResults.getItems().addAll(result);
        }
    }

    public Node[] getNodes() {
    	Node[] returnNode = { title, deckTitle, open, back, browseDeckResults};
    	return returnNode;
    }
    
    public String getTitle(){
        return deckTitle.getText();
    }

    public Button getBack() {
        return back;
    }

    public Button getOpen() {
        return open;
    }

    public TextField getDeckTitle() {
        return deckTitle;
    }

    public ListView<String> getBrowseDeckResults() {
        return browseDeckResults;
    }
}
