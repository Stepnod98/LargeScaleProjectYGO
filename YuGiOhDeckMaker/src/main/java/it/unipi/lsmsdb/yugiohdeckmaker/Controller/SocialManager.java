/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Exceptions.*;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.Row;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.SocialLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import javafx.event.ActionEvent;

public class SocialManager {

    private final User currentUser;
    private final SocialLayout socialLayout;

    public SocialManager(SocialLayout socialLayout, User currentUser){
        this.currentUser = currentUser;
        this.socialLayout = socialLayout;
        setEvents();
    }

    private void setEvents(){
        socialLayout.getShareDeck().setOnAction((ActionEvent ev)->{share();});
        socialLayout.getFindUser().setOnAction((ActionEvent ev)->{findUser();});
        socialLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        socialLayout.getBack().setOnAction((ActionEvent ev)->{
            GUIManager.openAppManager();});
        socialLayout.getViewRecDeck().setOnAction((ActionEvent ev)->{
            socialLayout.showDeckRecResults(Neo4jManager.getRecommendedDecks(currentUser));
            setLikeEvents();
            socialLayout.printLog("Recommended users shown");
            GUIManager.addNode(socialLayout.getTableNodes());
        });
        socialLayout.getViewRecUser().setOnAction((ActionEvent ev)->{
            socialLayout.showUserRecResults(Neo4jManager.getRecommendedUsers(currentUser));
            setFollowEvents();
            socialLayout.printLog("Recommended deck shown");
            GUIManager.addNode(socialLayout.getTableNodes());
        });
    }

    private void setFollowEvents(){

        for(int i = 0; i < socialLayout.getFindUserRecTable().getItems().size(); i++){
            Row row = (Row) socialLayout.getFindUserRecTable().getItems().get(i);
            row.getButton().setText("Follow");
            row.getButton().setOnAction((ActionEvent ev) -> {
                follow(new User(row.getInfo()));
                row.getButton().setDisable(true);
            });
        }
    }

    private void setLikeEvents(){

        for(int i = 0; i < socialLayout.getFindDeckRecTable().getItems().size(); i++){
            Row row = (Row) socialLayout.getFindDeckRecTable().getItems().get(i);
            row.getButton().setText("Like");
            row.getButton().setOnAction((ActionEvent ev) -> {
                like(new Deck(row.getInfo()));
                row.getButton().setDisable(true);
            });
        }
    }

    public void share(){
        try {
            Neo4jManager.shareDeck(currentUser,new Deck(socialLayout.getToShare().getText()));
            socialLayout.printLog("Deck shared!");

        } catch (DeckPresentException | DeckNotExistsException e) {
            System.out.println(e.getMessage());

            socialLayout.printError(e.getMessage());

        }
    }



    public void like(Deck deck){

        try {
            Neo4jManager.likeDeck(currentUser,deck);
            socialLayout.printLog("Deck liked!");

        } catch (LikePresentException | DeckYoursException e) {

            socialLayout.printError(e.getMessage());

        }

    }

    //This method is used to unlike a liked deck
    public void unlike(Deck deck){

        try {
            Neo4jManager.unlikeDeck(currentUser,deck);
            socialLayout.printLog("Like removed");
        } catch (LikeNotPresentException e) {

            socialLayout.printError(e.getMessage());

        }
    }

    //This method is used to follow on social the given user
    public void follow(User user){
        try {
            Neo4jManager.follow(currentUser,user);
            socialLayout.printLog("User followed");

        } catch (UserPresentException | UserNotExistsException e) {

            socialLayout.printError(e.getMessage());

        }
    }

    //This method is used to unfollow on social the given user
    public void unfollow(User user){
        try {
            Neo4jManager.unfollow(currentUser,user);
            socialLayout.printLog("User unfollowed");

        } catch (FriendshipNotFoundException e) {
            socialLayout.printError(e.getMessage());
        }
    }
    
    public void findUser(){

        User user = new User(socialLayout.getUserToFind().getText());
        if(MongoDBManager.findUser(user)){

            socialLayout.showUserFindResults(user.username);
            Row row = (Row) socialLayout.getFindUserTable().getItems().get(0);
            if(!Neo4jManager.checkFriendship(currentUser,user)){
                row.getButton().setText("Follow");
                row.getButton().setOnAction((ActionEvent ev) -> {
                    follow(new User(row.getInfo()));
                    row.getButton().setDisable(true);
                });
            }else{
                row.getButton().setText("Unfollow");
                row.getButton().setOnAction((ActionEvent ev) -> {
                    unfollow(new User(row.getInfo()));
                    row.getButton().setDisable(true);
                });
            }
            GUIManager.addNode(socialLayout.getTableNodes());
            socialLayout.printLog("User found");
        }else{
            socialLayout.printError("User not found");
        }
    }
    
    public void findDeck(){

        Deck deck = new Deck(socialLayout.getDeckToFind().getText());
        if(Neo4jManager.findDeckOnSocial(currentUser,deck)){

            socialLayout.showDeckFindResults(deck.getTitle());
            Row row = (Row) socialLayout.getFindDeckTable().getItems().get(0);

            if(!Neo4jManager.checkLikedDeck(currentUser,deck)) {
                row.getButton().setText("Like");
                row.getButton().setOnAction((ActionEvent ev) -> {
                    like(new Deck(row.getInfo()));
                    row.getButton().setDisable(true);
                });
            }else {
                row.getButton().setText("Remove Like");
                row.getButton().setOnAction((ActionEvent ev) -> {
                    unlike(new Deck(row.getInfo()));
                    row.getButton().setDisable(true);
                });
            }
            socialLayout.printLog("Deck found");
            GUIManager.addNode(socialLayout.getTableNodes());
        }else{
            socialLayout.printError("Deck not found");
        }

    }
}
