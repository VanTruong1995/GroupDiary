package com.example.admin.tablayout.Data;

public class DataEvents {
    private int idEvent;
    private String nameEvent, addressEvent;
    private int imageEvent;
    private long dateEvent;

    public DataEvents(int idEvent, String nameEvent, String addressEvent, int imageEvent, long dateEvent) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.addressEvent = addressEvent;
        this.imageEvent = imageEvent;
        this.dateEvent = dateEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getAddressEvent() {
        return addressEvent;
    }

    public void setAddressEvent(String addressEvent) {
        this.addressEvent = addressEvent;
    }

    public int getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(int imageEvent) {
        this.imageEvent = imageEvent;
    }

    public long getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(long dateEvent) {
        this.dateEvent = dateEvent;
    }
}
