package com.example.myapplication;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class InformPageAdapter extends FragmentPagerAdapter{

    public InformPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InformFragment.newInstance();
            case 1:
                return Inform1Fragment.newInstance();
            default:
                return InformFragment.newInstance();
        }
    }

    private static int PAGE_NUMBER = 2;

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "관련 정보";
            case 1:
                return "상세 정보";
            default:
                return null;
        }
    }
}
