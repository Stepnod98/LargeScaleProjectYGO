package it.unipi.lsmsdb.yugiohdeckmaker;

public class DeckPresentException extends Exception {

    public DeckPresentException (){
        super("Deck already present in the social network.");
    }
}
