package it.unipi.lsmsdb.yugiohdeckmaker.Controller;

import it.unipi.lsmsdb.yugiohdeckmaker.Layouts.SignUpLayout;
import javafx.event.ActionEvent;

public class SignUpManager {

    private static SignUpLayout signUpLayout;
    public SignUpManager(SignUpLayout signUpLayout){
        this.signUpLayout = signUpLayout;
        setEvents();
    }

    private void setEvents(){
        signUpLayout.getBack().setOnAction((ActionEvent ev)->{GUIManager.openLoginManager();});
        signUpLayout.getSign().setOnAction((ActionEvent ev)->{checkCredentials();});
    }

    public static void checkCredentials(){

    }

}
