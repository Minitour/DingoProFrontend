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

    public OperationalOfficer(String ID, String EMAIL, Integer ROLE_ID, String pin, String name, String phoneExtension, Integer position, Partnership ptship) {
        super(ID, EMAIL, ROLE_ID);
        this.pin = pin;
        this.name = name;
        this.phoneExtension = phoneExtension;
        this.position = position;
        this.ptship = ptship;
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
