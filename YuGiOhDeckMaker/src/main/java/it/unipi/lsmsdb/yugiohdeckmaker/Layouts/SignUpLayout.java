package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;

public class SignUpLayout {


    private Label user;
    private TextField username;
    private Label pwd;
    private TextField password;
    private Label confPwd;
    private TextField confirmPassword;
    private Label fName;
    private TextField firstName;
    private Label lName;
    private TextField lastName;
    private Label email;
    private TextField emailAddress;
    protected Button login;
    protected Button signUp;
    protected Button sign;
    protected Button back;


    public SignUpLayout(){
        fName = new Label("First Name");
        fName.setLayoutX(80);
        fName.setLayoutY(40);
        firstName = new TextField();
        firstName.setLayoutX(80);
        firstName.setLayoutY(80);
        firstName.setFocusTraversable(false);
        firstName.setMaxWidth(200);
        lName = new Label("Last Name");
        lName.setLayoutX(80);
        lName.setLayoutY(120);
        lastName = new TextField();
        lastName.setLayoutX(80);
        lastName.setLayoutY(160);
        lastName.setFocusTraversable(false);
        lastName.setMaxWidth(200);
        user = new Label("Insert Username");
        user.setLayoutX(80);
        user.setLayoutY(200);
        username = new TextField();
        username.setLayoutX(80);
        username.setLayoutY(240);
        username.setFocusTraversable(false);
        username.setMaxWidth(200);
        email = new Label("Insert Email");
        email.setLayoutX(280);
        email.setLayoutY(40);
        emailAddress = new TextField();
        emailAddress.setLayoutX(280);
        emailAddress.setLayoutY(80);
        emailAddress.setFocusTraversable(false);
        emailAddress.setMaxWidth(200);
        pwd = new Label("Insert Password");
        pwd.setLayoutX(280);
        pwd.setLayoutY(120);
        password = new TextField();
        password.setLayoutX(280);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);
        confPwd = new Label("Confirm Password");
        confPwd.setLayoutX(280);
        confPwd.setLayoutY(200);
        confirmPassword = new TextField();
        confirmPassword.setLayoutX(280);
        confirmPassword.setLayoutY(240);
        confirmPassword.setFocusTraversable(false);
        confirmPassword.setMaxWidth(200);
        sign = new Button("SIGN UP");
        sign.setLayoutY(280);
        sign.setLayoutX(80);
        sign.setMaxWidth(300);
        back = new Button("BACK");
        back.setLayoutX(640);
        back.setLayoutY(560);
        back.setMaxWidth(300);
    }

    public Node[] getSignUpNodes() {
        Node[] returnNode = {user, username, pwd, password, fName, firstName, lName, lastName,
                email, emailAddress,confPwd, confirmPassword, sign, back};
        return returnNode;
    }

    public Button getBack() {
        return back;
    }

    public Button getSign() {
        return sign;
    }

}
