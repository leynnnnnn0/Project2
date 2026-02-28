package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.models.OrderItemModel;

import java.util.ArrayList;

public class OrderItemModelAdapter extends RecyclerView.Adapter<OrderItemModelAdapter.OrderItemModelViewModel> {
    public Context context;
    public ArrayList<OrderItemModel> arrayList;

    public OrderItemModelAdapter(Context context, ArrayList<OrderItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderItemModelAdapter.OrderItemModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item, parent, false);
        return new OrderItemModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemModelAdapter.OrderItemModelViewModel holder, int position) {
       OrderItemModel model = arrayList.get(position);
       holder.name.setText(model.name);
       holder.total.setText(String.valueOf(model.total));
       holder.quantity.setText(String.valueOf(model.quantity));



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OrderItemModelViewModel extends RecyclerView.ViewHolder {
        TextView name, total, quantity;
        public OrderItemModelViewModel(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            total = itemView.findViewById(R.id.total);
            quantity = itemView.findViewById(R.id.quantity);

        }
    }
}
