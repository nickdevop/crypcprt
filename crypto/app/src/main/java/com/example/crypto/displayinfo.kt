package com.example.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_displayinfo.*

class displayinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayinfo)
datareceive()
    }

fun datareceive()
{
    status.text=intent.getStringExtra("status")
   name.text=intent.getStringExtra("cryptoname")
    currecy.text=  intent.getStringExtra("currency")
    currentprice.text=intent.getStringExtra("currentprice")
  exchange.text=  intent.getStringExtra("exchange")
  maxprice.text= intent.getStringExtra("highprice")
    marketcap.text= intent.getStringExtra("marketcap")
    maxprice.text=intent.getStringExtra("highprice")


val image=intent.getStringExtra("logo")
    Glide.with(this).load(image).override(512, 512).fitCenter().into(crypto_image)
    GlideApp.with(this).load(image).into(crypto_image)

}
}