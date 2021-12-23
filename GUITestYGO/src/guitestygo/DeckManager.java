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
    public static void findDeck(){
        String title = DeckManagerLayout.getDeckToBrowse();
        MongoDBManager.findDeck(title);
        //decidere se stampare a schermo da MongoDBManager o se farmi passare i valori e farlo da qui
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
