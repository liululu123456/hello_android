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
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sender: TextView
        val content: TextView


        init {
            sender = view.findViewById(R.id.sender)
            content = view.findViewById(R.id.content)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.content.text = dataSet[position].content
            viewHolder.sender.text = dataSet[position].sender.nick
    }

    override fun getItemCount() = dataSet.size

}
