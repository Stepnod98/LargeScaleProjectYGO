/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import java.util.*;
import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class DeckManager {
    private static DeckManagerLayout deckManagerLayout;
    public DeckManager(DeckManagerLayout deckManagerLayout){
        this.deckManagerLayout = deckManagerLayout;
        
    }
    public static void findDeck(){
        String title = DeckManagerLayout.getDeckToBrowse();
        Deck d = MongoDBManager.findDeck(title);
        //decidere se stampare a schermo da MongoDBManager o se farmi passare i valori e farlo da qui
        int i;
        for(i = 0; i < d.getCards().size(); i++){
            //stampa a schermo le carte
        }
    }
    
    public static void removeDeck(){
        String title = DeckManagerLayout.getDeckToRemove();
        MongoDBManager.remove(title);
    }
    
    public static void findTopXCard(){
        int x = parseInt(deckManagerLayout.getCardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXCards(x);
    }
    
    public static void findTopXECard(){
        int x = parseInt(deckManagerLayout.getECardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXECards(x);
    }
    
    public static void findMagicTrapDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findMagicTrapDeck();
    }
     
    public static void findArchetypeDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findArchetypeDeck();
    }
    
    public static void setEvents(){
        deckManagerLayout.findDeck.setOnAction((ActionEvent ev)->{findDeck();});
        deckManagerLayout.removeDeck.setOnAction((ActionEvent ev)->{removeDeck();});
        deckManagerLayout.findTopCards.setOnAction((ActionEvent ev)->{findTopXCard();});	
        deckManagerLayout.findTopECards.setOnAction((ActionEvent ev)->{findTopXECard();});
        deckManagerLayout.findMagicTrapDecks.setOnAction((ActionEvent ev)->{findMagicTrapDeck();}); 
        deckManagerLayout.findArchetypeDecks.setOnAction((ActionEvent ev)->{findArchetypeDeck();}); 
        deckManagerLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
     
    
}
