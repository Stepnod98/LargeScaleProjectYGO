package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class LikeNotPresentException extends Exception {
    public LikeNotPresentException (){ super("You can remove the like only on a liked deck!"); }
}
