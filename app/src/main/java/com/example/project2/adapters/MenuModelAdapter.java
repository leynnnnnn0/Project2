package com.example.project2.adapters;

import android.content.Context;
import android.net.Uri;
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
import com.example.project2.databinding.MenuListBinding;

import java.util.ArrayList;

public class MenuModelAdapter extends RecyclerView.Adapter<MenuModelAdapter.MenuModelViewModel> {
    public Context context;
    public ArrayList<MenuModel> arrayList;


    public MenuModelAdapter(Context context, ArrayList<MenuModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MenuModelAdapter.MenuModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MenuListBinding  binding = MenuListBinding.inflate(layoutInflater, parent, false);
        return new MenuModelViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuModelAdapter.MenuModelViewModel holder, int position) {
       MenuModel model = arrayList.get(position);
       holder.binding.name.setText(model.name);
       holder.binding.price.setText("P " + model.price);
        holder.binding.image.setImageURI(Uri.parse(model.image_path));

       holder.binding.box.setOnClickListener(v -> {
           NavHelper.navigate(context, AdminShowMenuActivity.class, model.id);
       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MenuModelViewModel extends RecyclerView.ViewHolder {
        MenuListBinding binding;
        public MenuModelViewModel(MenuListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
