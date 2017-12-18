package com.melvin.barcodescan.rxtoolsmake.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.melvin.barcodescan.R;
import com.melvin.barcodescan.rxtoolsmake.scaner.BeepManager;
import com.melvin.barcodescan.rxtoolsmake.scaner.CameraManager;
import com.melvin.barcodescan.rxtoolsmake.scaner.CaptureActivityHandler;
import com.melvin.barcodescan.rxtoolsmake.scaner.decoding.InactivityTimer;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.melvincommon.utils.PhotoUtils;
import com.melvin.melvincommon.utils.QrBarUtils;
import com.melvin.melvincommon.utils.ToastUtils;
import com.melvin.melvincommon.utils.statusbar.UtimateStatusBarUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Melvin
 * @CreatedDate 2017/11/3 10:09
 * @Description ${TODO}
 * @Update by       Melvin
 * @Date 2017/11/3 10:09
 * @Description ${TODO}
 */

public class ScannerCodeActivity extends BaseActivity implements SurfaceHolder.Callback {

    private RelativeLayout mContainer;//整个根布局
    private RelativeLayout mCropLayout;//扫描框的根布局

    private int mCropWidth = 0;//扫描边界的宽度
    private int mCropHeight = 0;//扫描边界的高度

    private boolean hasSurface;//是否有预览

    private InactivityTimer inactivityTimer;

    private CaptureActivityHandler handler;//扫描处理

    private boolean mFlashing = false;//闪光灯开启状态,默认关闭

    private BeepManager beepManager;//声音与震动
    private SurfaceView surfaceView;//预览界面

    private int openAlbumRequestCode = 5000;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.barcode_activity_scaner_code;
    }

    @Override
    protected void initView() {
        Logger.e("ScannerCodeActivity---initView");
        //透明状态栏和导航栏
        UtimateStatusBarUtil utimateStatusBarUtil = new UtimateStatusBarUtil(this);
        utimateStatusBarUtil.setImmersionBar();
        //初始化控件

        mContainer = (RelativeLayout) findViewById(R.id.rxtools_capture_containter);
        mCropLayout = (RelativeLayout) findViewById(R.id.capture_crop_layout);
        surfaceView = (SurfaceView) findViewById(R.id.rxtools_capture_preview);

        initScanerAnimation();//开启扫描动画效果

        hasSurface = false;//是否有预览

        inactivityTimer = new InactivityTimer(this);

        beepManager = new BeepManager(this);


    }



    @Override
    protected void onResume() {
        super.onResume();

        CameraManager.init(this);//初始化 CameraManager

        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (hasSurface) {
            initCamera(surfaceHolder);//Camera初始化
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Logger.e("*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();

        beepManager.close();

        if (!hasSurface) {
            surfaceView.getHolder().removeCallback(this);
        }
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void btn(View view){
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_mask_light:
                light();
                break;
            case  R.id.top_openpicture:
                //TODO:从图片中识别
                PhotoUtils.openAlbum(this,openAlbumRequestCode);
                break;
        }
    }

    public void handleDecode(Result result) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();;//扫描成功之后的振动与声音提示

        String result1 = result.getText();

        ToastUtils.showToastSafe("扫描结果："+result1);
        //不重启预览只能扫描一次
        restartPreviewAfterDelay(2000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.e("onActivityResult--onActivityResult");
        if (resultCode == RESULT_OK) {
            Logger.e("选择相片返回成功");
            Bitmap photo = PhotoUtils.getBitmapFromUri(data.getData(), this);

            Result result = QrBarUtils.decodeFromPhoto(photo);
            if (result != null) {
                BarcodeFormat type = result.getBarcodeFormat();
                String resultText = result.getText();
                if (BarcodeFormat.QR_CODE.equals(type)) {

                    ToastUtils.showToastSafe("二维码扫描结果"+resultText);
                } else if (BarcodeFormat.EAN_13.equals(type)) {

                    ToastUtils.showToastSafe("条形码扫描结果"+resultText);
                } else {

                    ToastUtils.showToastSafe("扫描结果"+resultText);
                }
            } else {
                ToastUtils.showToastSafe("图片识别失败");
            }
        }
    }

    /**
     * 重启预览的方法，如果扫描界面扫描后不被覆盖失去焦点，还在当前页面，需要调用该方法重启启用扫描功能
     * @param delayMS 重启预览时间，单位毫秒
     */
    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.barcode_restart_preview, delayMS);
        }
    }

    /**
     *
     * @return
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Camera初始化
     * @param surfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (CameraManager.get().isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            CameraManager.get().openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a RuntimeException!!!
            if (handler == null) {
                handler = new CaptureActivityHandler(this);
            }

            Point point = CameraManager.get().getCameraResolution();
            AtomicInteger width = new AtomicInteger(point.y);
            AtomicInteger height = new AtomicInteger(point.x);
            int cropWidth = mCropLayout.getWidth() * width.get() / mContainer.getWidth();
            int cropHeight = mCropLayout.getHeight() * height.get() / mContainer.getHeight();
            setCropWidth(cropWidth);
            setCropHeight(cropHeight);
        } catch (IOException ex) {
            Logger.w(""+ex);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Logger.w("Unexpected error initializing camera"+e);
            displayFrameworkBugMessageAndExit();
        }

    }

    /**
     * 抛出异常时的提示对话框
     */
    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("相机打开出错，请稍后重试");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int getCropWidth() {
        return mCropWidth;
    }

    public void setCropWidth(int cropWidth) {
        mCropWidth = cropWidth;
        CameraManager.FRAME_WIDTH = mCropWidth;

    }

    public int getCropHeight() {
        return mCropHeight;
    }

    public void setCropHeight(int cropHeight) {
        this.mCropHeight = cropHeight;
        CameraManager.FRAME_HEIGHT = mCropHeight;
    }

    /**
     * 开启扫描动画效果
     */
    private void initScanerAnimation() {
        ImageView mQrLineView = (ImageView) findViewById(R.id.capture_scan_line);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1200);
        mQrLineView.startAnimation(animation);
    }

    /**
     * 闪光灯开关
     */
    private void light() {
        if (mFlashing) {
            mFlashing = false;
            // 关闪光灯
            CameraManager.get().offLight();

        } else {
            mFlashing = true;
            // 开闪光灯
            CameraManager.get().openLight();


        }

    }


}
