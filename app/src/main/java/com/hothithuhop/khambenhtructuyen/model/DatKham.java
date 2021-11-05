package com.hothithuhop.khambenhtructuyen.model;

public class DatKham {
    public DatKham(int id_user, int id_bacsi, String ngaykham, String bhyt, String phongkham, int tongtien, String gioKham) {
        this.id_user = id_user;
        this.id_bacsi = id_bacsi;
        this.ngaykham = ngaykham;
        this.bhyt = bhyt;
        this.phongkham = phongkham;
        this.tongtien = tongtien;
        Giokham = gioKham;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAid_bacsi() {
        return id_bacsi;
    }

    public void setAid_bacsi(int aid_bacsi) {
        this.id_bacsi = aid_bacsi;
    }

    public String getNgaykham() {
        return ngaykham;
    }

    public void setNgaykham(String ngaykham) {
        this.ngaykham = ngaykham;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public String getPhongkham() {
        return phongkham;
    }

    public void setPhongkham(String phongkham) {
        this.phongkham = phongkham;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getGioKham() {
        return Giokham;
    }

    public void setGioKham(String gioKham) {
        Giokham = gioKham;
    }

    int id_user;
   int id_bacsi;
   String ngaykham;
   String bhyt;
   String phongkham;
   int tongtien;
   String Giokham;
}
