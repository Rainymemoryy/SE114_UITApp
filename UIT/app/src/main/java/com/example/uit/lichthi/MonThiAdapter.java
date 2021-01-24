package com.example.uit.lichthi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;

import java.util.List;

public class MonThiAdapter extends RecyclerView.Adapter<MonThiAdapter.MonThiViewHolder> {

    private List<MonThi> listMonThi;

    public void setData(List<MonThi> listMonThi) {
        this.listMonThi = listMonThi;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MonThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monthi, parent, false);
        return new MonThiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonThiViewHolder holder, int position) {
        MonThi monThi = listMonThi.get(position);
        if (monThi == null) return;


        holder.maMonThi.setText(monThi.getMaLop());
        holder.phongThi.setText(monThi.getPhongThi());
        holder.tenMonThi.setText(monThi.getTenMonThi());
        String tmp = "";
        switch (monThi.getCaThi()) {
            case "1":
                tmp = "07h30";
                break;
            case "2":
                tmp = "09h30";
                break;
            case "3":
                tmp = "13h30";
                break;
            default:
                tmp = "15H30";
                break;
        }
        holder.thoiGianThi.setText(tmp);

    }

    @Override
    public int getItemCount() {
        if (listMonThi == null) return 0;
        return listMonThi.size();
    }

    public class MonThiViewHolder extends RecyclerView.ViewHolder {

        private TextView maMonThi;
        private TextView thoiGianThi;
        private TextView phongThi;
        private TextView tenMonThi;

        public MonThiViewHolder(@NonNull View itemView) {
            super(itemView);

            maMonThi = itemView.findViewById(R.id.tv_monthi_mamonthi);
            thoiGianThi = itemView.findViewById(R.id.tv_monthi_thoigian);
            phongThi = itemView.findViewById(R.id.tv_monthi_phongthi);
            tenMonThi = itemView.findViewById(R.id.tv_monthi_tenmonthi);
        }
    }
}
