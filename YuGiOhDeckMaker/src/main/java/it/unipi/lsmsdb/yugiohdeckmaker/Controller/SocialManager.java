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
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.SocialLayout;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

public class SocialManager {

    private final User currentUser;
    private final SocialLayout socialLayout;

    public SocialManager(SocialLayout socialLayout, User currentUser){
        this.currentUser = currentUser;
        this.socialLayout = socialLayout;
        setEvents();
        setProfile();
    }

    private void setEvents(){
        socialLayout.getShareDeck().setOnAction((ActionEvent ev)->{
            share();
        });

        socialLayout.getFindUser().setOnAction((ActionEvent ev)->{
            findUser(new User(socialLayout.getUserToFind().getText()));
        });

        socialLayout.getFindDeck().setOnAction((ActionEvent ev)->{
            findDeck();
        });

        socialLayout.getBack().setOnAction((ActionEvent ev)->{
            GUIManager.openAppManager();});

        socialLayout.getViewRecDeck().setOnAction((ActionEvent ev)->{

            GUIManager.clearProfSocialLayout();
            GUIManager.clearRecSocialLayout();
            GUIManager.clearSocialLayout();
            socialLayout.showDeckRecResults(Neo4jManager.getRecommendedDecks(currentUser));
            setRecDeckEvents();
            socialLayout.printLog("Recommended decks shown");
            GUIManager.addNode(socialLayout.getFindDeckRecTable());

        });

        socialLayout.getViewRecUser().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfSocialLayout();
            GUIManager.clearRecSocialLayout();
            GUIManager.clearSocialLayout();
            socialLayout.showUserRecResults(Neo4jManager.getRecommendedUsers(currentUser));
            setRecUsersEvents();
            socialLayout.printLog("Recommended users shown");
            GUIManager.addNode(socialLayout.getFindUserRecTable());
        });

        socialLayout.getViewProfile().setOnAction((ActionEvent ev)->{

            GUIManager.clearProfSocialLayout();
            GUIManager.clearRecSocialLayout();
            GUIManager.clearSocialLayout();
            setProfile();
            socialLayout.printLog("Profile shown");
        });

        socialLayout.getUserToFind().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    socialLayout.getBrowseUserResults().setVisible(false);
                }else {
                    socialLayout.updateBrowseUserResults(Neo4jManager.browseUsers(newValue));
                }
            }
        });

        socialLayout.getUserToFind().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    socialLayout.getBrowseUserResults().setVisible(false);
                }
            }
        });

        socialLayout.getDeckToFind().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    socialLayout.getBrowseDeckResults().setVisible(false);
                }else {
                    socialLayout.updateBrowseDeckResults(Neo4jManager.browseDecks(newValue));
                }
            }
        });

        socialLayout.getDeckToFind().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    socialLayout.getBrowseDeckResults().setVisible(false);
                }
            }
        });

        socialLayout.getToShare().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    socialLayout.getBrowseYourDeckResults().setVisible(false);
                }else {
                    // TODO: 17/01/2022 METTERE MONGODB.FINDDECKS CON LA NEW VALUE
                    socialLayout.updateBrowseYourDeckResults(Neo4jManager.browseYourDecks(currentUser.username, newValue));
                }
            }
        });

        socialLayout.getDeckToFind().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    socialLayout.getBrowseYourDeckResults().setVisible(false);
                }
            }
        });

        socialLayout.getBrowseUserResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    socialLayout.getBrowseUserResults().setVisible(true);
                }else {
                    socialLayout.getBrowseUserResults().setVisible(false);
                }
            }
        });
        socialLayout.getBrowseYourDeckResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    socialLayout.getBrowseYourDeckResults().setVisible(true);
                }else {
                    socialLayout.getBrowseYourDeckResults().setVisible(false);
                }
            }
        });
        socialLayout.getBrowseDeckResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    socialLayout.getBrowseDeckResults().setVisible(true);
                }else {
                    socialLayout.getBrowseDeckResults().setVisible(false);
                }
            }
        });

        setBrowseEvents();
    }



    private void setBrowseEvents(){
        socialLayout.getBrowseUserResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseUserTasks());
            }
        });
        socialLayout.getBrowseDeckResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseDeckTasks());
            }
        });
        socialLayout.getBrowseYourDeckResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseYourDeckTasks());
            }
        });
    }

    private void browseUserTasks(){
        if(socialLayout.getBrowseUserResults().getSelectionModel().getSelectedItem() != null) {
            String selected = socialLayout.getBrowseUserResults().getSelectionModel().getSelectedItem();
            socialLayout.getBrowseUserResults().getSelectionModel().clearSelection();
            socialLayout.getUserToFind().setText(selected);
            socialLayout.getBrowseUserResults().setVisible(false);
        }
    }

    private void browseDeckTasks(){
        if(socialLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem() != null) {
            String selected = socialLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem();
            socialLayout.getBrowseDeckResults().getSelectionModel().clearSelection();
            socialLayout.getDeckToFind().setText(selected);
            socialLayout.getBrowseDeckResults().setVisible(false);
        }
    }

    private void browseYourDeckTasks(){
        if(socialLayout.getBrowseYourDeckResults().getSelectionModel().getSelectedItem() != null) {
            String selected = socialLayout.getBrowseYourDeckResults().getSelectionModel().getSelectedItem();
            socialLayout.getBrowseYourDeckResults().getSelectionModel().clearSelection();
            socialLayout.getToShare().setText(selected);
            socialLayout.getBrowseYourDeckResults().setVisible(false);
        }
    }

    private void showDeckTask(double x, double y, TableView<String> tw){
        if(tw.getSelectionModel().getSelectedItem() != null) {
            String title = tw.getSelectionModel().getSelectedItem();
            GUIManager.clearSocialLayout();

            setDeckVbox(new Deck(title), x, y);

            GUIManager.addNode(socialLayout.getDeckVbox());

        }
    }

    private void showUserTask(double x, double y, TableView<String> tw){
        if(tw.getSelectionModel().getSelectedItem() != null) {
            String username = tw.getSelectionModel().getSelectedItem();
            GUIManager.clearSocialLayout();

            setUserVbox(new User(username), x, y);

            GUIManager.addNode(socialLayout.getUserVbox());

        }
    }

    private void setRecUsersEvents(){
        socialLayout.getFindUserRecTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showUserTask(480,240, socialLayout.getFindUserRecTable()));
            }
        });
    }

    private void setRecDeckEvents(){
        socialLayout.getFindDeckRecTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showDeckTask(480, 240, socialLayout.getFindDeckRecTable()));
            }
        });
    }

    private void setFriendsTableEvents(){
        socialLayout.getFriendsTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showUserTask(480, 240, socialLayout.getFriendsTable()));
            }
        });
    }

    private void setFollowersTableEvents(){
        socialLayout.getFollowersTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showUserTask(480, 240, socialLayout.getFollowersTable()));
            }
        });
    }

    private void setSharedTableEvents(){
        socialLayout.getSharedDecksTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showDeckTask(480, 240, socialLayout.getSharedDecksTable()));
            }
        });
    }

    private void setLikedTableEvents(){
        socialLayout.getLikedDecksTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showDeckTask(480, 240, socialLayout.getLikedDecksTable()));
            }
        });
    }

    private void setRecentTableEvents(){
        socialLayout.getRecentDecksTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> showDeckTask(480, 240, socialLayout.getRecentDecksTable()));
            }
        });
    }

    private void setProfileEvents(){

        socialLayout.getViewFriends().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showFriends(Neo4jManager.getFriends(currentUser));
            setFriendsTableEvents();
            socialLayout.printLog("Friends shown");
            GUIManager.addNode(socialLayout.getFriendsTable());
        });

        socialLayout.getViewFollowers().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showFollowers(Neo4jManager.getFollowers(currentUser.username));
            setFollowersTableEvents();
            socialLayout.printLog("Followers shown");
            GUIManager.addNode(socialLayout.getFollowersTable());
        });

        socialLayout.getViewSharedDecks().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showSharedDecksResults(Neo4jManager.getSharedDecks(currentUser.username));
            setSharedTableEvents();
            socialLayout.printLog("Shared decks shown");
            GUIManager.addNode(socialLayout.getSharedDecksTable());
        });

        socialLayout.getViewLikedDecks().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showLikedDecksResults(Neo4jManager.getLikedDecks(currentUser.username));
            setLikedTableEvents();
            socialLayout.printLog("Liked decks shown");
            GUIManager.addNode(socialLayout.getLikedDecksTable());
        });

        socialLayout.getViewRecentDecks().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showRecentDecksResults(Neo4jManager.getRecentSharedDecks(currentUser));
            setRecentTableEvents();
            socialLayout.printLog("Liked decks shown");
            GUIManager.addNode(socialLayout.getRecentDecksTable());
        });
    }

    private void setUserFoundEvents(User user){

        socialLayout.getViewFollowers().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showFollowers(Neo4jManager.getFollowers(user.username));
            setFollowersTableEvents();
            socialLayout.printLog("Followers shown");
            GUIManager.addNode(socialLayout.getFollowersTable());
        });

        socialLayout.getViewSharedDecks().setOnAction((ActionEvent ev)->{
            GUIManager.clearProfTables();
            GUIManager.clearSocialLayout();
            socialLayout.showSharedDecksResults(Neo4jManager.getSharedDecks(user.username));
            setSharedTableEvents();
            socialLayout.printLog("Shared decks shown");
            GUIManager.addNode(socialLayout.getSharedDecksTable());
        });

        if (!Neo4jManager.checkFriendship(currentUser, user)) {
            socialLayout.getFollowUserButton().setText("Follow");
            socialLayout.getFollowUserButton().setOnAction((ActionEvent ev) -> {
                follow(user);
                socialLayout.getFollowUserButton().setDisable(true);
            });
        } else {
            socialLayout.getFollowUserButton().setText("Unfollow");
            socialLayout.getFollowUserButton().setOnAction((ActionEvent ev) -> {
                unfollow(user);
                socialLayout.getFollowUserButton().setDisable(true);
            });
        }
    }


//*************************************************************************************************************
//*                                    ACTIONS
//*************************************************************************************************************

    public void share(){
        try {
            Neo4jManager.shareDeck(currentUser,new Deck(socialLayout.getToShare().getText()));
            socialLayout.printLog("Deck shared!");

        } catch (DeckPresentException | DeckNotExistsException e) {

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

//
    public void findUser(User user){

        if(MongoDBManager.findUser(user)){

            GUIManager.clearProfSocialLayout();
            GUIManager.clearRecSocialLayout();
            GUIManager.clearSocialLayout();
            setUserProfile(user);

            socialLayout.printLog("User found");
        }else{
            socialLayout.printError("User not found");
        }
    }
    
    public void findDeck(){

        Deck deck = new Deck(socialLayout.getDeckToFind().getText());
        if(Neo4jManager.findDeckOnSocial(currentUser,deck)){

            GUIManager.clearProfSocialLayout();
            GUIManager.clearSocialLayout();
            GUIManager.clearRecSocialLayout();

            setDeckVbox(deck, 480, 240);

            GUIManager.addNode(socialLayout.getDeckVbox());
            socialLayout.printLog("Deck found");
        }else{
            socialLayout.printError("Deck not found");
        }
    }

    private void setDeckVbox(Deck deck,double x, double y){
        String creator = Neo4jManager.getCreator(deck.getTitle());
        String likes = Integer.toString(Neo4jManager.getLikes(deck.getTitle()));
        socialLayout.showDeckFindResults(deck.getTitle(), creator, likes, x, y);

        HBox hBox = (HBox) socialLayout.getDeckVbox().getChildren().get(socialLayout.getDeckVbox().getChildren().size()-1);

        if(!Neo4jManager.checkSharedDeck(currentUser,deck)){
            if(!Neo4jManager.checkLikedDeck(currentUser,deck)){
                ((Button)hBox.getChildren().get(0)).setText("Like");
                ((Button)hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                    like(deck);
                    hBox.getChildren().get(0).setDisable(true);
                });
            }else{
                ((Button)hBox.getChildren().get(0)).setText("Remove like");
                ((Button)hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                    unlike(deck);
                    hBox.getChildren().get(0).setDisable(true);
                });
            }
        }else{
            hBox.getChildren().get(0).setVisible(false);
        }

        ((Button)hBox.getChildren().get(1)).setText("View");
        ((Button)hBox.getChildren().get(1)).setOnAction((ActionEvent ev) -> {
            Deck d = MongoDBManager.findDeck(deck.getTitle());
            GUIManager.openDeckBuilder(d);
            hBox.getChildren().get(1).setDisable(true);
        });
    }

    private void setUserVbox(User user, double x, double y){
        String followers = Integer.toString(Neo4jManager.getCountFollowers(user.username));
        String sharedDecks = Integer.toString(Neo4jManager.getCountSharedDecks(user.username));
        String totalLikes = Integer.toString(Neo4jManager.getTotalLikes(user.username));
        socialLayout.showUserFindResults(user.username, followers, sharedDecks, totalLikes, x, y);


        HBox hBox = (HBox) socialLayout.getUserVbox().getChildren().get(socialLayout.getUserVbox().getChildren().size()-1);

        if(!currentUser.username.equals(user.username)) {
            if (!Neo4jManager.checkFriendship(currentUser, user)) {
                ((Button) hBox.getChildren().get(0)).setText("Follow");
                ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                    follow(user);
                    hBox.getChildren().get(0).setDisable(true);
                });
            } else {
                ((Button) hBox.getChildren().get(0)).setText("Unfollow");
                ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
                    unfollow(user);
                    hBox.getChildren().get(0).setDisable(true);
                });
            }
            ((Button)hBox.getChildren().get(1)).setText("View");
            ((Button)hBox.getChildren().get(1)).setOnAction((ActionEvent ev) -> {
                findUser(user);
                hBox.getChildren().get(1).setDisable(true);
            });
        }else{
            hBox.getChildren().get(0).setVisible(false);
            hBox.getChildren().get(1).setVisible(false);
        }

    }

    private void setUserInfo(User user){
        String followers = Integer.toString(Neo4jManager.getCountFollowers(user.username));
        String sharedDecks = Integer.toString(Neo4jManager.getCountSharedDecks(user.username));
        String totalLikes = Integer.toString(Neo4jManager.getTotalLikes(user.username));
        socialLayout.showUserInfo(user.username, followers, sharedDecks, totalLikes);
    }

    private void setUserProfile(User user){

        setUserInfo(user);
        socialLayout.showUserFoundActions();
        setUserFoundEvents(user);
        GUIManager.addNode(socialLayout.getViewSharedDecks());
        GUIManager.addNode(socialLayout.getViewFollowers());
        GUIManager.addNode(socialLayout.getFollowUserButton());
        GUIManager.addNode(socialLayout.getUserProfileVbox());
    }


    private void setProfile(){
        setUserInfo(currentUser);
        socialLayout.showProfileActions();
        setProfileEvents();
        GUIManager.addNode(socialLayout.getViewFriends());
        GUIManager.addNode(socialLayout.getViewFollowers());
        GUIManager.addNode(socialLayout.getViewSharedDecks());
        GUIManager.addNode(socialLayout.getViewLikedDecks());
        GUIManager.addNode(socialLayout.getViewRecentDecks());
        GUIManager.addNode(socialLayout.getUserProfileVbox());
    }
}
