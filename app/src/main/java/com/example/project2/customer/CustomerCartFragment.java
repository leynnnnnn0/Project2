package com.example.project2.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.R;
import com.example.project2.adapters.CartItemModelAdapter;
import com.example.project2.models.CartItemModel;

import java.util.ArrayList;

public class CustomerCartFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_cart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ArrayList<CartItemModel> model = new ArrayList<>();
        model.add(new CartItemModel(1, "Chicken Adobo", 150, 5));
        CartItemModelAdapter adapter = new CartItemModelAdapter(getContext(), model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}