package com.example.practice.camera;

import static com.example.practice.camera.CameraConst.*;
import static com.example.practice.camera.CameraPermission.*;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContentInfo;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.Toast;

import com.example.practice.databinding.ActivityPhotoAlbumBinding;
import com.example.practice.R;

import java.io.File;
import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PhotoAlbum extends Activity {
    private final static String TAG = "AppCompatActivity";

    private File currentImageFile = null;
    private Uri currentImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_photo_album);
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setText("back previous page");
        backBtn.setOnClickListener(view -> {
            finish();
        });

        Button cameraBtn = findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(view -> {
            onClickCamera();
        });

        Button albumBtn = findViewById(R.id.albumBtn);
        albumBtn.setOnClickListener(view -> {
            onClickAlbum();
        });
    }

    /**
     * 自己定义一个保存路径，（拍完的图片是不会保存到本地的， 我们可以自己写代码把图片保存到我们的SD卡里，然后再显示，这样的图片会清晰很多.）
     */
    public void createSavepath() {
        File dir = new File(Environment.getExternalStorageDirectory(), "pictures");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        currentImageFile = new File(dir, System.currentTimeMillis() + ".jpg");
        if (!currentImageFile.exists()) {
            try {
                currentImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("extra", "" + dir.toString());
    }


    private void onClickAlbum() {
        Log.i(TAG, "onClickAlbum: ");
        CameraPermission.autoObtainStoragePermission(this);
    }

    private void onClickCamera() {
        Log.i(TAG, "onClickCamera: ");
        CameraPermission.autoObtainCameraPermission(this);

        //这种方法是自定义路径存储图片
        createSavepath();//上面自定义保存路径
        currentImageUri = FileProvider.getUriForFile(this, "com.example.practice.file-provider", currentImageFile);
        //打开相机
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, currentImageUri);//输出到指定路径
        it.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//授予临时权限
        startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
    }

    //请求权限的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
//调用系统相机申请拍照权限回调
            case CAMERA_PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许授权
                    if (hasSdcard()) {
//拍照
                    } else {
                        Toast.makeText(this, "没有SD卡", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请允许打开相机", Toast.LENGTH_SHORT).show();
                }
                break;
            }
//调用系统相册申请Sdcard权限回调
            case STORAGE_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//打开相册
                } else {
                    Toast.makeText(this, "请允许操作SD卡", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}