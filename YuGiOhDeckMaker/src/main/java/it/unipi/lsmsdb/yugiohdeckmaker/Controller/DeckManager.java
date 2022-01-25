/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import static java.lang.Integer.parseInt;
import java.util.*;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckManagerLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class DeckManager {
    private DeckManagerLayout deckManagerLayout;
    public DeckManager(DeckManagerLayout deckManagerLayout){
        this.deckManagerLayout = deckManagerLayout;
        
    }
    public void findDeck(){
        String title = deckManagerLayout.getDeckToBrowse();
        if(!MongoDBManager.existsDeck(title))
            return;
        GUIManager.clearDeckManagerBoxes();
        GUIManager.clearDeckManagerLayout();
        deckManagerLayout.showDeckFindResults(title,GUIManager.getCurrentUser());
        setDeckVbox(title);
        GUIManager.addNode(deckManagerLayout.getDeckVbox());
    }

    public void findYourDecks(){
        GUIManager.clearDeckManagerLayout();
        GUIManager.clearDeckManagerBoxes();
        deckManagerLayout.showDeckResults(MongoDBManager.getDecks(GUIManager.getCurrentUser()));
        setDeckResultEvents();
        GUIManager.addNode(deckManagerLayout.getDeckResultTable());
    }
    
    public void findMagicTrapDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findMagicTrapDeck();
        GUIManager.clearDeckManagerLayout();
        GUIManager.clearDeckManagerBoxes();
        deckManagerLayout.showDeckResults(topList);
        setDeckResultEvents();
        GUIManager.addNode(deckManagerLayout.getDeckResultTable());
        
    }
     
    public void findArchetypeDeck(){
        GUIManager.clearDeckManagerLayout();
        GUIManager.clearDeckManagerBoxes();
        deckManagerLayout.showDeckResults(MongoDBManager.findArchetypeDeck());
        setDeckResultEvents();
        GUIManager.addNode(deckManagerLayout.getDeckResultTable());
    }

    // TODO: 23/01/2022 usare is numeric!!
    public void findAvgAtkDecks(){
        if(isNumeric(deckManagerLayout.getAvgAtk())) {
            List<String> topList = new ArrayList<>();
            topList = MongoDBManager.findMostAvgAtk(Integer.parseInt(deckManagerLayout.getAvgAtk()));
            GUIManager.clearDeckManagerLayout();
            GUIManager.clearDeckManagerBoxes();
            deckManagerLayout.showDeckResults(topList);
            setDeckResultEvents();
            GUIManager.addNode(deckManagerLayout.getDeckResultTable());
        }
    }
    
    public void setEvents(){

        
        deckManagerLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        deckManagerLayout.getFindMagicTrapDecks().setOnAction((ActionEvent ev)->{findMagicTrapDeck();});
        deckManagerLayout.getFindArchetypeDecks().setOnAction((ActionEvent ev)->{findArchetypeDeck();});
        deckManagerLayout.getMostAvgAtkDecks().setOnAction((ActionEvent ev)->{findAvgAtkDecks();});
        deckManagerLayout.getShowYourDecksButton().setOnAction((ActionEvent ev)->{findYourDecks();});
        deckManagerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});

        deckManagerLayout.getDeckToBrowseTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckManagerLayout.getBrowseDeckToFind().setVisible(false);
                }else {

                    deckManagerLayout.updateBrowseDeckToFind(MongoDBManager.findYourDecks(newValue));
                }
            }
        });

        deckManagerLayout.getDeckToBrowseTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    deckManagerLayout.getBrowseDeckToFind().setVisible(false);
                }
            }
        });

        deckManagerLayout.getBrowseDeckToFind().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    deckManagerLayout.getBrowseDeckToFind().setVisible(true);
                }else {
                    deckManagerLayout.getBrowseDeckToFind().setVisible(false);
                }
            }
        });

        setBrowseEvents();
    }

    private void setDeckResultEvents(){
        deckManagerLayout.getDeckResultTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showDeckTask());
                System.out.println(newValue);
            }
        });
    }

    private void setDeckVbox(String title){
        HBox hBox = (HBox) deckManagerLayout.getDeckVbox().getChildren().get(deckManagerLayout.getDeckVbox().getChildren().size()-1);

        ((Button)hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            Deck d = MongoDBManager.findDeck(title);
            GUIManager.openDeckBuilder(d);
            hBox.getChildren().get(0).setDisable(true);
        });

        ((Button)hBox.getChildren().get(1)).setOnAction((ActionEvent ev) -> {
            if(MongoDBManager.existsDeck(title)){
                MongoDBManager.removeDeck(title);
                Neo4jManager.delete(new Deck(title));
                GUIManager.clearDeckManagerLayout();
                GUIManager.clearDeckManagerBoxes();
            }
        });
    }

    private void setBrowseEvents(){
        deckManagerLayout.getBrowseDeckToFind().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardToAddTask());
            }
        });
    }

    private void browseCardToAddTask(){
        if(deckManagerLayout.getBrowseDeckToFind().getSelectionModel().getSelectedItem() != null) {
            String selected = deckManagerLayout.getBrowseDeckToFind().getSelectionModel().getSelectedItem();
            deckManagerLayout.getBrowseDeckToFind().getSelectionModel().clearSelection();
            deckManagerLayout.getDeckToBrowseTf().setText(selected);
            deckManagerLayout.getBrowseDeckToFind().setVisible(false);
        }
    }

    private void showDeckTask(){
        if(deckManagerLayout.getDeckResultTable().getSelectionModel().getSelectedItem() != null) {
            String title = deckManagerLayout.getDeckResultTable().getSelectionModel().getSelectedItem();
            GUIManager.clearDeckManagerBoxes();

            deckManagerLayout.showDeckFindResults(title,GUIManager.getCurrentUser());
            setDeckVbox(title);

            GUIManager.addNode(deckManagerLayout.getDeckVbox());

        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException | ClassCastException e){
            return false;
        }
    }
}
