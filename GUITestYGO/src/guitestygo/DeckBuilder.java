/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefano
 */
public class DeckBuilder {
    private static Deck deck;
    private static DeckBuilderLayout deckBuilderLayout;
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout){
        deck = new Deck("");
        this.deckBuilderLayout = deckBuilderLayout;
    }
    
    public static void addCard(){
        String t = DeckBuilderLayout.getCardToAdd();
        Card c = MongoDBManager.findCard(t);
        if(MongoDBManager.checkCardType(t)){
            deck.addCard(c);
        }
        else{
            deck.addECard(c);
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
    }
    
    public static void viewCard(){
        
    }
    
    public static void saveDeck(DeckBuilderLayout db){
        deck.setTitle(db.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);
        
    }
    
    public static void findStrongestCard(){
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
    }
    
    public static void findRarestCard(){
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findRarest(setName);
    }
}
