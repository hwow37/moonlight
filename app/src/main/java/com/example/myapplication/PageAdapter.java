package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return PageOneFragment.newInstance();
            case 1:

                return PageTwoFragment.newInstance();
            case 2:

                return PageThreeFragment.newInstance();
            case 3:

                return PageFourFragment.newInstance();
            default:
                return PageOneFragment.newInstance();
        }
    }

    private  static  int PAGE_NUMBER = 4;

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "전시회";
            case 1:
                return "연극";
            case 2:
                return "뮤지컬";
            case 3:
                return "공연";
            default:
                return null;
        }
    }
}
