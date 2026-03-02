package com.example.project2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.databinding.ActivityRegisterBinding;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.helpers.NavHelper;
import com.example.project2.helpers.SessionHelper;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register.setOnClickListener(v -> {

            String fullname = binding.firstName.getText().toString().trim() + " " + binding.lastName.getText().toString().trim();
            String role =  "customer";

            SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fullname", fullname);
            contentValues.put("email", binding.email.getText().toString().trim());
            contentValues.put("password", binding.password.getText().toString().trim());
            contentValues.put("role", role);
            long result = db.insert("users", null, contentValues);

            if (result != -1){
                Toast.makeText(this, "Registration successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else Toast.makeText(this, "Failed to Register!", Toast.LENGTH_SHORT).show();

        });

        binding.login.setOnClickListener(v -> {
            NavHelper.navigate(this, LoginActivity.class);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}