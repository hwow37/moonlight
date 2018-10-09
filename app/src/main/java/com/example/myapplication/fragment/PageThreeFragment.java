package com.example.myapplication.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Card;
import com.example.myapplication.adapter.CardListAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageThreeFragment extends Fragment {

    Context mContext;
    public static PageThreeFragment newInstance() {
        Bundle args = new Bundle();

        PageThreeFragment fragment = new PageThreeFragment();
        fragment.setArguments(args);
        return fragment;

    }
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Card> cardList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_three, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardList = new ArrayList<>();
        mAdapter = new CardListAdapter(getContext(),cardList);
        mRecyclerView.setAdapter(mAdapter);

      //  myList.add(new My("그대와 영원히", "대학로 한성아트홀", "실화를 바탕으로 진한 감동과 웃음 전달!", "남녀노소 모두에게 사랑받는 감성 가득한 공연", "2018.3.27 ~ 2018.6.20","352",R.drawable.image_3_1));


        return view;
    }

}
