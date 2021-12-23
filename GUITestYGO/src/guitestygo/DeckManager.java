/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.*;

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
            
        }
    }
    
    public static void removeDeck(){
        String title = DeckManagerLayout.getDeckToRemove();
        MongoDBManager.remove(title);
    }
    
    public static void findTopXCard(){
        List<String> topList = new ArrayList<>();
        int x;
    }
    
    public static void findTopXECard(){
        List<String> topList = new ArrayList<>();
    }
     
    
}
