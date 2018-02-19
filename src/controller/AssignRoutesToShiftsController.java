package controller;

import model.Route;
import model.Shift;
import network.APIManager;
import view.RouteCell;
import view.ShiftCell;

import java.util.ResourceBundle;

/**
 * Created By Tony on 18/02/2018
 */
public class AssignRoutesToShiftsController extends AssignFromToController<Route, Shift> {

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);
        from.setCellFactory(param -> new RouteCell());
        to.setCellFactory(param -> new ShiftCell());
    }

    public void refresh(){
        APIManager.getInstance().getRoutes((response, routes, exception) -> {
            from.getItems().clear();
            from.getItems().addAll(routes);
        });

        APIManager.getInstance().getShifts((response, shifts, exception) -> {
            to.getItems().clear();
            to.getItems().addAll(shifts);
        });
    }

    @Override
    protected void onAssign(Route from,Shift  to) {
        super.onAssign(from, to);
        APIManager.getInstance().assignRouteToShift(from,to,(response, exception) -> {
            //TODO: handle completion
        });
    }
}
