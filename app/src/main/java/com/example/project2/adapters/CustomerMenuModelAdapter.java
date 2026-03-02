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
import com.example.project2.admin.AdminShowMenuActivity;
import com.example.project2.helpers.NavHelper;
import com.example.project2.models.MenuModel;

import java.util.ArrayList;

public class CustomerMenuModelAdapter extends RecyclerView.Adapter<CustomerMenuModelAdapter.MenuModelViewModel> {
    public Context context;
    public ArrayList<MenuModel> arrayList;

    public CustomerMenuModelAdapter(Context context, ArrayList<MenuModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomerMenuModelAdapter.MenuModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.customer_menu_list, parent, false);
        return new MenuModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerMenuModelAdapter.MenuModelViewModel holder, int position) {
       MenuModel model = arrayList.get(position);
       holder.name.setText(model.name);
       holder.price.setText("P " +model.price);
       holder.box.setOnClickListener(v -> {
           NavHelper.navigate(context, AdminShowMenuActivity.class, 1);
       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MenuModelViewModel extends RecyclerView.ViewHolder {
        TextView name, price;
        CardView box;
        public MenuModelViewModel(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            box = itemView.findViewById(R.id.box);
        }
    }
}
