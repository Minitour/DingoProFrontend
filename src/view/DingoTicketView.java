package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Report;
import model.Vehicle;

/**
 * Created By Tony on 20/02/2018
 */
public class DingoTicketView extends UIFormView {

    @FXML
    private Label form_title;

    @FXML
    private Label form_subtitle;

    @FXML
    private Label vd_licensePlate;

    @FXML
    private Label vd_carModel;

    @FXML
    private Label vd_carColor;

    @FXML
    private TextArea description;

    @FXML
    private TextArea evidenceLink;

    public DingoTicketView(Report report) {
        super("/resources/xml/form_dingo_report.fxml");

        form_title.setText("REPORT ID: "+report.getAlphaNum());
        form_subtitle.setText("CREATED AT: "+report.getViolationDate());

        Vehicle vehicle = report.getVehicle();
        vd_licensePlate.setText(vehicle.getLicensePlate());
        vd_carColor.setText(vehicle.getModel().toString());
        vd_carColor.setText(vehicle.getColorHEX());

        description.setText(report.getDescription());

        evidenceLink.setText(report.getEvidenceLink());

        description.setEditable(false);
        evidenceLink.setEditable(false);

    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setFormMode(FormMode formMode) {

    }
}
