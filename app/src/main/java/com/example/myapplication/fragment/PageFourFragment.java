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
public class PageFourFragment extends Fragment {

    Context mContext;
    public static PageFourFragment newInstance() {
        Bundle args = new Bundle();
        PageFourFragment fragment = new PageFourFragment();
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
        View view = inflater.inflate(R.layout.fragment_page_four, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardList = new ArrayList<>();
        mAdapter = new CardListAdapter(getContext(),cardList);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }

    }
