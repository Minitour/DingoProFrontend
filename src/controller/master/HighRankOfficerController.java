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
            new GetAppealsReportController(),
            new GetTicketsReportController(),
            new AssignOfficerToTeamController(),
            new AssignTeamsToShiftsController(),
            new AssignRoutesToShiftsController(),
            new AddLandmarkController(),
            null,
            null,
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

        if (controller instanceof AssignTeamsToShiftsController){
            ((AssignTeamsToShiftsController) controller).refresh();
        }

        if(controller instanceof AssignRoutesToShiftsController){
            ((AssignRoutesToShiftsController) controller).refresh();
        }

        if(controller instanceof AddLandmarkController){
            ((AddLandmarkController) controller).refresh();
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
                "Assign Teams",
                "Assign Routes",
                "Add Landmarks",
                "Manage Routes",
                "Manage Shifts",
                "Update Password"
        };
    }
}
