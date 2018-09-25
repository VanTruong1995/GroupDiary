package com.example.admin.tablayout;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.tablayout.Data.DataExchange;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class EventActExchangeAdapter extends RecyclerView.Adapter<EventActExchangeAdapter.ExchangeViewHolder> {

    Context context;
    ArrayList<DataExchange> list;
    Dialog dialog;
    UpdateDataEventAct updateDataEventAct;

    public EventActExchangeAdapter(Context context, ArrayList<DataExchange> list) {
        this.context = context;
        this.list = list;
        updateDataEventAct = (UpdateDataEventAct) context;
    }

    @NonNull
    @Override
    public ExchangeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_exchange, viewGroup, false);

        return new ExchangeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeViewHolder exchangeViewHolder, int i) {
        final DataExchange dataExchange = list.get(list.size() - 1 - i);
        final int position = list.size() - 1 - i;
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        exchangeViewHolder.txtName.setText(dataExchange.getNameExchange());
        exchangeViewHolder.txtAmount.setText(dataExchange.getAmountExchange() + " kg");
        exchangeViewHolder.txtMoney.setText(decimalFormat.format(dataExchange.getMoneyExchange()) + " đ");
        exchangeViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDialog(dataExchange, position);
            }
        });
    }

    private void callDialog(final DataExchange dataExchange, final int position) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_edit_exchange);
        dialog.show();

        final EditText edtName, edtAmount, edtMoney;
        Button btnAdd, btnDelete;

        edtName = dialog.findViewById(R.id.edt_name);
        edtAmount = dialog.findViewById(R.id.edt_amount);
        edtMoney = dialog.findViewById(R.id.edt_money);
        btnAdd = dialog.findViewById(R.id.btn_edit);
        btnDelete = dialog.findViewById(R.id.btn_delete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                int amount = Integer.parseInt(edtAmount.getText().toString());
                int money = Integer.parseInt(edtMoney.getText().toString());
                MainActivity.groupSQLite.updateExchange(dataExchange.getIdEvent(), dataExchange.getIdExchange(),name,amount, money);

                list.clear();
                list.addAll(MainActivity.groupSQLite.getAllExchange(dataExchange.getIdEvent()));
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.groupSQLite.deleteExchange(dataExchange.getIdExchange());
                updateDataEventAct.updateData();
                list.remove(position);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ExchangeViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtAmount, txtMoney;
        CardView cardView;

        public ExchangeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAmount = itemView.findViewById(R.id.txt_amount);
            txtMoney = itemView.findViewById(R.id.txt_money);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
