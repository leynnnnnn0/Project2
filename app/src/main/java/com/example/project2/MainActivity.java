package com.example.project2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.project2.admin.AdminCustomerFragment;
import com.example.project2.admin.AdminDashboardFragment;
import com.example.project2.admin.AdminMenuFragment;
import com.example.project2.admin.AdminOrderFragment;
import com.example.project2.admin.AdminSalesFragment;
import com.example.project2.admin.AdminSettingsFragment;
import com.example.project2.admin.AdminStaffFragment;
import com.example.project2.customer.CustomerCartFragment;
import com.example.project2.customer.CustomerDashboardFragment;
import com.example.project2.customer.CustomerFavoriteFragment;
import com.example.project2.customer.CustomerOrderFragment;
import com.example.project2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    String role = "customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);



        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        if(role.equalsIgnoreCase("customer")){
            binding.favorite.setVisibility(View.VISIBLE);
            binding.cart.setVisibility(View.VISIBLE);

            binding.menu.setVisibility(View.GONE);

            binding.staff.setVisibility(View.GONE);
            binding.customer.setVisibility(View.GONE);
            binding.sales.setVisibility(View.GONE);
            loadFragment(new CustomerDashboardFragment());
        }else {
            loadFragment(new AdminDashboardFragment());
        }

        binding.dashboard.setOnClickListener(this);
        binding.menu.setOnClickListener(this);
        binding.order.setOnClickListener(this);
        binding.sales.setOnClickListener(this);
        binding.staff.setOnClickListener(this);
        binding.customer.setOnClickListener(this);
        binding.settings.setOnClickListener(this);


        binding.cart.setOnClickListener(this);
        binding.favorite.setOnClickListener(this);



    }



    public void onClick(View view){
        if(view.getId() == R.id.dashboard && role.equalsIgnoreCase("admin")){
            loadFragment(new AdminDashboardFragment());
        }
        if(view.getId() == R.id.menu){
            loadFragment(new AdminMenuFragment());
        }
        if(view.getId() == R.id.order && role.equalsIgnoreCase("admin")){
            loadFragment(new AdminOrderFragment());
        }
        if(view.getId() == R.id.sales){
            loadFragment(new AdminSalesFragment());
        }
        if(view.getId() == R.id.staff){
            loadFragment(new AdminStaffFragment());
        }
        if(view.getId() == R.id.customer){
            loadFragment(new AdminCustomerFragment());
        }
        if(view.getId() == R.id.settings){
            loadFragment(new AdminSettingsFragment());
        }
        if(view.getId() == R.id.favorite){
            loadFragment(new CustomerFavoriteFragment());
        }
        if(view.getId() == R.id.cart){
            loadFragment(new CustomerCartFragment());
        }
        if(view.getId() == R.id.order && role.equalsIgnoreCase("customer")){
            loadFragment(new CustomerOrderFragment());
        }

        if(view.getId() == R.id.dashboard && role.equalsIgnoreCase("customer")){
            loadFragment(new CustomerDashboardFragment());
        }


    }


    public void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}