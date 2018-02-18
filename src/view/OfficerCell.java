package view;

import model.OperationalOfficer;
import ui.UIListViewCell;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class OfficerCell extends UIListViewCell<OperationalOfficer,UIView> {

    @Override
    public UIView load(OperationalOfficer item) {
        return new OfficerCellView(item);
    }
}
