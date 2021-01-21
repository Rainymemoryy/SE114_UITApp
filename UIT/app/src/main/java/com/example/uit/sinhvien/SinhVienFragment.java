package com.example.uit.sinhvien;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.uit.Data;
import com.example.uit.R;

public class SinhVienFragment extends Fragment {


    ImageView imgAvt;
    TextView tvTen, tvMssv, TvEmail, tvNgaySinh, tvLop;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sinhvien, container, false);


        imgAvt = root.findViewById(R.id.img_sinhvien_avt);
        imgAvt.setImageBitmap(Data.bitmapAvt);


        tvTen = root.findViewById(R.id.tv_sinhvien_ten);
        tvTen.setText(Data.sinhVien.getHoTen());

        tvMssv = root.findViewById(R.id.tv_sinhvien_mssv);
        tvTen.setText(Data.sinhVien.getMSSV());

        TvEmail = root.findViewById(R.id.tv_sinhvien_email);
        tvTen.setText(Data.sinhVien.getMSSV()+"@gm.uit.edu.vn");

        tvNgaySinh = root.findViewById(R.id.tv_sinhvien_ngaysinh);
        tvTen.setText(Data.sinhVien.getNgaySinh());

        tvLop = root.findViewById(R.id.tv_sinhvien_lopsinhhoat);
        tvTen.setText(Data.sinhVien.getLopSH());



        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));

        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));

        return root;
    }


}