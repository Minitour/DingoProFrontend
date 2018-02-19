package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Report;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class TicketCreateView extends UIView {

    @FXML
    private ComboBox violationCombo;

    @FXML
    private TextField plateField;

    @FXML
    private TextField colorField;

    @FXML
    private ComboBox modelCombo;

    @FXML
    private TextField idField;

    @FXML
    private TextField licenseField;

    @FXML
    private TextField fnField;

    @FXML
    private TextField addressField;

    @FXML
    private ComboBox locationCombo;

    public TicketCreateView(){
        super("/resources/xml/ticket_create.fxml");

    }

    public TicketCreateView(Report report){
        this();
        violationCombo.getItems().add(report.getViolationType());
        violationCombo.getSelectionModel().select(0);

        //set vehicle data
        plateField.setText(report.getVehicle().getLicensePlate());
        colorField.setText(report.getVehicle().getColorHEX());
        modelCombo.getItems().add(report.getVehicle().getModel());
        modelCombo.getSelectionModel().select(0);

        //set suspect data
        idField.setText(report.getDefendant().getId().toString());
        licenseField.setText(report.getDefendant().getDrivingLicense());
        fnField.setText(report.getDefendant().getName());
        addressField.setText(report.getDefendant().getAddress());
        locationCombo.getItems().add(report.getOrderNum().getLatitude() + ", " + report.getOrderNum().getLongitude());
        locationCombo.getSelectionModel().select(0);

        //lockdown
        plateField.setEditable(false);
        colorField.setEditable(false);
        idField.setEditable(false);
        licenseField.setEditable(false);
        fnField.setEditable(false);
        addressField.setEditable(false);
    }


}
