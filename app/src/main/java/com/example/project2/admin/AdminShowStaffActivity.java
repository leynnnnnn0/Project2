package com.example.project2.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.R;
import com.example.project2.databinding.ActivityAdminShowStaffBinding;
import com.example.project2.helpers.NavHelper;

public class AdminShowStaffActivity extends AppCompatActivity {
    ActivityAdminShowStaffBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminShowStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.edit.setOnClickListener(v -> NavHelper.navigate(this, AdminEditMenuActivity.class));

        binding.back.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}