package com.example.uit;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uit.thongbao.ThongBao;
import com.example.uit.thongbao.ThongBaoAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    public static Map<String, String> cookies = new HashMap<String, String>();
    public static Context mConText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConText=MainActivity.this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#00002d"));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenNotification();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        ((TextView) headerView.findViewById(R.id.tv_header_ten)).setText(Data.sinhVien.getHoTen());
        ((TextView) headerView.findViewById(R.id.tv_header_mssv)).setText(Data.sinhVien.getMSSV());
        ((ImageView) headerView.findViewById(R.id.img_header_avt)).setImageBitmap(Data.bitmapAvt);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void clickOpenNotification() {
        View viewNotification = getLayoutInflater().inflate(R.layout.bottomsheet_thongbao, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewNotification);
        bottomSheetDialog.show();

        RecyclerView rcvThongBao;
        ThongBaoAdapter thongBaoAdapter;

        rcvThongBao = viewNotification.findViewById(R.id.rcv_thongbao);
        thongBaoAdapter = new ThongBaoAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rcvThongBao.setLayoutManager(linearLayoutManager);




        thongBaoAdapter.setData(getListThongBao());
        rcvThongBao.setAdapter(thongBaoAdapter);

        AppCompatImageButton btnClose = viewNotification.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    private List<ThongBao> getListThongBao() {

        List<ThongBao> listThongBao = new ArrayList<>();

        listThongBao.add(new ThongBao(
                "Thông báo học bù Cơ sở dữ liệu phân tán (IS211.K21) ngày 06/06/2020",
                "T2, 01/06/2020 - 09:29",
                "Nguyễn Hồ Duy Tri",
                "HTTT",
                "Cơ sở dữ liệu phân tán (IS211)",
                "IS211.K21",
                "B1.20",
                "2",
                "5",
                "7 , ngày 06/06/2020"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo nghỉ lớp Đại số tuyến tính (MA003.L11) ngày 23/01/2021",
                "T5, 21/01/2021 - 23:17",
                "Lê Phước Hải",
                "BMTL",
                "Đại số tuyến tính (MA003)",
                "MA003.L11",
                "C305",
                "6",
                "9",
                "7 , ngày 23/01/2021"
        ));

        listThongBao.add(new ThongBao(
                "Thông báo học bù Nhập môn lập trình (IT001.L13.1) ngày 20/01/2021",
                "T2, 18/01/2021 - 15:52",
                "Lương Văn Song",
                "HTTT",
                "Nhập môn lập trình (IT001)",
                "IT001.L13.1",
                "C211",
                "6",
                "10",
                "4 , ngày 20/01/2021"
        ));


        return Data.listThongBao;
    }


}