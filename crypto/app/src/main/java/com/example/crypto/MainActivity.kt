package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), click_listner {
    lateinit var madapter:cryptoadapter
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

    }
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

        val url="https://api.nomics.com/v1/currencies/ticker?key=44f4acd8b40bdfcded37dc3baf0f011083bc0999&per-page=100&page=1"
        val JsonArrayRequest=JsonArrayRequest(com.android.volley.Request.Method.GET,url,null,
            Response.Listener {


                for (i in 0 until 100)
                {
                    val Jsonobject=it.getJSONObject(i)
                    val cryptodata=cryptodata(
                        Jsonobject.getString("logo_url"),

                                Jsonobject.getString("name"),
                                Jsonobject.getString("price"),
                                Jsonobject.getString("status"),
                                Jsonobject.getString("high"),
                                Jsonobject.getString("currency"),
                                Jsonobject.getString("market_cap"),
                                Jsonobject.getString("price"),
                        Jsonobject.getString("num_exchanges"),
                        Jsonobject.getInt("rank")

                        )
                    arraydata.add(cryptodata)
                    searchdata.add(cryptodata)
                }
                madapter.updatedata(arraydata)

            }
        ,
            Response.ErrorListener {
Toast.makeText(this,"somthing went's wrong ",Toast.LENGTH_LONG).show()
            }

        )
Singleton.getInstance(this).addToRequestQueue(JsonArrayRequest)


    }

    override fun onclicklistner(
        data: String, data1: String, data2: String, data3: String, data4: String,
        data5: String, data6: String, data7: String, data8: String) {

val intent=Intent(this,displayinfo::class.java)
        intent.putExtra("status",data)
        intent.putExtra("cryptoname",data1)
        intent.putExtra("currency",data2)
        intent.putExtra("currentprice",data3)
        intent.putExtra("exchange",data4)
        intent.putExtra("highprice",data5)
        intent.putExtra("logo",data6)
        intent.putExtra("marketcap",data7)
        intent.putExtra("maxsupply",data8)
    startActivity(intent)}


}

