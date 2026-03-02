package com.example.project2.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.models.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionHelper {
    public  static  final  String USER_FOLDER = "user_session";
    public  static  final  String KEY_USER = "KEY_USER";

    public  static void setUser(Context context, UserModel user){
        JSONObject json = new JSONObject();
        try {
            json.put("id", user.getId());
            json.put("fullname", user.getFullname());
            json.put("email", user.getEmail());
            json.put("password", user.getPassword());
            json.put("role", user.getRole());

            SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FOLDER, Context.MODE_PRIVATE);
            sharedPreferences.edit().putString(KEY_USER, json.toString()).apply();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserModel getUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_FOLDER, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(KEY_USER, null);

        try {
            JSONObject json = new JSONObject(jsonString);
                return new UserModel(
                        json.getInt("id"),
                        json.getString("fullname"),
                        json.getString("email"),
                        json.getString("password"),
                        json.getString("role")
                );

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}


//public void setUserId(Context context, int userId){
//    SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_USER_ID, Context.MODE_PRIVATE);
//    sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply();
//}
//
//public int getUserId (Context context){
//    SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_USER_ID, Context.MODE_PRIVATE);
//    return sharedPreferences.getInt(KEY_USER_ID, -1);
//}