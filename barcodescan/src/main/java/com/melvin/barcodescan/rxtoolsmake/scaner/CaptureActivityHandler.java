package com.melvin.barcodescan.rxtoolsmake.scaner;

import android.os.Handler;
import android.os.Message;

import com.google.zxing.Result;
import com.melvin.barcodescan.R;
import com.melvin.barcodescan.rxtoolsmake.ui.ScannerCodeActivity;


/**
 * Author: Vondear
 * 描述: 扫描消息转发
 */
public final class CaptureActivityHandler extends Handler {

    DecodeThread         decodeThread = null;
    ScannerCodeActivity activity     = null;
    private State state;

    public CaptureActivityHandler(ScannerCodeActivity activity) {
        this.activity = activity;
        decodeThread = new DecodeThread(activity);
        decodeThread.start();
        state = State.SUCCESS;
        CameraManager.get().startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        if (message.what == R.id.barcode_auto_focus) {
            if (state == State.PREVIEW) {
                CameraManager.get().requestAutoFocus(this, R.id.barcode_auto_focus);
            }
        } else if (message.what == R.id.barcode_restart_preview) {
            restartPreviewAndDecode();
        } else if (message.what == R.id.barcode_decode_succeeded) {
            state = State.SUCCESS;
            activity.handleDecode((Result) message.obj);// 解析成功，回调
        } else if (message.what == R.id.barcode_decode_failed) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.barcode_decode);
        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        CameraManager.get().stopPreview();
        removeMessages(R.id.barcode_decode_succeeded);
        removeMessages(R.id.barcode_decode_failed);
        removeMessages(R.id.barcode_decode);
        removeMessages(R.id.barcode_auto_focus);
    }

    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.barcode_decode);
            CameraManager.get().requestAutoFocus(this, R.id.barcode_auto_focus);
        }
    }

    private enum State {
        PREVIEW, SUCCESS, DONE
    }

}
