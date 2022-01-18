/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.CardTile;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckBuilderLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

public class DeckBuilder {
    private Deck deck;
    private DeckBuilderLayout deckBuilderLayout;
    private DeckLayout deckLayout;
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout){
        deck = new Deck();
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
        setEvents();
    }

    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout, Deck d){
        deck = d;
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
        setEvents();
    }

    public void addCard(){
        deckBuilderLayout.clearLayout();
        String t = deckBuilderLayout.getCardToAdd();
        Card c = MongoDBManager.findCard(t);
        if(MongoDBManager.checkCardType(t)){
            if(!deck.addCard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the deck!");
                return;
            }

            for(int i = 0; i < DeckLayout.width; i++){
                for(int j = 0; j < DeckLayout.height; j++){
                    if(deckLayout.getBoard()[i][j].getCard() == ""){
                        deckLayout.getBoard()[i][j] = new CardTile(i,j,c.getImgURL(), c.getTitle());
                        GUIManager.openDeckBuilder(deck);
                        return;
                    }
                }
            }
        }
        else{
            if(!deck.addECard(c) && t!=""){
                deckBuilderLayout.showErrors("Cannot insert this card in the extradeck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    GUIManager.openDeckBuilder(deck);
                    return;
                }
            }
        }
    }

    public void addCardByAtk(){
        deckBuilderLayout.clearLayout();
        int atk = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(atk, true);
        deckBuilderLayout.showCardResults(c);
    }

    public  void addCardByDef(){
        deckBuilderLayout.clearLayout();
        int def = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(def, false);
        deckBuilderLayout.showCardResults(c);
    }

    public void viewMagicTraps(){
        deckBuilderLayout.clearLayout();
        List<String> c = MongoDBManager.findMagicTraps();
        deckBuilderLayout.showCardResults(c);
    }

    public void findTopXCard(){
        deckBuilderLayout.clearLayout();
        int x = parseInt(deckBuilderLayout.getCardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXCards(x);
        //add elements to gui
        //deckBuilderLayout.showCardResults(topList);
        List<String> list = new ArrayList<>();
        list.add("ed");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckBuilderLayout.showCardResults(list);
    }

    public void findTopXECard(){
        deckBuilderLayout.clearLayout();
        int x = parseInt(deckBuilderLayout.getECardsRank());
        //List<String> topList = new ArrayList<>();
        //topList = MongoDBManager.findTopXECards(x);
        //add elements to gui
        //deckBuilderLayout.showCardResults(topList);
        //test:
        List<String> list = new ArrayList<>();
        list.add("te");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");
        list.add("s6");
        list.add("s7");
        deckBuilderLayout.showCardResults(list);

    }

    public void removeCard(){
        deckBuilderLayout.clearLayout();
        String t = deckBuilderLayout.getCardToRemove();
        if(MongoDBManager.checkCardType(t)){
            deck.removeCardByTitle(t);
        }
        else{
            deck.removeECardByTitle(t);
        }
        GUIManager.openDeckBuilder(deck);
    }

    /*public static void viewCard(){

    }*/

    public void saveDeck(){
        deckBuilderLayout.clearLayout();
        if(deck.getCards().size()<40){
            deckBuilderLayout.showErrors("A deck cannot have less than 40 cards!");
            return;
        }
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);

    }

    /*public void findStrongestCard(){
        deckBuilderLayout.clearLayout();
        String setName = deckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }*/

    private void browseCardToAddTask(){
        if(deckBuilderLayout.getBrowseCardsToAdd().getSelectionModel().getSelectedItem() != null) {
            String selected = deckBuilderLayout.getBrowseCardsToAdd().getSelectionModel().getSelectedItem();
            deckBuilderLayout.getBrowseCardsToAdd().getSelectionModel().clearSelection();
            deckBuilderLayout.getCardToAddTf().setText(selected);
            deckBuilderLayout.getBrowseCardsToAdd().setVisible(false);
        }
    }

    private void browseCardToRemoveTask(){
        if(deckBuilderLayout.getBrowseCardsToRemove().getSelectionModel().getSelectedItem() != null) {
            String selected = deckBuilderLayout.getBrowseCardsToRemove().getSelectionModel().getSelectedItem();
            deckBuilderLayout.getBrowseCardsToRemove().getSelectionModel().clearSelection();
            deckBuilderLayout.getCardToRemoveTf().setText(selected);
            deckBuilderLayout.getBrowseCardsToRemove().setVisible(false);
        }
    }
    
    public void setEvents(){
        deckBuilderLayout.getAddCardByTitle().setOnAction((ActionEvent ev)->{addCard();});
        deckBuilderLayout.getAddCardByAtk().setOnAction((ActionEvent ev)->{addCardByAtk();});
        deckBuilderLayout.getAddCardByDef().setOnAction((ActionEvent ev)->{addCardByDef();});
        deckBuilderLayout.getMagicTraps().setOnAction((ActionEvent ev)->{viewMagicTraps();});
        deckBuilderLayout.getRemoveCard().setOnAction((ActionEvent ev)->{removeCard();});
        deckBuilderLayout.getSave().setOnAction((ActionEvent ev)->{saveDeck();});
        deckBuilderLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        deckBuilderLayout.getFindTopCards().setOnAction((ActionEvent ev)->{findTopXCard();});
        deckBuilderLayout.getFindTopECards().setOnAction((ActionEvent ev)->{findTopXECard();});

        deckBuilderLayout.getCardToAddTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckBuilderLayout.getBrowseCardsToAdd().setVisible(false);
                }else {
                    deckBuilderLayout.updateBrowseCardsToAdd(MongoDBManager.findCards(newValue));
                }
            }
        });

        deckBuilderLayout.getCardToAddTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    deckBuilderLayout.getBrowseCardsToAdd().setVisible(false);
                }
            }
        });

        deckBuilderLayout.getCardToRemoveTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    deckBuilderLayout.getBrowseCardsToRemove().setVisible(false);
                }else {
                    //deckBuilderLayout.updateBrowseCardsToRemove(MongoDBManager.findCards(newValue));
                    // TODO: 18/01/2022 PRENDERLE DAL DECK IN QUESTIONE
                    deckBuilderLayout.updateBrowseCardsToRemove(MongoDBManager.findCards(newValue));
                }
            }
        });

        deckBuilderLayout.getCardToRemoveTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    deckBuilderLayout.getBrowseCardsToRemove().setVisible(false);
                }
            }
        });
        deckBuilderLayout.getBrowseCardsToAdd().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    deckBuilderLayout.getBrowseCardsToAdd().setVisible(true);
                }else {
                    deckBuilderLayout.getBrowseCardsToAdd().setVisible(false);
                }
            }
        });
        deckBuilderLayout.getBrowseCardsToRemove().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    deckBuilderLayout.getBrowseCardsToRemove().setVisible(true);
                }else {
                    deckBuilderLayout.getBrowseCardsToRemove().setVisible(false);
                }
            }
        });

        setBrowseEvents();
    }

    private void setBrowseEvents(){
        deckBuilderLayout.getBrowseCardsToAdd().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardToAddTask());
            }
        });
        deckBuilderLayout.getBrowseCardsToRemove().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardToRemoveTask());
            }
        });
    }
}
