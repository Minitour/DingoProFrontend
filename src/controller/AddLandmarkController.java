package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Landmark;
import model.Route;
import network.APIManager;
import ui.UIViewController;


/**
 * Created By Tony on 20/02/2018
 */
public class AddLandmarkController extends UIViewController {

    @FXML
    private ComboBox<Route> comboBox;

    @FXML
    private TextField plannedArrival;

    @FXML
    private TextField lat;

    @FXML
    private TextField lon;

    @FXML
    private Button submit;

    public AddLandmarkController() {
        super("/resources/xml/controller_add_landmark.fxml");

        submit.setOnAction(this::onClick);
    }

    private void onClick(ActionEvent actionEvent) {
        Route r = comboBox.getSelectionModel().getSelectedItem();
        Landmark landmark = new Landmark(r,r.getLandmarks().size() + 1,plannedArrival.getText(),lat.getText(),lon.getText());
        APIManager.getInstance().addLandmarkToRoute(landmark, r, (response, exception) -> {
            plannedArrival.setText(null);
            lat.setText(null);
            lon.setText(null);
            refresh();
        });
    }

    public void refresh(){
        APIManager.getInstance().getRoutes((response, routes, exception) -> {
            comboBox.getItems().clear();
            comboBox.getItems().addAll(routes);
        });
    }
}