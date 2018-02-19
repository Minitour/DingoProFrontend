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
            new AssignOfficerToTeamController(),
            new AssignTeamsToShiftsController(),
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

        return controller.view;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "View Appeals",
                "View Reports",
                "Assign Officers",
                "Assign Teams",
                "Assign Routes",
                "Add Landmarks",
                "Update Password"
        };
    }
}
