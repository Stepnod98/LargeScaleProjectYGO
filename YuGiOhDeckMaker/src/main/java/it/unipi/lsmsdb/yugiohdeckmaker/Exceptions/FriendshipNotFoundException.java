package it.unipi.lsmsdb.yugiohdeckmaker.Exceptions;

public class FriendshipNotFoundException extends Exception {
    public FriendshipNotFoundException(){
        super("You can unfollow only the friends!");
    }
}
