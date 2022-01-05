package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class DeckYoursException extends Exception {

    public DeckYoursException(){
        super("You can't like yours deck");
    }
}
