package view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import model.Shift;

import java.time.ZoneId;
import java.util.Date;

/**
 * Created By Tony on 14/02/2018
 */
public class ShiftFormView extends UIFormView {

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<ShiftType> comboBox;

    public ShiftFormView() {
        super("/resources/xml/form_shift_create.fxml");
        comboBox.getItems().addAll(
                ShiftType.MORNING,
                ShiftType.NOON,
                ShiftType.EVENING,
                ShiftType.NIGHT
        );
    }

    @Override
    public boolean isValid() {
        try{
            getShift();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void reset() {
        comboBox.getSelectionModel().clearSelection();
    }

    @Override
    public void setFormMode(FormMode formMode) {

    }

    public Shift getShift(){
        return new Shift(null,getDate(),getType());
    }

    private Date getDate(){
        return Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private String getType(){
        return comboBox.getSelectionModel().getSelectedItem().value;
    }

    enum ShiftType{
        MORNING("MORNING"),
        NOON("NOON"),
        EVENING("EVENING"),
        NIGHT("NIGHT");

        String value;
        ShiftType(String val){
            value = val;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
