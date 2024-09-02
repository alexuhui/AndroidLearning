package com.example.firstlineandroidcode.video

import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstlineandroidcode.R
import java.io.File
import java.io.FileDescriptor
import java.io.IOException


class PlayVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        /**
         * VideoView不支持直接播放assets目录下的视频资源，所以我们只能寻找其他的解决方案。
         * res目录下允许我们再创建一个raw目录，像诸如音频、视频之类的资源文件也可以放在这里，
         * 并且VideoView是可以直接播放这个目录下的视频资源的。
         * */
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")

        val videoView : VideoView = findViewById(R.id.videoView)
        videoView.setVideoURI(uri)

        val play : Button = findViewById(R.id.play)
        play.setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
        }
        val pause :Button =findViewById(R.id.pause)
        pause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
        }
        val replay : Button = findViewById(R.id.replay)
        replay.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.resume() // 重新播放
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val videoView : VideoView = findViewById(R.id.videoView)
        videoView.suspend()
    }
}