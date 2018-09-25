package com.example.admin.tablayout.Data;

public class DataMember {
    private int idMember;
    private String nameMenber, phoneMember, addressMember, imageMember;
    private long dateMember;

    public DataMember(int idMember, String nameMenber, String phoneMember, String addressMember, String imageMember, long dateMember) {
        this.idMember = idMember;
        this.nameMenber = nameMenber;
        this.phoneMember = phoneMember;
        this.addressMember = addressMember;
        this.imageMember = imageMember;
        this.dateMember = dateMember;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getNameMenber() {
        return nameMenber;
    }

    public void setNameMenber(String nameMenber) {
        this.nameMenber = nameMenber;
    }

    public String getPhoneMember() {
        return phoneMember;
    }

    public void setPhoneMember(String phoneMember) {
        this.phoneMember = phoneMember;
    }

    public String getAddressMember() {
        return addressMember;
    }

    public void setAddressMember(String addressMember) {
        this.addressMember = addressMember;
    }

    public String getImageMember() {
        return imageMember;
    }

    public void setImageMember(String imageMember) {
        this.imageMember = imageMember;
    }

    public long getDateMember() {
        return dateMember;
    }

    public void setDateMember(long dateMember) {
        this.dateMember = dateMember;
    }
}
