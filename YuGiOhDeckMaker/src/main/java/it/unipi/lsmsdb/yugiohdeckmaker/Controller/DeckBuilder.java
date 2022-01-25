/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckBuilderLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DeckBuilder {
    private Deck deck;
    private DeckBuilderLayout deckBuilderLayout;


    public DeckBuilder(DeckBuilderLayout deckBuilderLayout){
        deck = new Deck();
        this.deckBuilderLayout = deckBuilderLayout;
        setEvents();
    }

    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, Deck d){
        deck = d;
        this.deckBuilderLayout = deckBuilderLayout;
        deckBuilderLayout.updateDeckCreationTable(d.getCardsTitles());
        deckBuilderLayout.updateExtraDeckCreationTable(d.getECardsTitles());
        deckBuilderLayout.getNumberOfCards().setText("Cards: "+deck.getCards().size()+"/40");
        if(deck.getCards().size() >= 40){
            deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
        }else {
            deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
        }
        deckBuilderLayout.updateExtraDeckCreationTable(deck.getECardsTitles());
        deckBuilderLayout.getNumberOfECards().setText("Extra Cards: "+deck.getECards().size()+"/10");
        if(deck.getECards().size() >= 10){
            deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
        }else {
            deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
        }
        setEvents();
    }

    public void addCard(String t){

        Card c = MongoDBManager.findCard(t);
        if(MongoDBManager.checkCardType(t)){
            if(!deck.addCard(c)){
                deckBuilderLayout.printError("Cannot insert this card in the deck!");
                return;
            }

            deckBuilderLayout.updateDeckCreationTable(deck.getCardsTitles());
            deckBuilderLayout.getNumberOfCards().setText("Cards: "+deck.getCards().size()+"/40");
            if(deck.getCards().size() >= 40){
                deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
            }else {
                deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
            }
        }// TODO: 20/01/2022 TUTTI i controlli , aggiungere extracards, visualizzare carta e controllare a 40 stop! ERRORE!!
        else{
            if(!deck.addECard(c) && !Objects.equals(t, "")){
                deckBuilderLayout.printError("Cannot insert this card in the extradeck!");
                return;
            }

            deckBuilderLayout.updateExtraDeckCreationTable(deck.getECardsTitles());
            deckBuilderLayout.getNumberOfECards().setText("Extra Cards: "+deck.getECards().size()+"/10");
            if(deck.getECards().size() >= 10){
                deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
            }else {
                deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
            }
        }
    }


    public void viewMagicTraps(){
        GUIManager.clearDeckBuilderBoxes();
        GUIManager.clearDeckBuilderCardResult();
        List<String> c = MongoDBManager.findMagicTraps();
        deckBuilderLayout.showCardResults(c);
        setCardFoundTableEvents();
    }

    public void findTopXCard(){
        GUIManager.clearDeckBuilderBoxes();
        GUIManager.clearDeckBuilderCardResult();
        if(isNumeric(deckBuilderLayout.getCardsRank())){
            int x = parseInt(deckBuilderLayout.getCardsRank());
            List<String> topList = MongoDBManager.findTopXCards(x);
            deckBuilderLayout.showCardResults(topList);
            setCardFoundTableEvents();
        }
    }

    public void findTopXECard(){
        GUIManager.clearDeckBuilderBoxes();
        GUIManager.clearDeckBuilderCardResult();

        if(isNumeric(deckBuilderLayout.getECardsRank())){
            int x = parseInt(deckBuilderLayout.getECardsRank());
            List<String> topList = MongoDBManager.findTopXECards(x);
            deckBuilderLayout.showCardResults(topList);
            setCardFoundTableEvents();
        }
    }

    public void removeCard(String t){

        if(MongoDBManager.checkCardType(t)){
            deck.removeCardByTitle(t);
            deckBuilderLayout.updateDeckCreationTable(deck.getCardsTitles());
            deckBuilderLayout.getNumberOfCards().setText("Cards: "+deck.getCards().size()+"/40");
            if(deck.getCards().size() >= 40){
                deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
            }else {
                deckBuilderLayout.getNumberOfCards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
            }
        }
        else{
            deck.removeECardByTitle(t);
            deckBuilderLayout.updateExtraDeckCreationTable(deck.getECardsTitles());
            deckBuilderLayout.getNumberOfECards().setText("Extra Cards: "+deck.getECards().size()+"/10");
            if(deck.getECards().size() >= 10){
                deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: green; -fx-font-weight: bold;");
            }else {
                deckBuilderLayout.getNumberOfECards().setStyle("-fx-text-inner-color: black; -fx-font-weight: bold;");
            }
        }
    }


    public void saveDeck(){

        if(deck.getCards().size()<40){
            deckBuilderLayout.printError("A deck cannot have less than 40 cards!");
            return;
        }
        if(!deckBuilderLayout.getDeckTitle().isEmpty()) {
            deck.setTitle(deckBuilderLayout.getDeckTitle());
            deck.setCreator(GUIManager.getCurrentUser());
            if(MongoDBManager.saveDeck(deck)){
                GUIManager.clearDeckBuilderBoxes();
                GUIManager.clearDeckBuilderCardResult();
                deckBuilderLayout.clearDecks();
                deckBuilderLayout.printLog("Deck saved correctly!");
                deck = new Deck();
            }else{
                deckBuilderLayout.printError("Cannot save this deck!");
            }

        }
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
    
    public void setEvents(){
        deckBuilderLayout.getFindCardButton().setOnAction((ActionEvent ev)->{
            GUIManager.clearDeckBuilderBoxes();
            GUIManager.clearDeckBuilderCardResult();
            if(deckBuilderLayout.getByTitleRadioButton().isSelected()) {

                if (MongoDBManager.findCard(deckBuilderLayout.getCardToAdd()) != null) {

                    setCardVbox(deckBuilderLayout.getCardToAdd(), "ADD");
                    GUIManager.addNode(deckBuilderLayout.getCardVbox());
                    List<String> tips = MongoDBManager.getTips(deckBuilderLayout.getCardToAdd());
                    if(tips != null){
                        int index = new Random().nextInt(tips.size());
                        deckBuilderLayout.setTips(tips.get(index));
                        GUIManager.addNode(deckBuilderLayout.getTips());
                    }

                }
            }else if(deckBuilderLayout.getByAtkRadioButton().isSelected() && isNumeric(deckBuilderLayout.getCardToAdd())){
                List<String> res = MongoDBManager.findCard(Integer.parseInt(deckBuilderLayout.getCardToAdd()),true);
                deckBuilderLayout.showCardResults(res);
                setCardFoundTableEvents();
            }else if(deckBuilderLayout.getByDefRadioButton().isSelected() && isNumeric(deckBuilderLayout.getCardToAdd())){

                List<String> res = MongoDBManager.findCard(Integer.parseInt(deckBuilderLayout.getCardToAdd()),false);
                deckBuilderLayout.showCardResults(res);
                setCardFoundTableEvents();
            }
        });
        deckBuilderLayout.getMagicTraps().setOnAction((ActionEvent ev)->{viewMagicTraps();});
        deckBuilderLayout.getSave().setOnAction((ActionEvent ev)->{saveDeck();});
        deckBuilderLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        deckBuilderLayout.getFindTopCards().setOnAction((ActionEvent ev)->{findTopXCard();});
        deckBuilderLayout.getFindTopECards().setOnAction((ActionEvent ev)->{findTopXECard();});

        deckBuilderLayout.getCardToAddTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                newValue = newValue.trim();
                if(newValue.equals("") || newValue.equals(" ")){
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

        deckBuilderLayout.getDeckCreationTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showCardTask("REMOVE", deckBuilderLayout.getDeckCreationTable()));
            }
        });

        deckBuilderLayout.getExtraDeckCreationTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showCardTask("REMOVE", deckBuilderLayout.getExtraDeckCreationTable()));
            }
        });


        deckBuilderLayout.getByTitleRadioButton().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(isNowSelected) {
                    deckBuilderLayout.getByDefRadioButton().setSelected(false);
                    deckBuilderLayout.getByAtkRadioButton().setSelected(false);
                }
            }
        });

        deckBuilderLayout.getByAtkRadioButton().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(isNowSelected) {
                    deckBuilderLayout.getByTitleRadioButton().setSelected(false);
                    deckBuilderLayout.getByDefRadioButton().setSelected(false);
                }
            }
        });

        deckBuilderLayout.getByDefRadioButton().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if(isNowSelected){
                    deckBuilderLayout.getByAtkRadioButton().setSelected(false);
                    deckBuilderLayout.getByTitleRadioButton().setSelected(false);
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
    }

    private void setCardFoundTableEvents(){
        deckBuilderLayout.getCardFindResultTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showCardTask("ADD", deckBuilderLayout.getCardFindResultTable() ));
            }
        });
    }

    private void showCardTask(String action, TableView<String> tw){
        if(tw.getSelectionModel().getSelectedItem() != null) {
            String cardTitle = tw.getSelectionModel().getSelectedItem();
            GUIManager.clearDeckBuilderBoxes();

            setCardVbox(cardTitle, action);
            List<String> tips = MongoDBManager.getTips(cardTitle);
            if(tips != null){
                int index = new Random().nextInt(tips.size());
                deckBuilderLayout.setTips(tips.get(index));
                GUIManager.addNode(deckBuilderLayout.getTips());
            }

            GUIManager.addNode(deckBuilderLayout.getCardVbox());

        }
    }

    private void setCardVbox(String cardTitle, String action){
        Image image = new Image("file:./../img/backCard.png", 250, 250, true, false);
        if(MongoDBManager.getImageUrl(new Card(cardTitle)) != null) {
            image = new Image(MongoDBManager.getImageUrl(new Card(cardTitle)), 250, 250, true, false);
        }

        deckBuilderLayout.showCardFindResults(cardTitle, image);

        HBox hBox = (HBox) deckBuilderLayout.getCardVbox().getChildren().get(deckBuilderLayout.getCardVbox().getChildren().size()-1);

        ((Button) hBox.getChildren().get(0)).setText(action);

        if(action.equals("ADD")){
            ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                addCard(cardTitle);
                clearSelection();
                GUIManager.clearDeckBuilderBoxes();
            });
        }else{
            ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                removeCard(cardTitle);
                clearSelection();
                GUIManager.clearDeckBuilderBoxes();
            });
        }

    }

    private void clearSelection(){
        if(deckBuilderLayout.getDeckCreationTable() != null) {
            clearSelection(deckBuilderLayout.getDeckCreationTable());
        }
        if(deckBuilderLayout.getExtraDeckCreationTable() != null){
            clearSelection(deckBuilderLayout.getExtraDeckCreationTable());
        }
        if(deckBuilderLayout.getCardFindResultTable() != null) {
            clearSelection(deckBuilderLayout.getCardFindResultTable());
        }
    }

    private void clearSelection(TableView tw){
        if(tw.getSelectionModel().getSelectedItem() != null) {
            tw.getSelectionModel().clearSelection();
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException | ClassCastException e){
            return false;
        }
    }

}
