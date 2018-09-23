package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import com.example.myapplication.localdatabase.DbOpenHelper;
import com.example.myapplication.localdatabase.InfoDB;
import com.example.myapplication.localdatabase.SearchAdapter;

public class SearchActivity extends AppCompatActivity {


    private EditText editsearch;
    private ImageButton btn_search;
    public Button tag1;
    public Button tag2;
    public Button tag3;
    public Button tag4;
    public Button tag5;
    public Button tag6;

    // 검색 중간 결과
    private List<InfoDB> list;
    private ListView listview;

    // DB
    private InfoDB infoDB;
    private DbOpenHelper dbOpenHelper;

    // 리스트
    private Cursor cursor;
    private ArrayList<InfoDB> arraylist;
    private SearchAdapter adapter;

    // 태그 생성 및 제거 순서를 위한 변수
    private int tagTopOrder = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(SearchActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(SearchActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(SearchActivity.this, AddActivity.class));
                    return true;
                case R.id.navigation_favorite:
                    startActivity(new Intent(SearchActivity.this, FavoriteActivity.class));
                    return true;
                case R.id.navigation_my:
                    startActivity(new Intent(SearchActivity.this, MyActivity.class));
                    return true;
            }
            return false;
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        editsearch = (EditText) findViewById(R.id.edit_search);
        editsearch.setPrivateImeOptions("defaultInputmode=korean;");
        btn_search = (ImageButton)findViewById(R.id.btn_search);
        listview = (ListView) findViewById(R.id.search_list);
        tag1 = (Button)findViewById(R.id.btn_tag1);
        tag2 = (Button)findViewById(R.id.btn_tag2);
        tag3 = (Button)findViewById(R.id.btn_tag3);
        tag4 = (Button)findViewById(R.id.btn_tag4);
        tag5 = (Button)findViewById(R.id.btn_tag5);
        tag6 = (Button)findViewById(R.id.btn_tag6);

        // DB Create and Open
        dbOpenHelper = new DbOpenHelper(this);
        dbOpenHelper.open();

        list = new ArrayList<InfoDB>();
        arraylist = new ArrayList<InfoDB>();

        // 리스트에 연동될 아답터 생성 및 연결
        adapter = new SearchAdapter(this, arraylist);
        listview.setAdapter(adapter);

        doWhileCursorToArray();
        Collections.sort(arraylist, meanComparator);
        adapter.setArrayList(arraylist);
        list.addAll(arraylist);
        arraylist.clear();
        arraylist.add(list.get(6));
        arraylist.add(list.get(8));
        arraylist.add(list.get(3));
        arraylist.add(list.get(2));
        arraylist.add(list.get(1));
        arraylist.add(list.get(4));

        //검색창
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                search(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });


        // 리스트 아이템 눌렸을 시 버튼 생성
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(arraylist.get(position).getName() == tag1.getText()
                        || arraylist.get(position).getName() == tag2.getText()
                        || arraylist.get(position).getName() == tag3.getText()
                        || arraylist.get(position).getName() == tag4.getText()
                        || arraylist.get(position).getName() == tag5.getText()
                        || arraylist.get(position).getName() == tag6.getText()) {
                }else{setTag(arraylist.get(position).getName());}
            }
        });
    }

    public void search(String charText) {
        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        arraylist.clear();

        // 문자 입력이 없을때는 아무것도 보이지 않는다.
        if (charText.length() == 0) {
            arraylist.clear();
        }
        // 문자 입력을 할때
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < list.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (list.get(i).getName().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    arraylist.add(list.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dbOpenHelper.close();
    }

    /**
     * DB에서 받아온 값을 ArrayList에 Add
     */
    private void doWhileCursorToArray() {

        cursor = null;
        cursor = dbOpenHelper.getAllColumns();

        while (cursor.moveToNext()) {
            infoDB = new InfoDB(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("check_word")));

            arraylist.add(infoDB);
        }

        cursor.close();
    }
    // 정렬, import 주의(android.~)
    private final static Comparator<InfoDB> meanComparator = new Comparator<InfoDB>() {
        private final Collator collator = Collator.getInstance();

        @Override
        public int compare(InfoDB object1, InfoDB object2) {
            return collator.compare(object1.getName(), object2.getName());
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                // 검색 버튼 클릭 시
                Intent intent = new Intent(SearchActivity.this,ResultActivity.class);
                intent.putExtra("tag1",tag1.getText().toString());
                intent.putExtra("tag2",tag2.getText().toString());
                intent.putExtra("tag3",tag3.getText().toString());
                intent.putExtra("tag4",tag4.getText().toString());
                intent.putExtra("tag5",tag5.getText().toString());
                intent.putExtra("tag6",tag6.getText().toString());
                intent.putExtra("tagTopOrder",tagTopOrder);
                startActivity(intent);
                break;

            // 태그 버튼 클릭 시 버튼 제거
            case R.id.btn_tag1:
                if(2 == tagTopOrder){
                    tag1.setText("");
                    tag1.setVisibility(View.GONE);
                    tagTopOrder--;
                }else{
                    arrTag(1);
                }
                break;
            case R.id.btn_tag2:
                if(3 == tagTopOrder){
                    tag2.setText("");
                    tag2.setVisibility(View.GONE);
                    tagTopOrder--;
                }else{
                    arrTag(2);
                }
                break;
            case R.id.btn_tag3:
                if(4 == tagTopOrder){
                    tag3.setText("");
                    tag3.setVisibility(View.GONE);
                    tagTopOrder--;
                }else{
                    arrTag(3);
                }
                break;
            case R.id.btn_tag4:
                if(5 == tagTopOrder){
                    tag4.setText("");
                    tag4.setVisibility(View.GONE);
                    tagTopOrder--;
                }else{
                    arrTag(4);
                }
                break;
            case R.id.btn_tag5:
                if(6 == tagTopOrder){
                    tag5.setText("");
                    tag5.setVisibility(View.GONE);
                    tagTopOrder--;
                }else{
                    arrTag(5);
                }
                break;
            case R.id.btn_tag6:
                tag6.setText("");
                tag6.setVisibility(View.GONE);
                tagTopOrder--;
                break;
        }
    }

    // 리스트 아이템 클릭 시 버튼 생성
    private void setTag(String string){
        switch (tagTopOrder) {
            case 1:
                tag1.setText(string);
                tag1.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
            case 2:
                tag2.setText(string);
                tag2.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
            case 3:
                tag3.setText(string);
                tag3.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
            case 4:
                tag4.setText(string);
                tag4.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
            case 5:
                tag5.setText(string);
                tag5.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
            case 6:
                tag6.setText(string);
                tag6.setVisibility(View.VISIBLE);
                tagTopOrder++;
                break;
        }
    }

    // 태그 중간에 지워도 맨 뒤에 추가되도록 하는 함수
    private void arrTag(int order){
        int i;
        for(i = order;i<tagTopOrder-1;i++){
            if(i == 1){
                tag1.setText(tag2.getText());
                tag2.setText("");
                tag2.setVisibility(View.GONE);
            }else if(i == 2){
                tag2.setText(tag3.getText());
                tag2.setVisibility(View.VISIBLE);
                tag3.setText("");
                tag3.setVisibility(View.GONE);
            }else if(i == 3){
                tag3.setText(tag4.getText());
                tag3.setVisibility(View.VISIBLE);
                tag4.setText("");
                tag4.setVisibility(View.GONE);
            }else if(i == 4){
                tag4.setText(tag5.getText());
                tag4.setVisibility(View.VISIBLE);
                tag5.setText("");
                tag5.setVisibility(View.GONE);
            }else if(i == 5){
                tag5.setText(tag6.getText());
                tag5.setVisibility(View.VISIBLE);
                tag6.setText("");
                tag6.setVisibility(View.GONE);
            }
        }
        tagTopOrder--;
    }
}
