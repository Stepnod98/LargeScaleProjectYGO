package it.unipi.lsmsdb.yugiohdeckmaker.Entities;

import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import org.bson.Document;

import java.util.*;

public class Deck {
    private String title;
    private String creator;
    private List<Card> cards = new ArrayList<Card>();
    private List<Card> extracards = new ArrayList<Card>();
    public Deck(String title){
        this.title = title;
    }
    public Deck(String title, String creator){
        this.title = title;
        this.creator = creator;
    }

    public Deck(Document d){
        this.title = d.getString("title");
        this.creator = d.getString("creator");
        List<Document> documentList = d.get("cards", List.class);
        for(int i = 0; i < documentList.size(); i++){
            this.cards.add(new Card(documentList.get(i)));
        }
        documentList = d.get("extra_deck", List.class);
        for(int i = 0; i < documentList.size(); i++){
            this.cards.add(new Card(documentList.get(i)));
        }
    }

    public List<Card> getCards(){
        return cards;
    }

    public List<Card> getECards(){
        return extracards;
    }

    public boolean addCard(Card c){
        int cont = 0;
        for(int i = 0; i < cards.size() -1; i++){
            if(cards.get(i).getTitle().equals(c.getTitle())){
                cont++;
            }
            if(cont < 3){
                cards.add(c);
            }
            else{
                System.out.println("WRN: You have already three copies of that card in your deck!");
                return false;
            }
        }
    return true;
    }

    public boolean addECard(Card c){
        int cont = 0;
        for(int i = 0; i < extracards.size() -1; i++){
            if(extracards.get(i).getTitle().equals(c.getTitle())){
                cont++;
            }
            if(cont < 3){
                extracards.add(c);
            }
            else{
                System.out.println("WRN: You have already three copies of that card in your deck!");
                return false;
            }
        }
        return true;
    }

    public void removeCardByTitle(String t){
        for(int i = 0; i < cards.size() -1; i++){
            if(cards.get(i).getTitle().equals(t)){
                cards.remove(i);
                return;
            }
        }
    }

    public void removeECardByTitle(String t){
        for(int i = 0; i < extracards.size() -1; i++){
            if(extracards.get(i).getTitle().equals(t)){
                extracards.remove(i);
                return;
            }
        }
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String t){
        title = t;
    }

    public String getCreator(){
        return creator;
    }

    public void setCreator(String user){
        creator = user;
    }

}