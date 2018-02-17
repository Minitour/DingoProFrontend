package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Shift {

    @Expose
    private int shiftCode;

    @Expose
    private Date shiftDate;

    @Expose
    private String type;

    public Shift(int shiftCode, Date shiftDate, String type) {
        setShiftCode(shiftCode);
        setShiftDate(shiftDate);
        setType(type);
    }

    public void setShiftCode(int shiftCode) {
        this.shiftCode = shiftCode;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getShiftCode() {
        return shiftCode;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public String getType() {
        return type;
    }
}
