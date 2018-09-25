package com.example.admin.tablayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.tablayout.Data.DataEvents;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentThird extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    Dialog dialog;
    ImageView ivAddEvent;
    EventAdapter adapter;
    ArrayList<DataEvents> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.third_fragment, container, false);

        getData();

        recyclerView = rootView.findViewById(R.id.recyclerview);
        ivAddEvent = rootView.findViewById(R.id.iv_add_event);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new EventAdapter(getContext(),list);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        ivAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(MainActivity.groupSQLite.getAllEvent());
        adapter.notifyDataSetChanged();
    }

    private void getData(){
        list.clear();
        list.addAll(MainActivity.groupSQLite.getAllEvent());
    }

    private void setDialog (){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_event);
        dialog.show();

        final EditText edtName, edtAddress;
        Button btnAdd, btnCancel;

        edtName = dialog.findViewById(R.id.edt_name);
        edtAddress = dialog.findViewById(R.id.edt_address);
        btnAdd = dialog.findViewById(R.id.btn_add);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();
                Calendar cal = Calendar.getInstance();
                long date = cal.getTimeInMillis();

                MainActivity.groupSQLite.addNewEvent(name, address, 0, date);

                list.clear();
                list.addAll(MainActivity.groupSQLite.getAllEvent());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
