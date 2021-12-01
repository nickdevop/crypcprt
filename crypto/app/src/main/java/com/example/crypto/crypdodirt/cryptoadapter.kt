package com.example.crypto

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto.crypdodirt.cryptodata

import com.github.twocoffeesoneteam.glidetovectoryou.*
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.lists.view.*
import java.text.DecimalFormat


class cryptoadapter(val item:ArrayList<cryptodata>, val listner1:click_listner):RecyclerView.Adapter<cryptoviewholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cryptoviewholder {
val listview=LayoutInflater.from(parent.context).inflate(R.layout.lists,parent,false)
        val cryptoviewholder=cryptoviewholder(listview)
        listview.card.setOnClickListener()
        {
            listner1.onclicklistner(
                       item[cryptoviewholder.adapterPosition].logos,
                       item[cryptoviewholder.adapterPosition].cryptoname,

                        item[cryptoviewholder.adapterPosition].currentPrice,

                item[cryptoviewholder.adapterPosition].Status,
                        item[cryptoviewholder.adapterPosition].highPrice,
                item[cryptoviewholder.adapterPosition].currency,
                        item[cryptoviewholder.adapterPosition].marketcap,
                item[cryptoviewholder.adapterPosition].Rank.toString(),
                item[cryptoviewholder.adapterPosition].currpercentage1,
                item[cryptoviewholder.adapterPosition].Price1d,
                item[cryptoviewholder.adapterPosition].Pricepercent1d,
                item[cryptoviewholder.adapterPosition].Price30d,
                item[cryptoviewholder.adapterPosition].Pricepercent30d,
                item[cryptoviewholder.adapterPosition].Price1y,
                item[cryptoviewholder.adapterPosition].Pricepercent1y
                )
        }
        return cryptoviewholder
    }


    override fun onBindViewHolder(holder: cryptoviewholder, position: Int) {
val currentPosition=item[position]
        holder.cryptoname.text=currentPosition.cryptoname
        holder.rank.text= "RANK :"+currentPosition.Rank.toString()
        val dec = DecimalFormat("##.##")
        if(currentPosition.Pricepercent1d.toFloat() >=0) {
            holder.per.text = "↑  "+dec.format(currentPosition.Pricepercent1d.toFloat().times(100)).toString() + " %"
               holder.per.setTextColor(Color.parseColor("#00A300"))
        }
        else
        {
            holder.per.text = "↓  "+dec.format(currentPosition.Pricepercent1d.toFloat().times(100)).toString() + " %"
            holder.per.setTextColor(Color.parseColor("#F60B06"))
        }
            Glide.with(holder.itemView).load(currentPosition.logos).override(512, 512).fitCenter().transform( BlurTransformation())

                .into(holder.logo)
       GlideApp.with(holder.itemView).load(currentPosition.logos).into(holder.logo)

    }

    override fun getItemCount(): Int {
return item.size
    }
    fun updatedata(updatedata:ArrayList<cryptodata>)
    {
        item.clear()
        item.addAll(updatedata)
        notifyDataSetChanged()
    }

}
class cryptoviewholder(itemview:View):RecyclerView.ViewHolder(itemview)
{
val logo : ImageView =itemview.findViewById(R.id.crypto_image1)
    val cryptoname : TextView =itemview.findViewById(R.id.name1)
    val rank : TextView =itemview.findViewById(R.id.rank1)
    val per:TextView=itemview.findViewById(R.id.per)
}


interface click_listner
{
    fun onclicklistner(
        data:String,
        data1: String,
        data2: String,
        data3: String,
        data4: String,
        data5: String,
        data6: String,
        data7: String,
        data8: String,
        data11: String,
        data21: String,
        data31: String,
        data41: String,
        data51: String,
        data61: String
    )
}

