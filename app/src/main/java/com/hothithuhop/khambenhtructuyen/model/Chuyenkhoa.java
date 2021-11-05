package com.hothithuhop.khambenhtructuyen.model;

public class Chuyenkhoa {
    public Chuyenkhoa(int id, String chuyenkhoa) {
        this.id = id;
        this.chuyenkhoa = chuyenkhoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int idKhoa) {
        this.id = idKhoa;
    }

    public String getChuyenkhoa() {
        return chuyenkhoa;
    }

    public void setChuyenkhoa(String chuyenkhoa) {
        this.chuyenkhoa = chuyenkhoa;
    }

    int id;
    String chuyenkhoa;
}
