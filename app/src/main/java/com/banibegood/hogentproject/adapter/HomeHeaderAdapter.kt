package com.banibegood.hogentproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.banibegood.hogentproject.R
import com.banibegood.hogentproject.database.game.Game

class HomeHeaderAdapter(private val list: MutableList<Game>) : RecyclerView.Adapter<HomeHeaderAdapter.ViewHolder>(){



    inner class ViewHolder(iv : View) : RecyclerView.ViewHolder(iv){
        var itemImage : ImageView
        var itemTitle : TextView
        var itemDetail : TextView
        init {
            itemImage = iv.findViewById(R.id.roundedImageView)
            itemTitle = iv.findViewById(R.id.game_title)
            itemDetail = iv.findViewById(R.id.game_details)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val vo = LayoutInflater.from(parent.context).inflate(R.layout.home_header,parent,false)
        return ViewHolder(vo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = list[position].title
        holder.itemDetail.text = list[position].shortDescription
    }

    override fun getItemCount(): Int {
        return list.size
    }
}