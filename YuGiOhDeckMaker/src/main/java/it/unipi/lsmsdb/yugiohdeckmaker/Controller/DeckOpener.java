/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckOpenerLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

public class DeckOpener {
    private DeckOpenerLayout deckOpenerLayout;
    public DeckOpener(DeckOpenerLayout deckOpenerLayout){
        this.deckOpenerLayout = deckOpenerLayout;
        setEvents();
        setBrowseEvents();
    }
    public void openDeck(){
        String t = deckOpenerLayout.getTitle();
        if(MongoDBManager.existsDeck(t)){
            Deck d = MongoDBManager.findDeck(t);
            GUIManager.openDeckBuilder(d);
        }
    }
    
    public void setEvents(){
        deckOpenerLayout.getOpen().setOnAction((ActionEvent ev)->{openDeck();});
        deckOpenerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        deckOpenerLayout.getDeckTitle().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckOpenerLayout.getBrowseDeckResults().setVisible(false);
                }else {
                    deckOpenerLayout.updateBrowseDeckResults(MongoDBManager.findDecks(newValue));
                }
            }
        });
        deckOpenerLayout.getDeckTitle().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    deckOpenerLayout.getBrowseDeckResults().setVisible(false);
                }
            }
        });
        deckOpenerLayout.getBrowseDeckResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    deckOpenerLayout.getBrowseDeckResults().setVisible(true);
                }else {
                    deckOpenerLayout.getBrowseDeckResults().setVisible(false);
                }
            }
        });
    }

    private void setBrowseEvents() {
        deckOpenerLayout.getBrowseDeckResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseDeckTasks());
            }
        });
    }

    private void browseDeckTasks(){
        if(deckOpenerLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem() != null) {
            String selected = deckOpenerLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem();
            deckOpenerLayout.getBrowseDeckResults().getSelectionModel().clearSelection();
            deckOpenerLayout.getDeckTitle().setText(selected);
            deckOpenerLayout.getBrowseDeckResults().setVisible(false);
        }
    }

}
