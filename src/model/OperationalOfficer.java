package model;

import com.google.gson.annotations.Expose;

public class OperationalOfficer {

    @Expose
    private String pin;

    @Expose
    private String name;

    @Expose
    private String phoneExtension;

    @Expose
    private int position;

    @Expose
    private Partnership ptship;

    public OperationalOfficer(String pin, String name, String phoneExtension, int position, Partnership ptship) {
        setPin(pin);
        setName(name);
        setPhoneExtension(phoneExtension);
        setPosition(position);
        setPtship(ptship);
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

    public int getPosition() {
        return position;
    }

    public Partnership getPtship() {
        return ptship;
    }

}
