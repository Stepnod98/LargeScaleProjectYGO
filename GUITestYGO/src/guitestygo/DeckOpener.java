/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static guitestygo.DeckBuilder.addCard;
import static guitestygo.DeckBuilder.findRarestCard;
import static guitestygo.DeckBuilder.findStrongestCard;
import static guitestygo.DeckBuilder.removeCard;
import static guitestygo.DeckBuilder.saveDeck;
import javafx.event.ActionEvent;

/**
 *
 * @author Stefano
 */
public class DeckOpener {
    private static DeckOpenerLayout deckOpenerLayout;
    public DeckOpener(DeckOpenerLayout deckOpenerLayout){
        this.deckOpenerLayout = deckOpenerLayout;
    }
    public static void openDeck(){
        String t = DeckOpenerLayout.getTitle();
        if(MongoDBManager.existsDeck(t)){
            Deck d = MongoDBManager.findDeck(t);
            GUIManager.openDeckBuilder(d);
        }
    }
    
    public static void setEvents(){
        deckOpenerLayout.open.setOnAction((ActionEvent ev)->{openDeck();});
        deckOpenerLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});	
    }
}
