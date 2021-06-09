package com.example.cookbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cookbook.db";
    private static final Integer DATABASE_VER = 1;

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] qss = {
                "CREATE TABLE RECIPE (ID INTEGER PRIMARY KEY AUTOINCREMENT, DISH_NAME TEXT, DISH_DESC TEXT, IMAGE TEXT)"
        };
        for(int i=0; i< qss.length; i++){
            db.execSQL(qss[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addDish(FoodModel f){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("DISH_NAME", f.getDish_name());
            cv.put("DISH_DESC", f.getDish_desc());
            cv.put("IMAGE", f.getImage());

            long insert = db.insert("RECIPE", null, cv);
            if(insert == -1){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            Log.e("addDish",e.toString());
        }
        return false;
    }

    public List<FoodModel> getDishes(){
        List<FoodModel> retlist= new ArrayList<>();

        String qs = "SELECT * FROM RECIPE";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qs, null);

        if(cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String dish_name = cursor.getString(1);
                String dish_desc = cursor.getString(2);
                String image = cursor.getString(3);

                FoodModel fm = new FoodModel(id, dish_name, dish_desc, image);

                retlist.add(fm);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return retlist;
    }
    public boolean removeFood(FoodModel f){
        SQLiteDatabase db = this.getWritableDatabase();
        String qs = "DELETE FROM RECIPE WHERE ID = " +  f.getId();

        Cursor cursor = db.rawQuery(qs, null);
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return false;
        }else{
            cursor.close();
            db.close();
            return true;
        }
    }

    public boolean updateDish(FoodModel f){
        SQLiteDatabase db = this.getWritableDatabase();
        String qs = "UPDATE RECIPE SET DISH_NAME = \"" + f.getDish_name() + "\", DISH_DESC = \"" + f.getDish_desc() + "\", IMAGE = '" + f.getImage() +"' WHERE ID = " + f.getId();

        Cursor cursor = db.rawQuery(qs, null);
        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return false;
        }else{
            cursor.close();
            db.close();
            return true;
        }
    }
}
