package com.example.admin.tablayout;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataEvents;
import com.example.admin.tablayout.Data.DataMember;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MemberActivity extends AppCompatActivity {

    TextView txtName, txtPhone, txtAddress, txtTotalEvent, txtDate, txtTotalContribute;
    ImageView ivEditMember;
    int idMember;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<DataEvents> list = new ArrayList<>();
    MemberActEventAdapter adapter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        idMember = getIntent().getIntExtra("dataMember", 0);
        getData();

        DataMember dataMember = MainActivity.groupSQLite.getAMemberInTableMember(idMember);
        int totalEvent = MainActivity.groupSQLite.getTotalEventMemberJoined(idMember);
        int totalContribute = MainActivity.groupSQLite.totalContributeOfMember(idMember);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataMember.getDateMember());

        txtName = findViewById(R.id.txt_name);
        txtPhone = findViewById(R.id.txt_phone);
        txtAddress = findViewById(R.id.txt_address);
        txtDate = findViewById(R.id.txt_date);
        txtTotalEvent = findViewById(R.id.txt_total_event);
        txtTotalContribute = findViewById(R.id.txt_total_contribute);
        recyclerView = findViewById(R.id.recyclerview);
        ivEditMember = findViewById(R.id.iv_edit_member);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtName.setText(dataMember.getNameMenber());
        txtAddress.setText("Địa chỉ: " + dataMember.getAddressMember());
        txtPhone.setText("Số điện thoại: " + dataMember.getPhoneMember());
        txtDate.setText("Ngày tham gia: " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        txtTotalEvent.setText("Tổng số sự kiện đã tham gia: " + totalEvent + " sự kiện");
        txtTotalContribute.setText("Đã đóng góp cho nhóm: " + decimalFormat.format(totalContribute) + " đ");

        adapter = new MemberActEventAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        ivEditMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialogEdit();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void callDialogEdit() {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_member);
        dialog.show();

        final EditText edtName, edtPhone, edtAddress;
        Button btnEdit, btnDelete;

        edtName = dialog.findViewById(R.id.edt_name);
        edtAddress = dialog.findViewById(R.id.edt_address);
        edtPhone = dialog.findViewById(R.id.edt_phone);
        btnEdit = dialog.findViewById(R.id.btn_edit);
        btnDelete = dialog.findViewById(R.id.btn_delete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();

                Calendar cal = Calendar.getInstance();
                long date = cal.getTimeInMillis();

                MainActivity.groupSQLite.updateMember(idMember, name, phone, address, "",date);

                txtName.setText(name);
                txtAddress.setText("Địa chỉ: " + address);
                txtPhone.setText("Số điện thoại: " + phone);
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.groupSQLite.deleteMember(idMember);
                finish();
            }
        });

    }

    private void getData() {
        list.clear();
        list.addAll(MainActivity.groupSQLite.getEventMemberJoined(idMember));
    }

}
