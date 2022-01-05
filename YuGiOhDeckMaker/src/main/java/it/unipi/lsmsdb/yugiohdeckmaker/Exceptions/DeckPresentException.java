package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class DeckPresentException extends Exception {

    public DeckPresentException (){
        super("Deck already present in the social network.");
    }
}
