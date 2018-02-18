package view;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import model.Report;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class TicketCreateView extends UIView {

    @FXML
    private ComboBox violationCombo;

    @FXML
    private JFXTextField plateField;

    @FXML
    private JFXTextField colorField;

    @FXML
    private ComboBox modelCombo;

    @FXML
    private JFXTextField idField;

    @FXML
    private JFXTextField licenseField;

    @FXML
    private JFXTextField fnField;

    @FXML
    private JFXTextField lnField;

    @FXML
    private JFXTextField addressField;

    @FXML
    private ComboBox locationCombo;

    public TicketCreateView(){
        super("/resources/xml/ticket_create.fxml");

    }

    public TicketCreateView(Report report){
        this();

        //TODO: complete setup

    }


}
