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
    
    public boolean addCard(Card c){
        if(cards.size() == 40){
            return false;
        }
        int cont = 0;
        for(int i = 0; i < cards.size() -1; i++){
            if(cards.get(i).getTitle().equals(c.getTitle())){
                cont++;
            }
            if(cont < 3){
                cards.add(c);
                return true;
            }
            else{
                System.out.println("WRN: You have already three copies of that card in your deck!");
            }
        }
        return false;
    }
    
    public boolean addECard(Card c){
        if(extracards.size() == 10){
            return false;
        }
        int cont = 0;
        for(int i = 0; i < extracards.size() -1; i++){
            if(extracards.get(i).getTitle().equals(c.getTitle())){
                cont++;
            }
            if(cont < 3){
                extracards.add(c);
                return true;
            }
            else{
                System.out.println("WRN: You have already three copies of that card in your deck!");
            }
        }
        return false;
    }
    
    public boolean removeCardByTitle(String t){
        for(int i = 0; i < cards.size() -1; i++){
            if(cards.get(i).getTitle().equals(t)){
                cards.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeECardByTitle(String t){
        for(int i = 0; i < extracards.size() -1; i++){
            if(extracards.get(i).getTitle().equals(t)){
                extracards.remove(i);
                return true;
            }
        }
        return false;
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
