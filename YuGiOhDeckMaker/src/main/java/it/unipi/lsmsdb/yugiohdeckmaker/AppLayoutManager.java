package it.unipi.lsmsdb.yugiohdeckmaker;

import javafx.event.ActionEvent;

public class AppLayoutManager {

    private final AppLayout appLayout;
    private final User user;

    public AppLayoutManager(AppLayout appLayout, User user){
        this.appLayout = appLayout;
        this.user = user;
        setEvents();
    }

    private void setEvents(){
        appLayout.getbDeckBuilder().setOnAction((ActionEvent ev)->{GUIManager.openDeckBuilder();});
        appLayout.getbDeckManager().setOnAction((ActionEvent ev)->{GUIManager.openDeckManager();});
        appLayout.getbSocial().setOnAction((ActionEvent ev)->{GUIManager.openSocialManager(user);});
        appLayout.getbLogout().setOnAction((ActionEvent ev)->{LoginManager.logout();});
    }
}
