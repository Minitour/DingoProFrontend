package view.cells;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Report;
import ui.UIListViewCell;
import ui.UIView;

/**
 * Created by Antonio Zaitoun on 18/02/2018.
 */
public class TicketCell extends UIListViewCell<Report, UIView> {

    @Override
    public UIView load(Report item) {
        UIView view = new UIView();
        view.getChildren().add(createView(item));
        return view;
    }

    /**
     * Create pane view from report item
     * @param item
     * @return
     */
    private static Pane createView(Report item){
        //create pane
        Pane pane = new Pane();

        //create vbox
        VBox box = new VBox();
        box.prefWidthProperty().bind(pane.widthProperty());
        box.setSpacing(4);
        box.setPadding(new Insets(8,8,8,8));

        //create labels
        Label title = new Label();
        title.setText(item.getAlphaNum());
        title.setFont(new Font("Helvetica Bold",20));
        Label sub = new Label();
        sub.setText(item.getViolationDate().toLocaleString());
        sub.setFont(new Font("Helvetica",16));

        //add labels to vbox
        box.getChildren().addAll(title,sub);

        //add vbox to pane
        pane.getChildren().addAll(box);
        return pane;
    }
}
