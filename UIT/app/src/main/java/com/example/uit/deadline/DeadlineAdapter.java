package com.example.uit.deadline;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.R;
import com.example.uit.lichthi.MonThi;
import com.example.uit.thongbao.ThongBao;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class DeadlineAdapter extends RecyclerView.Adapter<DeadlineAdapter.DeadlineViewHolder> {

    List<Deadline> listDeadline;
    Context mContext = null;

    public void setData(List<Deadline> listDeadline) {
        this.listDeadline = listDeadline;
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public DeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deadline, parent, false);
        mContext = parent.getContext();
        return new DeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineViewHolder holder, int position) {
        Deadline deadline = listDeadline.get(position);
        if (deadline == null) return;

        holder.tvSubject.setText(deadline.getSubject());
        holder.tvContent.setText(deadline.getContext());
        holder.tvTime.setText(deadline.getTimeDeadline());
        holder.tvState.setText(deadline.getTimeRemain());
        holder.tvSubmit.setText(deadline.getSubmitStatus());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, deadline.getUrl(), Toast.LENGTH_SHORT).show();
//                String url = deadline.getUrl();
//                Uri uri = Uri.parse(url);
//                Intent intent = new Intent();
//                intent.setData(uri);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(deadline.getUrl()));
                mContext.startActivity(browserIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (listDeadline == null) return 0;
        return listDeadline.size();
    }

    public class DeadlineViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubject, tvContent, tvTime, tvState, tvSubmit;
        Button btnView;

        public DeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tv_deadline_subject);
            tvContent = itemView.findViewById(R.id.tv_deadline_content);
            tvTime = itemView.findViewById(R.id.tv_deadline_time);
            tvState = itemView.findViewById(R.id.tv_deadline_state);
            tvSubmit = itemView.findViewById(R.id.tv_deadline_submit);

            btnView = itemView.findViewById(R.id.btn_deadline_btn_view);
        }
    }

}
