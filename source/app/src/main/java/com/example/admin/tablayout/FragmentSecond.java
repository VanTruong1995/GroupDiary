package com.example.admin.tablayout;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.tablayout.Data.DataMember;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentSecond extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    Dialog dialog;
    ImageView ivAddMember;
    ArrayList<DataMember> list = new ArrayList<>();
    MemberAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.second_fragment, container, false);

        getData();

        recyclerView = rootView.findViewById(R.id.recyclerview);
        ivAddMember = rootView.findViewById(R.id.iv_add_member);

        adapter = new MemberAdapter(getContext(),list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        ivAddMember.setOnClickListener(new View.OnClickListener() {
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
        list.addAll(MainActivity.groupSQLite.getAllMember());
        adapter.notifyDataSetChanged();

    }

    private void getData(){
        list.clear();
        list.addAll(MainActivity.groupSQLite.getAllMember());
    }

    private void setDialog (){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_member);
        dialog.show();

        final EditText edtName,edtPhone, edtAddress;
        Button btnAdd, btnCancel;

        edtName = dialog.findViewById(R.id.edt_name);
        edtPhone = dialog.findViewById(R.id.edt_phone);
        edtAddress = dialog.findViewById(R.id.edt_address);
        btnAdd = dialog.findViewById(R.id.btn_add);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                Calendar cal = Calendar.getInstance();
                long date = cal.getTimeInMillis();

                MainActivity.groupSQLite.addNewMember(name,phone,address,"",date);


                list.clear();
                list.addAll(MainActivity.groupSQLite.getAllMember());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                ((MainActivity) getActivity()).fragmentFirst.getUpdateData();
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
