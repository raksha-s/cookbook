package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FoodData>myFoodList;
    FoodData mFoodData;

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
                startActivity(i);
            }
        });

        reload_data();
//        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
//        mRecyclerView.setLayoutManager(gridLayoutManager);
//
//        myFoodList=new ArrayList<>();
//
//        MyAdapter myAdapter=new MyAdapter(MainActivity.this,myFoodList);
//        mRecyclerView.setAdapter(myAdapter);




    }
    public void reload_data() {
        ListView foodsList = (ListView) findViewById(R.id.foodlist);
        DBHandler dbh = new DBHandler(MainActivity.this);

        List<FoodModel> foodsArray = dbh.getDishes();

        CustomRecipeListAdapter adapter = new CustomRecipeListAdapter(MainActivity.this,foodsArray);
        foodsList.setAdapter(adapter);
    }



}