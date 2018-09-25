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

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeEventAdapter extends RecyclerView.Adapter<HomeEventAdapter.HomeEventViewHoler> {

    Context context;
    ArrayList<DataEvents> list;

    public HomeEventAdapter(Context context, ArrayList<DataEvents> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeEventViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_home_event, viewGroup, false);

        return new HomeEventViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeEventViewHoler homeEventViewHoler, int i) {
        DataEvents dataEvents = list.get(i);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dataEvents.getDateEvent());
        homeEventViewHoler.txtDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
        homeEventViewHoler.txtName.setText(dataEvents.getNameEvent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HomeEventViewHoler extends RecyclerView.ViewHolder {

        TextView txtName, txtDate;
        CircleImageView circleImageView;

        public HomeEventViewHoler(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name_event);
            txtDate = itemView.findViewById(R.id.txt_date_event);
            circleImageView = itemView.findViewById(R.id.circle_imageview);
        }
    }
}
