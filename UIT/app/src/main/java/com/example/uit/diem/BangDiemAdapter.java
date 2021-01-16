package com.example.uit.diem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichhoc.MonHocAdapter;
import com.example.uit.lichhoc.NgayHoc;
import com.example.uit.lichhoc.NgayHocAdapter;

import java.util.List;

public class BangDiemAdapter extends RecyclerView.Adapter<BangDiemAdapter.BangDiemViewHolder> {

    List<BangDiem> listBangDiem;
    private Context mContext;

    public void setData(List<BangDiem> listBangDiem) {
        this.listBangDiem = listBangDiem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BangDiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bangdiem, parent, false);
        return new BangDiemAdapter.BangDiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangDiemViewHolder holder, int position) {
        BangDiem bangDiem =  listBangDiem.get(position);
        if(bangDiem==null) return;

        holder.tvThongTin.setText(bangDiem.getThongTinBangDiem());

        holder.tvTongSoTinChi.setText(bangDiem.getTongSoTinChi());
        holder.tvDiemTrungBinh.setText(bangDiem.getDiemTrungBinh());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rcvDiem.setLayoutManager(linearLayoutManager);

        DiemThiAdapter diemThiAdapter = new DiemThiAdapter();
        diemThiAdapter.setData(bangDiem.getListDiemThi());

        holder.rcvDiem.setAdapter(diemThiAdapter);
    }

    @Override
    public int getItemCount() {
        if (listBangDiem == null) return 0;
        return listBangDiem.size();
    }

    public class BangDiemViewHolder extends RecyclerView.ViewHolder {
        TextView tvThongTin;
        RecyclerView rcvDiem;

        TextView tvTongSoTinChi, tvDiemTrungBinh;

        public BangDiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThongTin = itemView.findViewById(R.id.tv_bangdiem_thongtin);
            rcvDiem = itemView.findViewById(R.id.rcv_bangdiem_diem);


            tvTongSoTinChi = itemView.findViewById(R.id.tv_bangdiem_tongket_tc);
            tvDiemTrungBinh = itemView.findViewById(R.id.tv_bangdiem_tongket_tb);
        }
    }
}
