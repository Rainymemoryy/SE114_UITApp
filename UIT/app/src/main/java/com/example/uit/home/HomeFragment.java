package com.example.uit.home;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView rcvMonHoc;
    private RecyclerView rcvMonThi;

    MonHocAdapter monHocAdapter;
    MonThiAdapter monThiAdapter;

    private NgayThiAdapter ngayThiAdapter;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        rcvMonHoc = root.findViewById(R.id.rcv_home_monHoc);
        rcvMonThi = root.findViewById(R.id.rcv_home_monThi);

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
        List<MonHoc> listMonHoc = new ArrayList<>();
        listMonHoc.add(new MonHoc("SS003.L11", "7-8", "C308", "Huynh ho mong trinh", "Phap Luat Dai Cuong", "07\n30"));
        listMonHoc.add(new MonHoc("SS003.L11", "7-8", "C308", "Huynh ho mong trinh", "Phap Luat Dai Cuong", "07\n30"));

        return listMonHoc;
    }

    private List<MonThi> getListMonThi() {
        List<MonThi> listMonThi = new ArrayList<>();
        listMonThi.add(new MonThi("21\n30", "SS005", "Co so du lieu nang cao", "C308"));
        listMonThi.add(new MonThi("21\n30", "SS005", "Lap trinh truc quan", "C308"));
        listMonThi.add(new MonThi("12h", "SS001", "CO so diu lieu", "K201"));
        listMonThi.add(new MonThi("12.1h", "BB001", "ADA", "K201"));
        listMonThi.add(new MonThi("12h.22", "AA001", "123", "K201"));

        return Data.listMonThi;
    }

    private List<NgayThi> getListNgayThi() {

        List<NgayThi> listNgayThi = new ArrayList<>();

        List<MonThi> listMonThi = new ArrayList<>();
        listMonThi.add(new MonThi("12h", "SS001", "CO so diu lieu", "K201"));
        listMonThi.add(new MonThi("12.1h", "BB001", "ADA", "K201"));
        listMonThi.add(new MonThi("12h.22", "AA001", "123", "K201"));
        NgayThi ngayThi1 = new NgayThi("21/12", listMonThi);


        listNgayThi.add(ngayThi1);
        listNgayThi.add(ngayThi1);
        return listNgayThi;
    }

}