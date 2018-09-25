package com.example.admin.tablayout;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataMember;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    Context context;
    ArrayList<DataMember> list;

    public MemberAdapter(Context context, ArrayList<DataMember> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_member, viewGroup, false);

        return new MemberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, final int i) {
        memberViewHolder.txtName.setText(list.get(i).getNameMenber());
        memberViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MemberActivity.class);
                intent.putExtra("dataMember", list.get(i).getIdMember());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        CardView cardView;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
