package com.example.project2.admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.project2.R;

import com.example.project2.databinding.ActivityAdminShowMenuBinding;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.helpers.NavHelper;
import com.example.project2.models.MenuModel;

import java.util.ArrayList;

public class AdminShowMenuActivity extends AppCompatActivity {
    ActivityAdminShowMenuBinding binding;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityAdminShowMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        id = getIntent().getIntExtra("id", -1);


        binding.edit.setOnClickListener(v -> NavHelper.navigate(this, AdminEditMenuActivity.class, 1));

        binding.back.setOnClickListener(v -> finish());

        MenuModel menu = getMenu();
        binding.image.setImageURI(Uri.parse(menu.image_path));
        binding.name.setText(menu.name);
        binding.price.setText(String.valueOf(menu.price));
        binding.description.setText(menu.description);

        binding.edit.setOnClickListener(v -> {
            NavHelper.navigate(this, AdminEditMenuActivity.class, id);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public MenuModel getMenu()
    {
        MenuModel menu = new MenuModel();
        SQLiteDatabase db = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, image_path, name, description, price FROM menu WHERE id = " + id, null);

        if (cursor.moveToFirst()) {
            return new MenuModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4)
            );
        }

        return menu;
    }
}