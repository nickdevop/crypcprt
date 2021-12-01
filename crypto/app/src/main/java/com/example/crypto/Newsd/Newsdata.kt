package com.example.crypto.Newsd

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
import com.android.volley.toolbox.JsonObjectRequest
import com.example.crypto.R
import com.example.crypto.Singleton
import kotlinx.android.synthetic.main.activity_newsdata.*

class newsdata : AppCompatActivity() , clickhandling{
    private lateinit var  mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsdata)

        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchdata()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter=mAdapter
    }





    private fun fetchdata() {


        val url="https://min-api.cryptocompare.com/data/news/"

        val jsonArrayRequest= JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener {


                val newsarray = ArrayList<News>()
                for (i in 0 until it.length()) {
                    val jsonObject = it.getJSONObject(i)
                    val news = News(


                        jsonObject.getString("title"),
                        jsonObject.getString("source"),
                        jsonObject.getString("url"),
                        jsonObject.getString("imageurl")

                    )
                    newsarray.add(news)
                }

                mAdapter.updatenews(newsarray)

            }, Response.ErrorListener {

                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            })
        Singleton.getInstance(this).requestQueue.add(jsonArrayRequest)

    }

    override fun onclickhandling(item: News) {
        val builder =  CustomTabsIntent.Builder()
        val colorInt: Int = Color.parseColor("#FF0000") //red

        builder.setToolbarColor(colorInt)
        val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }

}