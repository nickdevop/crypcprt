package com.example.crypto.social

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.socialsiteslist.view.*


class  socialadapter(private  val lsitner1:listner,private  val lsitner2:listner
                     ,private  val lsitner3:listner,
                     private  val lsitner4:listner



):RecyclerView.Adapter<socialviewholder>()
{
    val arrayitem=ArrayList<datasocial>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): socialviewholder {
       val inflatedata=LayoutInflater.from(parent.context).inflate(R.layout.socialsiteslist,parent,false)

        val socialviewholder=socialviewholder(inflatedata)
inflatedata.website1.setOnClickListener()
{
lsitner1.wclicklistner(arrayitem[socialviewholder.adapterPosition].website_url)
}
        inflatedata.github1.setOnClickListener()
        {
            lsitner1.gclicklistner(arrayitem[socialviewholder.adapterPosition].github_url)
        }
        inflatedata.blog1.setOnClickListener()
        {
            lsitner1.bclicklistner(arrayitem[socialviewholder.adapterPosition].blog_url)
        }
        inflatedata.discord1.setOnClickListener()
        {
            lsitner1.dclicklistner(arrayitem[socialviewholder.adapterPosition].discord_url)
        }


        return socialviewholder
    }

    override fun onBindViewHolder(holder: socialviewholder, position: Int) {
        val currentPosition=arrayitem[position]
        holder.blog.text=currentPosition.blog_url
        holder.discord.text=currentPosition.discord_url
        holder.github.text=currentPosition.github_url
        holder.name.text=currentPosition.cyptname
        holder.website.text=currentPosition.website_url
        Glide.with(holder.itemView).load(currentPosition.logo_url).override(512, 512).fitCenter().transform( BlurTransformation())

            .into(holder.logourl)
        GlideApp.with(holder.itemView).load(currentPosition.logo_url).into(holder.logourl)
    }

    override fun getItemCount(): Int {
        return  arrayitem.size
    }
fun updatadata(data:ArrayList<datasocial>)
{
    arrayitem.clear()
    arrayitem.addAll(data)
    notifyDataSetChanged()
}

}
class socialviewholder(items:View):RecyclerView.ViewHolder(items)
{
val name:TextView=items.findViewById(R.id.wname)
val logourl:ImageView=items.findViewById(R.id.wcrypto_image)
    val website:TextView=items.findViewById(R.id.website)
    val github:TextView=items.findViewById(R.id.github)
    val blog:TextView=items.findViewById(R.id.blog)
    val discord:TextView=items.findViewById(R.id.discord)
}
interface listner
{
    fun  wclicklistner(data: String)
    {

    }
    fun  gclicklistner(data:String)
    {

    }
    fun  bclicklistner(data:String)
    {

    }
    fun  dclicklistner(data:String)
    {

    }
}