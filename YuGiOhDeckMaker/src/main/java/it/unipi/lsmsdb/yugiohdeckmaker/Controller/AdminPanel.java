/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.AdminLayout;
import javafx.event.ActionEvent;

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
        adminLayout.getRemoveCard().setOnAction((ActionEvent ev)->{removeCard();});
        adminLayout.getAddCard().setOnAction((ActionEvent ev)->{addCard();});
        adminLayout.getLogout().setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
