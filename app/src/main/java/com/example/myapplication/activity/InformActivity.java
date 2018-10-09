package com.example.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.BottomNavigationViewHelper;
import com.example.myapplication.adapter.InformPageAdapter;
import com.example.myapplication.R;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;


public class InformActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(InformActivity.this, MainActivity.class).addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT));
                    finish();
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(InformActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(InformActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    startActivity(new Intent(InformActivity.this, FavoriteActivity.class));
                    return true;
                case R.id.navigation_my:
                    startActivity(new Intent(InformActivity.this, MyActivity.class));
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

        Log.i("onClick", "InformActivity");

        InformPageAdapter informPageAdapter = new InformPageAdapter(
                getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(informPageAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
        mTab.bringToFront();
        //FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout1);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Intent intent = new Intent(InformActivity.this, ReviewActivity.class);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_share) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

