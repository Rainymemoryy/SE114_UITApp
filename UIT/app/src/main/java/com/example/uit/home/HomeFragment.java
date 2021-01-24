package com.example.uit.home;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;
import com.example.uit.deadline.Deadline;
import com.example.uit.deadline.DeadlineAdapter;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.MonHocAdapter;
import com.example.uit.lichhoc.NgayHoc;
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
    private RecyclerView rcvDeadline;

    MonHocAdapter monHocAdapter;
    MonThiAdapter monThiAdapter;
    DeadlineAdapter deadlineAdapter;

    TextView tvHienThiNgay, tvTen;

    int ii = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        rcvMonHoc = root.findViewById(R.id.rcv_home_monHoc);
        rcvMonThi = root.findViewById(R.id.rcv_home_monThi);
        rcvDeadline = root.findViewById(R.id.rcv_home_deadline);
        tvHienThiNgay = root.findViewById(R.id.tv_home_today);
        tvTen = root.findViewById(R.id.tv_home_ten);


        tvHienThiNgay.setText(Data.thoiGian);

        if (Data.sinhVien != null) {
            String[] strs = Data.sinhVien.getHoTen().split("\\ ");
            String str = strs[strs.length - 1];
            tvTen.setText(str);
        }

        //Load lichThi
        if (true) {
            monThiAdapter = new MonThiAdapter();
            LinearLayoutManager linearLayoutManagerMonThi = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
            rcvMonThi.setLayoutManager(linearLayoutManagerMonThi);
            monThiAdapter.setData(getListMonThi());
            rcvMonThi.setAdapter(monThiAdapter);

            if (getListMonThi().size() <= 0) {
                root.findViewById(R.id.pnl_lichthi).setVisibility(View.GONE);
                ii++;
            }
        }

        //LoadLichHoc
        if (true) {
            monHocAdapter = new MonHocAdapter();
            LinearLayoutManager linearLayoutManagerMonHoc = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
            rcvMonHoc.setLayoutManager(linearLayoutManagerMonHoc);

            List<MonHoc> listMonHoc = new ArrayList<>();

            String[] getDay = Data.thoiGian.split("\\,");
            Log.e("day", getDay[0]);
            switch (getDay[0]) {
                case "Th 2":
                    listMonHoc= getListMonHoc("Thứ 2");
                    break;
                case "Th 3":
                    listMonHoc= getListMonHoc("Thứ 3");
                    break;
                case "Th 4":
                    listMonHoc= getListMonHoc("Thứ 4");
                    break;
                case "Th 5":
                    listMonHoc= getListMonHoc("Thứ 5");
                    break;
                case "Th 6":
                    listMonHoc= getListMonHoc("Thứ 6");
                    break;
                case "Th 7":
                    listMonHoc= getListMonHoc("Thứ 7");
                    break;
            }
            monHocAdapter.setData(listMonHoc);
            rcvMonHoc.setAdapter(monHocAdapter);

            if (listMonHoc.size() <= 0) {
                root.findViewById(R.id.pnl_lichhoc).setVisibility(View.GONE);
                ii++;
            }
        }

        //Deadline
        if (true) {
            deadlineAdapter = new DeadlineAdapter();
            LinearLayoutManager linearLayoutManagerDeadline = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
            rcvDeadline.setLayoutManager(linearLayoutManagerDeadline);
            deadlineAdapter.setData(getListDeadline());
            rcvDeadline.setAdapter(deadlineAdapter);

            if (getListDeadline().size() <= 0) {
                root.findViewById(R.id.pnl_deadline).setVisibility(View.GONE);
                ii++;
            }
        }


        if (ii >= 3) {
            root.findViewById(R.id.tv_home_none).setVisibility(View.VISIBLE);
        }


        if (true) {
            this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#90a4ae"));


            this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#90a4ae"));

            this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#90a4ae"));
            this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#90a4ae"));

        }

        return root;
    }


    private List<MonHoc> getListMonHoc(String thuTrongTuan) {

        for (NgayHoc ngayHoc : Data.listNgayHocHT1)
            if (ngayHoc.getNgayHoc().contains(thuTrongTuan))
                return ngayHoc.getListMonHoc();
        return new ArrayList<>();
    }

    private List<MonThi> getListMonThi() {
        return Data.listLichThiHomNay;
    }

    private List<Deadline> getListDeadline() {

        return Data.listDeadline;

    }

}