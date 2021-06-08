package com.example.cookbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewFolder>{

    private Context mContext;
    private List<FoodData> myFoodList;

    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @Override
    public FoodViewFolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item,viewGroup,false);

        return new FoodViewFolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewFolder foodViewFolder, int i) {
        foodViewFolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
        foodViewFolder.mTitle.setText(myFoodList.get(i).getItemName());
        foodViewFolder.mDescription.setText(myFoodList.get(i).getItemDescription());
        foodViewFolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,DetailActivity.class);
                intent.putExtra("Image",myFoodList.get(foodViewFolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myFoodList.get(foodViewFolder.getAdapterPosition()).getItemDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}

class FoodViewFolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescription;
    CardView mCardView;


    public FoodViewFolder(View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.ivImage);
        mTitle=itemView.findViewById(R.id.tvTitle);
        mDescription=itemView.findViewById(R.id.tvDescription);
        mCardView=itemView.findViewById(R.id.myCardView);
    }
}
