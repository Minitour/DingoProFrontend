package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Appeal {

    @Expose
    private int serialNum;

    @Expose
    private String reason;

    @Expose
    private Date appealDate;

    @Expose
    private Defendant defendant;

    public Appeal(int serialNum, String reason, Date appealDate) {
        setSerialNum(serialNum);
        setReason(reason);
        setAppealDate(appealDate);
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setAppealDate(Date appealDate) {
        this.appealDate = appealDate;
    }

    public void setDefendant(Defendant defendant) { this.defendant = defendant; }

    public int getSerialNum() {
        return serialNum;
    }

    public String getReason() {
        return reason;
    }

    public Date getAppealDate() {
        return appealDate;
    }

    public Defendant getDefendant() { return defendant; }
}
