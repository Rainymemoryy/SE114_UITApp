package com.example.uit;

import android.content.Intent;
import android.graphics.Color;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.uit.diem.BangDiem;
import com.example.uit.diem.DiemThi;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.sinhvien.SinhVien;
import com.example.uit.thongbao.ThongBao;
import com.example.uit.thongbao.ThongBaoAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.transform.Result;

import static android.os.Build.*;

public class MainActivity extends AppCompatActivity {


    public LoadingDialog loadingDialog = null;
    private AppBarConfiguration mAppBarConfiguration;
    public static Map<String, String> cookies = new HashMap<String, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        loadingDialog = new LoadingDialog(MainActivity.this);
        loadingDialog.StartLoadingDialog();


        GetData();

    }

    public void GetData() {

        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            cookies = (Map<String, String>) bundle.getSerializable("Cookies");
        }

        ExamLoad examLoad = new ExamLoad();
        examLoad.execute();

        SinhVienLoad sinhVienLoad = new SinhVienLoad();
        sinhVienLoad.execute();

        NotificationLoad notificationLoad = new NotificationLoad();
        notificationLoad.execute();

        BangDiemLoad bangDiemLoad = new BangDiemLoad();
        bangDiemLoad.execute();

        LichHocLoad lichHocLoad = new LichHocLoad();
        lichHocLoad.execute();
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

        thongBaoAdapter.setData(Data.listThongBao);
        rcvThongBao.setAdapter(thongBaoAdapter);

        AppCompatImageButton btnClose = viewNotification.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    public class ExamLoad extends AsyncTask<Void, Void, List<MonThi>> {

        @Override
        protected void onPostExecute(List<MonThi> listMonThi) {
            super.onPostExecute(listMonThi);


            Log.e("Load", " da load xong lich thi");
        }

        @Override
        protected List<MonThi> doInBackground(Void... voids) {
            try {
                Data.listMonThi = new ArrayList<>();
                String url = "https://daa.uit.edu.vn/ajax-block/lichthi/ajax";
                Connection.Response res = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .ignoreContentType(true)
                        .execute();
                String data = res.body();


                JSONParser parser = new JSONParser();


                Object obj = parser.parse(data);

                JSONArray json = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject) json.get(1);

                String lichthi = jsonObject.get("data").toString();


                Document document = Jsoup.parse(lichthi);

                Elements elements = document.select("tr > td");


                for (int i = 0; i < elements.size(); i += 7) {

                    Data.listMonThi.add(new MonThi(elements.get(i).text(),
                            elements.get(i + 1).text(),
                            elements.get(i + 2).text(),
                            elements.get(i + 3).text(),
                            elements.get(i + 4).text(),
                            elements.get(i + 5).text(),
                            elements.get(i + 6).text()
                    ));
                }

                Data.listNgayThi = new ArrayList<>();

                for (int i = 0; i < Data.listMonThi.size(); i++) {

                    MonThi monThi = Data.listMonThi.get(i);

                    if (Data.listNgayThi.size() == 0) {
                        Data.listNgayThi.add(new NgayThi(
                                "Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi()
                        ));
                        Data.listNgayThi.get(Data.listNgayThi.size() - 1).addMonThi(monThi);
                    } else {
                        for (NgayThi ngayThi : Data.listNgayThi) {

                            if (ngayThi.getStrNgayThi().equals("Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi())) {
                                ngayThi.addMonThi(monThi);
                            } else {
                                Data.listNgayThi.add(new NgayThi(
                                        "Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi()
                                ));
                                Data.listNgayThi.get(Data.listNgayThi.size() - 1).addMonThi(monThi);
                            }
                        }
                    }
                }


                if (Data.listMonThi != null) {
                    Log.d("Arr size", Integer.toString(Data.listMonThi.size()));
                    return Data.listMonThi;
                } else {
                    return null;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            return null;


        }

    }

    public class SinhVienLoad extends AsyncTask<Void, Void, SinhVien> {


        @Override
        protected SinhVien doInBackground(Void... voids) {
            try {
                String url = "https://daa.uit.edu.vn/sinhvien/kqhoctap";
                Connection.Response res = null;
                res = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .execute();

                Document document = res.parse();
                Elements elements = document.select("td > strong");

                if (elements.size() != 0) {
                    Data.sinhVien = new SinhVien(
                            elements.get(0).text(), // Hoten
                            elements.get(1).text(), // NgaySinh
                            elements.get(2).text(), // GioiTinh
                            elements.get(3).text(), // MSSV
                            elements.get(4).text(), // LopSH
                            elements.get(5).text(), // Khoa
                            elements.get(6).text(), // BacDT
                            elements.get(7).text()); // HeDT
                    Log.d("Tên", Data.sinhVien.getHoTen());
                    return null;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(SinhVien sinhVien) {
            super.onPostExecute(sinhVien);
            Log.e("Load", " da load xong sinh vien");
        }

    }

    private ArrayList<String> ListLinkNoti() throws IOException, org.json.simple.parser.ParseException, JSONException {
        String url = "https://daa.uit.edu.vn/ajax-block/message/ajax";
        Connection.Response res2 = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .cookies(cookies)
                .execute();

        String data = res2.body();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(data);
        JSONArray json = (JSONArray) obj;
        JSONObject jsonObject = (JSONObject) json.get(1);
        String thongBao = jsonObject.get("data").toString();

        Document document = Jsoup.parse(thongBao);
        Elements elements = document.getElementsByTag("a");
        ArrayList<String> arrayList = new ArrayList<>();
        for (Element e : elements) {
            arrayList.add(e.attr("href"));
        }
        return arrayList;
    }

    public class NotificationLoad extends AsyncTask<Void, Void, List<ThongBao>> {
        @Override
        protected List<ThongBao> doInBackground(Void... voids) {
            try {

                Data.listThongBao = new ArrayList<>();
                ArrayList<String> link = ListLinkNoti();

                if (link != null) {
                    for (String item : link) {
                        Connection.Response res = null;
                        res = Jsoup.connect(item)
                                .method(Connection.Method.GET)
                                .cookies(cookies)
                                .execute();
                        Document document = res.parse();
                        Elements elements = document.select("p > strong");
                        Element elementTitle = document.getElementById("page-title");
                        String create_time = document.select("div > span").text();
                        ThongBao thongBao = new ThongBao(
                                elementTitle.text(), create_time,
                                elements.get(0).text(), elements.get(1).text(), elements.get(2).text(),
                                elements.get(3).text(), elements.get(4).text(), elements.get(5).text(),
                                elements.get(6).text(), elements.get(7).text()
                        );

                        Data.listThongBao.add(thongBao);


                    }
                    if (Data.listThongBao.size() != 0) {
                        return Data.listThongBao;
                    }

                } else {
                    return null;
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<ThongBao> thongBao) {
            super.onPostExecute(thongBao);
            Log.e("Load", " da load xong thong bao");
        }
    }

    public class BangDiemLoad extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loadingDialog.DismissDialog();

            Log.e("Load", " da load xong ket qua hoc tap");
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                Data.listBangDiem = new ArrayList<>();


                String url = "https://daa.uit.edu.vn/sinhvien/kqhoctap";
                Connection.Response res2 = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .execute();

                Document document = res2.parse();
                Elements body = document.select("[cellpadding=2]");
                Elements elements = body.select("tr >td");

                int n = 0;
                for (int i = 0; i < elements.size(); ) {
                    Element e = elements.get(i);
                    if (e.select("[colspan=10]").size() > 0) {

                        Data.listBangDiem.add(new BangDiem(e.text()));
                        i++;
                    } else if (e.select("[colspan=3]").size() > 0) {
                        n = i;
                        i = 10000;
                    } else {
                        if (elements.get(i + 2).select("td > strong").size() > 0) {

                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).setTongSoTinChi(elements.get(i + 3).text());
                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).setDiemTrungBinh(elements.get(i + 8).text());
                        } else {
                            DiemThi diemThi = new DiemThi(
                                    elements.get(i + 1).text(),
                                    elements.get(i + 3).text(),
                                    elements.get(i + 4).text(),
                                    elements.get(i + 5).text(),
                                    elements.get(i + 6).text(),
                                    elements.get(i + 7).text(),
                                    elements.get(i + 8).text()
                            );

                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).addDiemThi(diemThi);

                        }
                        i += 10;
                    }

                }

                Data.soTinChiDaHoc = elements.get(n + 1).text();
                Data.soTinChiTichLuy = elements.get(n + 9).text();
                Data.diemTrungBinh = elements.get(n + 22).text();

                for (int i = n; i < elements.size(); i++) {

                }
                return "s";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class LichHocLoad extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {


                String url = "https://daa.uit.edu.vn/ajax-block/tkb/ajax";
                Connection.Response res = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .ignoreContentType(true)
                        .execute();
                String data = res.body();


                JSONParser parser = new JSONParser();


                Object obj = parser.parse(data);

                JSONArray json = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject) json.get(1);

                String lichthi = jsonObject.get("data").toString();


                Document document = Jsoup.parse(lichthi);

                Elements elements = document.select("tr > td");
                Log.e("size: ", elements.size() + "");
                for (Element e : elements)
                    Log.e("data: ", e.text());


                return null;
            } catch (IOException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}