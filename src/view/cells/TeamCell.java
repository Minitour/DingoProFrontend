package view.cells;

import model.Partnership;
import ui.UIListViewCell;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class TeamCell extends UIListViewCell<Partnership,UIView> {

    @Override
    public UIView load(Partnership item) {
        return new TeamCellView(item);
    }
}
