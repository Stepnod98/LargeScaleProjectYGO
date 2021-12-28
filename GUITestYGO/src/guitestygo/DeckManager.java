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
        int i;
        List<String> result = new ArrayList<String>();
        for(i = 0; i < d.getCards().size(); i++){
            result.add(d.getCards().get(i).getTitle());
        }
        for(i = 0; i < d.getECards().size(); i++){
            result.add(d.getECards().get(i).getTitle());
        }
        deckManagerLayout.showDeckResults(result);
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public static void removeDeck(){
        String title = DeckManagerLayout.getDeckToRemove();
        MongoDBManager.remove(title);
    }
    
    public static void findTopXCard(){
        int x = parseInt(deckManagerLayout.getCardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXCards(x);
        //add elements to gui
        deckManagerLayout.showCardResults(topList);
        /*List<String> list = new ArrayList<>();
        list.add("g1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showCardResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public static void findTopXECard(){
        int x = parseInt(deckManagerLayout.getECardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXECards(x);
        //add elements to gui
        deckManagerLayout.showCardResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");
        list.add("s6");
        list.add("s7");
        deckManagerLayout.showCardResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
        
    }
    
    public static void findMagicTrapDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findMagicTrapDeck();
        //add elements to gui
        deckManagerLayout.showDeckResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("g1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showDeckResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
        
    }
     
    public static void findArchetypeDeck(){
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findArchetypeDeck();
        //add elements to gui
        deckManagerLayout.showDeckResults(topList);
        //test:
        /*List<String> list = new ArrayList<>();
        list.add("gg1");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckManagerLayout.showDeckResults(list);*/
        GUIManager.openDeckManagerResults(deckManagerLayout);
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
