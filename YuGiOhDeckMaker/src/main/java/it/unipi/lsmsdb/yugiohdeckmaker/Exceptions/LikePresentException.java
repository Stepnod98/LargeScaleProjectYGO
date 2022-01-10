package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class LikePresentException extends Exception {
    public LikePresentException (){
        super("You've already liked this deck!");
    }
}
