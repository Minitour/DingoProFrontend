package controller;

import model.Partnership;
import model.Shift;
import network.APIManager;
import view.cells.ShiftCell;
import view.cells.TeamCell;

import java.util.ResourceBundle;

/**
 * Created By Tony on 18/02/2018
 */
public class AssignTeamsToShiftsController extends AssignFromToController<Partnership, Shift> {

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        from.setCellFactory(param -> new TeamCell());
        to.setCellFactory(param -> new ShiftCell());
    }

    public void refresh(){
        APIManager.getInstance().getPartnerships((response, partnerships, exception) -> {
            from.getItems().clear();
            from.getItems().addAll(partnerships);
        });

        APIManager.getInstance().getShifts((response, shifts, exception) -> {
            to.getItems().clear();
            to.getItems().addAll(shifts);
        });
    }

    @Override
    protected void onAssign(Partnership from,Shift  to) {
        super.onAssign(from, to);
        APIManager.getInstance().assignPartnershipToShift(from,to,(response, exception) -> {
            //TODO: handle completion
        });
    }
}
