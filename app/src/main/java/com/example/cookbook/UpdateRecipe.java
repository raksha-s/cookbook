package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class UpdateRecipe extends AppCompatActivity {

    private Integer did;
    private String dish_name, dish_desc, dish_img;
    private EditText etxtUDishName, etxtUDishDesc;
    private ImageView ivUUpload;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        etxtUDishName = (EditText) findViewById(R.id.etxtUDishName);
        etxtUDishDesc = (EditText) findViewById(R.id.etxtUDishDesc);
        ivUUpload = (ImageView) findViewById(R.id.ivUUpload);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        did = getIntent().getIntExtra("ID", -1);
        dish_name = getIntent().getStringExtra("DISH_NAME");
        dish_desc = getIntent().getStringExtra("DISH_DESC");
        dish_img = getIntent().getStringExtra("DISH_IMG");



        etxtUDishName.setText(dish_name);
        etxtUDishDesc.setText(dish_desc);
        ivUUpload.setImageURI(Uri.parse(dish_img));

        ivUUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(UpdateRecipe.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dish_name = etxtUDishName.getText().toString();
                dish_desc = etxtUDishDesc.getText().toString();

                FoodModel f = new FoodModel(did, dish_name, dish_desc, dish_img);
                if(dvalidator(f)){
                    try{
                        DBHandler dbh = new DBHandler(UpdateRecipe.this);

                        boolean success = dbh.updateDish(f);
                        if(success){
                            Toast.makeText(UpdateRecipe.this, "Recipe Updated", Toast.LENGTH_SHORT).show();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }else{
                            Toast.makeText(UpdateRecipe.this, "Error", Toast.LENGTH_SHORT).show();
                            Intent returnIntent = new Intent();
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }

                    }catch (Exception e){
                        Log.d("UpdateRecipe",e.toString());
                    }
                }else{
                    Toast.makeText(UpdateRecipe.this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
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
            ivUUpload.setImageURI(uri);
            String strUri = uri.toString();

            dish_img = strUri;
        }

    }
}