package com.example.project2.admin;

import static com.example.project2.helpers.SessionHelper.getUser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.project2.MainActivity;
import com.example.project2.R;
import com.example.project2.adapters.MenuModelAdapter;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.helpers.NavHelper;
import com.example.project2.helpers.SessionHelper;
import com.example.project2.models.MenuModel;
import com.example.project2.databinding.FragmentAdminMenuBinding;
import com.example.project2.models.UserModel;

import java.util.ArrayList;


public class AdminMenuFragment extends Fragment {

    FragmentAdminMenuBinding binding;
    MenuModelAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminMenuBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;
        ArrayList<MenuModel> model = getMenu();
        adapter = new MenuModelAdapter(getContext(), model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        binding.add.setOnClickListener(v -> NavHelper.navigate(this.getContext(), AdminCreateMenuActivity.class));

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.arrayList = getMenu();
        adapter.notifyDataSetChanged();
    }

    public ArrayList<MenuModel> getMenu() {
        ArrayList<MenuModel> menu = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(getContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, image_path, name, price FROM menu", null);

        while (cursor.moveToNext()) {
            MenuModel menuModel = new MenuModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3)
            );
            menu.add(menuModel);
        }

        return menu;
    }
}