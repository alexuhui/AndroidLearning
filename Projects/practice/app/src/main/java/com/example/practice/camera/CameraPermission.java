package com.example.practice.camera;

import static com.example.practice.camera.CameraConst.*;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraPermission {
    private final static String TAG = "CameraPermission";

    /**
     * 动态申请sdcard读写权限
     */
    public final static void autoObtainStoragePermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
            } else {

            }
        } else {

        }
    }

    /**
     * 自Android 6.0以后对某些涉及用户隐私权限的获取需要动态获取，所以首先是检查权限，如没有权限则动态申请权限，这里我们需要用到的权限是WRITE_EXTERNAL_STORAGE和CAMERA。
     * FileProvider是ContentProvider的一个子类，用于应用程序之间私有文件的传递。自Android 7.0后系统禁止应用向外部公开file://URI ，
     * 因此需要FileProvider来向外界传递URI，传递的形式是content : //Uri，使用时需要在清单文件中注册。
     * <p>
     * <p>
     * 申请访问相机权限
     */
    public final static void autoObtainCameraPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission(context)) {//检查权限
                //没有权限，发送权限请求
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA)) {
                    Toast.makeText(context, "您已经拒绝过一次了", Toast.LENGTH_SHORT).show();
                }
                //请求相机权限
                requestPermissions(context);
            } else {
                //有权限直接调用系统相机拍照
                if (hasSdcard()) {
                    //sd卡挂载上了，可读可写
                    //打开相机
                } else {
                    Toast.makeText(context, "没有SD卡", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //检查权限
    public final static boolean checkPermission(Context context) {
//是否有权限
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean haveWritePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        return haveCameraPermission && haveWritePermission;
    }

    // 请求相机权限和sd卡权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private final static void requestPermissions(Context context) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public final static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
