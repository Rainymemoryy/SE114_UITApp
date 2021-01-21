package com.example.uit.lichthi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;


import java.util.ArrayList;
import java.util.List;

public class LichThiFragment extends Fragment {


    private RecyclerView rcvNgayThi;
    private NgayThiAdapter ngayThiAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_lichthi, container, false);


        rcvNgayThi = root.findViewById(R.id.rcv_ngaythi);
        ngayThiAdapter =  new NgayThiAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        rcvNgayThi.setLayoutManager(linearLayoutManager);

        ngayThiAdapter.setData(Data.listNgayThi);
        rcvNgayThi.setAdapter(ngayThiAdapter);



        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));

        return root;
    }

    private List<NgayThi> getListNgayThi() {

//        List<NgayThi> listNgayThi=new ArrayList<>();
//
//        List<MonThi> listMonThi1=new ArrayList<>();
//        listMonThi1.add(new MonThi("12h00", "", "SS006.L13", "K201"));
//        listMonThi1.add(new MonThi("12h30", "", "ADA", "K201"));
//        listMonThi1.add(new MonThi("12h22", "", "123", "K201"));
//        NgayThi ngayThi1 =  new NgayThi("Thứ hai, 26-10-2020", listMonThi1 );
//
//        listNgayThi.add(ngayThi1);
//        listNgayThi.add(ngayThi1);
//        listNgayThi.add(ngayThi1);
//        listNgayThi.add(ngayThi1);
//
//        List<MonThi> listMonThi2=new ArrayList<>();
//        listMonThi2.add(new MonThi("12\n00", "SS001", "Chuyên đề cơ sở dữ liệu nâng cao", "K201"));
//        listMonThi2.add(new MonThi("12\n30", "BB001", "Duong loi dang", "K201"));
//
//        NgayThi ngayThi2 =  new NgayThi("04 01", listMonThi2 );
//
//        listNgayThi.add(ngayThi2);
//        return listNgayThi;

        return new ArrayList<>(Data.listNgayThi);
    }
}