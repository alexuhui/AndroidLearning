package com.example.firstlineandroidcode.cameraalbum

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.firstlineandroidcode.R
import com.example.firstlineandroidcode.permission.PermissionCode
import java.io.File

class CameraAlbumActivity : AppCompatActivity() {

    private val TAG = "CameraAlbumActivity"

    private val takePictureContract = registerForActivityResult(ActivityResultContracts.TakePicture()) { result ->
        Log.d(TAG, "takePictureContract result: $result")
        when(result){
            true->{
                // 将拍摄的照片显示出来
                val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                val imageView:ImageView = findViewById(R.id.imageView)
                imageView.setImageBitmap(rotateIfRequired(bitmap))
            }
            false->{
                Log.d(TAG, "take picture failed!")
            }
        }
    }

    private val openAlbumContract = registerForActivityResult(ActivityResultContracts.OpenDocument()){ uri: Uri? ->
        Log.d(TAG, "openAlbumContract result: uri = $uri")
        if(uri != null) {
            // 处理选择的图片
            val bitmap = getBitmapFromUri(uri)
            val imageView :ImageView = findViewById(R.id.imageView)
            imageView.setImageBitmap(bitmap)
        }else{
            Log.d(TAG, "select picture failed!")
        }
    }

    private val outputImage: File by lazy {
        File(externalCacheDir, "output_image.jpg")
    }

    private val imageUri: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(this, "${packageName}.fileprovider", outputImage)
        } else {
            Uri.fromFile(outputImage)
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver
        .openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_album)

        val takePhotoBtn : Button = findViewById(R.id.takePhotoBtn)
        takePhotoBtn.setOnClickListener {
            // 显示或保存图片
            if (outputImage.exists()) {
                outputImage.delete()
            }
            outputImage.createNewFile()
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PermissionCode.REQUEST_CAMERA_PERMISSION)
            } else {
                takePictureContract.launch(imageUri)
            }
        }

        val fromAlbumBtn : Button = findViewById(R.id.fromAlbumBtn)
        fromAlbumBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PermissionCode.REQUEST_READ_EXTERNAL_STORAGE_PERMISSION)
            } else {
                openAlbumContract.launch(arrayOf("image/*"))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PermissionCode.REQUEST_CAMERA_PERMISSION ->{
                takePictureContract.launch(imageUri)
            }
            PermissionCode.REQUEST_READ_EXTERNAL_STORAGE_PERMISSION ->{
                openAlbumContract.launch(arrayOf("image/*"))
            }
        }
    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height,
            matrix, true)
        bitmap.recycle() // 将不再需要的Bitmap对象回收
        return rotatedBitmap
    }
}