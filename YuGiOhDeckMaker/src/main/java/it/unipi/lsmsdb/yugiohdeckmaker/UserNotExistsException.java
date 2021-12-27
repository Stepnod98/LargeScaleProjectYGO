package it.unipi.lsmsdb.yugiohdeckmaker;

public class UserNotExistsException extends Exception {
    UserNotExistsException(){
        super("This user doesn't exists");
    }
}
