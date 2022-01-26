package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.MongoDBManager;
import it.unipi.lsmsdb.yugiohdeckmaker.DBManagers.Neo4jManager;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Card;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.Deck;
import it.unipi.lsmsdb.yugiohdeckmaker.Entities.User;
import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.AdminLayout;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;


public class AdminPanel {

    private final AdminLayout adminLayout;
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
        adminLayout.clearAddCard();
        adminLayout.printLog("Card added!");
    }

    public void removeCard(String title){

        if(title.equals("")){
            return;
        }
        else{
            MongoDBManager.removeCard(title);
            GUIManager.clearAdminBoxes();
            adminLayout.printLog("Card removed!");
        }

    }

    public void removeDeck(String title){

        if(title.equals("")){
            return;
        }
        else{
            MongoDBManager.removeDeck(title);
            Neo4jManager.delete(new Deck(title));
            GUIManager.clearAdminBoxes();
            adminLayout.printLog("Deck removed!");
        }

    }

    public void removeUser(String username){

        if(username.equals("")){
            return;
        }
        else{
            MongoDBManager.removeUser(username);
            Neo4jManager.delete(new User(username));
            GUIManager.clearAdminBoxes();
            adminLayout.printLog("Username removed!");
        }

    }

    public void updateUsername(String oldUsername, String newUsername){
        if(newUsername.equals("")){
            return;
        }
        else{
            MongoDBManager.updateUsername(oldUsername, newUsername);
            Neo4jManager.updateUserDecks(oldUsername, newUsername);
            Neo4jManager.updateUser(oldUsername, newUsername);
            GUIManager.clearAdminBoxes();
            adminLayout.printLog("Username updated!");
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

        adminLayout.getRemoveDeck().setOnAction((ActionEvent ev)->{
            GUIManager.clearAdminBoxes();
            if(MongoDBManager.findDeck(adminLayout.getDeckToRemove()) != null){
                setDeckVbox();
                GUIManager.addNode(adminLayout.getDeckVbox());
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

                    adminLayout.updateBrowseUserResults(MongoDBManager.findUsers(newValue));
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

        adminLayout.getDeckToRemoveTf().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(newValue.equals("")){
                    adminLayout.getBrowseDeckResults().setVisible(false);
                }else {

                    adminLayout.updateBrowseDeckResults(MongoDBManager.findDecks(newValue));
                }
            }
        });
        adminLayout.getDeckToRemoveTf().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue){
                    adminLayout.getBrowseDeckResults().setVisible(false);
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

        adminLayout.getBrowseDeckResults().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if(newPropertyValue){
                    adminLayout.getBrowseDeckResults().setVisible(true);
                }else {
                    adminLayout.getBrowseDeckResults().setVisible(false);
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

        adminLayout.getBrowseDeckResults().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Platform.runLater(() -> browseDeckTasks());
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

    private void browseDeckTasks(){
        if(adminLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem() != null) {
            String selected = adminLayout.getBrowseDeckResults().getSelectionModel().getSelectedItem();
            adminLayout.getBrowseDeckResults().getSelectionModel().clearSelection();
            adminLayout.getDeckToRemoveTf().setText(selected);
            adminLayout.getBrowseDeckResults().setVisible(false);
        }
    }

    private void setUserVbox(){
        String username = adminLayout.getUserToRemove();
        adminLayout.showUserFindResults(username);

        HBox hBox = (HBox) adminLayout.getUserVbox().getChildren().get(adminLayout.getUserVbox().getChildren().size()-3);

        ((Button) hBox.getChildren().get(0)).setText("REMOVE");
        ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            removeUser(username);
            hBox.getChildren().get(0).setDisable(true);
        });
        HBox hBox2 = (HBox) adminLayout.getUserVbox().getChildren().get(adminLayout.getUserVbox().getChildren().size()-2);
        HBox hBox3 = (HBox) adminLayout.getUserVbox().getChildren().get(adminLayout.getUserVbox().getChildren().size()-1);
        ((Button) hBox3.getChildren().get(0)).setText("UPDATE");
        ((Button) hBox3.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            updateUsername(username, ((TextField) hBox2.getChildren().get(0)).getText());
            System.out.println("QUI!! " + ((TextField) hBox2.getChildren().get(0)).getText());
        });
    }

    private void setCardVbox(){
        String title = adminLayout.getCardToRemoveTitle();

        Image image = new Image("file:./../img/backCard.png", 300, 300, true, false);
        if(MongoDBManager.getImageUrl(new Card(title)) != null) {
            image = new Image(MongoDBManager.getImageUrl(new Card(title)), 300, 300, true, false);
        }

        adminLayout.showCardFindResults(title, image);

        HBox hBox = (HBox) adminLayout.getCardVbox().getChildren().get(adminLayout.getCardVbox().getChildren().size()-1);

        ((Button) hBox.getChildren().get(0)).setText("REMOVE");
        ((Button) hBox.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            removeCard(title);
            hBox.getChildren().get(0).setDisable(true);
        });
    }

    private void setDeckVbox(){
        String title = adminLayout.getDeckToRemove();
        String creator = MongoDBManager.getCreator(title);
        adminLayout.showDeckFindResults(title, creator);

        HBox hBox1 = (HBox) adminLayout.getDeckVbox().getChildren().get(adminLayout.getDeckVbox().getChildren().size()-1);

        ((Button) hBox1.getChildren().get(0)).setText("REMOVE");
        ((Button) hBox1.getChildren().get(0)).setOnAction((ActionEvent ev) -> {
            removeDeck(title);
            hBox1.getChildren().get(0).setDisable(true);
        });
    }
}