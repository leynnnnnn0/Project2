package com.example.project2.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.MainActivity;
import com.example.project2.R;
import com.example.project2.admin.AdminShowMenuActivity;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.helpers.NavHelper;
import com.example.project2.helpers.SessionHelper;
import com.example.project2.models.MenuModel;
import com.example.project2.databinding.CustomerMenuListBinding;
import com.example.project2.models.UserModel;

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
        CustomerMenuListBinding binding =  CustomerMenuListBinding.inflate(layoutInflater, parent, false);
        return new MenuModelViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerMenuModelAdapter.MenuModelViewModel holder, int position) {
       MenuModel model = arrayList.get(position);

       holder.binding.image.setImageURI(Uri.parse(model.image_path));
       holder.binding.name.setText(model.name);
       holder.binding.price.setText("P " + model.price);
       holder.binding.box.setOnClickListener(v -> {
           NavHelper.navigate(context, AdminShowMenuActivity.class, 1);
       });

       holder.binding.add.setOnClickListener(v -> {

           int menuId = model.id;
           int userId = SessionHelper.getUser(context).getId();

           SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();
           ContentValues cv = new ContentValues();
           cv.put("userId", userId);
           cv.put("menuId", menuId);
           cv.put("quantity", 1);
           long result = db.insert("cart", null, cv);

           Log.d("CHECK_ID", result + " here");

           if (result != -1){
               Toast.makeText(context, model.name + " added to cart!", Toast.LENGTH_SHORT).show();
           } else Toast.makeText(context, model.name + " failed add to cart!", Toast.LENGTH_SHORT).show();

       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MenuModelViewModel extends RecyclerView.ViewHolder {
        CustomerMenuListBinding binding;
        public MenuModelViewModel(CustomerMenuListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
