package com.example.uit;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

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


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


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





        //GetData();



    }

//    public void GetData() {
//
//        LocalDateTime myDateObj = LocalDateTime.now();
//
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy");
//        String formattedDate = myDateObj.format(myFormatObj);
//        Data.thoiGian = formattedDate;
//        Log.e("thoi gian ", formattedDate);
//
//
//        Bundle bundle = this.getIntent().getExtras();
//
//        if (bundle != null) {
//            cookies = (Map<String, String>) bundle.getSerializable("Cookies");
//        }
//
//        ExamLoad examLoad = new ExamLoad();
//        examLoad.execute();
//
//        SinhVienLoad sinhVienLoad = new SinhVienLoad();
//        sinhVienLoad.execute();
//
//        LinkImgLoad linkImgLoad = new LinkImgLoad();
//        linkImgLoad.execute();
//
//
//
//        NotificationLoad notificationLoad = new NotificationLoad();
//        notificationLoad.execute();
//
//        BangDiemLoad bangDiemLoad = new BangDiemLoad();
//        bangDiemLoad.execute();
//
//        LichHocLoad lichHocLoad = new LichHocLoad();
//        lichHocLoad.execute();
//
//
//
//
//
//    }


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

//    public class ExamLoad extends AsyncTask<Void, Void, List<MonThi>> {
//
//        @Override
//        protected void onPostExecute(List<MonThi> listMonThi) {
//            super.onPostExecute(listMonThi);
//
//
//            Log.e("Load", " da load xong lich thi");
//        }
//
//        @Override
//        protected List<MonThi> doInBackground(Void... voids) {
//            try {
//                Data.listMonThi = new ArrayList<>();
//                String url = "https://daa.uit.edu.vn/ajax-block/lichthi/ajax";
//                Connection.Response res = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .cookies(cookies)
//                        .ignoreContentType(true)
//                        .execute();
//                String data = res.body();
//
//
//                JSONParser parser = new JSONParser();
//
//
//                Object obj = parser.parse(data);
//
//                JSONArray json = (JSONArray) obj;
//
//                JSONObject jsonObject = (JSONObject) json.get(1);
//
//                String lichthi = jsonObject.get("data").toString();
//
//
//                Document document = Jsoup.parse(lichthi);
//
//                Elements elements = document.select("tr > td");
//
//
//                for (int i = 0; i < elements.size(); i += 7) {
//
//                    Data.listMonThi.add(new MonThi(elements.get(i).text(),
//                            elements.get(i + 1).text(),
//                            elements.get(i + 2).text(),
//                            elements.get(i + 3).text(),
//                            elements.get(i + 4).text(),
//                            elements.get(i + 5).text(),
//                            elements.get(i + 6).text()
//                    ));
//                }
//
//                Data.listNgayThi = new ArrayList<>();
//                Data.listLichThiHomNay = new ArrayList<>();
//
//                for (int i = 0; i < Data.listMonThi.size(); i++) {
//
//                    MonThi monThi = Data.listMonThi.get(i);
//
//                    if (Data.listNgayThi.size() == 0) {
//                        Data.listNgayThi.add(new NgayThi(
//                                "Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi()
//                        ));
//                        Data.listNgayThi.get(Data.listNgayThi.size() - 1).addMonThi(monThi);
//                    } else {
//                        for (NgayThi ngayThi : Data.listNgayThi) {
//
//                            if (ngayThi.getStrNgayThi().equals("Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi())) {
//                                ngayThi.addMonThi(monThi);
//                            } else {
//                                Data.listNgayThi.add(new NgayThi(
//                                        "Thứ " + monThi.getThuThi() + ", " + monThi.getNgayThi()
//                                ));
//                                Data.listNgayThi.get(Data.listNgayThi.size() - 1).addMonThi(monThi);
//                            }
//                        }
//                    }
//
//                    if (Data.thoiGian.contains(monThi.getNgayThi())) {
//                        Log.e("____________", Data.thoiGian + "|" + monThi.getNgayThi());
//                        Data.listLichThiHomNay.add(monThi);
//                    } else
//                        Log.e("++++++++++++", Data.thoiGian + "|" + monThi.getNgayThi());
//
//
//                }
//
//
//                if (Data.listMonThi != null) {
//                    Log.d("Arr size", Integer.toString(Data.listMonThi.size()));
//                    return Data.listMonThi;
//                } else {
//                    return null;
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//            }
//            return null;
//
//
//        }
//
//    }
//
//    public class SinhVienLoad extends AsyncTask<Void, Void, SinhVien> {
//
//
//        @Override
//        protected SinhVien doInBackground(Void... voids) {
//            try {
//                String url = "https://daa.uit.edu.vn/sinhvien/kqhoctap";
//                Connection.Response res = null;
//                res = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .cookies(cookies)
//                        .execute();
//
//                Document document = res.parse();
//                Elements elements = document.select("td > strong");
//                if (elements.size() != 0) {
//                    Data.sinhVien = new SinhVien(
//                            elements.get(0).text(), // Hoten
//                            elements.get(1).text(), // NgaySinh
//                            elements.get(2).text(), // GioiTinh
//                            elements.get(3).text(), // MSSV
//                            elements.get(4).text(), // LopSH
//                            elements.get(5).text(), // Khoa
//                            elements.get(6).text(), // BacDT
//                            elements.get(7).text()); // HeDT
//                    Log.d("Tên", Data.sinhVien.getHoTen());
//
//
//                    return null;
//                }
//
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(SinhVien sinhVien) {
//            super.onPostExecute(sinhVien);
//
//            Log.e("Load", " da load xong sinh vien");
//        }
//
//    }
//
//    public class LinkImgLoad extends AsyncTask<Void, Void, String> {
//
//        String Link;
//        @Override
//        protected String doInBackground(Void... voids) {
//
//
//            try {
//                String url = "https://daa.uit.edu.vn/khaibaolylich";
//                Connection.Response res = null;
//                res = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .cookies(cookies)
//                        .execute();
//
//                Document document = res.parse();
//                Elements elements = document.select("#hinhthe");
//
//                Link = elements.select("img").attr("src");
//
//
//                Data.linkImg = Link;
//
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//        protected void onPostExecute(String link){
//            super.onPostExecute(link);
//            Log.e("load", " link: " + Link );
//
//            DownloadImageTask downloadImageTask = new DownloadImageTask();
//            downloadImageTask.execute(Link);
//        }
//    }
//
//    @NotNull
//    private ArrayList<String> ListLinkNoti() throws IOException, org.json.simple.parser.ParseException, JSONException {
//        String url = "https://daa.uit.edu.vn/ajax-block/message/ajax";
//        Connection.Response res2 = Jsoup.connect(url)
//                .method(Connection.Method.GET)
//                .ignoreContentType(true)
//                .cookies(cookies)
//                .execute();
//
//        String data = res2.body();
//
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(data);
//        JSONArray json = (JSONArray) obj;
//        JSONObject jsonObject = (JSONObject) json.get(1);
//        String thongBao = jsonObject.get("data").toString();
//
//        Document document = Jsoup.parse(thongBao);
//        Elements elements = document.getElementsByTag("a");
//        ArrayList<String> arrayList = new ArrayList<>();
//        for (Element e : elements) {
//            arrayList.add(e.attr("href"));
//        }
//        return arrayList;
//    }
//
//    public class NotificationLoad extends AsyncTask<Void, Void, List<ThongBao>> {
//        @Override
//        protected List<ThongBao> doInBackground(Void... voids) {
//            try {
//
//                Data.listThongBao = new ArrayList<>();
//                ArrayList<String> link = ListLinkNoti();
//
//                if (link != null) {
//                    for (String item : link) {
//                        Connection.Response res = null;
//                        res = Jsoup.connect(item)
//                                .method(Connection.Method.GET)
//                                .cookies(cookies)
//                                .execute();
//                        Document document = res.parse();
//                        Elements elements = document.select("p > strong");
//                        Element elementTitle = document.getElementById("page-title");
//                        String create_time = document.select("div > span").text();
//                        ThongBao thongBao = new ThongBao(
//                                elementTitle.text(), create_time,
//                                elements.get(0).text(), elements.get(1).text(), elements.get(2).text(),
//                                elements.get(3).text(), elements.get(4).text(), elements.get(5).text(),
//                                elements.get(6).text(), elements.get(7).text()
//                        );
//
//                        Data.listThongBao.add(thongBao);
//
//
//                    }
//                    if (Data.listThongBao.size() != 0) {
//                        return Data.listThongBao;
//                    }
//
//                } else {
//                    return null;
//                }
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            } catch (org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<ThongBao> thongBao) {
//            super.onPostExecute(thongBao);
//            Log.e("Load", " da load xong thong bao");
//        }
//    }
//
//    public class BangDiemLoad extends AsyncTask<Void, Void, String> {
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            loadingDialog.DismissDialog();
//
//            Log.e("Load", " da load xong ket qua hoc tap");
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            try {
//                Data.listBangDiem = new ArrayList<>();
//
//
//                String url = "https://daa.uit.edu.vn/sinhvien/kqhoctap";
//                Connection.Response res2 = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .cookies(cookies)
//                        .execute();
//
//                Document document = res2.parse();
//                Elements body = document.select("[cellpadding=2]");
//                Elements elements = body.select("tr >td");
//
//                int n = 0;
//                for (int i = 0; i < elements.size(); ) {
//                    Element e = elements.get(i);
//                    if (e.select("[colspan=10]").size() > 0) {
//
//                        Data.listBangDiem.add(new BangDiem(e.text()));
//                        i++;
//                    } else if (e.select("[colspan=3]").size() > 0) {
//                        n = i;
//                        i = 10000;
//                    } else {
//                        if (elements.get(i + 2).select("td > strong").size() > 0) {
//
//                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).setTongSoTinChi(elements.get(i + 3).text());
//                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).setDiemTrungBinh(elements.get(i + 8).text());
//                        } else {
//                            DiemThi diemThi = new DiemThi(
//                                    elements.get(i + 1).text(),
//                                    elements.get(i + 3).text(),
//                                    elements.get(i + 4).text(),
//                                    elements.get(i + 5).text(),
//                                    elements.get(i + 6).text(),
//                                    elements.get(i + 7).text(),
//                                    elements.get(i + 8).text()
//                            );
//
//                            Data.listBangDiem.get(Data.listBangDiem.size() - 1).addDiemThi(diemThi);
//
//                        }
//                        i += 10;
//                    }
//
//                }
//
//                Data.soTinChiDaHoc = elements.get(n + 1).text();
//                Data.soTinChiTichLuy = elements.get(n + 9).text();
//                Data.diemTrungBinh = elements.get(n + 22).text();
//
//                for (int i = n; i < elements.size(); i++) {
//
//                }
//                return "s";
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    public class LichHocLoad extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//
//
//                String url = "https://daa.uit.edu.vn/ajax-block/tkb/ajax";
//                Connection.Response res = Jsoup.connect(url)
//                        .method(Connection.Method.GET)
//                        .cookies(cookies)
//                        .ignoreContentType(true)
//                        .execute();
//                String data = res.body();
//                JSONParser parser = new JSONParser();
//                Object obj = parser.parse(data);
//                JSONArray json = (JSONArray) obj;
//                JSONObject jsonObject = (JSONObject) json.get(1);
//                String lichthi = jsonObject.get("data").toString();
//                Document document = Jsoup.parse(lichthi);
//
//
//                Elements elements = document.select("tr > td");
//
//                Data.listMonHoc = new ArrayList<>();
//                Data.listMonHocHT1 = new ArrayList<>();
//                Data.listMonHocHT2 = new ArrayList<>();
//
//                for (int i = 0; i < elements.size(); i += 8) {
//
//
//                    String thuTietPhong = elements.get(i + 4).text();
//                    String[] output = thuTietPhong.split("\\, ");
//                    String thu = output[0];
//                    String tiet = (output[1].split("\\ "))[1];
//                    String phong = (output[2].split("\\ "))[1];
//
//
//                    String thoiGianBatDau = "", thoiGianKetThuc = "", thongTin = "";
//                    String hinhThuc = elements.get(i + 5).text();
//                    if (!hinhThuc.contains("HT2")) {
//
//                        int iThu = Integer.parseInt(tiet);
//                        int iKetThu = iThu % 10;
//                        int iBatDau = 1;
//                        while (iThu / 10 > 0) {
//                            iBatDau = iThu / 10;
//                            iThu /= 10;
//                        }
//
//
//                        switch (iBatDau) {
//                            case 1:
//                                thoiGianBatDau = "07h30";
//                                break;
//                            case 2:
//                                thoiGianBatDau = "08h15";
//                                break;
//                            case 3:
//                                thoiGianBatDau = "09h00";
//                                break;
//                            case 4:
//                                thoiGianBatDau = "10h00";
//                                break;
//                            case 5:
//                                thoiGianBatDau = "10h45";
//                                break;
//                            case 6:
//                                thoiGianBatDau = "13h00";
//                                break;
//                            case 7:
//                                thoiGianBatDau = "13h45";
//                                break;
//                            case 8:
//                                thoiGianBatDau = "14h30";
//                                break;
//                            case 9:
//                                thoiGianBatDau = "15h30";
//                                break;
//                            case 10:
//                                thoiGianBatDau = "16h15";
//                                break;
//                            default:
//                                thoiGianBatDau = "07h30";
//                        }
//                        switch (iKetThu) {
//                            case 1:
//                                thoiGianKetThuc = "08h15";
//                                break;
//                            case 2:
//                                thoiGianKetThuc = "09h00";
//                                break;
//                            case 3:
//                                thoiGianKetThuc = "09h45";
//                                break;
//                            case 4:
//                                thoiGianKetThuc = "10h45";
//                                break;
//                            case 5:
//                                thoiGianKetThuc = "11h30";
//                                break;
//                            case 6:
//                                thoiGianKetThuc = "13h45";
//                                break;
//                            case 7:
//                                thoiGianKetThuc = "14h30";
//                                break;
//                            case 8:
//                                thoiGianKetThuc = "15h15";
//                                break;
//                            case 9:
//                                thoiGianKetThuc = "16h15";
//                                break;
//                            case 10:
//                                thoiGianKetThuc = "17h00";
//                                break;
//                            default:
//                                thoiGianKetThuc = "08h15";
//                        }
//
//                        //tenMonHoc, maLop, thoiGianBatDau, thoiGianKetThu, hinhThuc, hocThu, phongHoc, tenGiangVien, thongTin;
//                        Data.listMonHocHT1.add(new MonHoc(
//                                elements.get(i + 1).text(),
//                                elements.get(i + 2).text(),
//                                thoiGianBatDau,
//                                thoiGianKetThuc,
//                                hinhThuc,
//                                thu,
//                                phong,
//                                elements.get(i + 5).text(),
//                                elements.get(i + 7).text()
//                        ));
//
//
//                    } else {
//                        Data.listMonHocHT2.add(new MonHoc(
//                                elements.get(i + 1).text(),
//                                elements.get(i + 2).text(),
//                                thoiGianBatDau,
//                                thoiGianKetThuc,
//                                hinhThuc,
//                                thu,
//                                phong,
//                                elements.get(i + 5).text(),
//                                elements.get(i + 7).text()
//                        ));
//                    }
//
//                }
//
//
//                Data.listNgayHocHT1 = new ArrayList<>();
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 2", new ArrayList<>()));
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 3", new ArrayList<>()));
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 4", new ArrayList<>()));
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 5", new ArrayList<>()));
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 6", new ArrayList<>()));
//                Data.listNgayHocHT1.add(new NgayHoc("Thứ 7", new ArrayList<>()));
//
//                for (MonHoc monHoc : Data.listMonHocHT1) {
//
//                    switch (monHoc.getHocThu()) {
//                        case "Thứ 2":
//                            Log.e("Thu 2", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(0).addMonHoc(monHoc);
//                            break;
//                        case "Thứ 3":
//                            Log.e("Thu 3", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(1).addMonHoc(monHoc);
//                            break;
//                        case "Thứ 4":
//                            Log.e("Thu 4", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(2).addMonHoc(monHoc);
//                            break;
//                        case "Thứ 5":
//                            Log.e("Thu 5", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(3).addMonHoc(monHoc);
//                            break;
//                        case "Thứ 6":
//                            Log.e("Thu 6", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(4).addMonHoc(monHoc);
//                            break;
//                        case "Thứ 7":
//                            Log.e("Thu 7", monHoc.getTenMonHoc());
//                            Data.listNgayHocHT1.get(5).addMonHoc(monHoc);
//                            break;
//                        default:
//                            Log.e("Chủ nhật", monHoc.getTenMonHoc());
//                    }
//
//
//                    Log.e("Data ", "|" + monHoc.getHocThu() + "|" + monHoc.getMaLop() + "|" + monHoc.getThoiGianBatDau() + "|" + monHoc.getThoiGianKetThu() + "|" + monHoc.getPhongHoc());
//                }
//
//                for (int i = Data.listNgayHocHT1.size() - 1; i >= 0; i--) {
//                    if (Data.listNgayHocHT1.get(i).getListMonHoc().size() == 0)
//                        Data.listNgayHocHT1.remove(i);
//                }
//
//
//                for (NgayHoc ngayHoc : Data.listNgayHocHT1)
//                    Log.e("Size", ngayHoc.getListMonHoc().size() + "");
//
//                return null;
//            } catch (IOException | org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//
//        String url;
//        Bitmap bitmap;
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            url = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            Data.bitmapAvt = mIcon11;
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            Log.e("download ", url);
//        }
//
//
//    }

}