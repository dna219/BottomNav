package Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc.bottomnav.DetailsActivity;
import com.example.pc.bottomnav.R;
import java.util.List;
import Model.Food;

/**
 * Created by PC on 5/31/2018.
 */

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Food> mFood;

    public FoodAdapter(List<Food> mFood) {
        this.mFood = mFood;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,parent,false);
        return new FoodViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Food food = mFood.get(mFood.size()-1-position);
        FoodViewHolder viewHolder= (FoodViewHolder) holder;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("NAME", food.getFoody());
                intent.putExtra("CODE", food.getCode());
                context.startActivity(intent);
            }
        });

        viewHolder.txtName.setText(food.getName());
        viewHolder.txtFood.setText(food.getFoody());
    }

    @Override
    public int getItemCount() {
        return mFood.size();
    }

    class FoodViewHolder extends  RecyclerView.ViewHolder{
        TextView txtName, txtFood, txtCode;
        ImageView imgPhoto;

        public FoodViewHolder(View itemView){
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtFood = (TextView) itemView.findViewById(R.id.txt_food);
        }
    }

}
