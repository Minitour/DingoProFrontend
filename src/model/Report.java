package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public abstract class Report {

    @Expose
    private String alphaNum;

    @Expose
    private Date violationDate;

    @Expose
    private String description;

    @Expose
    private String status;

    @Expose
    private String violationType;

    @Expose
    private Defendant defendant;

    @Expose
    private Vehicle vehicle;

    @Expose
    private Appeal appeal;

    public Report(String alphaNum, Date violationDate, String description, String status, String violationType, Defendant defendant, Vehicle vehicle, Appeal appeal) {
        setAlphaNum(alphaNum);
        setViolationDate(violationDate);
        setDescription(description);
        setStatus(status);
        setViolationType(violationType);
        setDefendant(defendant);
        setVehicle(vehicle);
        setAppeal(appeal);
    }

    public void setAlphaNum(String alphaNum) {
        this.alphaNum = alphaNum;
    }

    public void setViolationDate(Date violationDate) {
        this.violationDate = violationDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public void setDefendant(Defendant defendant) {
        this.defendant = defendant;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }

    public String getAlphaNum() {
        return alphaNum;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getViolationType() {
        return violationType;
    }

    public Defendant getDefendant() {
        return defendant;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Appeal getAppeal() {
        return appeal;
    }
}
