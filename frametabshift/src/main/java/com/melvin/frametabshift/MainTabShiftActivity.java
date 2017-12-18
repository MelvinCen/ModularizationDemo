package com.melvin.frametabshift;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.annotation.Router;
import com.melvin.frametabshift.bottomNav1.BottomNav1Activity;
import com.melvin.frametabshift.bottomNavigationView.BottomNavigationViewDemoActivity;
import com.melvin.frametabshift.fragmenthideshow.HideShowFragmentActivity;
import com.melvin.frametabshift.fragmenttabhost.FragmentTabhostActivity;
import com.melvin.frametabshift.tablayout.TablayoutFragmentActivity;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/26 14:22
 * @Description ${页面切换功能收集主页}
 * @Update by       Melvin
 * @Date 2017/10/26 14:22
 * @Description ${TODO}
 */

@Router("MainTabShiftActivity")
public class MainTabShiftActivity extends BaseActivity implements View.OnClickListener {

    //跳转至tablayout+fragment实现切换的页面
    private Button mBtTablyout;
    private Button mBtViewpager1;
    private Button mBtFragmenttabhost;
    private Button mBtFragmentHideShow;
    private Button mBtBottomNavigationView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.tab_shift_activity_main;
    }

    @Override
    protected void initView() {

        mBtTablyout = (Button) findViewById(R.id.tab_shift_main_bt_tablayout);
        mBtTablyout.setOnClickListener(this);

        mBtViewpager1 = (Button) findViewById(R.id.tab_shift_main_bt_bottomnav1);
        mBtViewpager1.setOnClickListener(this);

        mBtFragmenttabhost = (Button) findViewById(R.id.tab_shift_main_bt_fragmenttabhost);
        mBtFragmenttabhost.setOnClickListener(this);

        mBtFragmentHideShow = (Button) findViewById(R.id.tab_shift_main_bt_fragmenthideshow);
        mBtFragmentHideShow.setOnClickListener(this);

        mBtBottomNavigationView = (Button) findViewById(R.id.tab_shift_main_bt_bottomnavigationview);
        mBtBottomNavigationView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_shift_main_bt_tablayout:
                    startActivity(new Intent(this, TablayoutFragmentActivity.class));
                break;
            case R.id.tab_shift_main_bt_bottomnav1:
                startActivity(new Intent(this, BottomNav1Activity.class));
                break;
            case R.id.tab_shift_main_bt_fragmenttabhost:
                startActivity(new Intent(this, FragmentTabhostActivity.class));
                break;
            case R.id.tab_shift_main_bt_fragmenthideshow:
                startActivity(new Intent(this, HideShowFragmentActivity.class));
                break;
            case R.id.tab_shift_main_bt_bottomnavigationview:
                startActivity(new Intent(this, BottomNavigationViewDemoActivity.class));
                break;
        }
    }
}
