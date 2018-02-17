package model;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class OfficerReport extends Report {

    @Expose
    private Partnership part;

    @Expose
    private Route route;

    @Expose
    private Landmark orderNum;

    public OfficerReport(String alphaNum, Date violationDate, String description, String status, String violationType, Defendant defendant, Vehicle vehicle, Appeal appeal, Partnership part, Route route, Landmark orderNum) {
        super(alphaNum,violationDate,description,status,violationType,defendant,vehicle,appeal);
        setPart(part);
        setRoute(route);
        setOrderNum(orderNum);
    }


    public void setPart(Partnership part) {
        this.part = part;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setOrderNum(Landmark orderNum) {
        this.orderNum = orderNum;
    }

    public Partnership getPart() {
        return part;
    }

    public Route getRoute() {
        return route;
    }

    public Landmark getOrderNum() {
        return orderNum;
    }



}
