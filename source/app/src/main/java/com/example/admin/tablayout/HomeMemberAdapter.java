package com.example.admin.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataMember;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeMemberAdapter extends RecyclerView.Adapter<HomeMemberAdapter.HomeMemberViewHoler>{

    Context context;
    ArrayList<DataMember> list;

    public HomeMemberAdapter(Context context, ArrayList<DataMember> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeMemberViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_home_member, viewGroup, false);

        return new HomeMemberViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMemberViewHoler homeMemberViewHoler, int i) {
        DataMember dataMember = list.get(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataMember.getDateMember());
        homeMemberViewHoler.txtDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        homeMemberViewHoler.txtName.setText(dataMember.getNameMenber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeMemberViewHoler extends RecyclerView.ViewHolder{

        TextView txtName, txtDate;
        CircleImageView circleImageView;

        public HomeMemberViewHoler(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name_member);
            txtDate = itemView.findViewById(R.id.txt_date_member);
            circleImageView = itemView.findViewById(R.id.circle_imageview);

        }

    }
}
