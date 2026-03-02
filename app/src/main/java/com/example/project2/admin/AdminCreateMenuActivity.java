package com.example.project2.admin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.R;
import com.example.project2.databinding.ActivityAdminCreateMenuBinding;
import com.example.project2.helpers.DatabaseHelper;

public class AdminCreateMenuActivity extends AppCompatActivity {
    ActivityAdminCreateMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminCreateMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.save.setOnClickListener(v -> {
            SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("image_path", "");
            contentValues.put("name", binding.name.getText().toString().trim());
            contentValues.put("description", binding.description.getText().toString().trim());
            contentValues.put("price", Double.parseDouble(binding.price.getText().toString().trim()));
            contentValues.put("quantity", Integer.parseInt(binding.quantity.getText().toString().trim()));
            long result =  db.insert("menu", null, contentValues);

            if (result != -1){
                Toast.makeText(this, "New Menu added successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else Toast.makeText(this, "New Menu failed to add!", Toast.LENGTH_SHORT).show();
        });

        binding.back.setOnClickListener(v -> finish());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}