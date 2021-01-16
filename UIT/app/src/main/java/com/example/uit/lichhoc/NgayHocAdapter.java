package com.example.uit.lichhoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichthi.MonThiAdapter;
import com.example.uit.lichthi.NgayThi;

import java.util.List;

public class NgayHocAdapter extends RecyclerView.Adapter<NgayHocAdapter.NgayHocViewHolder> {

    private Context mContext;
    private List<NgayHoc> listNgayHoc;

    public void setData(List<NgayHoc> listNgayHoc){
        this.listNgayHoc=listNgayHoc;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NgayHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngayhoc, parent, false);
        return new NgayHocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayHocViewHolder holder, int position) {
        NgayHoc ngayHoc =  listNgayHoc.get(position);
        if(ngayHoc==null) return;

        holder.tvNgayHoc.setText(ngayHoc.getNgayHoc());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        holder.rcvMonHoc.setLayoutManager(linearLayoutManager);

        MonHocAdapter monHocAdapter = new MonHocAdapter();
        monHocAdapter.setData(ngayHoc.getListMonHoc());

        holder.rcvMonHoc.setAdapter(monHocAdapter);


    }

    @Override
    public int getItemCount() {
        if(listNgayHoc!=null) return listNgayHoc.size();
        return 0;
    }

    public class NgayHocViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNgayHoc;
        private RecyclerView rcvMonHoc;

        public NgayHocViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNgayHoc = itemView.findViewById(R.id.tv_ngayhoc);
            rcvMonHoc = itemView.findViewById(R.id.rcv_monhoc);
        }
    }

}
