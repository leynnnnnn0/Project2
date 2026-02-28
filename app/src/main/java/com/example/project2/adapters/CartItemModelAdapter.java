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
import com.example.project2.models.CartItemModel;

import java.util.ArrayList;

public class CartItemModelAdapter extends RecyclerView.Adapter<CartItemModelAdapter.CartItemModelViewModel> {
    public Context context;
    public ArrayList<CartItemModel> arrayList;

    public CartItemModelAdapter(Context context, ArrayList<CartItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CartItemModelAdapter.CartItemModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_item, parent, false);
        return new CartItemModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemModelAdapter.CartItemModelViewModel holder, int position) {
       CartItemModel model = arrayList.get(position);
       holder.name.setText(model.name);
       holder.price.setText(String.valueOf(model.price));
       holder.quantity.setText(String.valueOf(model.quantity));



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CartItemModelViewModel extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        public CartItemModelViewModel(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantity);

        }
    }
}
