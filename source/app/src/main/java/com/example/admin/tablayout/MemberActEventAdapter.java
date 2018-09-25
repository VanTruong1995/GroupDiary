package com.example.admin.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataEvents;

import java.util.ArrayList;
import java.util.Calendar;

public class MemberActEventAdapter extends RecyclerView.Adapter<MemberActEventAdapter.EventViewHolder> {

    Context context;
    ArrayList<DataEvents> list;

    public MemberActEventAdapter(Context context, ArrayList<DataEvents> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_event, viewGroup, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {
        DataEvents dataEvents = list.get(list.size() - 1 - i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataEvents.getDateEvent());
        eventViewHolder.txtName.setText(dataEvents.getNameEvent());
        eventViewHolder.txtAddress.setText(dataEvents.getAddressEvent());
        eventViewHolder.txtDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtAddress, txtDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAddress = itemView.findViewById(R.id.txt_address);
            txtDate = itemView.findViewById(R.id.txt_date);
        }
    }
}
