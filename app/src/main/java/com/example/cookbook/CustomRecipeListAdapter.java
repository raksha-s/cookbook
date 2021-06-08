package com.example.cookbook;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class CustomRecipeListAdapter extends ArrayAdapter<FoodModel> {
    public CustomRecipeListAdapter(Context context, List<FoodModel> foods) {
        super(context, 0, foods);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodModel f = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_row_item, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView dish_name = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView dish_desc = (TextView) convertView.findViewById(R.id.tvDescription);


        img.setImageURI(Uri.parse(f.getImage()));
        dish_name.setText(f.getDish_name());
        dish_desc.setText(f.getDish_desc());

        return convertView;
    }
}