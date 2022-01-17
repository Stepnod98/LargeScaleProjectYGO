/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Stefano
 */
public class AdminPanel {
    private static AdminLayout adminLayout;
    public AdminPanel(AdminLayout adminLayout){
        this.adminLayout = adminLayout;
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
        List<String> l = MongoDBManager.findUsers(adminLayout.getCardToRemoveTitle());
        /*List<String> l = new ArrayList<>();
        l.add("ad");
        l.add("eb");
        l.add("cgf");
        l.add("adedf");
        l.add("tel");*/
        BorderPane bp = BrowseManager.viewList(adminLayout.getCardToRemoveTf(), l);
        adminLayout.showListResults(bp, 520, 100);
    
    }
    
    public static void viewUserList(){
        adminLayout.clearErrors();
        List<String> l = MongoDBManager.findUsers(adminLayout.getUserToRemove());
        /*List<String> l = new ArrayList<>();
        l.add("ad");
        l.add("eb");
        l.add("cgf");
        l.add("adedf");
        l.add("tel");*/
        BorderPane bp = BrowseManager.viewList(adminLayout.getUserToRemoveTf(), l);
        adminLayout.showListResults(bp, 520, 230);
    }
    
    public static void setEvents(){
        adminLayout.getCardToRemoveTf().textProperty().addListener((obs, oldValue, newValue)->{viewCardList();});
        adminLayout.getUserToRemoveTf().textProperty().addListener((obs, oldValue, newValue)->{viewUserList();});
        adminLayout.removeCard.setOnAction((ActionEvent ev)->{removeCard();});
        adminLayout.removeUser.setOnAction((ActionEvent ev)->{removeUser();});
        adminLayout.addCard.setOnAction((ActionEvent ev)->{addCard();});	
        adminLayout.logout.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
