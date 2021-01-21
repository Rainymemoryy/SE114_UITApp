package com.example.uit.diem;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichhoc.NgayHocAdapter;
import com.stone.vega.library.VegaLayoutManager;


import java.util.ArrayList;
import java.util.List;

public class BangDiemFragment extends Fragment {


    private RecyclerView rcvBangDiem;
    private BangDiemAdapter bangDiemAdapter;

    private TextView tvSoTinChiDaHoc, tvSoTinChiTichLuy, tvDiemTrungBinh;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ketquahoctap, container, false);

        tvSoTinChiDaHoc = root.findViewById(R.id.tv_kqht_sotinchidahoc);
        tvSoTinChiTichLuy = root.findViewById(R.id.tv_kqht_sotinchitichluy);
        tvDiemTrungBinh = root.findViewById(R.id.tv_kqht_diemtrungbinh);

        tvSoTinChiDaHoc.setText(Data.soTinChiDaHoc);
        tvSoTinChiTichLuy.setText(Data.soTinChiTichLuy);
        tvDiemTrungBinh.setText(Data.diemTrungBinh);

        rcvBangDiem = root.findViewById(R.id.rcv_ketquahoctap);
        bangDiemAdapter =  new BangDiemAdapter();

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
//        rcvBangDiem.setLayoutManager(linearLayoutManager);

        rcvBangDiem.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false));

        bangDiemAdapter.setData(getListBangDiem());
        rcvBangDiem.setAdapter(bangDiemAdapter);



        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    private List<BangDiem> getListBangDiem() {
        List<BangDiem> listBangDiem=new ArrayList<>();


        List<DiemThi> listDiemThi = new ArrayList<>();
        listDiemThi.add(new DiemThi("SS004", "4", "2.2", "4.8","8", "9", "3"));
        listDiemThi.add(new DiemThi("SS004", "2", "", "4.8","8", "9", "3"));
        listDiemThi.add(new DiemThi("SS004", "9", "", "4.8","8", "", "3"));
        listDiemThi.add(new DiemThi("SS004", "4", "2.2", "4.8","8", "9", "3"));
        listDiemThi.add(new DiemThi("SS004", "4", "2.2", "4.8","", "9", "3"));
        listDiemThi.add(new DiemThi("SS004", "3", "2.2", "4.8","8", "9", "3"));
        listBangDiem.add(new BangDiem("hoc ky 1 2019 2020", listDiemThi));
        listBangDiem.add(new BangDiem("hoc ky 2 2010 2020", listDiemThi));

        return Data.listBangDiem;

    }


}