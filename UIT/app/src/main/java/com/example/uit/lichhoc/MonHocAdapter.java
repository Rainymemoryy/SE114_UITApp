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


public class MonHocAdapter extends RecyclerView.Adapter<MonHocAdapter.MonHocViewHolder> {

    List<MonHoc> listMonHoc;

    public void setData(List<MonHoc> listMonHoc) {
        this.listMonHoc = listMonHoc;
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
        if (monHoc == null) return;

        holder.tvThoiGian.setText(monHoc.getThoiGianBatDau()+" - "+ monHoc.getThoiGianKetThu());
        holder.tvPhongHoc.setText(monHoc.getPhongHoc());
        holder.tvMaLop.setText(monHoc.getMaLop());
        holder.tvTenMonHoc.setText(monHoc.getTenMonHoc());
    }

    @Override
    public int getItemCount() {
        if (listMonHoc == null) return 0;
        return listMonHoc.size();
    }

    public class MonHocViewHolder extends RecyclerView.ViewHolder {

        TextView tvThoiGian, tvMaLop, tvPhongHoc, tvTenMonHoc;

        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThoiGian = itemView.findViewById(R.id.tv_monhoc_thoigian);
            tvMaLop = itemView.findViewById(R.id.tv_monhoc_maLop);
            tvPhongHoc = itemView.findViewById(R.id.tv_monhoc_phonghoc);
            tvTenMonHoc = itemView.findViewById(R.id.tv_monhoc_tenmonhoc);
        }
    }
}
