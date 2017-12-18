package com.melvin.melvincommon.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;

import com.melvin.melvincommon.utils.CommonRefUtils;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @Author Melvin
 * @CreatedDate 2017/8/2 15:58
 * @Description ${TODO}
 * @Update by       MelvinCen
 * @Date 2017/8/2 15:58
 * @Description ${TODO}
 */

public class BaseApplication extends Application {

    private static Context appContext;

    /**
     * 主线程Handler
     */
    public static Handler mMainThreadHandler;

    /**
     * 主线程ID
     */
    public static int mMainThreadId = -1;
    /**
     * 主线程
     */
    public static Thread mMainThread;

    /**
     * 主线程Looper
     */
    public static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化thread和handler
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        appContext = getApplicationContext();
        CommonRefUtils.init(this);
        Hawk.init(this).build();
        PrettyFormatStrategy prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("Melvin--Log")
                .build();
        //必须在CommonRefUtils初始化之后才能使用isAppDebug方法
        Logger.addLogAdapter(new AndroidLogAdapter(prettyFormatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return CommonRefUtils.isAppDebug();
            }
        });

        Logger.e("BaseApplication启动");

    }


    //全局的context改为用CommonRefUtils工具提供
    public static Context getAppContext(){
        return appContext;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * 获取设备唯一标识
     *
     */

    public String getDeviceId() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_token = tm.getDeviceId();
        return device_token;
    }

}
