package com.example.uit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;


import com.example.uit.deadline.Deadline;
import com.example.uit.diem.BangDiem;
import com.example.uit.diem.DiemThi;
import com.example.uit.hocphi.HocPhi;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.noti.ThongBaoBroadcast;
import com.example.uit.sinhvien.SinhVien;
import com.example.uit.thongbao.ThongBao;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
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
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.AlarmManager;
import android.app.PendingIntent;

public class LoadDataActivity extends AppCompatActivity {

    public static Map<String, String> cookies = new HashMap<String, String>();
    int nLoad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#ffffff"));
        GetData();
    }

    public void GetData() {

        LocalDateTime myDateObj = LocalDateTime.now();

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("EEE, dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        Data.thoiGian = formattedDate;


        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null) {
            cookies = (Map<String, String>) bundle.getSerializable("Cookies");
        }


        SinhVienLoad sinhVienLoad = new SinhVienLoad();
        sinhVienLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        LinkImgLoad linkImgLoad = new LinkImgLoad();
        linkImgLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        NotificationLoad notificationLoad = new NotificationLoad();
        notificationLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        BangDiemLoad bangDiemLoad = new BangDiemLoad();
        bangDiemLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        LichHocLoad lichHocLoad = new LichHocLoad();
        lichHocLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        DeadlineLoad deadlineLoad = new DeadlineLoad();
        deadlineLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        HocPhiLoad hocPhiLoad = new HocPhiLoad();
        hocPhiLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        //createNotificationChannel();

        //createNoti("khang", "mot hai ba bon", 2021, 0, 24, 14, 35, 10);
        //createNoti("Lich thi", "SS007.L21, 09h30, B308", 2021, 0, 24, 15, 9, 10);
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "deadlineReminderChannel";
            String description = "Channel for Deadline Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("deadlinenotify", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void createNoti(String title, String content, int year, int month, int day, int hour, int minute, int second) {


        Intent intent = new Intent(LoadDataActivity.this, ThongBaoBroadcast.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(LoadDataActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(year, month, day, hour, minute, second);


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void SwitchMainActivity() {
        if (nLoad == 8) {
            Intent intent = new Intent(LoadDataActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    public class ExamLoad extends AsyncTask<Void, Void, List<MonThi>> {

        @Override
        protected void onPostExecute(List<MonThi> listMonThi) {
            super.onPostExecute(listMonThi);
            nLoad++;
            Log.e("Load", " da load xong lich thi " + nLoad);

            SwitchMainActivity();
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
                    String monThi = null;
                    for (MonHoc monHoc : Data.listMonHocHT1) {
                        if (monHoc.getMaLop().contains(elements.get(i + 2).text())) {
                            monThi = monHoc.getTenMonHoc();

                        }

                    }

                    Data.listMonThi.add(new MonThi(
                            elements.get(i).text(),
                            elements.get(i + 1).text(),
                            elements.get(i + 2).text(),
                            elements.get(i + 3).text(),
                            elements.get(i + 4).text(),
                            elements.get(i + 5).text(),
                            elements.get(i + 6).text(),
                            monThi
                    ));
                }

                Data.listNgayThi = new ArrayList<>();
                Data.listLichThiHomNay = new ArrayList<>();

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

                    if (Data.thoiGian.contains(monThi.getNgayThi())) {

                        Data.listLichThiHomNay.add(monThi);
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
            nLoad++;
            Log.e("Load", " da load xong sinh vien " + nLoad);
            SwitchMainActivity();
        }

    }

    public class LinkImgLoad extends AsyncTask<Void, Void, String> {

        String Link;

        @Override
        protected String doInBackground(Void... voids) {


            try {
                String url = "https://daa.uit.edu.vn/khaibaolylich";
                Connection.Response res = null;
                res = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .execute();

                Document document = res.parse();
                Elements elements = document.select("#hinhthe");

                Link = elements.select("img").attr("src");


                Data.linkImg = Link;

                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String link) {
            super.onPostExecute(link);

            DownloadImageTask downloadImageTask = new DownloadImageTask();
            downloadImageTask.execute(Link);
            //downloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @NotNull
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
            nLoad++;
            Log.e("Load", " da load xong thong bao " + nLoad);
        }
    }

    public class BangDiemLoad extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            nLoad++;

            Log.e("Load", " da load xong ket qua hoc tap " + nLoad);
            SwitchMainActivity();
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

    public class LichHocLoad extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
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

                //Data.listMonHoc = new ArrayList<>();
                Data.listMonHocHT1 = new ArrayList<>();
                Data.listMonHocHT2 = new ArrayList<>();

                for (int i = 0; i < elements.size(); i += 8) {


                    String thuTietPhong = elements.get(i + 4).text();
                    String[] output = thuTietPhong.split("\\, ");
                    String thu = output[0];
                    String tiet = (output[1].split("\\ "))[1];
                    String phong = (output[2].split("\\ "))[1];


                    String thoiGianBatDau = "", thoiGianKetThuc = "", thongTin = "";
                    String hinhThuc = elements.get(i + 5).text();
                    if (!hinhThuc.contains("HT2")) {

                        int iThu = Integer.parseInt(tiet);
                        int iKetThu = iThu % 10;
                        int iBatDau = 1;
                        while (iThu / 10 > 0) {
                            iBatDau = iThu / 10;
                            iThu /= 10;
                        }


                        switch (iBatDau) {
                            case 1:
                                thoiGianBatDau = "07h30";
                                break;
                            case 2:
                                thoiGianBatDau = "08h15";
                                break;
                            case 3:
                                thoiGianBatDau = "09h00";
                                break;
                            case 4:
                                thoiGianBatDau = "10h00";
                                break;
                            case 5:
                                thoiGianBatDau = "10h45";
                                break;
                            case 6:
                                thoiGianBatDau = "13h00";
                                break;
                            case 7:
                                thoiGianBatDau = "13h45";
                                break;
                            case 8:
                                thoiGianBatDau = "14h30";
                                break;
                            case 9:
                                thoiGianBatDau = "15h30";
                                break;
                            case 10:
                                thoiGianBatDau = "16h15";
                                break;
                            default:
                                thoiGianBatDau = "07h30";
                        }
                        switch (iKetThu) {
                            case 1:
                                thoiGianKetThuc = "08h15";
                                break;
                            case 2:
                                thoiGianKetThuc = "09h00";
                                break;
                            case 3:
                                thoiGianKetThuc = "09h45";
                                break;
                            case 4:
                                thoiGianKetThuc = "10h45";
                                break;
                            case 5:
                                thoiGianKetThuc = "11h30";
                                break;
                            case 6:
                                thoiGianKetThuc = "13h45";
                                break;
                            case 7:
                                thoiGianKetThuc = "14h30";
                                break;
                            case 8:
                                thoiGianKetThuc = "15h15";
                                break;
                            case 9:
                                thoiGianKetThuc = "16h15";
                                break;
                            case 10:
                                thoiGianKetThuc = "17h00";
                                break;
                            default:
                                thoiGianKetThuc = "08h15";
                        }

                        //tenMonHoc, maLop, thoiGianBatDau, thoiGianKetThu, hinhThuc, hocThu, phongHoc, tenGiangVien, thongTin;
                        Data.listMonHocHT1.add(new MonHoc(
                                elements.get(i + 1).text(),
                                elements.get(i + 2).text(),
                                thoiGianBatDau,
                                thoiGianKetThuc,
                                hinhThuc,
                                thu,
                                phong,
                                elements.get(i + 5).text(),
                                elements.get(i + 7).text()
                        ));


                    } else {

                        Elements eleTmp = elements.get(i + 7).select("*");
                        //tr > td
                        Log.e("a", eleTmp.size() + "");

                        Log.e("b", eleTmp.get(3).text());

                        Data.listMonHocHT2.add(new MonHoc(
                                elements.get(i + 1).text(),
                                elements.get(i + 2).text(),
                                thoiGianBatDau,
                                thoiGianKetThuc,
                                hinhThuc,
                                thu,
                                phong,
                                elements.get(i + 5).text(),
                                elements.get(i + 7).text()

                                ///"Tiết 10 ngày 2020-11-24, P. B5.08\nTiết 10 ngày 2020-12-08, P. B3.14\nTiết 10 ngày 2020-12-22, P. C308"
                        ));
                    }

                }


                Data.listNgayHocHT1 = new ArrayList<>();
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 2", new ArrayList<>()));
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 3", new ArrayList<>()));
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 4", new ArrayList<>()));
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 5", new ArrayList<>()));
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 6", new ArrayList<>()));
                Data.listNgayHocHT1.add(new NgayHoc("Thứ 7", new ArrayList<>()));

                for (MonHoc monHoc : Data.listMonHocHT1) {

                    switch (monHoc.getHocThu()) {
                        case "Thứ 2":

                            Data.listNgayHocHT1.get(0).addMonHoc(monHoc);
                            break;
                        case "Thứ 3":

                            Data.listNgayHocHT1.get(1).addMonHoc(monHoc);
                            break;
                        case "Thứ 4":

                            Data.listNgayHocHT1.get(2).addMonHoc(monHoc);
                            break;
                        case "Thứ 5":

                            Data.listNgayHocHT1.get(3).addMonHoc(monHoc);
                            break;
                        case "Thứ 6":

                            Data.listNgayHocHT1.get(4).addMonHoc(monHoc);
                            break;
                        case "Thứ 7":

                            Data.listNgayHocHT1.get(5).addMonHoc(monHoc);
                            break;
                        default:

                    }


                }

                for (int i = Data.listNgayHocHT1.size() - 1; i >= 0; i--) {
                    if (Data.listNgayHocHT1.get(i).getListMonHoc().size() == 0)
                        Data.listNgayHocHT1.remove(i);
                }


                return null;
            } catch (IOException | org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            nLoad++;

            Log.e("Load", " da load xong lich hoc " + nLoad);
            SwitchMainActivity();

            ExamLoad examLoad = new ExamLoad();
            examLoad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        String url;
        Bitmap bitmap;

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            url = urls[0];

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            Data.bitmapAvt = mIcon11;
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            nLoad++;

            Log.e("load   ", url + " " + nLoad);
            SwitchMainActivity();
        }


    }

    public class DeadlineLoad extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {


                Data.listDeadline = new ArrayList<>();

                String url = "https://courses.uit.edu.vn/login/index.php";
                Connection.Response tmp = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .execute();

                Document document = tmp.parse();
                Elements tokenElement = document.getElementsByAttributeValue("name", "logintoken");
                String token = tokenElement.attr("value");

                tmp = Jsoup.connect(url)
                        .method(Connection.Method.POST)
                        .cookies(tmp.cookies())
                        .data("username", LoginActivity.usr)
                        .data("password", LoginActivity.pas)
                        .data("logintoken", token)
                        .execute();
                Map<String, String> cookie = tmp.cookies();


                String url2 = "https://courses.uit.edu.vn/calendar/view.php?view=upcoming";
                //String url2 = "https://courses.uit.edu.vn/calendar/view.php?view=upcoming&time=1609434000";
                Connection.Response res = Jsoup.connect(url2)
                        .cookies(cookie)
                        .execute();
                Document doc = res.parse();

                Elements elements = doc.select("a.card-link");

                ArrayList<String> link = new ArrayList<>();

                for (Element element : elements) {
                    String temp = element.attr("href");
                    if (temp.contains("assign")) {
                        link.add(temp);
                    }
                }
                for (String item : link) {
                    Connection.Response response = Jsoup.connect(item)
                            .cookies(cookie)
                            .execute();
                    Document document1 = response.parse();
                    String[] subjectArr = document1.getElementsByTag("h1").text().split("-");
                    String subject = subjectArr[1];
                    String content = document1.getElementsByTag("h2").text();
                    Elements elements1 = document1.getElementsByTag("td");
                    String submitStatus = elements1.get(0).text().contains("No attempt") ? "Chưa nộp bài" : "Đã nộp bài";
                    String timeDeadline = elements1.get(2).text();
                    String timeRemain = elements1.get(3).text().contains("is overdue") ? "Hết hạn" : "Còn chờ";

                    Data.listDeadline.add(new Deadline(subject, content, timeDeadline, timeRemain, submitStatus, item));
                    Log.d("Subject", subject);
                }

                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            nLoad++;
            Log.e("load ", "da load xong deadline " + nLoad);
            SwitchMainActivity();
        }
    }

    public class HocPhiLoad extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            nLoad++;

            Log.e("Load", " da load xong hoc phi " + nLoad);
            SwitchMainActivity();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {


                String url = "https://daa.uit.edu.vn/tracuu/hocphi";
                Connection.Response res2 = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .execute();

                Document document = res2.parse();
                Elements body = document.select(".container-inline.form-wrapper");
                Log.e("hoc phi ", body.size() + "");

                Data.listHocPhi = new ArrayList<>();
                for (int i = 1; i < body.size(); i++) {
                    Element e = body.get(i);

                    String strHocKy = e.select(".fieldset-legend").get(0).text();
                    strHocKy = strHocKy.replace("Thông tin học phí học kỳ", "HK").replace("năm học ", "NH ");
                    ;

                    Log.e("hocKy", strHocKy);

                    Elements data = e.select("tbody > tr > td");
                    String soTienPhaiDong = "", soTienDaDong = "", trangThai = "", trangThaiChiTiet = "";
                    for (int j = 0; j + 2 < data.size(); j += 2) {

                        if (data.get(j).text().contains("Số tiền phải đóng")) {
                            soTienPhaiDong = data.get(j + 1).text();
                            Log.e("soTienPhaiDong", soTienPhaiDong);
                        }
                        if (data.get(j).text().contains("Số tiền đã đóng")) {

                            soTienDaDong = data.get(j + 1).text();
                            Log.e("soTienDaDong", soTienDaDong);
                        }
                        if (data.get(j).text().contains("Còn thừa") || data.get(j).text().contains("Còn nợ")) {
                            trangThai = data.get(j).text();
                            Log.e("trangThai", trangThai);
                            trangThaiChiTiet = data.get(j + 1).text();
                            if (trangThaiChiTiet.contains("Thanh toán HP tại đây"))
                                trangThaiChiTiet = trangThaiChiTiet.replace("Thanh toán HP tại đây", "");
                            Log.e("trangThaiChiTiet", trangThaiChiTiet);
                        }

                    }

                    Data.listHocPhi.add(new HocPhi(
                            strHocKy,
                            soTienPhaiDong,
                            soTienDaDong,
                            trangThai,
                            trangThaiChiTiet
                    ));


                }

                return "s";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}