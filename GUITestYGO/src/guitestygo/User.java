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
public class User {
    private String username;
    private List<Deck> deckList = new ArrayList<Deck>();
    public User(String username, ArrayList<Deck> list){
        this.username = username;
        deckList = list;
    }
    public String getUsername(){
        return username;
    }
}
