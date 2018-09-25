package com.example.admin.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataMember;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DialogMemberAdapter extends RecyclerView.Adapter<DialogMemberAdapter.MemberViewHoler>{

    Context context;
    ArrayList<DataMember> list;
    int idEvent;
    UpdateDataEventAct updateDataEventAct;

    public DialogMemberAdapter(Context context, ArrayList<DataMember> list, int idEvent) {
        this.context = context;
        this.list = list;
        this.idEvent = idEvent;
        updateDataEventAct = (UpdateDataEventAct) context;
    }

    @NonNull
    @Override
    public MemberViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_dialog_member, viewGroup, false);
        return new MemberViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHoler memberViewHoler, final int i) {
        final DataMember dataMember = list.get(i);
        memberViewHoler.txtName.setText(dataMember.getNameMenber());
        memberViewHoler.txtPhone.setText(dataMember.getPhoneMember());
        memberViewHoler.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.groupSQLite.addMemberJoinEvent(idEvent,dataMember.getIdMember());
                list.remove(i);
                updateDataEventAct.updateData();
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MemberViewHoler extends RecyclerView.ViewHolder{

        TextView txtName, txtPhone;
        CircleImageView circleImageView;
        CardView cardView;

        public MemberViewHoler(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name_member);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            circleImageView = itemView.findViewById(R.id.circle_imageview);
            cardView = itemView.findViewById(R.id.cardview);
        }

    }
}
