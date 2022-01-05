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
import javafx.event.ActionEvent;

public class DeckBuilder {
    private static Deck deck;
    private static DeckBuilderLayout deckBuilderLayout;
    private static DeckLayout deckLayout;
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout){
        deck = new Deck("");
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
    }
    
    public static void addCard(){
        deckBuilderLayout.clearErrors();
        String t = DeckBuilderLayout.getCardToAdd();
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
                    return;
                }
            }
        }
    }
    
    public static void addCardByAtk(){
        deckBuilderLayout.clearErrors();
        int atk = parseInt(DeckBuilderLayout.getCardToAdd());
        Card c = MongoDBManager.findCard(atk, true);
        if(MongoDBManager.checkCardType(c.getTitle())){
            if(!deck.addCard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the deck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                for(int j = 0; j < DeckLayout.height; j++){
                    if(deckLayout.getBoard()[i][j].getCard() == ""){
                        deckLayout.getBoard()[i][j] = new CardTile(i,j,c.getImgURL(), c.getTitle());
                        return;
                    }
                }
            }
        }
        else{
            if(!deck.addECard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the extradeck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
       }
    }
    
    public static void addCardByDef(){
        deckBuilderLayout.clearErrors();
        int def = parseInt(DeckBuilderLayout.getCardToAdd());
        Card c = MongoDBManager.findCard(def, false);
        if(MongoDBManager.checkCardType(c.getTitle())){
            if(!deck.addCard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the deck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                for(int j = 0; j < DeckLayout.height; j++){
                    if(deckLayout.getBoard()[i][j].getCard() == ""){
                        deckLayout.getBoard()[i][j] = new CardTile(i,j,c.getImgURL(), c.getTitle());
                        return;
                    }
                }
            }
        }
        else{
            if(!deck.addECard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the extradeck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
        }
    }
    
    public static void removeCard(){
        deckBuilderLayout.clearErrors();
        String t = DeckBuilderLayout.getCardToRemove();
        if(MongoDBManager.checkCardType(t)){
            deck.removeCardByTitle(t);
        }
        else{
            deck.removeECardByTitle(t);
        }
        for(int i = 0; i < DeckLayout.width; i++){
            for(int j = 0; j < DeckLayout.height; j++){
                if(deckLayout.getBoard()[i][j].getCard() == t){
                    deckLayout.getBoard()[i][j] = new CardTile(i, j);
                    return;
                }
            }
        }
    }
    
    public static void viewCard(){
        
    }
    
    public static void saveDeck(){
        deckBuilderLayout.clearErrors();
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.currentUser.username);
        MongoDBManager.saveDeck(deck);
        
    }
    
    public static void findStrongestCard(){
        deckBuilderLayout.clearErrors();
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }
    
    /*public static void findRarestCard(){
        deckBuilderLayout.clearErrors();
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findRarest(setName);
        deckBuilderLayout.showCard(t);
    }*/
    
    public static void setEvents(){
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
