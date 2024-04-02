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
import com.thoughtworks.androidtrain.model.Tweet

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
            sender.text = item.sender.nick
            avatar.load(item.sender.avatar)
        }
    }

    // 里边并没有逻辑实现，可以删掉定义
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

//    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
//
//
//        if (viewHolder is ItemViewHolder) {
//            viewHolder.content.text = dataSet[position].content
//            viewHolder.sender.text = dataSet[position].sender.nick
//
////            要将图像加载到自定义目标中，可以使用 ImageRequest，但是我们可以直接使用ImageView的Loader，
////            因为coil实现在ImageView中实现了扩展方法load
//
//            val request = ImageRequest.Builder(viewHolder.avatar.context)
//                .data(dataSet[position].sender.avatar)
//                .target(viewHolder.avatar)
//                .build()
//            val imageLoader = ImageLoader.Builder(viewHolder.avatar.context)
//                .build()
//            imageLoader.enqueue(request)
//
//        }
//    }

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
