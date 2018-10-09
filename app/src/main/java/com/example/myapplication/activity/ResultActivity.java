package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.myapplication.BottomNavigationViewHelper;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ResultPageAdapter;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


public class ResultActivity extends AppCompatActivity {

    // 태그
    private EditText editsearch;
    private ImageButton btn_search;
    public Button tag1;
    public Button tag2;
    public Button tag3;
    public Button tag4;
    public Button tag5;
    public ViewPager mViewPager;
    public LinearLayout mlayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(ResultActivity.this, MainActivity.class).addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT));
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(ResultActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(ResultActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    startActivity(new Intent(ResultActivity.this, FavoriteActivity.class));
                    return true;
                case R.id.navigation_my:
                    startActivity(new Intent(ResultActivity.this, MyActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int tags;

        editsearch = (EditText) findViewById(R.id.edit_search);
        tag1 = (Button) findViewById(R.id.btn_tag1);
        tag2 = (Button) findViewById(R.id.btn_tag2);
        tag3 = (Button) findViewById(R.id.btn_tag3);
        tag4 = (Button) findViewById(R.id.btn_tag4);
        tag5 = (Button) findViewById(R.id.btn_tag5);
        btn_search = (ImageButton) findViewById(R.id.btn_search);


        tags = intent.getIntExtra("tagTopOrder", 2);

        switch (tags) {
            case 6:
                tag5.setText(intent.getStringExtra("tag5"));
                tag5.setVisibility(View.VISIBLE);
            case 5:
                tag4.setText(intent.getStringExtra("tag4"));
                tag4.setVisibility(View.VISIBLE);
            case 4:
                tag3.setText(intent.getStringExtra("tag3"));
                tag3.setVisibility(View.VISIBLE);
            case 3:
                tag2.setText(intent.getStringExtra("tag2"));
                tag2.setVisibility(View.VISIBLE);
            case 2:
                tag1.setText(intent.getStringExtra("tag1"));
                tag1.setVisibility(View.VISIBLE);
                break;
        }

        ResultPageAdapter mResultPageAdapter = new ResultPageAdapter(
                getSupportFragmentManager()
        );

        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
        mTab.bringToFront();

        //
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mResultPageAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position != 0) {
                    mlayout = (LinearLayout) findViewById(R.id.search_top);
                    mlayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state != 1) {
                    mlayout = (LinearLayout) findViewById(R.id.search_top);
                    mlayout.setVisibility(View.GONE);
                }

            }
        });

        // navigationBar set
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        Menu menu = navigation.getMenu();
        for (int i = 1; i < 5; i++) {
            MenuItem menuItems = menu.getItem(i);
            menuItems.setCheckable(false);
        }
        MenuItem menuItems2 = menu.getItem(2);
        menuItems2.setChecked(true);
        MenuItem menuItem0 = menu.getItem(0);
        menuItem0.setCheckable(false);
    }
}


