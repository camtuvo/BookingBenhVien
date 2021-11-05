package com.hothithuhop.khambenhtructuyen.model;

public class BillResponse {
    int idHD;
    String HotenUser;
    String TenBS;
    String ngaykham;
    String giokham;
    String bhyt;
    String phongkham;
    int tongtien;
    int trangthai;

    public BillResponse(int idHD, String hotenUser, String tenBS, String ngaykham, String giokham, String bhyt, String phongkham, int tongtien, int trangthai) {
        this.idHD = idHD;
        HotenUser = hotenUser;
        TenBS = tenBS;
        this.ngaykham = ngaykham;
        this.giokham = giokham;
        this.bhyt = bhyt;
        this.phongkham = phongkham;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
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

    public String getGiokham() {
        return giokham;
    }

    public void setGiokham(String giokham) {
        this.giokham = giokham;
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
