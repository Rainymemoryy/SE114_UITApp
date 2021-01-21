package com.example.uit.home;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.MonHocAdapter;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.MonThiAdapter;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.lichthi.NgayThiAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView rcvMonHoc;
    private RecyclerView rcvMonThi;

    MonHocAdapter monHocAdapter;
    MonThiAdapter monThiAdapter;

    TextView tvHienThiNgay, tvTen;

    private NgayThiAdapter ngayThiAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        rcvMonHoc = root.findViewById(R.id.rcv_home_monHoc);
        rcvMonThi = root.findViewById(R.id.rcv_home_monThi);
        tvHienThiNgay = root.findViewById(R.id.tv_home_today);
        tvTen = root.findViewById(R.id.tv_home_ten);


        tvHienThiNgay.setText(Data.thoiGian);

        if(Data.sinhVien!=null){
            String []strs= Data.sinhVien.getHoTen().split("\\ ");
            String str = strs[strs.length-1];
            tvTen.setText(str);
        }





        monHocAdapter = new MonHocAdapter();
        monThiAdapter = new MonThiAdapter();

        LinearLayoutManager linearLayoutManagerMonHoc = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        LinearLayoutManager linearLayoutManagerMonThi = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);

        rcvMonHoc.setLayoutManager(linearLayoutManagerMonHoc);
        rcvMonThi.setLayoutManager(linearLayoutManagerMonThi);

        monHocAdapter.setData(getListMonHoc());
        monThiAdapter.setData(getListMonThi());

        rcvMonHoc.setAdapter(monHocAdapter);
        rcvMonThi.setAdapter(monThiAdapter);

        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#90a4ae"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#90a4ae"));

        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#90a4ae"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#90a4ae"));





        return root;
    }


    private List<MonHoc> getListMonHoc() {

//        List<MonHoc> listTmp=new ArrayList<>();
//        for(MonHoc monHoc : Data.listMonHoc){
//
//
//        }
        return null;
    }

    private List<MonThi> getListMonThi() {
        List<MonThi> listMonThi = new ArrayList<>();
        return Data.listLichThiHomNay;
    }

    private List<NgayThi> getListNgayThi() {

        List<NgayThi> listNgayThi = new ArrayList<>();
        
        return listNgayThi;
    }

}