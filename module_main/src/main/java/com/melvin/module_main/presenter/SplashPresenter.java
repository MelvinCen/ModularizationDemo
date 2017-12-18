package com.melvin.module_main.presenter;

import android.content.Intent;
import android.os.CountDownTimer;

import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.module_main.ui.main.MainActivity;
import com.melvin.module_main.ui.splash.SplashActivity;
import com.melvin.module_main.ui.splash.SplashView;


/**
 * @Author Melvin
 * @CreatedDate 2017/8/31 13:53
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/31 13:53
 * @Description ${TODO}
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    public SplashPresenter(SplashView splashView) {
        attachView(splashView);
    }

    //由于CountDownTimer有一定的延迟，所以这里设置3400
    private CountDownTimer countDownTimer = new CountDownTimer(3400, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
//            mSpJumpBtn.setText("跳过(" + millisUntilFinished / 1000 + "s)");
            getView().changeCountDownTime(millisUntilFinished);
        }

        @Override
        public void onFinish() {
//            mSpJumpBtn.setText("跳过(" + 0 + "s)");
//            gotoLoginOrMainActivity();
            getView().changeCountDownTime(0);
            getView().goToMain();
        }
    };

    //开始倒计时
    public void startClock(){
        countDownTimer.start();
    }

    //关闭倒计时
    public void closeClock(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    //获取intent
    public Intent getIntent(){
        Intent intent = new Intent((SplashActivity)getView(), MainActivity.class);
        return intent;
    }

}
