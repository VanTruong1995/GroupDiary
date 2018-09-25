package com.example.admin.tablayout;

import android.Manifest;
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
import com.example.admin.tablayout.Data.DataExchange;
import com.example.admin.tablayout.Data.DataMember;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EventActivity extends AppCompatActivity implements UpdateDataEventAct {

    ImageView ivEditEvent, ivAddMember, ivAddExchange;
    TextView txtName, txtAddress, txtDate, txtTotalMember, txtTotalContribute;
    Dialog dialogAddMember, dialogAddExchange, dialogEditEvent;
    Toolbar toolbar;
    int idEvent;
    DataEvents dataEvents;
    RecyclerView rvExchange, rvMember;
    EventActExchangeAdapter adapterExchange;
    EventActMemberAdapter adapterMember;
    ArrayList<DataMember> listMember = new ArrayList<>();
    ArrayList<DataExchange> listExchange = new ArrayList<>();
    DecimalFormat decimalFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        decimalFormat = new DecimalFormat("#,###");

        idEvent = getIntent().getIntExtra("dataEvent",0);
        getDataExchange();
        getDataMember();
        dataEvents = MainActivity.groupSQLite.getAEventInTableEvents(idEvent);
        int totalMember = MainActivity.groupSQLite.getTotalMemberJoinEvent(idEvent);
        int totalContribute = MainActivity.groupSQLite.getTotalMoneyExchangeInEvent(idEvent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataEvents.getDateEvent());

        ivEditEvent = findViewById(R.id.iv_edit_event);
        ivAddExchange = findViewById(R.id.iv_add_exchange);
        ivAddMember = findViewById(R.id.iv_add_member);
        txtName = findViewById(R.id.txt_name);
        txtAddress = findViewById(R.id.txt_address);
        txtDate = findViewById(R.id.txt_date);
        txtTotalMember = findViewById(R.id.txt_total_member);
        txtTotalContribute = findViewById(R.id.txt_total_contribute);
        rvExchange = findViewById(R.id.rv_exchange);
        rvMember = findViewById(R.id.rv_member);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        txtName.setText(dataEvents.getNameEvent());
        txtAddress.setText("Địa chỉ: " + dataEvents.getAddressEvent());
        txtDate.setText("Ngày tổ chức : " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        txtTotalMember.setText("Tổng số thành viên đã tham gia: " + totalMember + " người");
        txtTotalContribute.setText("Tổng đóng góp : " + decimalFormat.format(totalContribute) + " đ");

        adapterExchange = new EventActExchangeAdapter(this, listExchange);
        adapterMember = new EventActMemberAdapter(this, listMember, idEvent);


        LinearLayoutManager linear1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linear2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvExchange.setLayoutManager(linear1);
        rvExchange.setAdapter(adapterExchange);

        rvMember.setLayoutManager(linear2);
        rvMember.setAdapter(adapterMember);


        ivEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogEditEvent();
            }
        });
        ivAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogAddMember();
            }
        });
        ivAddExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogAddExchange();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getDataExchange(){
        listExchange.clear();
        listExchange.addAll(MainActivity.groupSQLite.getAllExchange(idEvent));
    }

    private void getDataMember(){
        listMember.clear();
        listMember.addAll(MainActivity.groupSQLite.getMemberJoinedEvent(idEvent));
    }

    private  void setDialogAddMember(){
        dialogAddMember = new Dialog(this);
        dialogAddMember.setContentView(R.layout.dialog_add_member_join);
        dialogAddMember.show();

        ArrayList<DataMember> list = new ArrayList<>();
        RecyclerView recyclerView;
        Button btnCancel;
        recyclerView = dialogAddMember.findViewById(R.id.recyclerview);
        btnCancel = dialogAddMember.findViewById(R.id.btn_cancel);

        list.addAll(MainActivity.groupSQLite.getMemberNotJoin(idEvent));
        DialogMemberAdapter adapter = new DialogMemberAdapter(this, list,idEvent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddMember.dismiss();
            }
        });
    }

    private void setDialogAddExchange(){
        dialogAddExchange = new Dialog(this);
        dialogAddExchange.setContentView(R.layout.dialog_add_exchange);
        dialogAddExchange.show();

        final EditText edtName, edtAmount, edtMoney;
        Button btnAdd, btnCancel;

        edtName = dialogAddExchange.findViewById(R.id.edt_name);
        edtAmount = dialogAddExchange.findViewById(R.id.edt_amount);
        edtMoney = dialogAddExchange.findViewById(R.id.edt_money);
        btnAdd = dialogAddExchange.findViewById(R.id.btn_add);
        btnCancel = dialogAddExchange.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                int amount = Integer.parseInt(edtAmount.getText().toString());
                int money = Integer.parseInt(edtMoney.getText().toString());
                MainActivity.groupSQLite.addNewExchange(idEvent,name,amount,money);

                int totalContribute = MainActivity.groupSQLite.getTotalMoneyExchangeInEvent(idEvent);
                txtTotalContribute.setText("Tổng đóng góp : " + decimalFormat.format(totalContribute) + " đ");

                listExchange.clear();
                listExchange.addAll(MainActivity.groupSQLite.getAllExchange(idEvent));
                adapterExchange.notifyDataSetChanged();
                dialogAddExchange.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddExchange.dismiss();
            }
        });

    }

    private void setDialogEditEvent () {
        dialogEditEvent = new Dialog(this);
        dialogEditEvent.setContentView(R.layout.dialog_edit_event);
        dialogEditEvent.show();

        final EditText edtName, edtAddress;
        Button btnEdit, btnDelete;

        edtName = dialogEditEvent.findViewById(R.id.edt_name);
        edtAddress = dialogEditEvent.findViewById(R.id.edt_address);
        btnEdit = dialogEditEvent.findViewById(R.id.btn_edit);
        btnDelete = dialogEditEvent.findViewById(R.id.btn_delete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();

                Calendar cal = Calendar.getInstance();
                long date = cal.getTimeInMillis();

                txtName.setText(name);
                txtAddress.setText("Địa chỉ: " + address);
                MainActivity.groupSQLite.updateEvent(idEvent,name,address,0,date);
                dialogEditEvent.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.groupSQLite.deleteEvent(idEvent);
                finish();
            }
        });


    }

    @Override
    public void updateData() {
        int totalMember = MainActivity.groupSQLite.getTotalMemberJoinEvent(idEvent);
        txtTotalMember.setText("Tổng số thành viên đã tham gia: " + totalMember + " người");
        int totalContribute = MainActivity.groupSQLite.getTotalMoneyExchangeInEvent(idEvent);
        txtTotalContribute.setText("Tổng đóng góp : " + decimalFormat.format(totalContribute) + " đ");

        listMember.clear();
        listMember.addAll(MainActivity.groupSQLite.getMemberJoinedEvent(idEvent));
        adapterMember.notifyDataSetChanged();


    }

}
