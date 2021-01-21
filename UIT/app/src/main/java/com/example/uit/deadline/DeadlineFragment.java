package com.example.uit.deadline;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uit.Data;
import com.example.uit.R;

import java.util.ArrayList;
import java.util.List;

public class DeadlineFragment extends Fragment {


    private RecyclerView rcvDeadline;
    private DeadlineAdapter deadlineAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_deadline, container, false);

        rcvDeadline = root.findViewById(R.id.rcv_deadline);
        deadlineAdapter = new DeadlineAdapter();
        rcvDeadline.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false));

        deadlineAdapter.setData(getListDeadline());
        rcvDeadline.setAdapter(deadlineAdapter);


        this.getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getActivity().getWindow().setStatusBarColor(Color.parseColor("#f5f5f5"));


        this.getActivity().findViewById(R.id.toolbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setBackgroundColor(Color.parseColor("#f5f5f5"));
        this.getActivity().findViewById(R.id.appbar).setOutlineSpotShadowColor(Color.parseColor("#f5f5f5"));


        return root;
    }

    private List<Deadline> getListDeadline() {
//        Data.listDeadline = new ArrayList<>();
//
//        Data.listDeadline.add(new Deadline(
//                "SE114.L11",
//                "Bài tập bổ sung",
//                "07/01/2021 00:00:00",
//                "Hết hạn",
//                "Chưa nộp bài",
//                "https://www.youtube.com/watch?v=CdfSbH4Ytm4"
//        ));
//
//
//        Data.listDeadline.add(new Deadline(
//                "SE114.L11",
//                "Nộp đồ án môn học",
//                "07/01/2021 00:00:00",
//                "Chưa nộp bài",
//                "Chưa nộp bài",
//                "https://www.youtube.com/watch?v=CdfSbH4Ytm4"
//        ));


        return Data.listDeadline;
    }


}