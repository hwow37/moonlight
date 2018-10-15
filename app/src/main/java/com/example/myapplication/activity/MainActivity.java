package com.example.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.BottomNavigationViewHelper;
import com.example.myapplication.adapter.PageAdapter;
import com.example.myapplication.R;
import com.example.myapplication.localdatabase.DbOpenHelper;


public class MainActivity extends AppCompatActivity {

    private DbOpenHelper dbOpenHelper;
    private SharedPreferences prefs;
    private long time = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(MainActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                    return true;
                case R.id.navigation_my:
                    startActivity(new Intent(MainActivity.this, MyActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("EasyWordBook", MODE_PRIVATE);
        boolean onDB = prefs.getBoolean("key_firstStart", false);

        dbOpenHelper = new DbOpenHelper(this);
        dbOpenHelper.open();
        // 최초 DB Create
        if (!onDB) {
            dbOpenHelper.insertColumn("#샤롯데씨어터", 0);
            dbOpenHelper.insertColumn("#디큐브", 0);
            dbOpenHelper.insertColumn("#전시회", 0);
            dbOpenHelper.insertColumn("#디뮤지엄", 0);
            dbOpenHelper.insertColumn("#6월", 0);
            dbOpenHelper.insertColumn("#6월 전시회", 0);
            dbOpenHelper.insertColumn("#6월 연극", 0);
            dbOpenHelper.insertColumn("#6월 공연", 0);
            dbOpenHelper.insertColumn("#6월 뮤지컬", 0);
            dbOpenHelper.insertColumn("#이야기", 0);
            dbOpenHelper.insertColumn("#대림미술관", 0);
            dbOpenHelper.insertColumn("#연극", 1);
            dbOpenHelper.insertColumn("#강남미술관", 0);
            dbOpenHelper.insertColumn("#인생사진관", 0);
            dbOpenHelper.insertColumn("#이상한나라", 1);
            dbOpenHelper.insertColumn("#춘화", 0);
            dbOpenHelper.insertColumn("#데스브로피", 0);
            dbOpenHelper.insertColumn("#7월", 0);
            dbOpenHelper.insertColumn("#뮤지컬", 1);
            dbOpenHelper.insertColumn("#라이어", 0);
            dbOpenHelper.insertColumn("#아트홀", 0);
            dbOpenHelper.insertColumn("#대학로", 1);
            dbOpenHelper.insertColumn("#코엑스", 0);
            dbOpenHelper.insertColumn("#5월", 0);
            dbOpenHelper.insertColumn("#시카고", 0);
            dbOpenHelper.insertColumn("#디큐브아트", 0);
            dbOpenHelper.insertColumn("#서울", 0);
            dbOpenHelper.insertColumn("#충무로", 1);
            dbOpenHelper.insertColumn("#영등포", 0);
            dbOpenHelper.insertColumn("#타임스퀘어", 0);
            dbOpenHelper.insertColumn("#예술의전당", 0);
            dbOpenHelper.insertColumn("#극단", 0);
            dbOpenHelper.insertColumn("#무한동력", 0);
            dbOpenHelper.insertColumn("#콘서트", 0);
            dbOpenHelper.insertColumn("#롯데뮤지엄", 0);
            dbOpenHelper.insertColumn("#아드만", 0);
            dbOpenHelper.insertColumn("#특별전", 0);
            dbOpenHelper.insertColumn("#애니메이션", 0);
            dbOpenHelper.insertColumn("#K현대미술관", 0);
            dbOpenHelper.insertColumn("    추천 태그", 0);
        }

        // Shares Preference 수정
        SharedPreferences.Editor ed = prefs.edit();
        ed.putBoolean("key_firstStart", true);
        ed.commit();

        dbOpenHelper.close();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setCustomView(R.layout.main_actionbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        PageAdapter pageAdapter = new PageAdapter(
                getSupportFragmentManager()
        );
        ViewPager mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(pageAdapter);

        TabLayout mTab = (TabLayout) findViewById(R.id.tabs);
        mTab.setupWithViewPager(mViewPager);
        mTab.bringToFront();

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

        /// 해시 키 값 구하기
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finish();
        }
    }
}
