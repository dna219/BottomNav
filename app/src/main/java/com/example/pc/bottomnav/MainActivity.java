package com.example.pc.bottomnav;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import layout.AdditionFragment;
import layout.DashFragment;
import layout.HomeFragment;
import layout.NotiFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private Toolbar toolbar;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String stringTitle = "SEFO" ;
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = DashFragment.newInstance();
                    stringTitle = "DASHBOARD";
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = NotiFragment.newInstance();
                    stringTitle = "THÔNG BÁO";
                    break;
                case R.id.navigation_addition:
                    selectedFragment = AdditionFragment.newInstance();
                    stringTitle = "CÀI ĐẶT";
                    break;
                default:
                    stringTitle = "SEFO";
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, selectedFragment);
            fragmentTransaction.commit();
            toolbar.setTitle(stringTitle);
            return true;
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, HomeFragment.newInstance());
        fragmentTransaction.commit();

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("app_title");
    }



    @Override
    public void onItemPressed(Uri uri) {
    }
}
