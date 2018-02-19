package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.EmailValidator;
import network.APIManager;
import ui.UIViewController;
import view.DialogView;

import java.util.ResourceBundle;


/**
 * Created By Tony on 14/02/2018
 */
public class LoginController extends UIViewController {

    @FXML
    private Label copyright;

    @FXML
    private TextField userInputField;

    @FXML
    private TextField passwordInputField;

    @FXML
    private TextField idInputField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button submitAppealButton;

    private Authentication authentication;

    private AppealCallback appealCallback;

    private DialogView dialogView = new DialogView();

    private boolean isDialogShowing = false;

    /**
     * Create a UIViewController instance from any fxml file.
     */
    public LoginController() {
        super("/resources/xml/controller_login.fxml");
    }

    private void doLogin(){

        if(!isDialogShowing){
            String email = userInputField.getText();
            String password = passwordInputField.getText();

            if(EmailValidator.validate(email) && password.length() >= 8) {
                APIManager.getInstance().login(email, password, (response, id, token, roleId, ex) -> {
                    if (roleId != -1)
                        passwordInputField.setText(null);

                    if(roleId == -1){
                        if(ex != null){
                            dialogView.setTitle("Error");
                            dialogView.setMessage(ex.getLocalizedMessage());
                            ex.printStackTrace();
                        }else{
                            dialogView.setTitle("Invalid Credentials");
                            dialogView.setMessage("Incorrect email or password. Please try again.");
                        }
                        dialogView.setPostiveEventHandler(event1 -> dialogView.close());
                        dialogView.getPostiveButton().setText("Close");
                        dialogView.show(view);
                        isDialogShowing = true;
                    }

                    if (authentication != null)
                        authentication.onAuth(roleId,ex);

                });
            }else{
                dialogView.setTitle("Invalid Credentials");
                dialogView.setMessage("Please check your login info and try again.");
                dialogView.setPostiveEventHandler(event1 -> dialogView.close());
                dialogView.getPostiveButton().setText("Close");
                dialogView.show(this.view);
                isDialogShowing = true;
            }
        }else{
            dialogView.close();
            isDialogShowing = false;
        }
    }

    private void doAppeal(){
        appealCallback.makeAppeal(idInputField.getText());
    }

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        loginButton.setOnAction(this::onEnter);
        userInputField.setOnAction(this::onEnter);
        passwordInputField.setOnAction(this::onEnter);
        idInputField.setOnAction(this::onAppeal);
        submitAppealButton.setOnAction(this::onAppeal);
    }

    public void setOnExit(EventHandler<ActionEvent> eventHandler){
        exitButton.setOnAction(eventHandler);
    }

    public void onEnter(ActionEvent actionEvent) {
        doLogin();
    }

    public void onAppeal(ActionEvent actionEvent) {
        doAppeal();
    }

    @FunctionalInterface
    public interface Authentication{
        void onAuth(int role, Exception e);
    }

    @FunctionalInterface
    public interface AppealCallback{
        void makeAppeal(String id);
    }

    public void setOnAuth(Authentication authentication) {
        this.authentication = authentication;
    }

    public void setAppealCallback(AppealCallback appealCallback) {
        this.appealCallback = appealCallback;
    }
}
