package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.helpers.NavHelper;
import com.example.project2.models.SalesModel;

import java.util.ArrayList;

public class SalesModelAdapter extends RecyclerView.Adapter<SalesModelAdapter.SalesModelViewModel> {
    public Context context;
    public ArrayList<SalesModel> arrayList;

    public SalesModelAdapter(Context context, ArrayList<SalesModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SalesModelAdapter.SalesModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sales_list, parent, false);
        return new SalesModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesModelAdapter.SalesModelViewModel holder, int position) {
       SalesModel model = arrayList.get(position);
       holder.quantity.setText(String.valueOf(model.quantity));
        holder.name.setText(model.name);
        holder.total.setText(String.valueOf(model.total));


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SalesModelViewModel extends RecyclerView.ViewHolder {
        TextView quantity, name, total;

        public SalesModelViewModel(@NonNull View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.quantity);
            name = itemView.findViewById(R.id.name);
            total = itemView.findViewById(R.id.total);

        }
    }
}
