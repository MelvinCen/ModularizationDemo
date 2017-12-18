package com.melvin.barcodescan;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.github.mzule.activityrouter.annotation.Router;
import com.melvin.barcodescan.rxtoolsmake.ui.ScannerCodeActivity;
import com.melvin.barcodescan.simplify.activity.CaptureActivity;
import com.melvin.melvincommon.base.BaseActivity;
import com.melvin.melvincommon.base.BasePresenter;
import com.melvin.melvincommon.utils.ToastUtils;
import com.melvin.melvincommon.utils.permission.DefaultPermission;
import com.melvin.melvincommon.utils.permission.PermissionListener;
import com.orhanobut.logger.Logger;

import java.util.List;

@Router("BarCodeScanMainActivity")
public class BarCodeScanMainActivity extends BaseActivity implements View.OnClickListener,PermissionListener {

    private Button        mBtOpenScanCode;
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 103;
    public static final int REQUEST_CODE_SETTING           = 300;
    private Button mBtRxToolsScanner;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutRes() {
        return R.layout.barcode_activity_main;
    }

    @Override
    protected void initView() {
        requestPermissions();
        mBtOpenScanCode = (Button) findViewById(R.id.barcode_open_scan_code);
        mBtRxToolsScanner = (Button) findViewById(R.id.barcode_rxtools_scanner);
        mBtOpenScanCode.setOnClickListener(this);
        mBtRxToolsScanner.setOnClickListener(this);


    }
    //1,
    private void requestPermissions() {

        DefaultPermission.requestPermission(this, REQUEST_CODE_CAMERA_PERMISSION,Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE);

    }
    //2,
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        DefaultPermission.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }


    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.barcode_open_scan_code:
//                Intent intent = new Intent(this, CaptureActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.barcode_rxtools_scanner:
//                Intent intent1 = new Intent(this, ScannerCodeActivity.class);
//                startActivity(intent1);
//                break;
//        }


        if (v.getId() == R.id.barcode_open_scan_code) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.barcode_rxtools_scanner) {
            Logger.e("barcode_rxtools_scanner");
            Intent intent1 = new Intent(this, ScannerCodeActivity.class);
            startActivity(intent1);
        }
    }
    //3,
    @Override
    public void onGranted(int requestCode, List<String> grantedPermission) {

    }
    //3,
    @Override
    public void onAllGranted(int requestCode, List<String> allGrantedPermission) {
        ToastUtils.showToastSafe("相机可用！！！");

    }
    //3,
    @Override
    public void onDenied(int requestCode, List<String> deniedPermission) {
        //4,
        DefaultPermission.checkDeniedPermissionsNeverAskAgain(this,deniedPermission,REQUEST_CODE_SETTING);
    }

    //5,
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SETTING) {
            ToastUtils.showToastSafe("设置中开启了权限,相机可用！！！");
        }
    }

}
