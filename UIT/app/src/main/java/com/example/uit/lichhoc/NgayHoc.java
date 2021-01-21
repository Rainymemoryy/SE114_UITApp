package com.example.uit.lichhoc;

import java.util.ArrayList;
import java.util.List;

public class NgayHoc {
    private String ngayHoc;
    List<MonHoc> listMonHoc;

    public NgayHoc(String ngayHoc, List<MonHoc> listMonHoc) {
        this.ngayHoc = ngayHoc;
        this.listMonHoc = listMonHoc;
    }

    public String getNgayHoc() {
        return ngayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        this.ngayHoc = ngayHoc;
    }

    public List<MonHoc> getListMonHoc() {
        return listMonHoc;
    }

    public void setListMonHoc(List<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
    }

    public void addMonHoc(MonHoc monHoc){
        if(listMonHoc==null) listMonHoc=new ArrayList<>();
        listMonHoc.add(monHoc);
    }
}
