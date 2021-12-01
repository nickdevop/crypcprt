package com.example.crypto.crypdodirt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.toolbox.JsonArrayRequest
import com.example.crypto.Newsd.newsdata
import com.example.crypto.R
import com.example.crypto.Singleton
import com.example.crypto.click_listner
import com.example.crypto.cryptoadapter
import com.example.crypto.social.socialdata

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
class MainActivity : AppCompatActivity(), click_listner {
    lateinit var madapter: cryptoadapter
    var searchdata=ArrayList<cryptodata>()

    val arraydata=ArrayList<cryptodata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
loding()

        Recycler.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        fetchdata()
        searchitem()
        madapter= cryptoadapter(searchdata,this)
        Recycler.adapter=madapter
        bottom()
        searchview.clearFocus()
    }


    fun bottom() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home1 -> {
//                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.news -> {
                    Toast.makeText(this, "Top Headlines", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, newsdata::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.sociallinks -> {
                    Toast.makeText(this, "Social Sites", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, socialdata::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false } } }



fun searchitem()
{

    if(searchview!=null)
        {

            searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener
            {
                override fun  onQueryTextChange(newtext: String?): Boolean {
                    if(newtext!!.isNotEmpty()) {
                        searchdata.clear()
                        val stringstore = newtext.lowercase(Locale.getDefault())
                        arraydata.forEach {
                            if (it.cryptoname.lowercase(Locale.getDefault())
                                    .contains(stringstore)) {
                                searchdata.add(it)
                            }}
                        Recycler.adapter!!.notifyDataSetChanged()

                    }
                    else
                    {

                        searchdata.clear()

                        searchdata.addAll(arraydata)

                        Recycler.adapter!!.notifyDataSetChanged()}
                    return true

                }

                override fun onQueryTextSubmit(newtext: String?): Boolean {
                    return false }
            }) } }




    fun loding()
    {
        Toast.makeText(this," Please wait :) ",Toast.LENGTH_SHORT).show()


    }

    fun fetchdata()
    {

        val url="https://api.nomics.com/v1/currencies/ticker?key=44f4acd8b40bdfcded37dc3baf0f011083bc0999&interval=1h,1d,30d,365d&convert=INR&per-page=60&page=1"
        val JsonArrayRequest=JsonArrayRequest(com.android.volley.Request.Method.GET,url,null,
            {


                for (i in 0 until 60)
                {
                    val Jsonobject=it.getJSONObject(i)

                    val d1h=it.getJSONObject(i).getJSONObject("1d")

                    val d1=it.getJSONObject(i).getJSONObject("1d")
                    val d1m=it.getJSONObject(i).getJSONObject("30d")
                    val d1y=it.getJSONObject(i).getJSONObject("365d")

                    val cryptodata= cryptodata(
                        Jsonobject.getString("logo_url"),

                                Jsonobject.getString("name"),
                                Jsonobject.getString("price"),
                                Jsonobject.getString("status"),
                                Jsonobject.getString("high"),
                                Jsonobject.getString("currency"),
                                Jsonobject.getString("market_cap"),
                        Jsonobject.getInt("rank"),
                       d1h.getString("price_change_pct"),
                        d1.getString("price_change"),
                        d1.getString("price_change_pct"),
                        d1m.getString("price_change"),
                        d1m.getString("price_change_pct"),
                        d1y.getString("price_change"),
                        d1y.getString("price_change_pct"),

                        )
                    arraydata.add(cryptodata)
                    searchdata.add(cryptodata)
                }
                madapter.updatedata(arraydata)

            }
        ,
            {
Toast.makeText(this,"somthing went's wrong ",Toast.LENGTH_LONG).show()
            })
Singleton.getInstance(this).addToRequestQueue(JsonArrayRequest)


    }

    override fun onclicklistner(
        data: String,
        data1: String,
        data2: String,
        data3: String,
        data4: String,
        data5: String,
        data6: String,
        data7: String,
        data8: String,

        data9: String,
        data10: String,
        data11: String,
        data12: String,
        data13: String,
        data14: String
    ) {
val intent=Intent(this, displayinfo::class.java)
        intent.putExtra("logo",data)
        intent.putExtra("cryptoname",data1)
        intent.putExtra("currentprice",data2)
        intent.putExtra("status",data3)
        intent.putExtra("highprice",data4)
        intent.putExtra("currency",data5)
        intent.putExtra("marketcap",data6)
        intent.putExtra("rank",data7)
        intent.putExtra("per1h",data8)
        intent.putExtra("price1d", data9)
        intent.putExtra("per1d", data10)
        intent.putExtra("price1m",data11)
        intent.putExtra("per1m", data12)
        intent.putExtra("price1y", data13)
        intent.putExtra("per1y", data14)

    startActivity(intent)}


}

