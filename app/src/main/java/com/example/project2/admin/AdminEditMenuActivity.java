package com.example.project2.admin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project2.R;
import com.example.project2.databinding.ActivityAdminEditMenuBinding;
import com.example.project2.helpers.DatabaseHelper;
import com.example.project2.models.MenuModel;

public class AdminEditMenuActivity extends AppCompatActivity {
    ActivityAdminEditMenuBinding binding;
    int id;

    ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result ->{
                if (result.getResultCode() == RESULT_OK){
                    Uri uri = result.getData().getData();
                    getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    binding.image.setImageURI(uri);
                    binding.image.setTag(uri.toString());
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAdminEditMenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        id = getIntent().getIntExtra("id", -1);


        MenuModel menu = getMenu();
        binding.image.setImageURI(Uri.parse(menu.image_path));
        binding.name.setText(menu.name);
        binding.price.setText(String.valueOf(menu.price));
        binding.description.setText(menu.description);
        binding.quantity.setText(String.valueOf(menu.quantity));

        binding.image.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            pickImage.launch(intent);
        });
        binding.save.setOnClickListener(v -> {
            SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("image_path", binding.image.getTag().toString());
            contentValues.put("name", binding.name.getText().toString().trim());
            contentValues.put("description", binding.description.getText().toString().trim());
            contentValues.put("price", Double.parseDouble(binding.price.getText().toString().trim()));
            contentValues.put("quantity", Integer.parseInt(binding.quantity.getText().toString().trim()));
            long result =  db.update("menu",  contentValues, "id = ?", new String[]{String.valueOf(1)});

            if (result == 1){
                Toast.makeText(this, "Menu updated successfully!", Toast.LENGTH_SHORT).show();
                finish();
            } else Toast.makeText(this, "Menu failed to update!", Toast.LENGTH_SHORT).show();
        });

        binding.back.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public MenuModel getMenu()
    {
        MenuModel menu = new MenuModel();
        SQLiteDatabase db = new DatabaseHelper(getApplicationContext()).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM menu WHERE id = ?", new String[]{String.valueOf(id)});

        if (cursor.moveToNext()) {
            return new MenuModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getInt(5)
            );
        }
        return menu;
    }
}