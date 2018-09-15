package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

