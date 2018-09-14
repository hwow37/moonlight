package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inform1Fragment extends Fragment {


    public static Inform1Fragment newInstance() {
        Bundle args = new Bundle();

        Inform1Fragment fragment = new Inform1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inform1_fragment, container, false);

        ImageView imageView1 = (ImageView)view.findViewById(R.id.detail1);
        return view;
    }

}
