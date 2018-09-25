package com.example.admin.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataExchange;
import com.example.admin.tablayout.Data.DataMember;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventActMemberAdapter extends RecyclerView.Adapter<EventActMemberAdapter.MemberViewHolder> {

    Context context;
    ArrayList<DataMember> list;
    int idEvent;
    UpdateDataEventAct updateDataEventAct;

    public EventActMemberAdapter(Context context, ArrayList<DataMember> list, int idEvent) {
        this.context = context;
        this.list = list;
        this.idEvent = idEvent;
        updateDataEventAct = (UpdateDataEventAct) context;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_act_member, viewGroup, false);
        return new MemberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, final int i) {
        final DataMember dataMember = list.get(i);
        memberViewHolder.txtName.setText(dataMember.getNameMenber());
        memberViewHolder.txtPhone.setText(dataMember.getPhoneMember());
        memberViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.groupSQLite.deleteMemberJoinedEvent(idEvent,dataMember.getIdMember());
                updateDataEventAct.updateData();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MemberViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPhone;
        ImageView ivDelete;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name_member);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }
}
