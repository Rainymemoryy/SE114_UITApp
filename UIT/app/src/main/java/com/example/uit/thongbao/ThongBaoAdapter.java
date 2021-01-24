package com.example.uit.thongbao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichthi.NgayThiAdapter;

import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ThongBaoViewHolder> {

    List<ThongBao> listThongBao;

    public void setData(List<ThongBao> listThongBao) {
        this.listThongBao = listThongBao;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongbao, parent, false);
        return new ThongBaoAdapter.ThongBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        ThongBao thongBao = listThongBao.get(position);
        if(thongBao==null)return;

        holder.tvTitle.setText(thongBao.getTitle());
        holder.tvContent.setText("Vào thứ " + thongBao.getThu() + "| Từ tiết " + thongBao.getTietBD() + " đến tiết " + thongBao.getTietKT() + " | Phòng :" + thongBao.getPhong());
        holder.tvTime.setText(thongBao.getCreateTime());

    }

    @Override
    public int getItemCount() {
        if (listThongBao == null) return 0;
        return listThongBao.size();

    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvTime;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_thongbao_title);
            tvContent = itemView.findViewById(R.id.tv_thongbao_content);
            tvTime = itemView.findViewById(R.id.tv_thongbao_time);
        }
    }
}
