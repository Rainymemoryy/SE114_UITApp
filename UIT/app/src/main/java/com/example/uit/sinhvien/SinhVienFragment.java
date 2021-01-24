package com.example.uit.sinhvien;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import com.example.uit.Data;
import com.example.uit.LoadDataActivity;
import com.example.uit.LoginActivity;
import com.example.uit.R;

public class SinhVienFragment extends Fragment {


    ImageView imgAvt;
    TextView tvTen, tvMssv, TvEmail, tvNgaySinh, tvLop;
    AppCompatImageButton btnDangXuat;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sinhvien, container, false);


        imgAvt = root.findViewById(R.id.img_sinhvien_avt);
        imgAvt.setImageBitmap(Data.bitmapAvt);


        tvTen = root.findViewById(R.id.tv_sinhvien_ten);
        tvTen.setText(Data.sinhVien.getHoTen());

        tvMssv = root.findViewById(R.id.tv_sinhvien_mssv);
        tvMssv.setText(Data.sinhVien.getMSSV());

        TvEmail = root.findViewById(R.id.tv_sinhvien_email);
        TvEmail.setText(Data.sinhVien.getMSSV() + "@gm.uit.edu.vn");

        tvNgaySinh = root.findViewById(R.id.tv_sinhvien_ngaysinh);
        tvNgaySinh.setText(Data.sinhVien.getNgaySinh());

        tvLop = root.findViewById(R.id.tv_sinhvien_lopsinhhoat);
        tvLop.setText(Data.sinhVien.getLopSH());

        btnDangXuat = root.findViewById(R.id.btn_dangXuat);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));

        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));

        return root;
    }


}