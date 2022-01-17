/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

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
        deckManagerLayout.clearErrors();
        String title = deckManagerLayout.getDeckToBrowse();
        Deck d = MongoDBManager.findDeck(title);
        int i;
        List<String> result = new ArrayList<String>();
        for(i = 0; i < d.getCards().size(); i++){
            result.add(d.getCards().get(i).getTitle());
        }
        for(i = 0; i < d.getECards().size(); i++){
            result.add(d.getECards().get(i).getTitle());
        }
        deckManagerLayout.showCardResults(result);
        GUIManager.openDeckManagerResults(deckManagerLayout);
    }
    
    public static void removeDeck(){
        deckManagerLayout.clearErrors();
        String title = deckManagerLayout.getDeckToRemove();
        MongoDBManager.remove(title);
    }
    
    public static void findMagicTrapDeck(){
        deckManagerLayout.clearErrors();
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
        deckManagerLayout.clearErrors();
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
    
    public static void findAvgAtkDecks(){
        deckManagerLayout.clearErrors();
        List<String> topList = new ArrayList<>();
        int th = Integer.parseInt(deckManagerLayout.getAvgAtk());
        topList = MongoDBManager.findMostAvgAtk(th);
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
    
    public static void viewListToBrowse(){
            deckManagerLayout.clearErrors();
            List<String> l = MongoDBManager.findDecks(deckManagerLayout.getDeckToBrowse());
            /*List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");*/
            BorderPane bp = BrowseManager.viewList(deckManagerLayout.getDeckToBrowseTf(), l);
            deckManagerLayout.showListResults(bp, 180, 60);
    
    }
    
    public static void viewListToRemove(){
            deckManagerLayout.clearErrors();
            List<String> l = MongoDBManager.findDecks(deckManagerLayout.getDeckToRemove());
            /*List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");*/
            BorderPane bp = BrowseManager.viewList(deckManagerLayout.getDeckToRemoveTf(), l);
            deckManagerLayout.showListResults(bp, 180, 100);
    }
    
    public static void setEvents(){
        deckManagerLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        deckManagerLayout.getRemoveDeck().setOnAction((ActionEvent ev)->{removeDeck();});
        deckManagerLayout.getDeckToBrowseTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToBrowse();
        });
        deckManagerLayout.getDeckToRemoveTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToRemove();
        });
        deckManagerLayout.getFindMagicTrapDecks().setOnAction((ActionEvent ev)->{findMagicTrapDeck();}); 
        deckManagerLayout.getFindArchetypeDecks().setOnAction((ActionEvent ev)->{findArchetypeDeck();});
        deckManagerLayout.getMostAvgAtkDecks().setOnAction((ActionEvent ev)->{findAvgAtkDecks();}); 
        deckManagerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
     
    
}
