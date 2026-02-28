package com.example.project2.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.adapters.OrderItemModelAdapter;
import com.example.project2.databinding.ActivityAdminShowOrderBinding;
import com.example.project2.models.OrderItemModel;

import java.util.ArrayList;

public class AdminShowOrderActivity extends AppCompatActivity {
    ActivityAdminShowOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminShowOrderBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        binding.back.setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<OrderItemModel> model = new ArrayList<>();
        model.add(new OrderItemModel(1, "Chicken Adobo", 150, 5));
        OrderItemModelAdapter adapter = new OrderItemModelAdapter(this, model);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}