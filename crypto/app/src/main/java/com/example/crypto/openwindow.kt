package com.example.crypto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView
import com.example.crypto.crypdodirt.MainActivity
import java.util.*

class openwindow : AppCompatActivity() {
    val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_openwindow)
        val videoview:VideoView=findViewById(R.id.videoview)


        val videopath:String="android.resource://$packageName/${R.raw.lo}"
        val myUri: Uri = Uri.parse(videopath)
       videoview.setVideoURI(myUri)
        videoview.requestFocus()
        videoview.start()
chnager()
    }

    fun chnager() {
        val handler = Handler()
        val runnable = Runnable {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
        handler.postDelayed(runnable, 4000)
    }
}