package com.example.uit.hocphi;

public class HocPhi {
    String hocKy, soTienPhaiDong, soTienDaDong, trangThai, chiTietTrangThai;

    public HocPhi(String hocKy, String soTienPhaiDong, String soTienDaDong, String trangThai, String chiTietTrangThai) {
        this.hocKy = hocKy;
        this.soTienPhaiDong = soTienPhaiDong;
        this.soTienDaDong = soTienDaDong;
        this.trangThai = trangThai;
        this.chiTietTrangThai = chiTietTrangThai;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public String getSoTienPhaiDong() {
        return soTienPhaiDong;
    }

    public void setSoTienPhaiDong(String soTienPhaiDong) {
        this.soTienPhaiDong = soTienPhaiDong;
    }

    public String getSoTienDaDong() {
        return soTienDaDong;
    }

    public void setSoTienDaDong(String soTienDaDong) {
        this.soTienDaDong = soTienDaDong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getChiTietTrangThai() {
        return chiTietTrangThai;
    }

    public void setChiTietTrangThai(String chiTietTrangThai) {
        this.chiTietTrangThai = chiTietTrangThai;
    }
}
