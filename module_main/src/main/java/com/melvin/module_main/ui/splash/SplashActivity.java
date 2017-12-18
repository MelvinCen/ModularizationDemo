package com.melvin.module_main.ui.splash;

import android.view.View;
import android.widget.Button;

import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.module_main.R;
import com.melvin.module_main.presenter.SplashPresenter;




/**
 * @Author Melvin
 * @CreatedDate 2017/8/31 13:53
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/31 13:53
 * @Description ${TODO}
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView, View.OnClickListener {


    Button    mSpJumpBtn;

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    protected int layoutRes() {

        return R.layout.module_main_activity_splash;
    }

    @Override
    protected void initView() {
        mSpJumpBtn = (Button) findViewById(R.id.sp_jump_btn);
        mSpJumpBtn.setOnClickListener(this);
        mSpJumpBtn.setVisibility(View.VISIBLE);
        mPresenter.startClock();

    }

    @Override
    public void changeCountDownTime(long time) {
        mSpJumpBtn.setText("跳过(" + time / 1000 + "s)");
    }

    @Override
    public void getDataEmpty(Throwable t) {

    }

    @Override
    public void onRequestSuccessData(Object data) {

    }



    //跳转去主页
    @Override
    public void goToMain() {
        if (mPresenter.getIntent() != null) {
            startActivity(mPresenter.getIntent());
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.closeClock();
    }

    @Override
    public void onClick(View v) {
        goToMain();
    }
}
