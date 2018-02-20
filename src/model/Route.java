package model;

import com.google.gson.annotations.Expose;

import java.util.Collection;
import java.util.Date;

public class Route {

    @Expose
    private Integer serialNum;

    @Expose
    private Date creationDate;

    @Expose
    private Collection<Landmark> landmarks;

    public Route(Integer serialNum) {
        setSerialNum(serialNum);
    }


    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public Collection<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(Collection<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Route #" + serialNum +" ["+landmarks.size()+" landmarks]";
    }
}
