package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddRecipe extends AppCompatActivity {

    private String dish_name, dish_desc, image;
    private EditText etxtDishName, etxtDishDesc;
    private ImageView ivUpload;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        etxtDishName = (EditText) findViewById(R.id.etxtDishName);
        etxtDishDesc = (EditText) findViewById(R.id.etxtDishDesc);
        btnSave = (Button) findViewById(R.id.btnSave);

        ivUpload = (ImageView) findViewById(R.id.ivUpload);
        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(AddRecipe.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dish_name = etxtDishName.getText().toString();
                dish_desc = etxtDishDesc.getText().toString();

                FoodModel f = new FoodModel(dish_name, dish_desc, image);
                if(dvalidator(f)){
                    try{
                        DBHandler dbh = new DBHandler(AddRecipe.this);

                        boolean success = dbh.addDish(f);
                        if(success){
                            Toast.makeText(AddRecipe.this, "Recipe Added", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddRecipe.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        Log.d("AddRecipe",e.toString());
                    }
                }else{
                    Toast.makeText(AddRecipe.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean dvalidator(FoodModel f){
        if(f.getDish_name().equals("")) return false;
        if(f.getDish_desc().equals("")) return false;
        if(f.getImage().equals("")) return false;

        if(f.getDish_name().equals(null)) return false;
        if(f.getDish_desc().equals(null)) return false;
        if(f.getImage().equals(null)) return false;
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            ivUpload.setImageURI(uri);
            String strUri = uri.toString();

            image = strUri;
        }

    }
}