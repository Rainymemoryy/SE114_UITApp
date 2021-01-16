package com.example.uit.lichhoc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.uit.R;
import com.example.uit.lichthi.MonThi;
import com.example.uit.lichthi.MonThiAdapter;

import java.util.List;


public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.MonHocViewHolder>  {

    List<MonHoc> listMonHoc;

    public void setData( List<MonHoc> listMonHoc){
        this.listMonHoc=listMonHoc;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monhoc, parent, false);
        return new MonHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocViewHolder holder, int position) {
        MonHoc monHoc = listMonHoc.get(position);
        if(monHoc==null)return;

        holder.tvTenGiangVien.setText("Gv. "+ monHoc.getTenGiangVien());
        holder.tvThoiGianBatDau.setText( monHoc.getThoiGianBatDau());
        holder.tvTenMonHoc.setText( monHoc.getTenMonHoc());
        holder.tvThongTin.setText( monHoc.getMaMonHoc()+ "  Tiết: "+ monHoc.getTietHoc()+ "  Phòng: " + monHoc.getPhongHoc());
    }

    @Override
    public int getItemCount() {
        if(listMonHoc==null) return 0;
        return listMonHoc.size();
    }

    public  class MonHocViewHolder extends RecyclerView.ViewHolder{

        TextView tvTenGiangVien, tvThoiGianBatDau,tvThongTin, tvTenMonHoc;

        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenGiangVien=itemView.findViewById(R.id.tv_tenGiangVien);
            tvThoiGianBatDau=itemView.findViewById(R.id.tv_thoiGianBatDauMonHoc);
            tvTenMonHoc=itemView.findViewById(R.id.tv_tenMonHoc);
            tvThongTin=itemView.findViewById(R.id.tv_thongTin);
        }
    }
}
