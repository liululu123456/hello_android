package com.thoughtworks.androidtrain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.model.entity.Tweet

class CustomAdapter(private var dataSet: List<Tweet>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_BOTTOM = 1

    class ItemViewHolder(view: View) : ViewHolder(view) {
        private val sender: TextView by lazy { view.findViewById(R.id.sender) }
        private val content: TextView by lazy { view.findViewById(R.id.content) }
        private val avatar: ImageView by lazy { view.findViewById(R.id.photo) }

        fun bind(item: Tweet) {
            content.text = item.content
            sender.text = item.sender?.nick
            avatar.load(item.sender?.avatar)
        }
    }

    class BottomBarViewHolder(view: View) : ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.tweets_row_view, viewGroup, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.tweets_bottom_bar, viewGroup, false)
            BottomBarViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (viewHolder is ItemViewHolder) {
            viewHolder.bind(dataSet[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < dataSet.size) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_BOTTOM
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size + 1
    }

}
