/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.DeckOpenerLayout;
import javafx.event.ActionEvent;

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
        deckOpenerLayout.getOpen().setOnAction((ActionEvent ev)->{openDeck();});
        deckOpenerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }
}
