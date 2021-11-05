package com.hothithuhop.khambenhtructuyen.model;

public class Hoadon {
    String HotenUser;
    String TenBS;
    String ngaykham;
    String bhyt;
    String phongkham;
    String giokham;
    int tongtien;
    int trangthai;

    public Hoadon(String hotenUser, String tenBS, String ngaykham, String bhyt, String phongkham, String giokham, int tongtien, int trangthai) {
        HotenUser = hotenUser;
        TenBS = tenBS;
        this.ngaykham = ngaykham;
        this.bhyt = bhyt;
        this.phongkham = phongkham;
        this.giokham = giokham;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public String getHotenUser() {
        return HotenUser;
    }

    public void setHotenUser(String hotenUser) {
        HotenUser = hotenUser;
    }

    public String getTenBS() {
        return TenBS;
    }

    public void setTenBS(String tenBS) {
        TenBS = tenBS;
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

    public String getGiokham() {
        return giokham;
    }

    public void setGiokham(String giokham) {
        this.giokham = giokham;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
