package com.example.uit.diem;

import java.util.ArrayList;
import java.util.List;

public class BangDiem {
    List<DiemThi> listDiemThi;
    String thongTinBangDiem;
    String tongSoTinChi, diemTrungBinh;


    public BangDiem(String thongTinBangDiem,List<DiemThi> listDiemThi) {
        this.listDiemThi = listDiemThi;
        this.thongTinBangDiem = thongTinBangDiem;
    }

    public BangDiem(String thongTinBangDiem) {
        this.thongTinBangDiem = thongTinBangDiem;
    }

    public BangDiem() {
    }

    public List<DiemThi> getListDiemThi() {
        return listDiemThi;
    }

    public void setListDiemThi(List<DiemThi> listDiemThi) {
        this.listDiemThi = listDiemThi;
    }

    public String getThongTinBangDiem() {
        return thongTinBangDiem;
    }

    public void setThongTinBangDiem(String thongTinBangDiem) {
        this.thongTinBangDiem = thongTinBangDiem;
    }

    public String getTongSoTinChi() {
        return tongSoTinChi;
    }

    public void setTongSoTinChi(String tongSoTinChi) {
        this.tongSoTinChi = tongSoTinChi;
    }

    public String getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(String diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public void addDiemThi(DiemThi diemThi){
        if(listDiemThi==null)
            listDiemThi=new ArrayList<>();
        listDiemThi.add(diemThi);
    }
}
