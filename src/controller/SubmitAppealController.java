package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Appeal;
import model.Defendant;
import model.Report;
import network.APIManager;
import ui.UIViewController;
import view.DialogView;

import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created By Tony on 18/02/2018
 */
public class SubmitAppealController extends UIViewController {

    @FXML
    private Label id;

    @FXML
    private TextField idField;

    @FXML
    private TextArea reason;

    @FXML
    private Label date;

    @FXML
    private Button submit;

    @FXML
    private Button exitButton;

    public SubmitAppealController() {
        super("/resources/xml/controller_appeal.fxml");

    }

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        submit.setOnAction(event -> {
            Defendant defendant = new Defendant(Integer.parseInt(id.getText()),null,null,null);
            Appeal appeal = new Appeal(0,reason.getText(),new Date());
            appeal.setDefendant(defendant);
            appeal.setReport(new Report(idField.getText()));
            submitAppeal(appeal);
        });

        date.setText(new Date().toLocaleString());

    }

    public void setId(String id) {
        this.id.setText(id);
    }

    private void submitAppeal(Appeal appeal){
        DialogView dialogView = new DialogView();
        dialogView.setTitle("Submitting Appeal");
        dialogView.setMessage("Please wait");
        dialogView.show(this.view);
        APIManager.getInstance().submitAppeal(appeal,(response, exception) -> {
            if(response.isOK()){
                dialogView.setMessage("Appeal Submitted!");
                idField.setText(null);
                reason.setText(null);
                date.setText(new Date().toLocaleString());

            }else{
                dialogView.setMessage("Something went wrong: "+(exception == null ? response.getMessage() : exception.getMessage()));
            }
            dialogView.getPostiveButton().setText("Done");
            dialogView.setPostiveEventHandler(event -> dialogView.close());
            dialogView.getPostiveButton().setVisible(true);
        });
    }

    @Override
    public String title() {
        return "Submit Appeal";
    }

    public void setOnExit(EventHandler<ActionEvent> eventHandler){
        exitButton.setOnAction(eventHandler);
    }
}
