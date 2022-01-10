/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import static java.lang.Integer.parseInt;

import java.util.List;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.CardTile;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckBuilderLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckLayout;
import javafx.event.ActionEvent;

public class DeckBuilder {
    private Deck deck;
    private DeckBuilderLayout deckBuilderLayout;
    private DeckLayout deckLayout;
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout){
        deck = new Deck();
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
    }

    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout, Deck d){
        deck = d;
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
        setEvents();
    }

    public void addCard(){
        deckBuilderLayout.clear();
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
        deckBuilderLayout.clear();
        int atk = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(atk, true);
        deckBuilderLayout.showCardResults(c);
    }

    public  void addCardByDef(){
        deckBuilderLayout.clear();
        int def = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(def, false);
        deckBuilderLayout.showCardResults(c);
    }

    public void removeCard(){
        deckBuilderLayout.clear();
        String t = DeckBuilderLayout.getCardToRemove();
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
        deckBuilderLayout.clear();
        if(deck.getCards().size()<40){
            deckBuilderLayout.showErrors("A deck cannot have less than 40 cards!");
            return;
        }
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);

    }

    public void findStrongestCard(){
        deckBuilderLayout.clear();
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }
    
    public void setEvents(){
        deckBuilderLayout.getFindStrongest().setOnAction((ActionEvent ev)->{findStrongestCard();});
        //deckBuilderLayout.getFindRarest().setOnAction((ActionEvent ev)->{findRarestCard();});
        deckBuilderLayout.getAddCardByTitle().setOnAction((ActionEvent ev)->{addCard();});
        deckBuilderLayout.getAddCardByAtk().setOnAction((ActionEvent ev)->{addCardByAtk();});
        deckBuilderLayout.getAddCardByDef().setOnAction((ActionEvent ev)->{addCardByDef();});
        deckBuilderLayout.getRemoveCard().setOnAction((ActionEvent ev)->{removeCard();});
        deckBuilderLayout.getSave().setOnAction((ActionEvent ev)->{saveDeck();});
        deckBuilderLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
