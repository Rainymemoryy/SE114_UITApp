package com.example.uit.diem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichhoc.MonHocAdapter;

import java.util.List;

public class DiemThiAdapter extends RecyclerView.Adapter<DiemThiAdapter.DiemThiViewHolder>{

    List<DiemThi> listDiemThi;

    public void setData(List<DiemThi> listDiemThi){
        this.listDiemThi =listDiemThi;
    }

    @NonNull
    @Override
    public DiemThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diem, parent, false);
        return new DiemThiAdapter.DiemThiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemThiViewHolder holder, int position) {
        DiemThi diemThi = listDiemThi.get(position);
        if(diemThi==null)return;

        holder.tvMaMonHoc.setText(diemThi.getMaMonHoc());
        holder.tvTinChi.setText(diemThi.gettC());
        holder.giuaKy.setText(diemThi.getgK());
        holder.cuoiKy.setText(diemThi.getcK());
        holder.trungBinh.setText(diemThi.gettB());
        holder.quaTrinh.setText(diemThi.getqT());
        holder.thucHanh.setText(diemThi.gettH());
    }

    @Override
    public int getItemCount() {
        if(listDiemThi==null)return 0;
        return listDiemThi.size();
    }

    public class DiemThiViewHolder  extends RecyclerView.ViewHolder{
        TextView tvMaMonHoc, tvTinChi, quaTrinh, giuaKy, cuoiKy, trungBinh, thucHanh;

        public DiemThiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaMonHoc = itemView.findViewById(R.id.tv_diem_mamonhoc);
            tvTinChi = itemView.findViewById(R.id.tv_diem_diem_tinchi);
            giuaKy = itemView.findViewById(R.id.tv_diem_diem_gk);
            cuoiKy = itemView.findViewById(R.id.tv_diem_diem_ck);
            trungBinh = itemView.findViewById(R.id.tv_diem_diem_tb);
            quaTrinh = itemView.findViewById(R.id.tv_diem_diem_qt);
            thucHanh = itemView.findViewById(R.id.tv_diem_diem_th);

        }
    }

}
