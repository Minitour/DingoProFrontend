package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Appeal;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class AppealView extends UIView {

    @FXML
    private TextField userId;

    @FXML
    private TextField idField;

    @FXML
    private TextArea reason;

    @FXML
    private Label date;

    public AppealView(){
        super("/resources/xml/form_appeal.fxml");
    }

    public AppealView(Appeal appeal){
        this();

        //TODO: complete setup
        int id = appeal.getDefendant().getId();
        String reportId = appeal.getReport().getAlphaNum();
        String reason = appeal.getReason();
        String date = appeal.getAppealDate().toLocaleString();

        userId.setText(id+"");
        idField.setText(reportId);
        this.reason.setText(reason);
        this.date.setText(date);

        userId.setEditable(false);
        this.reason.setEditable(false);
        this.idField.setEditable(false);
    }

    public TextField getIdField() {
        return idField;
    }

    public TextArea getReason() {
        return reason;
    }

    public Label getDate() {
        return date;
    }
}
