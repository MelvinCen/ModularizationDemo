package com.melvin.frametabshift.fragmenttabhost;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
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
 * @CreatedDate 2017/11/23 14:10
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/11/23 14:10
 * @Description ${TODO}
 */

public class FragmentTabhostActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    private FrameLayout mFlContent;
    private FragmentTabHost mTabhost;

    private String[] tabText = { "聊天", "朋友", "通讯录", "设置" };
    private int[] imageRes = new int[] { R.drawable.tab_shift_tab_chat, R.drawable.tab_shift_tab_friends, R.drawable.tab_shift_tab_contacts, R.drawable.tab_shift_tab_setting };
    private Class[] fragments = new Class[] { ChatFragment.class, FriendsFragment.class, ContactsFragment.class, SettingFragment.class };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.tab_shift_activity_fragmenttabhost;
    }

    @Override
    protected void initView() {

        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTabhost = (FragmentTabHost) findViewById(R.id.tabhost);

        mTabhost.setup(this,getFragmentManager(),R.id.fl_content);
        mTabhost.getTabWidget().setDividerDrawable(null);
        mTabhost.setOnTabChangedListener(this);

        initTab();

    }

    private void initTab() {
        for (int i = 0; i < tabText.length; i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.tab_shift_item_tab, null);
            ((TextView) view.findViewById(R.id.tv)).setText(tabText[i]);
            ((ImageView) view.findViewById(R.id.iv)).setImageResource(imageRes[i]);

            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(tabText[i]).setIndicator(view);
            mTabhost.addTab(tabSpec, fragments[i], null);
            mTabhost.setTag(i);
        }
    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
