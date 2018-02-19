package view;

import model.Route;
import ui.UIListViewCell;
import ui.UIView;

/**
 * Created By Tony on 17/02/2018
 */
public class RouteCell extends UIListViewCell<Route,UIView> {

    @Override
    public UIView load(Route item) {
        return new RouteCellView(item);
    }
}
