package controller.master;

import controller.*;
import ui.UIView;
import ui.UIViewController;

/**
 * Created By Tony on 18/02/2018
 */
public class OperationalOfficerMasterController extends MasterMenuController {

    private UIViewController[] controllers = {
            new CreateReportController(),
            new UpdatePasswordController()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        UIViewController controller =  controllers[index];
        return controller.view;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "Create Ticket",
                "Update Password"
        };
    }
}
