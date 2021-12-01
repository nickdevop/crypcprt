package com.example.crypto.social

import android.app.VoiceInteractor
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.crypto.Newsd.News
import com.example.crypto.R
import com.example.crypto.Singleton
import kotlinx.android.synthetic.main.activity_socialdata.*
import org.json.JSONObject

class socialdata : AppCompatActivity(), listner {
    lateinit var madapter:socialadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socialdata)
        recyclerView.layoutManager=LinearLayoutManager(this)
        datafetch()
       madapter=socialadapter(this,this,this,this)
        recyclerView.adapter=madapter

    }

    fun datafetch()

    {
        val url="https://api.nomics.com/v1/currencies?key=44f4acd8b40bdfcded37dc3baf0f011083bc0999&ids=BTC,ETH,XRP,ADA,USDT,BNB,SOL,XRP,DOT,DOGE,USDC,LUNA,UNI,YAM,AVAX,FTXTOKEN,LINK,ALGO,BUSD,LTC,AVAX,FTXTOKEN,LINK,ALGO,BUSD,LTC,BCH,ICP,WBTC,MATIC,FIL,ATOM,TRX,VET,XLM,ETC,THETA,DAI,CETH&attributes=id,name,logo_url,website_url%20,logo_url,blog_url,discord_url,faccebook_url,medium_url,github_url,telegram_url,youtube_url&per-page=100&page=1"
    val jsonArrayRequest=JsonArrayRequest(Request.Method.GET,url,null
    ,Response.Listener {
                       val datastorage= ArrayList<datasocial>()
            for (i in 0 until it.length())
            {
                val jsonObject=it.getJSONObject(i)
                val data=datasocial(
                    jsonObject.getString("logo_url"),
                    jsonObject.getString("name"),
                    jsonObject.getString("website_url"),
                    jsonObject.getString("github_url"),
                    jsonObject.getString("blog_url"),
                    jsonObject.getString("discord_url"))

                datastorage.add(data)
            }
            madapter.updatadata(datastorage)

        }
    ,Response.ErrorListener {
        Toast.makeText(this,"Something wents wrong",Toast.LENGTH_SHORT).show()
        }

    )
Singleton.getInstance(this).requestQueue.add(jsonArrayRequest)
    }

    override fun wclicklistner(data: String) {
        super.wclicklistner(data)
        val builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF0000") //red

        builder.setToolbarColor(colorInt)
        val customTabsIntent = builder.build();
        if(data!="")
        {
        customTabsIntent.launchUrl(this, Uri.parse(data))}
    else
            {
                Toast.makeText(this,"coming in future..",Toast.LENGTH_SHORT).show()
            }
        }

    override fun gclicklistner(data: String) {
        super.gclicklistner(data)
        val builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF0000") //red

        builder.setToolbarColor(colorInt)
        val customTabsIntent = builder.build();
        if(data!="")
        {
            customTabsIntent.launchUrl(this, Uri.parse(data))}
        else
        {
            Toast.makeText(this,"coming in future..",Toast.LENGTH_SHORT).show()
        }
    }

    override fun bclicklistner(data: String) {
        super.bclicklistner(data)
        val builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF0000") //red

        builder.setToolbarColor(colorInt)
        val customTabsIntent = builder.build();
        if(data!="")
        {
            customTabsIntent.launchUrl(this, Uri.parse(data))}
        else
        {
            Toast.makeText(this,"coming in future..",Toast.LENGTH_SHORT).show()
        }
    }

    override fun dclicklistner(data: String) {
        super.dclicklistner(data)
        val builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF0000") //red

        builder.setToolbarColor(colorInt)
        val customTabsIntent = builder.build();
        if(data!="")
        {
            customTabsIntent.launchUrl(this, Uri.parse(data))}
        else
        {
            Toast.makeText(this,"coming in future..",Toast.LENGTH_SHORT).show()
        }
    }
    }

