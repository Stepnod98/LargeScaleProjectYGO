package it.unipi.lsmsdb.yugiohdeckmaker;

public class UserPresentException extends Exception {
    public UserPresentException (){
        super("Friendship already present.");
    }
}
