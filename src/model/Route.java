package model;

import com.google.gson.annotations.Expose;

public class Route {

    @Expose
    private Integer serialNum;

    public Route(Integer serialNum) {
        setSerialNum(serialNum);
    }


    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getSerialNum() {
        return serialNum;
    }
}
