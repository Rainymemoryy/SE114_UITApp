package com.example.uit.lichhoc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;

import java.util.List;


public class MonHocHinhThuc2Adapter extends RecyclerView.Adapter<MonHocHinhThuc2Adapter.MonHocViewHolder> {

    List<MonHoc> listMonHoc;

    public void setData(List<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monhoc_hinhthuc2, parent, false);
        return new MonHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonHocViewHolder holder, int position) {
        MonHoc monHoc = listMonHoc.get(position);
        if (monHoc == null) return;

        holder.tvTenGiangVien.setText(monHoc.getTenGiangVien());
        holder.tvMaLop.setText(monHoc.getMaLop());
        holder.tvThongTin.setText(monHoc.getThongTin());
        holder.tvTenMocHoc.setText(monHoc.getTenMonHoc());

    }

    @Override
    public int getItemCount() {
        if (listMonHoc == null) return 0;
        return listMonHoc.size();
    }

    public class MonHocViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenGiangVien, tvMaLop, tvThongTin, tvTenMocHoc;

        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenGiangVien = itemView.findViewById(R.id.tv_hinhthuc2_tengiangvien);
            tvMaLop = itemView.findViewById(R.id.tv_hinhthuc2_malop);
            tvThongTin = itemView.findViewById(R.id.tv_hinhthuc2_thongtin);
            tvTenMocHoc = itemView.findViewById(R.id.tv_hinhthuc2_tenmonhoc);
        }
    }
}
