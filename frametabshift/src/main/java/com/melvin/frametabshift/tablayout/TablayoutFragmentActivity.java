package com.melvin.frametabshift.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.melvin.frametabshift.R;
import com.melvin.frametabshift.tablayout.adapter.MyFragmentPageAdapter;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BaseFragment;
import com.melvin.melvincommon.base.BasePresenter;

import java.util.ArrayList;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/26 14:41
 * @Description ${tablayout + fragment切换页面}
 * @Update by       Melvin
 * @Date 2017/10/26 14:41
 * @Description ${TODO}
 */

public class TablayoutFragmentActivity extends BaseActivity {

    //1 ~ 4 个标签
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    //所有fragment页面的集合
    private ArrayList<BaseFragment> fragmentsList;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.tab_shift_activity_tabcontain;
    }

    @Override
    protected void initView() {

        mViewPager = (ViewPager) findViewById(R.id.tab_shift_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_shift_tablayout);

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        fragmentsList = new ArrayList<>();
        fragmentsList.add(fragment1);
        fragmentsList.add(fragment2);
        fragmentsList.add(fragment3);
        fragmentsList.add(fragment4);

        //使用适配器将ViewPager与Fragment绑定在一起
        MyFragmentPageAdapter myFragmentPageAdapter = new MyFragmentPageAdapter(getFragmentManager(), fragmentsList);
        mViewPager.setAdapter(myFragmentPageAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

    }
}
