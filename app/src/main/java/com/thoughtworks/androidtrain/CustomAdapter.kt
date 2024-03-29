package com.thoughtworks.androidtrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
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
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_view, viewGroup, false)
            return ItemViewHolder(view)
        }
        else {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.bottom_bar, viewGroup, false)
            return BottomBarViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (position < dataSet.size) {
            val itemViewHolder = viewHolder as ItemViewHolder
            itemViewHolder.content.text = dataSet[position].content
            itemViewHolder.sender.text = dataSet[position].sender.nick
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
