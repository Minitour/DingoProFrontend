package model;

import com.google.gson.annotations.Expose;

public class Route {

    @Expose
    private int serialNum;

    public Route(int serialNum) {
        setSerialNum(serialNum);
    }


    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getSerialNum() {
        return serialNum;
    }
}
