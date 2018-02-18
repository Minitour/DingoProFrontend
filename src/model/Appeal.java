package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class Appeal {

    @Expose
    private Integer serialNum;

    @Expose
    private String reason;

    @Expose
    private Date appealDate;

    @Expose
    private Defendant defendant;

    @Expose
    private Report report;

    public Appeal(Integer serialNum, String reason, Date appealDate) {
        setSerialNum(serialNum);
        setReason(reason);
        setAppealDate(appealDate);
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setAppealDate(Date appealDate) {
        this.appealDate = appealDate;
    }

    public void setDefendant(Defendant defendant) { this.defendant = defendant; }

    public Integer getSerialNum() {
        return serialNum;
    }

    public String getReason() {
        return reason;
    }

    public Date getAppealDate() {
        return appealDate;
    }

    public Defendant getDefendant() {
        return defendant;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
