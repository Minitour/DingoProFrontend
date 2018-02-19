package model;

import com.google.gson.annotations.Expose;

public class VehicleModel {

    @Expose
    private String modelNum;

    @Expose
    private String name;

    public VehicleModel(String modelNum, String name) {
        setModelNum(modelNum);
        setName(name);
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNum() {
        return modelNum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + modelNum + "] " + name ;
    }
}
