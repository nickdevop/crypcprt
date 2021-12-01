package com.example.crypto.crypdodirt

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.crypto.R
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
import kotlinx.android.synthetic.main.activity_displayinfo.*
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue


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
    val price1h=intent.getStringExtra("currentprice")!!.toFloat()
    currentprice.text="₹ "+price1h
  maxprice.text="₹ "+ intent.getStringExtra("highprice")?.toFloat()
    marketcap.text= intent.getStringExtra("marketcap")
val image=intent.getStringExtra("logo")
    Glide.with(this).load(image).override(512, 512).fitCenter().into(crypto_image)
    GlideApp.with(this).load(image).into(crypto_image)
    cryrank.text=intent.getStringExtra("rank")
    val dec = DecimalFormat("##.##")
    val credits = dec.format(intent.getStringExtra("per1h")?.toFloat()?.times(100))
    val credits1d = dec.format(intent.getStringExtra("per1d")?.toFloat()?.times(100))

    if(credits.toFloat() >=0)
    {
        pinc.text="↑  "+credits.toString()+ " %"
           pinc.setTextColor(Color.parseColor("#00A300"))

    }
   else
    {
        pinc.text= "↓  "+credits.toString()+" %"
            pinc.setTextColor(Color.parseColor("#F60B06"))

    }
    if(credits1d.toFloat() >=0)
    {
        priceat1per.text="↑  "+credits1d.toString()+ " %"
        priceat1per.setTextColor(Color.parseColor("#00A300"))
    }
    else
    {
        priceat1per.text= "↓  "+credits1d.toString()+" %"
        priceat1per.setTextColor(Color.parseColor("#F60B06"))
    }

    val price1d=intent.getStringExtra("currentprice")?.toFloat()?.plus(intent.getStringExtra("price1d")?.toFloat()!!)

        priceat1.text="₹ "+price1d

    val priceat1m1=intent.getStringExtra("currentprice")?.toFloat()?.plus(intent.getStringExtra("price1m")?.toFloat()!!)
        priceat1m.text="₹ "+priceat1m1

   val priceat30per1=dec.format(intent.getStringExtra("per1m")?.toFloat()?.times(100))

    if(priceat30per1.toFloat() >=0)
    {
        priceat30per.text="↑  "+priceat30per1.toString()+ " %"
        priceat30per.setTextColor(Color.parseColor("#00A300"))
    }
    else
    {
        priceat30per.text= "↓  "+priceat30per1.toString()+" %"
        priceat30per.setTextColor(Color.parseColor("#F60B06"))
    }


    val priceat1y1=intent.getStringExtra("price1y")?.toFloat()?.plus(intent.getStringExtra("price1m")?.toFloat()!!)
        priceat1y.text="₹ "+priceat1y1

      val  priceatper1y1=dec.format(intent.getStringExtra("per1y")?.toFloat()?.times(100))
    if(priceatper1y1.toFloat() >=0)
    {
        priceatper1y.text="↑  "+priceatper1y1.toString()+ " %"
        priceatper1y.setTextColor(Color.parseColor("#00A300"))
    }
    else
    {
        priceatper1y.text= "↓  "+priceatper1y1.toString()+" %"
        priceatper1y.setTextColor(Color.parseColor("#F60B06"))
    }

barchart(price1h!!,price1d!!,priceat1m1!!,priceat1y1!!)
}


    fun barchart(h1:Float,d1:Float,m1:Float,y1:Float)
    {

        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f,h1))

        entries.add(BarEntry(2f,d1))
        entries.add(BarEntry(3f,m1))
        entries.add(BarEntry(4f,y1))



        val depenses = BarDataSet(entries, "1Hour            1Day                      1 MONTH                           1 YEAR")

        val Date: ArrayList<IBarDataSet> = ArrayList()
        val Data = BarData(depenses)

        barChart.setData(Data)

        barChart.setTouchEnabled(true)
        barChart.setDragEnabled(true)
        barChart.setScaleEnabled(true)
        barChart.description.isEnabled = false
        barChart.animateXY(2000, 2000)
        barChart.setHorizontalScrollBarEnabled(true)
        barChart.setDoubleTapToZoomEnabled(true)


        depenses.setColors(*ColorTemplate.MATERIAL_COLORS)




    }

}
//2021-09-12T00:00:00Z