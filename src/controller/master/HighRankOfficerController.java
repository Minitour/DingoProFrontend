package controller.master;

import controller.AppealMenuController;
import controller.UpdatePasswordController;
import ui.UIView;
import ui.UIViewController;

/**
 * Created By Tony on 18/02/2018
 */
public class HighRankOfficerController extends MasterMenuController {

    private UIViewController[] controllers = {
            new AppealMenuController(),
            null,
            null,
            null,
            new UpdatePasswordController()
    };

    @Override
    public UIView viewForIndexAt(int index) {
        return controllers[index].view;
    }

    @Override
    public String[] itemsForMenu() {
        return new String[]{
                "View Appeals",
                "Assign Officers",
                "Assign Teams",
                "Assign Routes",
                "Update Password"
        };
    }
}
