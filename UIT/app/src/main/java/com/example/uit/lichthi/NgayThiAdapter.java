package com.example.uit.lichthi;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;

import java.util.List;

public class NgayThiAdapter extends RecyclerView.Adapter<NgayThiAdapter.NgayThiViewHolder> {

    private Context mContext;
    private List<NgayThi> listNgayThi;

    public void setData(List<NgayThi> listNgayThi){
        this.listNgayThi=listNgayThi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NgayThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaythi, parent, false);
        return new NgayThiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayThiViewHolder holder, int position) {
        NgayThi ngayThi =  listNgayThi.get(position);
        if(ngayThi==null) return;

        holder.tvNgayThi.setText(ngayThi.getStrNgayThi());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rcvMonThi.setLayoutManager(linearLayoutManager);

        MonThiAdapter monThiAdapter = new MonThiAdapter();
        monThiAdapter.setData(ngayThi.getListMonThi());

        holder.rcvMonThi.setAdapter(monThiAdapter);


    }

    @Override
    public int getItemCount() {
        if(listNgayThi!=null) return listNgayThi.size();
        return 0;
    }

    public class NgayThiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNgayThi;
        private RecyclerView rcvMonThi;

        public NgayThiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNgayThi = itemView.findViewById(R.id.tv_ngaythi);
            rcvMonThi = itemView.findViewById(R.id.rcv_monthi);
        }
    }

}
