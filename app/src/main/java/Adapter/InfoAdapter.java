package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.bottomnav.R;

import java.util.List;

import Model.Food;

/**
 * Created by PC on 5/31/2018.
 */

public class InfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Food> mFood;

    public InfoAdapter(List<Food> mFood) {
        this.mFood = mFood;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        return new FoodViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Food food = mFood.get(mFood.size() - 1 - position);
        FoodViewHolder viewHolder = (FoodViewHolder) holder;

        viewHolder.txtName.setText(food.getName());
        viewHolder.txtFood.setText(food.getPrice());
        viewHolder.txtCode.setText(food.getCode());
    }

    @Override
    public int getItemCount() {

        return mFood.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtFood, txtCode;
        ImageView imgPhoto;

        public FoodViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtFood = (TextView) itemView.findViewById(R.id.txt_food);
            txtCode = (TextView) itemView.findViewById(R.id.txt_code);
            imgPhoto = (ImageView) itemView.findViewById((R.id.imageView));
        }
    }

}