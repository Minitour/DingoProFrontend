package main;

import controller.LoginController;
import controller.SubmitAppealController;
import controller.master.AdminMasterController;
import controller.master.HighRankOfficerController;
import controller.master.MasterMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import network.AutoSignIn;
import ui.UIViewController;

import java.util.Optional;

/**
 * Created By Tony on 14/02/2018
 */
public class AppDelegate extends Application {

    private final LoginController loginController = initLoginController();
    private final SubmitAppealController appealController = initSubmitController();

    private Stage loginStage;
    private Stage mainStage = new Stage();

    public static void main(String... args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.loginStage = primaryStage;
        loginStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(loginController.view));
        primaryStage.show();

        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });


    }

    private LoginController initLoginController(){
        LoginController controller = new LoginController();

        controller.setOnExit(event -> loginStage.close());
        controller.setOnAuth((role,ex)-> {
            if(role != -1) {
                onLoginSuccess(role);
            }
        });

        controller.setAppealCallback(this::onMakeAppeal);

        return controller;
    }

    private SubmitAppealController initSubmitController(){
        SubmitAppealController appealController = new SubmitAppealController();
        appealController.setOnExit(event -> {
            mainStage.close();
            loginStage.show();
        });
        return appealController;
    }

    void onLoginSuccess(int role){
        MasterMenuController controller = null;
        switch (role){
            case 0:
                controller = new AdminMasterController();
                break;
            case 1:
                controller = new HighRankOfficerController();
                break;
            case 2:
                controller = null;
                break;
        }

        assert controller != null;
        controller.setOnLogout(event -> {
            mainStage.close();
            AutoSignIn.reset();
            loginStage.show();
        });

        //loginStage.getScene().setRoot(controller.view);
        loginStage.close();
        showLoggedInStage(controller,1200,false);
    }

    void onMakeAppeal(String id){
        appealController.setId(id);
        showLoggedInStage(appealController,700,true);
    }

    void showLoggedInStage(UIViewController controller,int size,boolean borderless){

        //get the old scene
        Scene s = Optional.ofNullable(mainStage).orElseGet(Stage::new).getScene();

        //release old stage and create new one
        mainStage = new Stage();

        //set style
        if(borderless)
            mainStage.initStyle(StageStyle.UNDECORATED);

        //set title
        mainStage.setTitle(controller.title());

        //if s is null, create a new scene
        if(s == null)
            s = new Scene(controller.view,size,((double) 8/12) * size);
        else{
            //else update it's root
            s.setRoot(controller.view);
            mainStage.setWidth(size);
            mainStage.setHeight(((double) 8/12) * size);
        }


        mainStage.setScene(s);

        mainStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        mainStage.show();
    }
}
