package it.unipi.lsmsdb.yugiohdeckmaker.Entities;

import java.util.List;

public class User {
    public String username;
    public String email;
    public String pwd;
    public String firstName;
    public String lastName;
    public List<String> decks;

    public User(String username,String pwd ,String firstName, String lastName, String email){
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username){
        this.username = username;
    }
    public User(String username, List<String> decks){
        this(username);
        this.decks = decks;
    }

    public boolean checkDeck(String title){
        for(int i = 0; i < decks.size(); i++){
            if(decks.get(i).equals(title))
                return true;
        }
        return false;
    }
}
