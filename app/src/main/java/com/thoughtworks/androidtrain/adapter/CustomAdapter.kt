package com.thoughtworks.androidtrain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.model.Tweet

class CustomAdapter(private val dataSet: List<Tweet>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_BOTTOM = 1
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sender: TextView
        val content: TextView
        init {
            sender = view.findViewById(R.id.sender)
            content = view.findViewById(R.id.content)
        }
    }
    class BottomBarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView
        init {
            textView = view.findViewById(R.id.bar)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if ( viewHolder is ItemViewHolder) {
            viewHolder.content.text = dataSet[position].content
            viewHolder.sender.text = dataSet[position].sender.nick
        }
    }

    override fun getItemViewType(position: Int):Int{
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
