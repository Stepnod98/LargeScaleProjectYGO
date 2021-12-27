/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;
import java.util.Scanner;
import javafx.scene.control.*;

/**
 *
 * @author Fabiano
 */
public class SocialManager {

    private final User currentUser;
    private final SocialLayout socialLayout;

    public SocialManager(SocialLayout socialLayout, User currentUser){
        this.currentUser = currentUser;
        this.socialLayout = socialLayout;
        setEvents();

        // TODO: 27/12/2021 Delete this and call the method to printe the lists with button!
        System.out.println(currentUser.username);
        System.out.println(Neo4jManager.getRecommendedDecks(currentUser));
        System.out.println(Neo4jManager.getRecommendedUsers(currentUser));
    }

    private void setEvents(){
        socialLayout.getShareDeck().setOnAction((ActionEvent ev)->{share();});
        socialLayout.getFindUser().setOnAction((ActionEvent ev)->{findUser();});
        socialLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        socialLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }

    private void setFollowEvents(User user){
        //socialLayout.getFollow().setOnAction((ActionEvent ev)->{follow(user);});
    }

    private void setLikeEvents(Deck deck){
        //socialLayout.getFollow().setOnAction((ActionEvent ev)->{like(deck);});
    }

    public void share(){
        try {
            Neo4jManager.shareDeck(currentUser,new Deck(socialLayout.toShare.getText()));
        } catch (DeckPresentException | DeckNotExistsException e) {
            System.out.println(e.getMessage());

            // TODO: 25/12/2021
            //socialLayout.printError(e.getMessage());
        }
    }


    // TODO: 27/12/2021 Associate this method with the button like near the user found
    public void like(Deck deck){

        try {
            Neo4jManager.likeDeck(currentUser,deck);
        } catch (LikePresentException | DeckYoursException e) {
            // TODO: 27/12/2021  
            //socialLayout.printError(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    // TODO: 27/12/2021 Create the button removeLike
    //This method is used to unlike a liked deck
    public void unlike(Deck deck){

        try {
            Neo4jManager.unlikeDeck(currentUser,deck);
        } catch (LikeNotPresentException e) {
            // TODO: 27/12/2021
            //socialLayout.printError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    // TODO: 26/12/2021 Associate this method with the button follow near the user found
    //This method is used to follow on social the given user
    public void follow(User user){
        try {
            Neo4jManager.follow(currentUser,user);
        } catch (UserPresentException | UserNotExistsException e) {
            // TODO: 26/12/2021
            // socialLayout.printError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    // TODO: 27/12/2021 Create the button remove
    //This method is used to unfollow on social the given user
    public void unfollow(User user){
        try {
            Neo4jManager.unfollow(currentUser,user);
        } catch (FriendshipNotFoundException e) {
            // TODO: 27/12/2021
            // socialLayout.printError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    public void findUser(){
        //If the user is found then add to the variable userToFollow!
        User user = new User(socialLayout.userToFind.getText());
        if(Neo4jManager.findUser(user)){
            // TODO: 27/12/2021
            //Show user found with button
            //socialLayoutShowUserFound(user.username);
            //setFollowEvents(user);

            //FOR TEST THIS HAS TO BE DELETED!
            System.out.println("User found! Would you want to add "+user.username+" to your friends?[0\\1]");
            Scanner scanner = new Scanner(System.in);
            int in = scanner.nextInt();
            if(in == 1){
                follow(user);
            }
        }else{
            //socialLayout.printError("User not found");
            System.out.println("User not found");
        }
    }
    
    public void findDeck(){

        Deck deck = new Deck(socialLayout.deckToFind.getText());
        if(Neo4jManager.findDeckOnSocial(currentUser,deck)){
            //Show deck found with button
            //socialLayoutShowDeckFound(deck.getTitle());
            //setLikeEvents(deck);

            //FOR TEST THIS HAS TO BE DELETED!
            System.out.println("Deck found! Would you want to like "+deck.getTitle()+"?[0\\1]");
            Scanner scanner = new Scanner(System.in);
            int in = scanner.nextInt();
            if(in == 1){
                like(deck);
            }
        }else{
            //socialLayout.printError("User not found");
            System.out.println("Deck "+deck.getTitle()+" not found");
        }

    }
}
