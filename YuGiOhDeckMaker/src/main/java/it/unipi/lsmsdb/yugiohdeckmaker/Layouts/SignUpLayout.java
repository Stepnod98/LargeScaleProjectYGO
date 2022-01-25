package it.unipi.lsmsdb.yugiohdeckmaker.Layouts;

import javafx.scene.Node;
import javafx.scene.control.*;

public class SignUpLayout {


    private Label user;
    private TextField username;
    private Label pwd;
    private PasswordField password;
    private Label confPwd;
    private PasswordField confirmPassword;
    private Label fName;
    private TextField firstName;
    private Label lName;
    private TextField lastName;
    private Label email;
    private TextField emailAddress;
    private Button sign;
    private Button back;
    private Label log;
    private TextField logText;


    public SignUpLayout(){

        logText = new TextField();
        logText.setLayoutX(480);
        logText.setLayoutY(240);
        logText.setMinWidth(240);
        logText.setEditable(false);
        log = new Label("Log:");
        log.setLayoutX(480);
        log.setLayoutY(200);
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
        password = new PasswordField();
        password.setLayoutX(280);
        password.setLayoutY(160);
        password.setFocusTraversable(false);
        password.setMaxWidth(200);
        confPwd = new Label("Confirm Password");
        confPwd.setLayoutX(280);
        confPwd.setLayoutY(200);
        confirmPassword = new PasswordField();
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
                email, emailAddress,confPwd, confirmPassword, sign, back, log, logText};
        return returnNode;
    }

    public void printError(String err){
        logText.setText(err);
        logText.setStyle("-fx-text-inner-color: red;");
    }

    public void printLog(String log){
        logText.setText(log);
        logText.setStyle("-fx-text-inner-color: green;");
    }

    public Button getBack() {
        return back;
    }

    public Button getSign() {
        return sign;
    }

    public TextField getConfirmPassword() {
        return confirmPassword;
    }

    public TextField getEmailAddress() {
        return emailAddress;
    }

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getLogText() {
        return logText;
    }
}
