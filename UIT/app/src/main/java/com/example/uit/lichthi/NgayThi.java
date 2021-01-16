package com.example.uit.lichthi;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class NgayThi {
    private String strNgayThi;
    List<MonThi> listMonThi = new ArrayList<>();

    public NgayThi(String strNgayThi, List<MonThi> listMonThi) {
        this.strNgayThi = strNgayThi;
        this.listMonThi = listMonThi;
    }

    public NgayThi(String strNgayThi) {
        this.strNgayThi = strNgayThi;
    }

    public String getStrNgayThi() {
        return strNgayThi;
    }

    public void setStrNgayThi(String strNgayThi) {
        this.strNgayThi = strNgayThi;
    }

    public List<MonThi> getListMonThi() {
        return listMonThi;
    }

    public void setListMonThi(List<MonThi> listMonThi) {
        this.listMonThi = listMonThi;
    }

    public void addMonThi(MonThi monThi){
        if(listMonThi==null) listMonThi=new ArrayList<>();
        listMonThi.add(monThi);
    }

}
