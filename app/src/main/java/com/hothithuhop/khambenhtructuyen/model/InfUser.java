package com.hothithuhop.khambenhtructuyen.model;

public class InfUser {


    public InfUser(String hoten, String taiKhoan, String matKhau, int SDT, String ngaysinh, String gioitinh, String CMND, String nghenghiep, String diaChi) {
        Hoten = hoten;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        this.SDT = SDT;
        Ngaysinh = ngaysinh;
        Gioitinh = gioitinh;
        this.CMND = CMND;
        Nghenghiep = nghenghiep;
        DiaChi = diaChi;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
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

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getNghenghiep() {
        return Nghenghiep;
    }

    public void setNghenghiep(String nghenghiep) {
        Nghenghiep = nghenghiep;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    String Hoten;
    String TaiKhoan;
    String MatKhau;
    int SDT;
    String Ngaysinh;
    String Gioitinh;
    String CMND;
    String Nghenghiep;
    String DiaChi;
}
