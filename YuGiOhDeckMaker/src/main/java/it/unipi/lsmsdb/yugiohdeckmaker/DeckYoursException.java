package it.unipi.lsmsdb.yugiohdeckmaker;

public class DeckYoursException extends Exception {

    DeckYoursException(){
        super("You can't like yours deck");
    }
}
