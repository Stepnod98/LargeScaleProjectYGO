package it.unipi.lsmsdb.yugiohdeckmaker;

public class LikePresentException extends Exception {
    public LikePresentException (){
        super("You've already liked this deck!");
    }
}
