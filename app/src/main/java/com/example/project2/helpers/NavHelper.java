package com.example.project2.helpers;

import android.content.Context;
import android.content.Intent;

public class NavHelper {
    public static void navigate(Context context, Class<?> destination)
    {
        context.startActivity(new Intent(context, destination));
    }

    public static void navigate(Context context, Class<?> destination, int id)
    {
        Intent intent = new Intent(context, destination);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
