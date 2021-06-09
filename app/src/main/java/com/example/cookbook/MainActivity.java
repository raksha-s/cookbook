package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    RecyclerView mRecyclerView;
//    List<FoodData>myFoodList;
//    FoodData mFoodData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab;
        fab = (FloatingActionButton) findViewById(R.id.fab_AddRecipe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddRecipe.class);
                startActivityForResult(i, 1);
            }
        });

        reload_data();

        ListView foodlist = (ListView) findViewById(R.id.foodlist);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == 1) { //AddRecipe Result
//            if(resultCode == Activity.RESULT_OK){
                reload_data();
//            }
//        }
//        if (requestCode == 2) { //Delete Result
//            if(resultCode == Activity.RESULT_OK){
//                reload_data();
//            }
//        }
    } //onActivityResult

    public void reload_data() {
        ListView foodsList = (ListView) findViewById(R.id.foodlist);
        DBHandler dbh = new DBHandler(MainActivity.this);

        List<FoodModel> foodsArray = dbh.getDishes();

        CustomRecipeListAdapter adapter = new CustomRecipeListAdapter(MainActivity.this,foodsArray);
        foodsList.setAdapter(adapter);

        foodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodModel ff = foodsArray.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("DISH_NAME", ff.getDish_name());
                intent.putExtra("DISH_DESC", ff.getDish_desc());
                intent.putExtra("DISH_IMG", ff.getImage());
                intent.putExtra("ID", ff.getId());
                startActivityForResult(intent, 2);

            }
        });

    }



}