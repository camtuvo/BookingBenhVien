package com.hothithuhop.khambenhtructuyen.model;

public class ReBill {
    private String hovaten;
    private int idHD;

    public ReBill(String hovaten, int idHD) {
        this.hovaten = hovaten;
        this.idHD = idHD;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }
}
