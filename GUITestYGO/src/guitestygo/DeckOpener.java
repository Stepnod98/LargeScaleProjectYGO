/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

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
    //browse:
    public static void viewListToOpen(){
            deckOpenerLayout.clearErrors();
            //List<String> l = MongoDBManager.findCards(deckOpenerLayout.getTitle());
            List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");
            
            BorderPane bp = BrowseManager.viewList(deckOpenerLayout.getTitleTf(), l);
            deckOpenerLayout.showListResults(bp, 130, 50);
    }
    
    public static void setEvents(){
        //evento browse
        deckOpenerLayout.getTitleTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToOpen();
        });
        deckOpenerLayout.getOpen().setOnAction((ActionEvent ev)->{openDeck();});
        deckOpenerLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});	
    }
}
