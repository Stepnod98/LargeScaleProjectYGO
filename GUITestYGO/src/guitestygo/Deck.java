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
public class Deck {
    private static String title;
    private static String creator;
    private static List<Card> cards = new ArrayList<Card>();
    private static List<Card> extracards = new ArrayList<Card>();
    public Deck(String title){
        this.title = title;
    }
    
    public List<Card> getCards(){
        return cards;
    }
    
    public List<Card> getECards(){
        return extracards;
    }
    
    public void addCard(Card c){
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
            }
        }
    }
    
    public void addECard(Card c){
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
            }
        }
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