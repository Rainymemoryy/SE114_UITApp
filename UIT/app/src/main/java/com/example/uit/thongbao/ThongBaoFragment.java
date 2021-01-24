package com.example.uit.thongbao;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichhoc.NgayHocAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoFragment extends Fragment {


    Button btnClose;
    RecyclerView rcvThongBao;
    ThongBaoAdapter thongBaoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.bottomsheet_thongbao, container, false);


        root.findViewById(R.id.textView3).setVisibility(View.GONE);

        rcvThongBao = root.findViewById(R.id.rcv_thongbao);
        thongBaoAdapter = new ThongBaoAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);

        rcvThongBao.setLayoutManager(linearLayoutManager);


        List<ThongBao> listThongBao = new ArrayList<>();

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        Log.e("dadasdasdsadsadsadsadsadsad", "dadasdsadasdassss");

        thongBaoAdapter.setData(listThongBao);

        rcvThongBao.setAdapter(thongBaoAdapter);


        Window window = this.getActivity().getWindow();
        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    List<ThongBao> getListThongBao() {
        List<ThongBao> listThongBao = new ArrayList<>();

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "Cơ sở dữ liệu phân tán (IS211)",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));
        return listThongBao;
    }
}