package model;

import com.google.gson.annotations.Expose;

public class OperationalOfficer extends Account {

    @Expose
    private String pin;

    @Expose
    private String name;

    @Expose
    private String phoneExtension;

    @Expose
    private Integer position;

    @Expose
    private Partnership ptship;

    public OperationalOfficer(String ID, String EMAIL, String pin, String name, String phoneExtension, Integer position) {
        super(ID, EMAIL, 2);
        this.pin = pin;
        this.name = name;
        this.phoneExtension = phoneExtension;
        this.position = position;
    }

    public OperationalOfficer(String ID, String EMAIL, String password, String pin, String name, String phoneExtension, Integer position) {
        super(ID, EMAIL, 2, password);
        this.pin = pin;
        this.name = name;
        this.phoneExtension = phoneExtension;
        this.position = position;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPtship(Partnership ptship) {
        this.ptship = ptship;
    }

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public Integer getPosition() {
        return position;
    }

    public Partnership getPtship() {
        return ptship;
    }

}
