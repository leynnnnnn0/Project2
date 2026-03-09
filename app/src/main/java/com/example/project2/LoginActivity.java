package com.example.project2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.admin.AdminDashboardFragment;
import com.example.project2.customer.CustomerDashboardFragment;
import com.example.project2.databinding.ActivityLoginBinding;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.helpers.NavHelper;
import com.example.project2.helpers.SessionHelper;
import com.example.project2.models.UserModel;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(v -> {

            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();

            SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM  users WHERE email = ? and password = ?", new String[]{email, password});

            if (cursor.moveToFirst()){
                UserModel user = new UserModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                SessionHelper.setUser(this, user);
                try {
                    Log.d("CHECK ROLE", "ROlE: " + SessionHelper.getUser(this).toString());
                }catch (Exception e){
                    Log.d("info_error", e.getMessage());
                }

                if (user.role.equals("customer")){
                    NavHelper.navigate(this, MainActivity.class);
                } else if (user.role.equals("staff") ||user.role.equals("admin")) {
                    NavHelper.navigate(this, MainActivity.class);
                }

                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Wrong credentials failed to login!", Toast.LENGTH_SHORT).show();
        });

        binding.register.setOnClickListener(v -> {
            NavHelper.navigate(this, RegisterActivity.class);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}