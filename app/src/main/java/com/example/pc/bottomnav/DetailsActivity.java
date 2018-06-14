package com.example.pc.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.Food;

/**
 * Created by PC on 6/2/2018.
 */

public class DetailsActivity extends AppCompatActivity {
    Food newPost = null;
    TextView toolbar_title;
    String title_bar, code;
    private Toolbar toolbar;
    private int like, dislike;
    private boolean rated = false;

    private TextView textBrief, textPrice, textAddress, textLike, textDislike;
    private ImageView imgDetails;
    private ImageButton likeButton, dislikeButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imgDetails = (ImageView) findViewById(R.id.image_details);
        textBrief = (TextView) findViewById(R.id.textViewDetail);
        textPrice = (TextView) findViewById(R.id.txt_price);
        textAddress = (TextView) findViewById(R.id.txt_address);
        textLike = (TextView) findViewById(R.id.txt_like);
        textDislike = (TextView) findViewById(R.id.txt_dislike);

        likeButton = (ImageButton) findViewById(R.id.likeButton);
        dislikeButton = (ImageButton) findViewById(R.id.dislikeButton);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        title_bar = intent.getStringExtra("NAME");
        code = intent.getStringExtra("CODE");
        toolbar_title.setText(title_bar);

        reference = firebaseDatabase.getInstance().getReference().child("food");
        Query post = FirebaseDatabase.getInstance().getReference("food").child(code);
        post.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food newPost = dataSnapshot.getValue(Food.class);
                Picasso.get().load(newPost.getImgUrl()).into(imgDetails);
                textBrief.setText(newPost.getBrief());
                textPrice.setText(newPost.getPrice());
                textAddress.setText(newPost.getAddress());
                like = newPost.getLike_num();
                dislike = newPost.getDislike_num();
                textLike.setText(Integer.toString(like));
                textDislike.setText(Integer.toString(dislike));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rated)
                    Toast.makeText(DetailsActivity.this, "Bạn đã bình chọn", Toast.LENGTH_SHORT).show();
                else {
                    like++;
                    reference.child(code).child("like_num").setValue(like);
                    rated = true;
                    Toast.makeText(DetailsActivity.this, "Bạn đã bình chọn thành công", Toast.LENGTH_SHORT).show();

                }
            }
        });


        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rated)
                    Toast.makeText(DetailsActivity.this, "Bạn đã bình chọn", Toast.LENGTH_SHORT).show();
                else {
                    dislike++;
                    reference.child(code).child("dislike_num").setValue(dislike);
                    rated = true;
                    Toast.makeText(DetailsActivity.this, "Bạn đã bình chọn thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
