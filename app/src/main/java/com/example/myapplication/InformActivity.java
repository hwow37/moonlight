package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


public class InformActivity extends AppCompatActivity {
    public FloatingActionButton fab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_search:

                    return true;
                case R.id.navigation_add:

                    return true;
                case R.id.navigation_favorite:

                    return true;
                case R.id.navigation_my:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setCustomView(R.layout.inform_actionbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        InformPageAdapter informPageAdapter = new InformPageAdapter(
                getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(informPageAdapter);

        fab = (FloatingActionButton)findViewById(R.id.fab);

        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
        mTab.bringToFront();
      //  FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout1);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.fab) {
            Intent intent = new Intent(InformActivity.this, InformReviewActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

       // Intent intent = new Intent(InformActivity.this, ReviewActivity.class);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.action_share) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

