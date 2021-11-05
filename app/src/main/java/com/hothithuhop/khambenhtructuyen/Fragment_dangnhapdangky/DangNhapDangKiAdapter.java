package com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


class ViewTabLayoutAdapter extends FragmentStatePagerAdapter {


    public ViewTabLayoutAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Fragment_dangnhap();
            case 1:
                return new Fragment_dangky();

            default:
                return new  Fragment_dangnhap();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }


}
