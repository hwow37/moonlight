package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformFragment extends Fragment {


    public static InformFragment newInstance() {
        Bundle args = new Bundle();

        InformFragment fragment = new InformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inform_fragment, container, false);
        Button b1 = (Button)view.findViewById(R.id.btn1);
        Button b2 = (Button)view.findViewById(R.id.btn2);
        Button b3 = (Button)view.findViewById(R.id.btn3);

        b1.setText("#보티니카");
        b2.setText("#갤러리아포레");
        b3.setText("#서울숲역");
        return view;
    }

}
