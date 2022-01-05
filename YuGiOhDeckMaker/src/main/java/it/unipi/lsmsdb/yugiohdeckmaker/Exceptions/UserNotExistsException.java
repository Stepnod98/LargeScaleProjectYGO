package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class UserNotExistsException extends Exception {
    public UserNotExistsException(){
        super("This user doesn't exists");
    }
}
