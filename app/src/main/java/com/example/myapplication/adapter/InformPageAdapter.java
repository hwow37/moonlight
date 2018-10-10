package com.example.myapplication.adapter;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.InformFragment;
import com.example.myapplication.fragment.ReviewFragment;

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
                return ReviewFragment.newInstance();
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
                return "상세 정보";
            case 1:
                return "관련 후기";
            default:
                return null;
        }
    }
}