package view;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Appeal;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class AppealView extends UIView {
    @FXML
    private JFXTextField idField;

    @FXML
    private TextArea reason;

    @FXML
    private Label date;

    public AppealView(){
        super("/resources/xml/appeal_create.fxml");
    }

    public AppealView(Appeal appeal){
        this();

        //TODO: complete setup
    }

    public JFXTextField getIdField() {
        return idField;
    }

    public TextArea getReason() {
        return reason;
    }

    public Label getDate() {
        return date;
    }
}
