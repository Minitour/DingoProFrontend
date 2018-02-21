package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.*;
import network.APIManager;

import java.util.Date;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class TicketCreateView extends UIFormView {

    @FXML
    private Label form_title;

    @FXML
    private Label form_subtitle;

    @FXML
    private TextField violation;

    @FXML
    private TextField plateField;

    @FXML
    private TextField colorField;

    @FXML
    private ComboBox<VehicleModel> modelCombo;

    @FXML
    private TextField idField;

    @FXML
    private TextField licenseField;

    @FXML
    private TextField fnField;

    @FXML
    private TextField addressField;

    @FXML
    private ComboBox<Landmark> locationCombo;

    @FXML
    private TextArea descriptionField;

    public TicketCreateView(){
        super("/resources/xml/form_ticket_create.fxml");

        form_title.setText("New Ticket");
        form_subtitle.setText("Create a new ticket");

        APIManager.getInstance().getVehicleModels((response, vehicleModels, exception) -> {
            modelCombo.getItems().clear();
            modelCombo.getItems().addAll(vehicleModels);
        });

        APIManager.getInstance().getLandmarks((response, landmarks, exception) -> {
            locationCombo.getItems().clear();
            locationCombo.getItems().addAll(landmarks);
        });


    }

    public TicketCreateView(Report report){
        super("/resources/xml/form_ticket_create.fxml");

        form_title.setText("Report ID: "+report.getAlphaNum());
        form_subtitle.setText(report.getViolationDate().toLocaleString());

        violation.setText(report.getViolationType());

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
        locationCombo.getItems().add(report.getOrderNum());
        locationCombo.getSelectionModel().select(0);

        descriptionField.setText(report.getDescription());

        //lockdown
        plateField.setEditable(false);
        colorField.setEditable(false);
        idField.setEditable(false);
        licenseField.setEditable(false);
        fnField.setEditable(false);
        addressField.setEditable(false);
        descriptionField.setEditable(false);
    }


    public Report getReport(){
        Report r =  new Report(null,
                new Date(),
                getDescription(),
                null,
                getViolationType(),
                getSuspect(),
                getVehicle(),
                null);

        r.setRoute(getRoute());
        r.setOrderNum(getLandmark());
        return r;
    }

    private String getViolationType(){
        return violation.getText();
    }

    private Vehicle getVehicle(){
        String licensePlate = plateField.getText();
        String color = colorField.getText();
        VehicleModel model = modelCombo.getSelectionModel().getSelectedItem();

        return new Vehicle(licensePlate,color,model);
    }

    private Defendant getSuspect(){
        String id = idField.getText();
        String license = licenseField.getText();
        String fullNAme = fnField.getText();
        String address = addressField.getText();
        return new Defendant(Integer.parseInt(id),license,fullNAme,address);
    }

    private String getDescription(){
        return descriptionField.getText();
    }

    private Route getRoute(){
        return locationCombo.getSelectionModel().getSelectedItem().getRoute();
    }

    private Landmark getLandmark(){
        return locationCombo.getSelectionModel().getSelectedItem();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void reset() {
        plateField.setText(null);
        colorField.setText(null);
        idField.setText(null);
        licenseField.setText(null);
        fnField.setText(null);
        addressField.setText(null);
    }

    @Override
    public void setFormMode(FormMode formMode) {

    }
}
