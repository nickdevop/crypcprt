package com.example.crypto.Newsd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crypto.R

class NewsListAdapter(private val listner:clickhandling): RecyclerView.Adapter<NewsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.newslist,parent,false)
        val newsviewholder=NewsViewHolder(view)
        view.setOnClickListener()
        {
            listner.onclickhandling(items[newsviewholder.adapterPosition])
        }
        return newsviewholder
    }

    fun updatenews(updatenews:ArrayList<News>)
    {
        items.clear()
        items.addAll(updatenews)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text= currentItem.title
        holder.author.text="Source : "+currentItem.source
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.Image)
    }

    override fun getItemCount(): Int {
        return  items.size

    }

}
class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    val titleView: TextView = itemView.findViewById(R.id.titles)
    val Image: ImageView =itemView.findViewById(R.id.image)
    val author: TextView =itemView.findViewById(R.id.author)
}
interface clickhandling
{
    fun onclickhandling(item:News)
    {

    }
}