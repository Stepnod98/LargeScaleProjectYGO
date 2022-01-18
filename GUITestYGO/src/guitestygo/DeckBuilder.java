/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Stefano
 */
public class DeckBuilder {
    private static Deck deck;
    private static DeckBuilderLayout deckBuilderLayout;
    private static DeckLayout deckLayout;
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout){
        deck = new Deck();
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
    }
    
    public DeckBuilder(DeckBuilderLayout deckBuilderLayout, DeckLayout deckLayout, Deck d){
        deck = d;
        this.deckBuilderLayout = deckBuilderLayout;
        this.deckLayout = deckLayout;
        setEvents();
    }
    
    public static void addCard(){
        deckBuilderLayout.clearLayout();
        String t = deckBuilderLayout.getCardToAdd();
        Card c = MongoDBManager.findCard(t);
        if(MongoDBManager.checkCardType(t)){
            if(!deck.addCard(c)){
                deckBuilderLayout.showErrors("Cannot insert this card in the deck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                for(int j = 0; j < DeckLayout.height; j++){
                    if(deckLayout.getBoard()[i][j].getCard() == ""){
                        deckLayout.getBoard()[i][j] = new CardTile(i,j,c.getImgURL(), c.getTitle());
                        return;
                    }
                }
            }
        }
        else{
            if(!deck.addECard(c) && t!=""){
                deckBuilderLayout.showErrors("Cannot insert this card in the extradeck!");
                return;
            }
            for(int i = 0; i < DeckLayout.width; i++){
                if(deckLayout.getBoard()[i][4].getCard() == ""){
                    deckLayout.getBoard()[i][4] = new CardTile(i,4,c.getImgURL(), c.getTitle());
                    return;
                }
            }
        }
    }
    
    public static void addCardByAtk(){
        deckBuilderLayout.clearLayout();
        int atk = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(atk, true);
        deckBuilderLayout.showCardResults(c);
    }
    
    public static void addCardByDef(){
        deckBuilderLayout.clearLayout();
        int def = parseInt(deckBuilderLayout.getCardToAdd());
        List<String> c = MongoDBManager.findCard(def, false);
        deckBuilderLayout.showCardResults(c);
    }
    
    public static void viewMagicTraps(){
        deckBuilderLayout.clearLayout();
        List<String> c = MongoDBManager.findMagicTraps();
        deckBuilderLayout.showCardResults(c);
    }
    
    public static void removeCard(){
        deckBuilderLayout.clearLayout();
        String t = deckBuilderLayout.getCardToRemove();
        if(MongoDBManager.checkCardType(t)){
            deck.removeCardByTitle(t);
        }
        else{
            deck.removeECardByTitle(t);
        }
        for(int i = 0; i < DeckLayout.width; i++){
            for(int j = 0; j < DeckLayout.height; j++){
                if(deckLayout.getBoard()[i][j].getCard() == t){
                    deckLayout.getBoard()[i][j] = new CardTile(i, j);
                    return;
                }
            }
        }
    }
    
    public static void viewListToAdd(){
            deckBuilderLayout.clearLayout();
            //List<String> l = MongoDBManager.findCards(deckBuilderLayout.getCardToAdd());
            List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");
            
            BorderPane bp = BrowseManager.viewList(deckBuilderLayout.getCardToAddTf(), l);
            deckBuilderLayout.showListResults(bp, 520, 100);
    }
    
    public static void viewListToRemove(){
            deckBuilderLayout.clearLayout();
            //List<String> l = MongoDBManager.findCards(deckBuilderLayout.getCardToRemove());
            List<String> l = new ArrayList<>();
            l.add("ad");
            l.add("eb");
            l.add("cgf");
            l.add("adedf");
            l.add("tel");
            BorderPane bp = BrowseManager.viewList(deckBuilderLayout.getCardToRemoveTf(), l);
            deckBuilderLayout.showListResults(bp, 520, 300);
    }
    
    /*public static void viewCard(){
        
    }*/
    public static void findTopXCard(){
        deckBuilderLayout.clearLayout();
        int x = parseInt(deckBuilderLayout.getCardsRank());
        List<String> topList = new ArrayList<>();
        topList = MongoDBManager.findTopXCards(x);
        //add elements to gui
        //deckBuilderLayout.showCardResults(topList);
        List<String> list = new ArrayList<>();
        list.add("ed");
        list.add("g2");
        list.add("g3");
        list.add("g4");
        list.add("g5");
        deckBuilderLayout.showCardResults(list);
    }
    
    public static void findTopXECard(){
        deckBuilderLayout.clearLayout();
        int x = parseInt(deckBuilderLayout.getECardsRank());
        //List<String> topList = new ArrayList<>();
        //topList = MongoDBManager.findTopXECards(x);
        //add elements to gui
        //deckBuilderLayout.showCardResults(topList);
        //test:
        List<String> list = new ArrayList<>();
        list.add("te");
        list.add("s2");
        list.add("s3");
        list.add("s4");
        list.add("s5");
        list.add("s6");
        list.add("s7");
        deckBuilderLayout.showCardResults(list);
        
    }
    
    public static void saveDeck(){
        deckBuilderLayout.clearLayout();
        if(deck.getCards().size()<40){
            deckBuilderLayout.showErrors("A deck cannot have less than 40 cards!");
            return;
        }
        deck.setTitle(deckBuilderLayout.getDeckTitle());
        deck.setCreator(GUIManager.getCurrentUser());
        MongoDBManager.saveDeck(deck);
        
    }
    
    /*public static void findStrongestCard(){
        deckBuilderLayout.clearLayout();
        String setName = DeckBuilderLayout.getSetName();
        String t = MongoDBManager.findMostAtk(setName);
        deckBuilderLayout.showCard(t);
    }*/
    
    public static void setEvents(){
        deckBuilderLayout.getAddCardByTitle().setOnAction((ActionEvent ev)->{addCard();});
        deckBuilderLayout.getAddCardByAtk().setOnAction((ActionEvent ev)->{addCardByAtk();});
        deckBuilderLayout.getAddCardByDef().setOnAction((ActionEvent ev)->{addCardByDef();});
        deckBuilderLayout.getMagicTraps().setOnAction((ActionEvent ev)->{viewMagicTraps();});
        deckBuilderLayout.getRemoveCard().setOnAction((ActionEvent ev)->{removeCard();});
        deckBuilderLayout.getSave().setOnAction((ActionEvent ev)->{saveDeck();});	
        deckBuilderLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
        deckBuilderLayout.getFindTopCards().setOnAction((ActionEvent ev)->{findTopXCard();});	
        deckBuilderLayout.getFindTopECards().setOnAction((ActionEvent ev)->{findTopXECard();});
        deckBuilderLayout.getCardToAddTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToAdd();
        });
        
        deckBuilderLayout.getCardToRemoveTf().textProperty().addListener((obs, oldValue, newValue)->{
            viewListToRemove();
        });
    }
}
