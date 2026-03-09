package com.example.project2.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.R;
import com.example.project2.adapters.OrderModelAdapter;
import com.example.project2.models.OrderModel;

import com.example.project2.databinding.FragmentAdminOrderBinding;

import java.util.ArrayList;


public class AdminOrderFragment extends Fragment {
    FragmentAdminOrderBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentAdminOrderBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;
        ArrayList<OrderModel> model = new ArrayList<>();
        model.add(new OrderModel(1, "ORD-439943", "pending"));
        OrderModelAdapter adapter = new OrderModelAdapter(getContext(), model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}