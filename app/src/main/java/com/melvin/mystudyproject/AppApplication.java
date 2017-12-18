package com.melvin.mystudyproject;

import android.content.Context;
import android.util.Log;

import com.github.mzule.activityrouter.annotation.Modules;
import com.melvin.melvincommon.base.BaseApplication;
import com.melvin.melvincommon.utils.CommonRefUtils;
import com.orhanobut.logger.Logger;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.collector.CrashReportData;
import org.acra.sender.EmailIntentSender;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

/**
 * @Author Melvin
 * @CreatedDate 2017/10/12 13:42
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/10/12 13:42
 * @Description ${TODO}
 */
//router一定要添加app壳工程的module否则会报找不到类的错误
@Modules({"app","main","pulltorefresh","tabshift","TableExample","BarCodeScan"})
@ReportsCrashes(
        mailTo = "22925941@qq.com",
        mode = ReportingInteractionMode.DIALOG,
        customReportContent = {
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.PHONE_MODEL,
                ReportField.CUSTOM_DATA,
                ReportField.BRAND,
                ReportField.STACK_TRACE,
                ReportField.LOGCAT,
                ReportField.USER_COMMENT},
        resToastText = R.string.crash_toast_text,
        resDialogText = R.string.crash_dialog_text,
        resDialogTitle = R.string.crash_dialog_title)
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //崩溃日志记录初始化
        ACRA.init(this);
        ACRA.getErrorReporter().removeAllReportSenders();
        ACRA.getErrorReporter().setReportSender(new CrashReportSender());



        boolean appDebug = CommonRefUtils.isAppDebug();
        Log.e("启动AppApplication","appDebug = " + appDebug);
        Logger.e("AppApplication启动");
    }

    /**
     * 发送崩溃日志
     */
    private class CrashReportSender implements ReportSender {
        CrashReportSender() {
            ACRA.getErrorReporter().putCustomData("PLATFORM", "ANDROID");
            ACRA.getErrorReporter().putCustomData("BUILD_ID", android.os.Build.ID);
            ACRA.getErrorReporter().putCustomData("DEVICE_NAME", android.os.Build.PRODUCT);
        }

        @Override
        public void send(Context context, CrashReportData crashReportData) throws ReportSenderException {
            EmailIntentSender emailSender = new EmailIntentSender(getApplicationContext());
            emailSender.send(context, crashReportData);
        }
    }
}
