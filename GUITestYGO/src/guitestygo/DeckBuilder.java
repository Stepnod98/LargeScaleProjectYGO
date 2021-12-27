/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
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
        String t = DeckBuilderLayout.getCardToAdd();
        Card c = MongoDBManager.findCard(t);
        if(MongoDBManager.checkCardType(t)){
            if(deck.getCards().size() == 40){
                return;
            }
            deck.addCard(c);
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
            if(deck.getECards().size() == 10){
                return;
            }
            deck.addECard(c);
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
        }
    }
    
    public static void addCardByAtk(){
        int atk = parseInt(DeckBuilderLayout.getCardToAdd());
        Card c = MongoDBManager.findCard(atk, true);
        if(MongoDBManager.checkCardType(c.getTitle())){
            if(deck.getCards().size() == 40){
                return;
            }
            deck.addCard(c);
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
            if(deck.getECards().size() == 10){
                return;
            }
            deck.addECard(c);
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
        }
    }
    
    public static void addCardByDef(){
        int def = parseInt(DeckBuilderLayout.getCardToAdd());
        Card c = MongoDBManager.findCard(def, false);
        if(MongoDBManager.checkCardType(c.getTitle())){
            if(deck.getCards().size() == 40){
                return;
            }
           deck.addCard(c);
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
            if(deck.getECards().size() == 10){
                return;
            }
            deck.addECard(c);
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
        }
    }
    
    public static void removeCard(){
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
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);
        
    }
    
    public static void findStrongestCard(){
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }
    
    public static void findRarestCard(){
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findRarest(setName);
        deckBuilderLayout.showCard(t);
    }
    
    public static void setEvents(){
        deckBuilderLayout.findStrongest.setOnAction((ActionEvent ev)->{findStrongestCard();});
        deckBuilderLayout.findRarest.setOnAction((ActionEvent ev)->{findRarestCard();});	
        deckBuilderLayout.addCardByTitle.setOnAction((ActionEvent ev)->{addCard();});
        deckBuilderLayout.addCardByAtk.setOnAction((ActionEvent ev)->{addCardByAtk();});
        deckBuilderLayout.addCardByDef.setOnAction((ActionEvent ev)->{addCardByDef();});
        deckBuilderLayout.removeCard.setOnAction((ActionEvent ev)->{removeCard();});
        deckBuilderLayout.save.setOnAction((ActionEvent ev)->{saveDeck();});	
        deckBuilderLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
