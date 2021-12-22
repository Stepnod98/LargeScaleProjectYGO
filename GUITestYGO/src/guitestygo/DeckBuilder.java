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
    public DeckBuilder(){
        deck = new Deck("");
    }
    
    public static void addCard(){
        String t = DeckBuilderLayout.getCardToAdd();
        Card c = MongoDBManager.findCard(t);
        deck.addCard(c);
    }
    
    public static void removeCard(){
        String t = DeckBuilderLayout.getCardToRemove();
        deck.removeCardByTitle(t);
    }
    
    public static void viewCard(){
        
    }
    
    public static void saveDeck(){
        
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
