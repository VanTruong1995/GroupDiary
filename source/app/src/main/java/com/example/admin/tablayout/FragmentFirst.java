package com.example.admin.tablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataEvents;
import com.example.admin.tablayout.Data.DataMember;

import java.util.ArrayList;

public class FragmentFirst extends Fragment {

    RecyclerView recyclerViewMember, recyclerViewEvent;
    View rootView;
    TextView txtTotalMember, txtTotalEvent;
    ArrayList<DataMember> listMember = new ArrayList<>();
    ArrayList<DataEvents> listEvent = new ArrayList<>();
    HomeEventAdapter adapterEvent;
    HomeMemberAdapter adapterMember;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.first_fragment, container, false);

        getData();
        int totalMember = MainActivity.groupSQLite.getTotalMember();
        int totalEvent = MainActivity.groupSQLite.getTotalEvent();

        recyclerViewMember   = rootView.findViewById(R.id.rv_member);
        recyclerViewEvent    = rootView.findViewById(R.id.rv_event);
        txtTotalMember = rootView.findViewById(R.id.txt_total_member);
        txtTotalEvent = rootView.findViewById(R.id.txt_total_event);

        txtTotalMember.setText("Tổng thành viên: " + totalMember + " thành viên");
        txtTotalEvent.setText("Tổng sự kiện: " + totalEvent + " sự kiện");

        adapterEvent = new HomeEventAdapter(getContext(),listEvent);
        adapterMember = new HomeMemberAdapter(getContext(),listMember);

        LinearLayoutManager linearEvent = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearMember = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewEvent.setLayoutManager(linearEvent);
        recyclerViewEvent.setAdapter(adapterEvent);

        recyclerViewMember.setLayoutManager(linearMember);
        recyclerViewMember.setAdapter(adapterMember);

        return rootView;
    }

    private void getData(){
        listEvent.clear();
        listEvent.addAll(MainActivity.groupSQLite.get5NewEvent());
        listMember.clear();
        listMember.addAll(MainActivity.groupSQLite.get5NewMember());
    }

    public void getUpdateData(){
        getData();
        adapterEvent.notifyDataSetChanged();
        adapterMember.notifyDataSetChanged();
    }
}
