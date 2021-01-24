package com.example.uit;


import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.uit.deadline.Deadline;
import com.example.uit.diem.BangDiem;
import com.example.uit.hocphi.HocPhi;
import com.example.uit.lichhoc.MonHoc;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.NgayThi;
import com.example.uit.sinhvien.SinhVien;
import com.example.uit.thongbao.ThongBao;

import java.util.List;

public class Data {
    public static List<NgayThi> listNgayThi = null;
    public static List<MonThi> listMonThi = null;
    public static SinhVien sinhVien = null;
    public static List<ThongBao> listThongBao = null;
    public static List<BangDiem> listBangDiem = null;
    public static String thoiGian = "";


    public static List<MonHoc> listMonHocHT1 = null;
    public static List<MonHoc> listMonHocHT2 = null;
    public static List<NgayHoc> listNgayHocHT1 = null;

    public static List<MonThi> listLichThiHomNay = null;

    public static String soTinChiDaHoc = "", soTinChiTichLuy = "", diemTrungBinh = "";

    public static Bitmap bitmapAvt;
    public static String linkImg;

    public static List<Deadline> listDeadline;
    public static int idNotify = 142153;

    public static List<HocPhi> listHocPhi;


}
