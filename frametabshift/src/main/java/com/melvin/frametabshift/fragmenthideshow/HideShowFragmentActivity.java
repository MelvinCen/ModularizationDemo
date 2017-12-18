package com.melvin.frametabshift.fragmenthideshow;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.melvin.frametabshift.R;
import com.melvin.frametabshift.appfragment.ChatFragment;
import com.melvin.frametabshift.appfragment.ContactsFragment;
import com.melvin.frametabshift.appfragment.FriendsFragment;
import com.melvin.frametabshift.appfragment.SettingFragment;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;

/**
 * @Author Melvin
 * @CreatedDate 2017/11/23 15:53
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/11/23 15:53
 * @Description ${TODO}
 */

public class HideShowFragmentActivity extends BaseActivity {

    private LinearLayout mLlChat,mLlFriends,mLlContacts,mLlSetting;
    private TextView mTvChat,mTvFriends,mTvContacts,mTvSetting;
    private ImageView          mIvChat,mIvFriends,mIvContacts,mIvSetting;
    private FragmentSwitchTool mFragmentSwitchTool;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.tab_shift_activity_hideshow;
    }

    @Override
    protected void initView() {

        mLlChat = (LinearLayout) findViewById(R.id.ll_bottomnav1_chat);
        mLlFriends = (LinearLayout) findViewById(R.id.ll_bottomnav1_friends);
        mLlContacts = (LinearLayout) findViewById(R.id.ll_bottomnav1_contacts);
        mLlSetting = (LinearLayout) findViewById(R.id.ll_bottomnav1_setting);

        mIvChat = (ImageView) findViewById(R.id.iv_bottomnav1_chat);
        mIvFriends = (ImageView) findViewById(R.id.iv_bottomnav1_friends);
        mIvContacts = (ImageView) findViewById(R.id.iv_bottomnav1_contacts);
        mIvSetting = (ImageView) findViewById(R.id.iv_bottomnav1_setting);

        mTvChat = (TextView) findViewById(R.id.tv_bottomnav1_chat);
        mTvFriends = (TextView) findViewById(R.id.tv_bottomnav1_friends);
        mTvContacts = (TextView) findViewById(R.id.tv_bottomnav1_contacts);
        mTvSetting = (TextView) findViewById(R.id.tv_bottomnav1_setting);

        mFragmentSwitchTool = new FragmentSwitchTool(getFragmentManager(), R.id.fl_Container);
        mFragmentSwitchTool.setClickableViews(mLlChat,mLlFriends,mLlContacts,mLlSetting);
        mFragmentSwitchTool.addSelectedViews(new View[]{mIvChat,mTvChat}).addSelectedViews(new View[]{mIvFriends,mTvFriends})
                .addSelectedViews(new View[]{mIvContacts,mTvContacts}).addSelectedViews(new View[]{mIvSetting,mTvSetting});
        mFragmentSwitchTool.setFragments(ChatFragment.class, FriendsFragment.class, ContactsFragment.class, SettingFragment.class);

        mFragmentSwitchTool.changeTag(mLlChat);

    }
}
