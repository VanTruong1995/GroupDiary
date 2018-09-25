package com.example.admin.tablayout.Data;

public class DataExchange {
    private int idExchange, idEvent;
    private String nameExchange;
    private int amountExchange, moneyExchange;

    public DataExchange(int idExchange, int idEvent, String nameExchange, int amountExchange, int moneyExchange) {
        this.idExchange = idExchange;
        this.idEvent = idEvent;
        this.nameExchange = nameExchange;
        this.amountExchange = amountExchange;
        this.moneyExchange = moneyExchange;
    }

    public int getIdExchange() {
        return idExchange;
    }

    public void setIdExchange(int idExchange) {
        this.idExchange = idExchange;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNameExchange() {
        return nameExchange;
    }

    public void setNameExchange(String nameExchange) {
        this.nameExchange = nameExchange;
    }

    public int getAmountExchange() {
        return amountExchange;
    }

    public void setAmountExchange(int amountExchange) {
        this.amountExchange = amountExchange;
    }

    public int getMoneyExchange() {
        return moneyExchange;
    }

    public void setMoneyExchange(int moneyExchange) {
        this.moneyExchange = moneyExchange;
    }
}
