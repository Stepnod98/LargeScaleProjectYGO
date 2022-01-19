/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.AdminLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;



public class AdminPanel {

    private AdminLayout adminLayout;
    public AdminPanel(AdminLayout adminLayout){
        this.adminLayout = adminLayout;
        setEvents();
        setBrowseEvents();
    }

    public void addCard(){
        String title = adminLayout.getTitle();
        String imgurl = adminLayout.getImageUrl();
        int atk = Integer.parseInt(adminLayout.getAtk());
        int def = Integer.parseInt(adminLayout.getDef());
        int level = Integer.parseInt(adminLayout.getLevel());
        String desc = adminLayout.getDesc();
        String type = adminLayout.getType();
        String archetype = adminLayout.getArchetype();
        String attribute = adminLayout.getAttribute();
        String effectType = adminLayout.getEffectType();
        MongoDBManager.insertCard(title, imgurl, atk, def, level, desc, type, archetype, attribute, effectType);
    }

    public void removeCard(String title){

        if(title.equals("")){
            return;
        }
        else{
            MongoDBManager.remove(title);
        }

    }

    public void removeUser(String username){

        if(username.equals("")){
            return;
        }
        else{
            MongoDBManager.remove(username);
            Neo4jManager.delete(new User(username));
        }

    }


    public void setEvents(){
        adminLayout.getRemoveUser().setOnAction((ActionEvent ev)->{
            GUIManager.clearAdminBoxes();

            if(MongoDBManager.findUser(new User (adminLayout.getUserToRemove()))){
                setUserVbox();
                GUIManager.addNode(adminLayout.getUserVbox());
            }

        });

        adminLayout.getRemoveCard().setOnAction((ActionEvent ev)->{

            GUIManager.clearAdminBoxes();

            if(MongoDBManager.findCard(adminLayout.getCardToRemoveTitle()) != null){
                setCardVbox();
                GUIManager.addNode(adminLayout.getCardVbox());
            }


        });
        adminLayout.getAddCard().setOnAction((ActionEvent ev)->{addCard();});
        adminLayout.getLogout().setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
        adminLayout.getCardToRemoveTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    adminLayout.getBrowseCardResults().setVisible(false);
                }else {
                    adminLayout.updateBrowseCardResults(MongoDBManager.findCards(newValue));
                }
            }
        });
        adminLayout.getCardToRemoveTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    adminLayout.getBrowseCardResults().setVisible(false);
                }
            }
        });


        adminLayout.getUserToRemoveTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    adminLayout.getBrowseUserResults().setVisible(false);
                }else {

                    adminLayout.updateBrowseUserResults(Neo4jManager.browseUsers(newValue));
                }
            }
        });
        adminLayout.getUserToRemoveTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    adminLayout.getBrowseUserResults().setVisible(false);
                }
            }
        });
        adminLayout.getBrowseCardResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    adminLayout.getBrowseCardResults().setVisible(true);
                }else {
                    adminLayout.getBrowseCardResults().setVisible(false);
                }
            }
        });
        adminLayout.getBrowseUserResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    adminLayout.getBrowseUserResults().setVisible(true);
                }else {
                    adminLayout.getBrowseUserResults().setVisible(false);
                }
            }
        });
    }

    private void setBrowseEvents() {
        adminLayout.getBrowseCardResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseCardTasks());
            }
        });
        adminLayout.getBrowseUserResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseUserTasks());
            }
        });
    }

    private void browseCardTasks(){
        if(adminLayout.getBrowseCardResults().getSelectionModel().getSelectedItem() != null) {
            String selected = adminLayout.getBrowseCardResults().getSelectionModel().getSelectedItem();
            adminLayout.getBrowseCardResults().getSelectionModel().clearSelection();
            adminLayout.getCardToRemoveTf().setText(selected);
            adminLayout.getBrowseCardResults().setVisible(false);
        }
    }

    private void browseUserTasks(){
        if(adminLayout.getBrowseUserResults().getSelectionModel().getSelectedItem() != null) {
            String selected = adminLayout.getBrowseUserResults().getSelectionModel().getSelectedItem();
            adminLayout.getBrowseUserResults().getSelectionModel().clearSelection();
            adminLayout.getUserToRemoveTf().setText(selected);
            adminLayout.getBrowseUserResults().setVisible(false);
        }
    }

    private void setUserVbox(){
        String username = adminLayout.getUserToRemove();
        adminLayout.showUserFindResults(username);

        HBox hBox = (HBox) adminLayout.getUserVbox().getChildren().get(adminLayout.getUserVbox().getChildren().size()-1);

        ((Button) hBox.getChildren().get(0)).setText("REMOVE");
        ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            removeUser(username);
            hBox.getChildren().get(0).setDisable(true);
        });
    }

    private void setCardVbox(){
        String title = adminLayout.getCardToRemoveTitle();

        Image image = new Image(MongoDBManager.getImageUrl(new Card(title)), 300, 300, true, false);


        adminLayout.showCardFindResults(title, image);

        HBox hBox = (HBox) adminLayout.getCardVbox().getChildren().get(adminLayout.getCardVbox().getChildren().size()-1);

        ((Button) hBox.getChildren().get(0)).setText("REMOVE");
        ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            removeCard(title);
            hBox.getChildren().get(0).setDisable(true);
        });
    }
}
