/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

/**
 *
 * @author Stefano
 */
public class DeckOpener {
    public static void openDeck(){
        String t = DeckOpenerLayout.getTitle();
        if(MongoDBManager.existsDeck(t)){
            Deck d = MongoDBManager.findDeck(t);
            GUIManager.openDeckBuilder(d);
        }
    }
}
