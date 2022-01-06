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
    
    public static void addCard(){
        deckBuilderLayout.clearErrors();
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
        int atk = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(atk, true);
        deckBuilderLayout.showCardResults(c);
    }
    
    public static void addCardByDef(){
        deckBuilderLayout.clearErrors();
        int def = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(def, false);
        deckBuilderLayout.showCardResults(c);
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
    
    /*public static void viewCard(){
        
    }*/
    
    public static void saveDeck(){
        deckBuilderLayout.clearErrors();
        if(deck.getCards().size()<40){
            deckBuilderLayout.showErrors("A deck cannot have less than 40 cards!");
            return;
        }
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);
        
    }
    
    public static void findStrongestCard(){
        deckBuilderLayout.clearErrors();
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }
    
    public static void setEvents(){
        deckBuilderLayout.findStrongest.setOnAction((ActionEvent ev)->{findStrongestCard();});
        deckBuilderLayout.addCardByTitle.setOnAction((ActionEvent ev)->{addCard();});
        deckBuilderLayout.addCardByAtk.setOnAction((ActionEvent ev)->{addCardByAtk();});
        deckBuilderLayout.addCardByDef.setOnAction((ActionEvent ev)->{addCardByDef();});
        deckBuilderLayout.removeCard.setOnAction((ActionEvent ev)->{removeCard();});
        deckBuilderLayout.save.setOnAction((ActionEvent ev)->{saveDeck();});	
        deckBuilderLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
