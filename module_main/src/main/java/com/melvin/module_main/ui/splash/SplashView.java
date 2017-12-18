package com.melvin.module_main.ui.splash;


import com.melvin.melvincommon.base.BaseView;

/**
 * @Author Melvin
 * @CreatedDate 2017/8/31 13:56
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/31 13:56
 * @Description ${TODO}
 */

public interface SplashView extends BaseView {

    void changeCountDownTime(long time);

    void goToMain();

    void getDataEmpty(Throwable t);
}
