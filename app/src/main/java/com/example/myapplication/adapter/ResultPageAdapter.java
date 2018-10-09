package com.example.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.ResultInfoFragment;
import com.example.myapplication.fragment.ResultReviewFragment;

public class ResultPageAdapter extends FragmentPagerAdapter {

    public ResultPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ResultInfoFragment.newInstance();
            case 1:
                return ResultReviewFragment.newInstance();
            default:
                return ResultInfoFragment.newInstance();
        }
    }

    private  static  int PAGE_NUMBER = 2;

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "검색 결과";
            case 1:
                return "관련 후기";
            default:
                return null;
        }
    }
}

