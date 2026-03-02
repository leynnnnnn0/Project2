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
import com.example.project2.databinding.ActivityAdminEditStaffBinding;
import com.example.project2.helpers.DatabaseHelper;

public class AdminEditStaffActivity extends AppCompatActivity {
    ActivityAdminEditStaffBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminEditStaffBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.save.setOnClickListener(v -> {

            String fullname = binding.firstName.getText().toString().trim() + " " + binding.lastName.getText().toString().trim();

            SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fullname", fullname);
            contentValues.put("email", binding.email.getText().toString().trim());
            contentValues.put("password", binding.password.getText().toString().trim());
            long result =  db.update("users", contentValues, "id = ?", new String[]{String.valueOf(1)});

            if (result == 1){
                Toast.makeText(this, "Staff updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else Toast.makeText(this, "Staff failed to update!", Toast.LENGTH_SHORT).show();
        });

        binding.back.setOnClickListener(v -> finish());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}