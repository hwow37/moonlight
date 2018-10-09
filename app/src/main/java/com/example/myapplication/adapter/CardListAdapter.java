package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Card;
import com.example.myapplication.R;
import com.example.myapplication.activity.InformActivity;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter< CardListAdapter.ViewHolder> {

    Context context;
    private List<Card> cardList;


    public CardListAdapter(Context context, List<Card> cardList) {

        this.context = context;
        this.cardList = cardList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.card_view, parent, false);

        return new ViewHolder(v);

    }


    @Override

    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mTitle.setText(cardList.get(position).text);
        holder.mPlace.setText(cardList.get(position).text1);
        holder.mContentTitle.setText(cardList.get(position).text2);
        holder.mContent.setText(cardList.get(position).text3);
        holder.mContent1.setText(cardList.get(position).text4);
        holder.mCount.setText(cardList.get(position).text5);
        holder.mImageView.setImageResource(cardList.get(position).img);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, InformActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }


    @Override

    public int getItemCount() {

        return cardList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public final View mView;
        public final TextView mTitle;
        public final TextView mPlace;
        public final ImageView mImageView;
        public final TextView mContentTitle;
        public final TextView mContent;
        public final TextView mContent1;
        public final TextView mCount;
        public final CardView cv;


        public ViewHolder(View view) {

            super(view);

            mView = view;
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mPlace = (TextView) view.findViewById(R.id.tv_place);
            mImageView=(ImageView) view.findViewById(R.id.iv_image);
            mContentTitle = (TextView) view.findViewById(R.id.tv_content_title);
            mContent = (TextView) view.findViewById(R.id.tv_content);
            mContent1 = (TextView) view.findViewById(R.id.tv_content1);
            mCount=(TextView)view.findViewById((R.id.tv_count));
            cv = (CardView) view.findViewById(R.id.cv);


        }

    }


}
