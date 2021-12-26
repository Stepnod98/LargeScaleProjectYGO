/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;

import java.util.Scanner;

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
        System.out.println(currentUser.username);
    }

    private void setEvents(){
        socialLayout.getShareDeck().setOnAction((ActionEvent ev)->{share();});
        socialLayout.getFindUser().setOnAction((ActionEvent ev)->{findUser();});
        socialLayout.getFindDeck().setOnAction((ActionEvent ev)->{findDeck();});
        socialLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openAppManager();});
    }

    public void openSocial(){
        
    }
    public void share(){
        try {
            Neo4jManager.shareDeck(currentUser,socialLayout.toShare.getText());
        } catch (DeckPresentException | DeckNotExistsException e) {
            System.out.println(e.getMessage());

            // TODO: 25/12/2021
            //socialLayout.printError(e.getMessage());
        }
    }

    // TODO: 26/12/2021 Associate this method with the button follow near the user found
    public void follow(){

        try {
            //Here the userToFind has to been changed with user found! that is stored in the local instance of socialManager!
            Neo4jManager.follow(currentUser,new User(socialLayout.userToFind.getText()));
        } catch (UserPresentException e) {
            // TODO: 26/12/2021
            // socialLayout.printError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
    
    public void findUser(){
        //If the user is found then add to the variable userToFollow!
        if(Neo4jManager.findUser(new User(socialLayout.userToFind.getText()))){
            //Show user found with button
            //socialLayoutShowUserFound(socialLayout.userToFind.getText());

            //FOR TEST THIS HAS TO BE DELETED!
            System.out.println("User found! Would you want to add "+socialLayout.userToFind.getText()+" to your friends?[0\\1]");
            Scanner scanner = new Scanner(System.in);
            int in = scanner.nextInt();
            if(in == 1){
                follow();
            }
        }else{
            //socialLayout.printError("User not found");
            System.out.println("User not found");
        }
    }
    
    public void findDeck(){
        
    }
}
