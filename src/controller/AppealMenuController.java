package controller;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Appeal;
import ui.UIListViewCell;
import ui.UIView;
import view.AppealView;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Antonio Zaitoun on 14/01/2018.
 */
public class AppealMenuController extends SplitViewController{

    List<Appeal> appealList;

    @Override
    public void viewWillLoad(ResourceBundle bundle) {
        super.viewWillLoad(bundle);

        //appealList = QueryHelper.getAppeals();

        listView.setCellFactory(param -> new AppealCell());

        listView.getItems().addAll(appealList);

    }

    @Override
    protected void onListItemChanged(int value) {
        Appeal appeal = appealList.get(value);

        AppealView view = new AppealView(appeal);

        showView(view);
    }

    @Override
    public String title() {
        return "Appeals";
    }

    /**
     * Cell class to display the ticket cell
     */
    static class AppealCell extends UIListViewCell<Appeal, UIView> {

        @Override
        public UIView load(Appeal item) {
            UIView view = new UIView();
            view.getChildren().add(createView(item));
            return view;
        }

        /**
         * Create pane view from report item
         * @param item
         * @return
         */
        private static Pane createView(Appeal item){
            //create pane
            Pane pane = new Pane();

            //create vbox
            VBox box = new VBox();
            box.prefWidthProperty().bind(pane.widthProperty());
            box.setSpacing(4);
            box.setPadding(new Insets(8,8,8,8));

            //create labels
            Label title = new Label();
            title.setText(item.getDefendant().getId() + "");
            title.setFont(new Font("Helvetica Bold",20));
            Label sub = new Label();
            sub.setText(item.getAppealDate().toLocaleString());
            sub.setFont(new Font("Helvetica",16));

            //add labels to vbox
            box.getChildren().addAll(title,sub);

            //add vbox to pane
            pane.getChildren().addAll(box);
            return pane;
        }
    }
}
