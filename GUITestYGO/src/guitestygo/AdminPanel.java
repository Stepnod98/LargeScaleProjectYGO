/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

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
        
    }
    
    public static void removeCard(){
        
    }
    
    public static void setEvents(){
        adminLayout.removeCard.setOnAction((ActionEvent ev)->{removeCard();});
        adminLayout.addCard.setOnAction((ActionEvent ev)->{addCard();});	
        adminLayout.logout.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
