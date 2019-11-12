package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.google.firebase.firestore.*;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        Tab1Fragment.OnMyListener, Tab2Fragment.OnMyListener,
        Tab3Fragment.OnMyListener, Tab4Fragment.OnMyListener, Tab5Fragment.OnMyListener  {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SlidingUpPanelLayout mLayout;

    private ArrayList<MyData> mData = new ArrayList<MyData>();
    private GridView mGrid;
    private MyAdapter mAdapter;

    //
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    String search_name;
    int category;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        if (mLayout != null) {
            mLayout.setAnchorPoint(0.4f); // slide up 50% then stop
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

        }
//
        // position  = tabLayout.getSelectedTabPosition();
        // category = mViewPager.getAdapter().getPageTitle(position).toString();



    }

    //
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "채소");
        adapter.addFragment(new Tab2Fragment(), "수산/해산");
        adapter.addFragment(new Tab3Fragment(), "음료/간식");
        adapter.addFragment(new Tab4Fragment(), "정육/계란");
        adapter.addFragment(new Tab5Fragment(), "국/반찬/메인요리");

        viewPager.setAdapter(adapter);
    }
    //

    public void onReceivedData(Object data){
        Intent intent = new Intent(this, DetailActivity.class);
        String datas[] = (String[])data;
        intent.putExtra("img", datas[0]);
        intent.putExtra("name",datas[1]);
        intent.putExtra("price",datas[2]);
        intent.putExtra("category",datas[3]);
        intent.putExtra("country",datas[4]);
        startActivity(intent);
    }

    public void goData(String text, int i)
    {
        search_name= text;
        category = i;

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("search_name", search_name);
        intent.putExtra("category", category);
        startActivity(intent);
    }


}