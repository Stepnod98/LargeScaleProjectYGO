/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.AdminLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import static java.lang.Integer.parseInt;

public class AdminPanel {

    private static AdminLayout adminLayout;
    public AdminPanel(AdminLayout adminLayout){
        this.adminLayout = adminLayout;
        setEvents();
        setBrowseEvents();
    }

    public static void addCard(){
        String title = adminLayout.getTitle();
        String imgurl = adminLayout.getImageUrl();
        int atk = parseInt(adminLayout.getAtk());
        int def = parseInt(adminLayout.getDef());
        int level = parseInt(adminLayout.getLevel());
        String desc = adminLayout.getDesc();
        String type = adminLayout.getType();
        String archetype = adminLayout.getArchetype();
        String attribute = adminLayout.getAttribute();
        String effectType = adminLayout.getEffectType();
        MongoDBManager.insertCard(title, imgurl, atk, def, level, desc, type, archetype, attribute, effectType);
    }

    public static void removeCard(){
        String title = adminLayout.getCardToRemoveTitle();
        if(title.equals("")){
            return;
        }
        else{
            MongoDBManager.remove(title);
        }

    }

    public static void removeUser(){
        String username = adminLayout.getUserToRemove();
        if(username.equals("")){
            return;
        }
        else{
            MongoDBManager.remove(username);
        }

    }

    public static void viewCardList(){
        adminLayout.clearErrors();
        //List<String> l = MongoDBManager.findUsers(adminLayout.getCardToRemoveTitle());
        /*List<String> l = new ArrayList<>();
        l.add("ad");
        l.add("eb");
        l.add("cgf");
        l.add("adedf");
        l.add("tel");*/
        //BorderPane bp = BrowseManager.viewList(adminLayout.getCardToRemoveTf(), l);
        //adminLayout.showListResults(bp, 520, 100);

    }

    public static void viewUserList(){
        adminLayout.clearErrors();
        //List<String> l = MongoDBManager.findUsers(adminLayout.getUserToRemove());
        /*List<String> l = new ArrayList<>();
        l.add("ad");
        l.add("eb");
        l.add("cgf");
        l.add("adedf");
        l.add("tel");*/
        //BorderPane bp = BrowseManager.viewList(adminLayout.getUserToRemoveTf(), l);
        //adminLayout.showListResults(bp, 520, 230);
    }

    public static void setEvents(){
        adminLayout.getRemoveUser().setOnAction((ActionEvent ev)->{removeUser();});
        adminLayout.getRemoveCard().setOnAction((ActionEvent ev)->{removeCard();});
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
}
