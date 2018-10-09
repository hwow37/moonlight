package com.example.myapplication.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.BottomNavigationViewHelper;
import com.example.myapplication.Card;
import com.example.myapplication.adapter.CardListAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

public class FavoriteActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    CardListAdapter cardListAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(FavoriteActivity.this, MainActivity.class).addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT));
                    finish();
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(FavoriteActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(FavoriteActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    return true;
                case R.id.navigation_my:
                    startActivity(new Intent(FavoriteActivity.this, MyActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setCustomView(R.layout.main_actionbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final LinearLayoutManager mLayoutManager;
        mRecyclerView = findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(FavoriteActivity.this);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card("이상한 나라의 괴짜들: Geek Zone", "K현대미술관", "500여점의 작품이 전시된 대규모 전시!", "한국 젊은 작가 30여명이 참여하고 500여점의 작품이 전시된 대규모 전시!", "2018.3.27 ~ 2018.6.20", "170", R.drawable.image_main));
        cardList.add(new Card("이상한 나라의 괴짜들: Geek Zone", "K현대미술관", "500여점의 작품이 전시된 대규모 전시!", "한국 젊은 작가 30여명이 참여하고 500여점의 작품이 전시된 대규모 전시!", "2018.3.27 ~ 2018.6.20", "170", R.drawable.image_main));
        cardListAdapter = new CardListAdapter(this, cardList);
        mRecyclerView.setAdapter(cardListAdapter);

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
