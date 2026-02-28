package com.example.project2.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2.R;
import com.example.project2.adapters.CustomerMenuModelAdapter;
import com.example.project2.adapters.MenuModelAdapter;
import com.example.project2.adapters.OrderModelAdapter;
import com.example.project2.models.MenuModel;
import com.example.project2.models.OrderModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerDashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerDashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerDashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerDashboardFragment newInstance(String param1, String param2) {
        CustomerDashboardFragment fragment = new CustomerDashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ArrayList<MenuModel> model = new ArrayList<>();
        model.add(new MenuModel(1, "Chicken Adobo", "$150.00"));
        model.add(new MenuModel(1, "Chicken Adobo", "$150.00"));
        model.add(new MenuModel(1, "Chicken Adobo", "$150.00"));
        CustomerMenuModelAdapter adapter = new CustomerMenuModelAdapter(getContext(), model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);

        return view;
    }
}