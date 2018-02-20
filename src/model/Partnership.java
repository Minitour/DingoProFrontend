package model;

import com.google.gson.annotations.Expose;

import java.util.Collection;
import java.util.Date;

public class Partnership {

    @Expose
    private Integer ptshipNum;

    @Expose
    private Date creationDate;

    @Expose
    private Collection<OperationalOfficer> officers;


    public Partnership(Integer ptshipNum, OperationalOfficer officerABadge, OperationalOfficer officerBBadge) {
        setPtshipNum(ptshipNum);
    }


    public void setPtshipNum(Integer ptshipNum) {
        this.ptshipNum = ptshipNum;
    }

    public int getPtshipNum() {
        return ptshipNum;
    }

    public boolean addOfficerToPartnership(OperationalOfficer officer) {
        if (officer != null) {
            return officers.add(officer);
        }
        return false;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setOfficers(Collection<OperationalOfficer> officers) {
        this.officers = officers;
    }

    public Collection<OperationalOfficer> getOfficers() {
        return officers;
    }
}

