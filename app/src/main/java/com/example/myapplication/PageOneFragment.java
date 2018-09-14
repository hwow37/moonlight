package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageOneFragment extends Fragment {

    Context mContext;

    public static PageOneFragment newInstance() {
        Bundle args = new Bundle();

        PageOneFragment fragment = new PageOneFragment();
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

        View view = inflater.inflate(R.layout.fragment_page_one, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardList = new ArrayList<>();
        mAdapter = new CardListAdapter(getContext(),cardList);
        mRecyclerView.setAdapter(mAdapter);

     //   myList.add(new My("이상한 나라의 괴짜들: Geek Zone", "K현대미술관", "500여점의 작품이 전시된 대규모 전시!", "한국 젊은 작가 30여명이 참여하고 500여점의 작품이 전시된 대규모 전시!","2018.3.27 ~ 2018.6.20", "170", R.drawable.image_1_6));

        return view;

    }

}
