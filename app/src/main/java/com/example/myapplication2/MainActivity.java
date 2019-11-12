package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Tab1Fragment.OnMyListener, Tab2Fragment.OnMyListener, Tab3Fragment.OnMyListener, Tab4Fragment.OnMyListener, Tab5Fragment.OnMyListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SlidingUpPanelLayout mLayout;

    private ArrayList<MyData> mData = new ArrayList<MyData>();
    private GridView mGrid;
    private MyAdapter mAdapter;

    //
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        if (mLayout != null) {
            mLayout.setAnchorPoint(0.4f); // slide up 50% then stop
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

        }
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "채소");
        adapter.addFragment(new Tab2Fragment(), "수산/해산");
        adapter.addFragment(new Tab3Fragment(), "음료/간식");
        adapter.addFragment(new Tab4Fragment(), "정육/계란");
        adapter.addFragment(new Tab5Fragment(), "국/반찬/메인요리");

        viewPager.setAdapter(adapter);
    }

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
}