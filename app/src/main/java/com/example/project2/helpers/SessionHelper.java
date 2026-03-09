package com.example.project2.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionHelper {

    public static final String USER_SESSION = "user_session";

    public static void setUser(Context context, UserModel user){
        try {
            JSONObject json = new JSONObject();
            json.put("id", user.getId());
            json.put("fullname", user.getFullname());
            json.put("email", user.getEmail());
            json.put("password", user.getPassword());
            json.put("role", user.getRole());

            SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(USER_SESSION, json.toString()).apply();

        } catch ( JSONException e ){
            throw new RuntimeException(e);
        }
    }

    public static UserModel getUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(USER_SESSION, null);

        try {
            JSONObject json = new JSONObject(jsonString);
                return  new UserModel(
                        json.getInt("id"),
                    json.getString("fullname"),
                    json.getString("email"),
                    json.getString("password"),
                    json.getString("role")
                );

        } catch ( JSONException e ){
            throw new RuntimeException(e);
        }

    }

}

