/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitestygo;

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
    }
    
    public static void logout(){
        GUIManager.openLoginManager();
    }
    
    public static void signup(){
        
    }
}
