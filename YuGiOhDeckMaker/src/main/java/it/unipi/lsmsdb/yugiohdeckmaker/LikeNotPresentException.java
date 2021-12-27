package it.unipi.lsmsdb.yugiohdeckmaker;

public class LikeNotPresentException extends Exception {
    public LikeNotPresentException (){ super("You can remove the like only on a liked deck!"); }
}
