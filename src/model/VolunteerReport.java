package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class VolunteerReport extends Report {

    @Expose
    private String evidenceLink;

    public VolunteerReport(String alphaNum, Date violationDate, String description, String status, String violationType, Defendant defendant, Vehicle vehicle, Appeal appeal, String evidenceLink) {
        super(alphaNum,violationDate,description,status,violationType,defendant,vehicle,appeal);
        setEvidenceLink(evidenceLink);
    }

    public void setEvidenceLink(String evidenceLink) {
        this.evidenceLink = evidenceLink;
    }

    public String getEvidenceLink() {
        return evidenceLink;
    }
}
