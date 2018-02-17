package model;

import com.google.gson.annotations.Expose;

public class Defendant{

    @Expose
    private int id;

    @Expose
    private String drivingLicense;

    @Expose
    private String name;

    @Expose
    private String address;

    public Defendant(int id, String drivingLicense, String name, String address) {
        setId(id);
        setDrivingLicense(drivingLicense);
        setName(name);
        setAddress(address);
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
