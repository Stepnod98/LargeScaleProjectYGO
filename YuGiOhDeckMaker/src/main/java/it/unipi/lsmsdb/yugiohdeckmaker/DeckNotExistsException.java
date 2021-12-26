package it.unipi.lsmsdb.yugiohdeckmaker;

public class DeckNotExistsException extends Exception {
    DeckNotExistsException(){
        super("You don't have this deck.");
    }
}
