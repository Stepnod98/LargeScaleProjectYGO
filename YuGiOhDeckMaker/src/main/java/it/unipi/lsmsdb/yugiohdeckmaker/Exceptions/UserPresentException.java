package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class UserPresentException extends Exception {
    public UserPresentException (){
        super("Friendship already present.");
    }
}
