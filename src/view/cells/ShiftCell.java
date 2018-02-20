package view.cells;

import model.Shift;
import ui.UIListViewCell;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class ShiftCell extends UIListViewCell<Shift,UIView> {

    @Override
    public UIView load(Shift item) {
        return new ShiftCellView(item);
    }
}
