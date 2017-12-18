package com.melvin.frametabshift.bottomNav1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melvin.frametabshift.R;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * @Author Melvin
 * @CreatedDate 2017/11/22 16:54
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/11/22 16:54
 * @Description ${TODO}
 */

public class BottomNav1Activity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {


    ViewPager    mViewpager;

    ImageView    mIvChat;

    TextView     mTvChat;

    LinearLayout mLlChat;

    ImageView    mIvFriends;

    TextView     mTvFriends;

    LinearLayout mLlFriends;

    ImageView    mIvContacts;

    TextView     mTvContacts;

    LinearLayout mLlContacts;

    ImageView    mIvSetting;

    TextView     mTvSetting;

    LinearLayout mLlSetting;

    private Unbinder  mUnbinder;
    private ImageView ivCurrent;//目前显示的ImageView
    private TextView  tvCurrent;//目前显示的TextView
    private List<Fragment> views = new ArrayList();//存储
    private MyPagerAdapter mMyPagerAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.tab_shift_activity_bottomnav1;
    }

    @Override
    protected void initView() {

        mViewpager = (ViewPager) findViewById(R.id.bottomnav1_viewpager);

        mLlChat = (LinearLayout) findViewById(R.id.ll_bottomnav1_chat);
        mLlFriends = (LinearLayout) findViewById(R.id.ll_bottomnav1_friends);
        mLlContacts = (LinearLayout) findViewById(R.id.ll_bottomnav1_contacts);
        mLlSetting = (LinearLayout) findViewById(R.id.ll_bottomnav1_setting);

        mLlChat.setOnClickListener(this);
        mLlFriends.setOnClickListener(this);
        mLlContacts.setOnClickListener(this);
        mLlSetting.setOnClickListener(this);

        mIvChat = (ImageView) findViewById(R.id.iv_bottomnav1_chat);
        mIvFriends = (ImageView) findViewById(R.id.iv_bottomnav1_friends);
        mIvContacts = (ImageView) findViewById(R.id.iv_bottomnav1_contacts);
        mIvSetting = (ImageView) findViewById(R.id.iv_bottomnav1_setting);

        mTvChat = (TextView) findViewById(R.id.tv_bottomnav1_chat);
        mTvFriends = (TextView) findViewById(R.id.tv_bottomnav1_friends);
        mTvContacts = (TextView) findViewById(R.id.tv_bottomnav1_contacts);
        mTvSetting = (TextView) findViewById(R.id.tv_bottomnav1_setting);

        mIvChat.setSelected(true);
        mTvChat.setSelected(true);

        ivCurrent = mIvChat;
        tvCurrent = mTvChat;

        ChatFragment chatFragment = new ChatFragment();
        FriendsFragment friendsFragment = new FriendsFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        SettingFragment settingFragment = new SettingFragment();
        views.add(chatFragment);
        views.add(friendsFragment);
        views.add(contactsFragment);
        views.add(settingFragment);

        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mMyPagerAdapter);

        mViewpager.addOnPageChangeListener(this);



    }

    /**
     * 根据选中切换页面
     * @param id
     */
    private void changeTab(int id) {
        ivCurrent.setSelected(false);
        tvCurrent.setSelected(false);

        switch (id) {
            case R.id.ll_bottomnav1_chat:
                mViewpager.setCurrentItem(0);
            case 0:
                mIvChat.setSelected(true);
                mTvChat.setSelected(true);
                ivCurrent = mIvChat;
                tvCurrent = mTvChat;
                break;
            case R.id.ll_bottomnav1_friends:
                mViewpager.setCurrentItem(1);
            case 1:
                mIvFriends.setSelected(true);
                mTvFriends.setSelected(true);
                ivCurrent = mIvFriends;
                tvCurrent = mTvFriends;
                break;
            case R.id.ll_bottomnav1_contacts:
                mViewpager.setCurrentItem(2);
            case 2:
                mIvContacts.setSelected(true);
                mTvContacts.setSelected(true);
                ivCurrent = mIvContacts;
                tvCurrent = mTvContacts;
                break;
            case R.id.ll_bottomnav1_setting:
                mViewpager.setCurrentItem(3);
            case 3:
                mIvSetting.setSelected(true);
                mTvSetting.setSelected(true);
                ivCurrent = mIvSetting;
                tvCurrent = mTvSetting;
                break;
        }



    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        changeTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }


    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }
    }
}
