package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class DeckNotExistsException extends Exception {
    public DeckNotExistsException(){
        super("You don't have this deck.");
    }
}
