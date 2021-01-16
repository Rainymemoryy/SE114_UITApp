package com.example.uit.lichhoc;

public class MonHoc {

    private  String maMonHoc;
    private  String tietHoc;
    private String phongHoc;
    private  String tenGiangVien;
    private  String tenMonHoc;
    private  String thoiGianBatDau;




    public MonHoc(String maMonHoc, String tietHoc, String phongHoc, String tenGiangVien, String tenMonHoc, String thoiGianBatDau) {
        this.maMonHoc = maMonHoc;
        this.tietHoc = tietHoc;
        this.phongHoc = phongHoc;
        this.tenGiangVien = tenGiangVien;
        this.tenMonHoc = tenMonHoc;
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTietHoc() {
        return tietHoc;
    }

    public void setTietHoc(String tietHoc) {
        this.tietHoc = tietHoc;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }
}
