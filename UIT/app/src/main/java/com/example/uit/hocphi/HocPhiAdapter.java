package com.example.uit.hocphi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichhoc.MonHoc;

import java.util.List;


public class HocPhiAdapter extends RecyclerView.Adapter<HocPhiAdapter.HocPhiVewHolder> {

    List<HocPhi> listHocPhi;

    public void setData(List<HocPhi> listHocPhi) {
        this.listHocPhi = listHocPhi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HocPhiVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hocphi, parent, false);
        return new HocPhiVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HocPhiVewHolder holder, int position) {
        HocPhi hocPhi = listHocPhi.get(position);
        if (hocPhi == null) return;

        holder.tvHocKy.setText(hocPhi.getHocKy());
        holder.tvTrangThai.setText(hocPhi.getTrangThai());
        holder.tvTrangThaiChiTiet.setText(hocPhi.getChiTietTrangThai());
        holder.tvSoTienPhaiDong.setText(hocPhi.getSoTienPhaiDong());
        holder.tvSoTienDaDong.setText(hocPhi.getSoTienDaDong());
    }

    @Override
    public int getItemCount() {
        if (listHocPhi == null) return 0;
        return listHocPhi.size();
    }

    public class HocPhiVewHolder extends RecyclerView.ViewHolder {

        TextView tvHocKy, tvTrangThai,tvTrangThaiChiTiet, tvSoTienPhaiDong, tvSoTienDaDong;

        public HocPhiVewHolder(@NonNull View itemView) {
            super(itemView);
            tvHocKy = itemView.findViewById(R.id.tv_hocphi_hocky);
            tvTrangThai = itemView.findViewById(R.id.tv_hocphi_trangthai);
            tvTrangThaiChiTiet = itemView.findViewById(R.id.tv_hocphi_chitiettrangthai);
            tvSoTienPhaiDong = itemView.findViewById(R.id.tv_hocphi_sotienphaidong);
            tvSoTienDaDong = itemView.findViewById(R.id.tv_hocphi_sotiendadong);

        }
    }
}
