package controller.master;

import controller.*;
import ui.UIView;
import ui.UIViewController;

/**
 * Created By Tony on 18/02/2018
 */
public class HighRankOfficerController extends MasterMenuController {

    private UIViewController[] controllers = {
            new AppealMenuController(),
            new ReportMenuController(),
            new GetTicketsReportController(),
            new GetAppealsReportController(),
            new AssignOfficerToTeamController(),
            new AssignTripleController(),
            new AddLandmarkController(),
            new ManageTeamsController(),
            new ManageRoutesController(),
            new ManageShiftsController(),
            new UpdatePasswordController()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        UIViewController controller =  controllers[index];

        if(controller instanceof AppealMenuController){
            ((AppealMenuController) controller).refresh();
        }

        if(controller instanceof ReportMenuController){
            ((ReportMenuController) controller).refresh();
        }

        if (controller instanceof AssignOfficerToTeamController){
            ((AssignOfficerToTeamController) controller).refresh();
        }

        if(controller instanceof AddLandmarkController){
            ((AddLandmarkController) controller).refresh();
        }

        if(controller instanceof ManageTeamsController){
            ((ManageTeamsController) controller).refresh();
        }

        if(controller instanceof ManageRoutesController){
            ((ManageRoutesController) controller).refresh();
        }

        if(controller instanceof ManageShiftsController){
            ((ManageShiftsController) controller).refresh();
        }

        if(controller instanceof AssignTripleController){
            ((AssignTripleController) controller).refresh();
        }

        return controller.view;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "View Appeals",
                "View Reports",
                "Tickets Report",
                "Appeals Report",
                "Assign Officers",
                "Assign Shifts",
                "Add Landmarks",
                "Manage Teams",
                "Manage Routes",
                "Manage Shifts",
                "Update Password"
        };
    }
}
