package controller;

import com.jfoenix.controls.JFXSnackbar;
import model.OperationalOfficer;
import model.Partnership;
import network.APIManager;
import view.cells.OfficerCell;
import view.cells.TeamCell;

import java.util.ResourceBundle;

/**
 * Created By Tony on 18/02/2018
 */
public class AssignOfficerToTeamController extends AssignFromToController<OperationalOfficer, Partnership> {

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        from.setCellFactory(param -> new OfficerCell());
        to.setCellFactory(param -> new TeamCell());

    }

    public void refresh(){
        APIManager.getInstance().getPartnerships((response, partnerships, exception) -> {
            to.getItems().clear();
            to.getItems().addAll(partnerships);
        });

        APIManager.getInstance().getOfficers((response, officers, exception) -> {
            from.getItems().clear();
            from.getItems().addAll(officers);
        });
    }

    @Override
    protected void onAssign(OperationalOfficer from, Partnership to) {
        super.onAssign(from, to);
        APIManager.getInstance().assignOfficerToPartnership(from, to, (response, exception) -> {
            JFXSnackbar bar = new JFXSnackbar(this.view);
            if (response.isOK()) {
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Assigned Officer "+from.getName() + " to Team #"+to.getPtshipNum()));
            }
            else
                bar.enqueue(new JFXSnackbar.SnackbarEvent("Something went wrong: "+response.getMessage()));
            reset();
            refresh();
        });
    }

    @Override
    public String title() {
        return "Assign Officer To Team";
    }
}
