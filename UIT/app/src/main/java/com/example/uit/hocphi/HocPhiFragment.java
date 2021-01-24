package com.example.uit.hocphi;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.MonHocHinhThuc2Adapter;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichhoc.NgayHocAdapter;

import java.util.List;

import static android.widget.CompoundButton.GONE;
import static android.widget.CompoundButton.OnCheckedChangeListener;
import static android.widget.CompoundButton.VISIBLE;

public class HocPhiFragment extends Fragment {


    private RecyclerView rcvHocPhi;
    private HocPhiAdapter hocPhiAdapter;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_hocphi, container, false);


        rcvHocPhi = root.findViewById(R.id.rcv_hocphi);
        hocPhiAdapter = new HocPhiAdapter();
        rcvHocPhi.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false));
        hocPhiAdapter.setData(getListHocPhi());
        rcvHocPhi.setAdapter(hocPhiAdapter);



        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    private List<HocPhi> getListHocPhi() {
        return Data.listHocPhi;
    }

}