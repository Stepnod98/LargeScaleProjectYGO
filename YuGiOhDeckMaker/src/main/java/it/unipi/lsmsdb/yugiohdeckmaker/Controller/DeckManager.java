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

public class DeckManager {
    private DeckManagerLayout deckManagerLayout;
    public DeckManager(DeckManagerLayout deckManagerLayout){
        this.deckManagerLayout = deckManagerLayout;
        
    }
    public void findDeck(){
        String title = deckManagerLayout.getDeckToBrowse();
        if(!MongoDBManager.existsDeck(title))
            return;

        Deck d = MongoDBManager.findDeck(title);
        int i;
        List<String> result = new ArrayList<String>();
        for(i = 0; i < d.getCards().size(); i++){
            result.add(d.getCards().get(i).getTitle());
        }
        for(i = 0; i < d.getECards().size(); i++){
            result.add(d.getECards().get(i).getTitle());
        }
        deckManagerLayout.showDeckResults(result);
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public void removeDeck(){
        String title = deckManagerLayout.getDeckToRemove();
        if(MongoDBManager.existsDeck(title)){
            MongoDBManager.remove(title);
            Neo4jManager.delete(new Deck(title));
        }

    }

    
    public void findMagicTrapDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findMagicTrapDeck();
        //add elements to gui
        deckManagerLayout.showDeckResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("g1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showDeckResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
        
    }
     
    public void findArchetypeDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findArchetypeDeck();
        //add elements to gui
        deckManagerLayout.showDeckResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("gg1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showDeckResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public void findAvgAtkDecks(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findMostAvgAtk(Integer.parseInt(deckManagerLayout.getAvgAtk()));
        //add elements to gui
        deckManagerLayout.showDeckResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("gg1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showDeckResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public void setEvents(){
// TODO: 18/01/2022 togliere questa cosa 
        List<String> list = new ArrayList<>();
        list.add("te");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");
        list.add("s6");
        list.add("s7");
        
        deckManagerLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        deckManagerLayout.getRemoveDeck().setOnAction((ActionEvent ev)->{removeDeck();});
        deckManagerLayout.getFindMagicTrapDecks().setOnAction((ActionEvent ev)->{findMagicTrapDeck();});
        deckManagerLayout.getFindArchetypeDecks().setOnAction((ActionEvent ev)->{findArchetypeDeck();});
        deckManagerLayout.getMostAvgAtkDecks().setOnAction((ActionEvent ev)->{findAvgAtkDecks();});
        deckManagerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});

        deckManagerLayout.getDeckToBrowseTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckManagerLayout.getBrowseDeckToFind().setVisible(false);
                }else {

                    // TODO: 18/01/2022 Query Mongo sui tuoi deck!
                    //deckBuilderLayout.updateBrowseCardsToAdd(MongoDBManager.findCards(newValue));
                    deckManagerLayout.updateBrowseDeckToFind(list);
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

        deckManagerLayout.getDeckToRemoveTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckManagerLayout.getBrowseDeckToRemove().setVisible(false);
                }else {
                    // TODO: 18/01/2022 Query Mongo sui tuoi deck!
                    //deckBuilderLayout.updateBrowseCardsToRemove(MongoDBManager.findCards(newValue));
                    deckManagerLayout.updateBrowseDeckToRemove(list);
                }
            }
        });

        deckManagerLayout.getDeckToRemoveTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    deckManagerLayout.getBrowseDeckToRemove().setVisible(false);
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
        deckManagerLayout.getBrowseDeckToRemove().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    deckManagerLayout.getBrowseDeckToRemove().setVisible(true);
                }else {
                    deckManagerLayout.getBrowseDeckToRemove().setVisible(false);
                }
            }
        });

        setBrowseEvents();
    }

    private void setBrowseEvents(){
        deckManagerLayout.getBrowseDeckToFind().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardToAddTask());
            }
        });
        deckManagerLayout.getBrowseDeckToRemove().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardToRemoveTask());
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

    private void browseCardToRemoveTask(){
        if(deckManagerLayout.getBrowseDeckToRemove().getSelectionModel().getSelectedItem() != null) {
            String selected = deckManagerLayout.getBrowseDeckToRemove().getSelectionModel().getSelectedItem();
            deckManagerLayout.getBrowseDeckToRemove().getSelectionModel().clearSelection();
            deckManagerLayout.getDeckToRemoveTf().setText(selected);
            deckManagerLayout.getBrowseDeckToRemove().setVisible(false);
        }
    }
}
