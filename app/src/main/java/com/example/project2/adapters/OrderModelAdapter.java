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
import com.example.project2.admin.AdminShowOrderActivity;
import com.example.project2.helpers.NavHelper;
import com.example.project2.models.OrderModel;

import java.util.ArrayList;

public class OrderModelAdapter extends RecyclerView.Adapter<OrderModelAdapter.OrderModelViewModel> {
    public Context context;
    public ArrayList<OrderModel> arrayList;

    public OrderModelAdapter(Context context, ArrayList<OrderModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderModelAdapter.OrderModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_list, parent, false);
        return new OrderModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderModelAdapter.OrderModelViewModel holder, int position) {
       OrderModel model = arrayList.get(position);
       holder.orderNumber.setText(model.orderNumber);
       holder.status.setText(model.status);
       holder.box.setOnClickListener(v -> {
           NavHelper.navigate(context, AdminShowOrderActivity.class, 1);
       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OrderModelViewModel extends RecyclerView.ViewHolder {
        TextView orderNumber, status;
        CardView box;
        public OrderModelViewModel(@NonNull View itemView) {
            super(itemView);

            orderNumber = itemView.findViewById(R.id.orderNumber);
            status = itemView.findViewById(R.id.status);
            box = itemView.findViewById(R.id.box);
        }
    }
}
