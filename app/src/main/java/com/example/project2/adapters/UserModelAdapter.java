package com.example.project2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.admin.AdminShowStaffActivity;
import com.example.project2.helpers.NavHelper;
import com.example.project2.models.UserModel;

import java.util.ArrayList;

public class UserModelAdapter extends RecyclerView.Adapter<UserModelAdapter.UserModelViewModel> {
    public Context context;
    public ArrayList<UserModel> arrayList;

    public UserModelAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserModelAdapter.UserModelViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_list, parent, false);
        return new UserModelViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModelAdapter.UserModelViewModel holder, int position) {
       UserModel model = arrayList.get(position);
       holder.firstName.setText(model.fullname);
       holder.email.setText(model.email);
       holder.box.setOnClickListener(v -> {
           NavHelper.navigate(context, AdminShowStaffActivity.class, 1);
       });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UserModelViewModel extends RecyclerView.ViewHolder {
        TextView firstName, email;
        CardView box;
        public UserModelViewModel(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            email = itemView.findViewById(R.id.email);
            box = itemView.findViewById(R.id.box);
        }
    }
}
