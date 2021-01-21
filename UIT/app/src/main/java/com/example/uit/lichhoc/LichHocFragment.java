package com.example.uit.lichhoc;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.lichthi.NgayThiAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.CompoundButton.*;

public class LichHocFragment extends Fragment {


    private RecyclerView rcvNgayHoc, rcvMonHocHinhThuc2;
    private NgayHocAdapter ngayHocAdapter;
    private MonHocHinhThuc2Adapter monHocHinhThuc2Adapter;
    private Switch switchHinhThuc;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_lichhoc, container, false);


        rcvNgayHoc = root.findViewById(R.id.rcv_ngayHoc);
        rcvMonHocHinhThuc2 = root.findViewById(R.id.rcv_monhochinhthuc2);
        switchHinhThuc = root.findViewById(R.id.switch_lichhoc);

        switchHinhThuc.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton,
                                                 boolean b) {
                        Log.e("On or off", b ? "on" : "off");

                        if (!b) {
                            rcvNgayHoc.setVisibility(VISIBLE);
                            rcvMonHocHinhThuc2.setVisibility(GONE);
                        } else {
                            rcvNgayHoc.setVisibility(GONE);
                            rcvMonHocHinhThuc2.setVisibility(VISIBLE);
                        }

                    }
                });



        ngayHocAdapter = new NgayHocAdapter();
        rcvNgayHoc.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false));
        ngayHocAdapter.setData(getListNgayHoc());
        rcvNgayHoc.setAdapter(ngayHocAdapter);


        monHocHinhThuc2Adapter = new MonHocHinhThuc2Adapter();
        rcvMonHocHinhThuc2.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false));
        monHocHinhThuc2Adapter.setData(getListMonHocHinhThuc2());
        rcvMonHocHinhThuc2.setAdapter(monHocHinhThuc2Adapter);





        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    private List<NgayHoc> getListNgayHoc() {
        return Data.listNgayHocHT1;
    }

    private List<MonHoc> getListMonHocHinhThuc2() {
        return Data.listMonHocHT2;
    }

}