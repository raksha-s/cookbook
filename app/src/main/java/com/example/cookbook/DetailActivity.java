package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView foodDescription, tvtitle;
    ImageView foodImage;
    Button btnDelete, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodDescription = (TextView) findViewById(R.id.txtDescription);
        foodImage = (ImageView) findViewById(R.id.ivImage2);
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        Integer dish_id = getIntent().getIntExtra("ID", -1);
        String dish_name = getIntent().getStringExtra("DISH_NAME");
        String dish_desc = getIntent().getStringExtra("DISH_DESC");
        String dish_img = getIntent().getStringExtra("DISH_IMG");

        tvtitle.setText(dish_name);
        foodDescription.setText(dish_desc);
        foodImage.setImageURI(Uri.parse(dish_img));

        FoodModel f = new FoodModel(dish_id, dish_name, dish_desc, dish_img);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbh = new DBHandler(DetailActivity.this);
                boolean success = dbh.removeFood(f);
                if(success){
                    Toast.makeText(DetailActivity.this, "Recipe Removed", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }else{
                    Toast.makeText(DetailActivity.this, "Could Not Delete Recipe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ei = new Intent(DetailActivity.this, UpdateRecipe.class);

                ei.putExtra("ID", dish_id);
                ei.putExtra("DISH_NAME", dish_name);
                ei.putExtra("DISH_DESC", dish_desc);
                ei.putExtra("DISH_IMG", dish_img);

                startActivityForResult(ei, 3);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) { //UpdateRecipe Result
            if(resultCode == Activity.RESULT_OK){
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    } //onActivityResult
}