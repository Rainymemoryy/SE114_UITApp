package com.example.uit.lichhoc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.lichthi.NgayThiAdapter;

import java.util.ArrayList;
import java.util.List;

public class LichHocFragment extends Fragment {


    private RecyclerView rcvNgayHoc;
    private NgayHocAdapter ngayHocAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_lichhoc, container, false);


        rcvNgayHoc = root.findViewById(R.id.rcv_ngayHoc);
        ngayHocAdapter =  new NgayHocAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);


        rcvNgayHoc.setLayoutManager(linearLayoutManager);

        ngayHocAdapter.setData(getListNgayHoc());
        rcvNgayHoc.setAdapter(ngayHocAdapter);



        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));

        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    private List<NgayHoc> getListNgayHoc() {

        List<NgayHoc> listNgayHoc=new ArrayList<>();

        List<MonHoc> listmonhoc1=  new ArrayList<>();
        listmonhoc1.add(new MonHoc("SS001.L11", "7-10", "C.314(GD.A2)", "Huyen ho mong trinh", "Lap trinh game", "07\n30"));
        listmonhoc1.add(new MonHoc("SS001", "7-10", "C.314", "Huyen ho mong trinh", "Lap trinh game", "07\n30"));

        listNgayHoc.add(new NgayHoc("21 n12", listmonhoc1));
        listNgayHoc.add(new NgayHoc("21 n12", listmonhoc1));
        listNgayHoc.add(new NgayHoc("21 12", listmonhoc1));

        List<MonHoc> listmonhoc2=  new ArrayList<>();
        listmonhoc2.add(new MonHoc("SS001", "7-10", "C.314", "Huyen ho mong trinh", "Lap trinh game", "07\n30"));

        listNgayHoc.add(new NgayHoc("12 04", listmonhoc2));
        return listNgayHoc;
    }
}