package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    CardListAdapter cardListAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(FavoriteActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(FavoriteActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    startActivity(new Intent(FavoriteActivity.this, FavoriteActivity.class));
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        final LinearLayoutManager mLayoutManager;
        mRecyclerView = findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(FavoriteActivity.this);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card("이상한 나라의 괴짜들: Geek Zone", "K현대미술관", "500여점의 작품이 전시된 대규모 전시!", "한국 젊은 작가 30여명이 참여하고 500여점의 작품이 전시된 대규모 전시!","2018.3.27 ~ 2018.6.20", "170", R.drawable.image_main));
        cardList.add(new Card("이상한 나라의 괴짜들: Geek Zone", "K현대미술관", "500여점의 작품이 전시된 대규모 전시!", "한국 젊은 작가 30여명이 참여하고 500여점의 작품이 전시된 대규모 전시!","2018.3.27 ~ 2018.6.20", "170", R.drawable.image_main));
        cardListAdapter = new CardListAdapter(this,cardList);
        mRecyclerView.setAdapter(cardListAdapter);
    }
}
