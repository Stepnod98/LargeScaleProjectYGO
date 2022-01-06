/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

import static java.lang.Integer.parseInt;
import javafx.event.ActionEvent;

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
        String set = adminLayout.getCardToRemoveSet();
        if(title.equals("")){
            return;
        }
        if(set.equals("")){
            MongoDBManager.remove(title);
        }
        else{
            MongoDBManager.remove(title, set);
        }
                
    }
    
    public static void setEvents(){
        adminLayout.removeCard.setOnAction((ActionEvent ev)->{removeCard();});
        adminLayout.addCard.setOnAction((ActionEvent ev)->{addCard();});	
        adminLayout.logout.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
