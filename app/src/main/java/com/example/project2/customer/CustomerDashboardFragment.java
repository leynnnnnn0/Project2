package com.example.project2.customer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.R;
import com.example.project2.adapters.CustomerMenuModelAdapter;
import com.example.project2.adapters.MenuModelAdapter;
import com.example.project2.adapters.OrderModelAdapter;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.models.MenuModel;
import com.example.project2.models.OrderModel;

import com.example.project2.databinding.FragmentCustomerDashboardBinding;
import java.util.ArrayList;

public class CustomerDashboardFragment extends Fragment {
    FragmentCustomerDashboardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerDashboardBinding.inflate(inflater, container, false);

        RecyclerView recyclerView =  binding.recyclerView;
        ArrayList<MenuModel> model = showMenu();

        CustomerMenuModelAdapter adapter = new CustomerMenuModelAdapter(getContext(), model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
    public ArrayList<MenuModel> showMenu(){
        SQLiteDatabase db = new DatabaseHelper(getContext()).getReadableDatabase();
        ArrayList<MenuModel> menuModel = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT id, image_path, name, price FROM menu", null);

        while (cursor.moveToNext()){
            MenuModel menu = new MenuModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3)
            );
            menuModel.add(menu);
        }
        return menuModel;
    }
}

