package com.hothithuhop.khambenhtructuyen.model;

public class Info {
    public Info(String taiKhoan, String matKhau) {
        MatKhau = matKhau;
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    String MatKhau;
    String TaiKhoan;
}
