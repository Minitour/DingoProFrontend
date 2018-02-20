package controller;

import com.jfoenix.controls.JFXSnackbar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Partnership;
import model.Route;
import model.Shift;
import network.APIManager;
import ui.UIViewController;
import view.cells.RouteCell;
import view.cells.ShiftCell;
import view.cells.TeamCell;


/**
 * Created By Tony on 20/02/2018
 */
public class AssignTripleController extends UIViewController {

    @FXML
    private Label titleLabel;

    @FXML
    private ListView<Partnership> list1;

    @FXML
    private ListView<Shift> list2;

    @FXML
    private ListView<Route> list3;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Button assign;

    public AssignTripleController() {
        super("/resources/xml/controller_triple_assign.fxml");
        list1.setCellFactory(param -> new TeamCell());
        list2.setCellFactory(param -> new ShiftCell());
        list3.setCellFactory(param -> new RouteCell());

        label1.setText("Select Team");
        label2.setText("Select Shift");
        label3.setText("Select Route");

        titleLabel.setText("Assign Team to Shift On Route");

        assign.setOnAction(event -> {
            Partnership partnership = list1.getSelectionModel().getSelectedItem();
            Shift shift = list2.getSelectionModel().getSelectedItem();
            Route route = list3.getSelectionModel().getSelectedItem();

            if(partnership == null|| shift == null || route == null)
                return;

            assign(partnership,shift,route);
        });
    }

    public void refresh(){
        APIManager.getInstance().getPartnerships((response, partnerships, exception) -> {
            list1.getItems().clear();
            list1.getItems().addAll(partnerships);
        });

        APIManager.getInstance().getShifts((response, shifts, exception) -> {
            list2.getItems().clear();
            list2.getItems().addAll(shifts);
        });

        APIManager.getInstance().getRoutes((response, routes, exception) -> {
            list3.getItems().clear();
            list3.getItems().addAll(routes);
        });
    }

    private void assign(Partnership partnership,Shift shift,Route route){
        APIManager.getInstance().assignTeamToShiftToRoute(partnership,shift,route,(res,ex)->{
            JFXSnackbar bar = new JFXSnackbar(this.view);
            if (res.isOK()) {
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Assigned Team "+partnership.getPtshipNum() + " to Shift "+shift.getShiftCode() + " on Route "+route.getSerialNum()));
                reset();
            }
            else
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Something went wrong: "+res.getMessage()));
        });
    }

    private void reset(){
        list1.getSelectionModel().clearSelection();
        list2.getSelectionModel().clearSelection();
        list3.getSelectionModel().clearSelection();
    }
}
