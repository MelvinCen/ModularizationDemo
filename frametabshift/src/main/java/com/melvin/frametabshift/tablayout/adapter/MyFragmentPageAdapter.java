package com.melvin.frametabshift.tablayout.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.view.ViewGroup;

import com.melvin.frametabshift.tablayout.FragmentPagerAdapter;
import com.melvin.melvincommon.base.BaseFragment;

import java.util.List;

/**
 * @Author Melvin
 * @CreatedDate 2017/9/29 14:50
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/9/29 14:50
 * @Description ${TODO}
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"Tab1", "Tab2", "Tab3","Tab4"};

    private List<BaseFragment> fragmentsList;


    public MyFragmentPageAdapter(FragmentManager fm, List<BaseFragment> fragmentsLists) {
        super(fm);
        fragmentsList = fragmentsLists;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
