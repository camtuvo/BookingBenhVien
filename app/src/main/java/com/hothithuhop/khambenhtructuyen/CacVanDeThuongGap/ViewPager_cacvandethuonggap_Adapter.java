package com.hothithuhop.khambenhtructuyen.CacVanDeThuongGap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPager_cacvandethuonggap_Adapter extends FragmentStatePagerAdapter {

    public ViewPager_cacvandethuonggap_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return  new TienloiFragment();
            case 1:
                return  new hotroFragment();
            default:
                return new TienloiFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position)
        {
            case 0:
                title="Tiện lợi";
                break;
            case 1:
                title="Hỗ trợ";
                break;
        }
        return title;
    }
}
