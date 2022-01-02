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
public class LoginManager {
    private static LoginLayout loginLayout;
    public LoginManager(LoginLayout loginLayout){
        this.loginLayout = loginLayout;
    }
    public static void login(){
        /*if(){
            GUIManager.openAdminPanel();
        }*/
        GUIManager.openAppManager();
        //GUIManager.openAdminPanel();
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void checkCredentials(){
        
    }
    
    public static void signup(){
        GUIManager.openSignUpManager();
        loginLayout.sign.setOnAction((ActionEvent ev)->{checkCredentials();}); 
    }
    
    public static void setEvents(){
        loginLayout.login.setOnAction((ActionEvent ev)->{login();});	
        loginLayout.signUp.setOnAction((ActionEvent ev)->{signup();});
    }
    
    public static void setSignUpEvents(){
         loginLayout.back.setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
    }
}
