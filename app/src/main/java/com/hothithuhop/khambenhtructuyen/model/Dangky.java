package com.hothithuhop.khambenhtructuyen.model;

public class Dangky {
    public Dangky(String hoten, String sdt, String taiKhoan, String matKhau) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        Hoten = hoten;
        this.SDT = sdt;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getSdt() {
        return SDT;
    }

    public void setSdt(String sdt) {
        this.SDT = sdt;
    }

    String TaiKhoan;
    String MatKhau;
    String Hoten;
    String SDT;

}
