package com.hothithuhop.khambenhtructuyen.model;

public class Bacsi {
    int id_bs;
    String chuyenkhoa;
    String TenBS;
    int SDT_BS;
    int GiaTien;

    public Bacsi(int id_bs, String chuyenkhoa, String tenBS, int SDT_BS, int giaTien) {
        this.id_bs = id_bs;
        this.chuyenkhoa = chuyenkhoa;
        TenBS = tenBS;
        this.SDT_BS = SDT_BS;
        GiaTien = giaTien;
    }

    public int getId_bs() {
        return id_bs;
    }

    public void setId_bs(int id_bs) {
        this.id_bs = id_bs;
    }

    public String getChuyenkhoa() {
        return chuyenkhoa;
    }

    public void setChuyenkhoa(String chuyenkhoa) {
        this.chuyenkhoa = chuyenkhoa;
    }

    public String getTenBS() {
        return TenBS;
    }

    public void setTenBS(String tenBS) {
        TenBS = tenBS;
    }

    public int getSDT_BS() {
        return SDT_BS;
    }

    public void setSDT_BS(int SDT_BS) {
        this.SDT_BS = SDT_BS;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }
}
