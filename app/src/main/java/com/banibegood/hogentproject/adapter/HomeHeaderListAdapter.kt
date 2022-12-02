package com.banibegood.hogentproject.adapter

import android.transition.TransitionInflater.from
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider.Factory.Companion.from
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.banibegood.hogentproject.R
import com.banibegood.hogentproject.database.game.Game
import timber.log.Timber

class HomeHeaderListAdapter() : ListAdapter<Game,HomeHeaderListAdapter.ListViewHolder>(GameListDiffCallBack()){

    class ListViewHolder(view : View) : RecyclerView.ViewHolder(view){

        var itemImage : ImageView
        var itemTitle : TextView
        var itemDetail : TextView
        init {
            itemImage = view.findViewById(R.id.roundedImageView)
            itemTitle = view.findViewById(R.id.game_title)
            itemDetail = view.findViewById(R.id.game_details)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ListViewHolder(inflater.inflate(R.layout.home_header,parent,false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        Timber.tag("BIND VIEW HOLDER").i("setting holder components")

        holder.itemTitle.text = item.title
        holder.itemDetail.text = item.shortDescription

    }

}


class GameListDiffCallBack() : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}

