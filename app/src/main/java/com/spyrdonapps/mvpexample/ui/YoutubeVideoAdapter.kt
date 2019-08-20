package com.spyrdonapps.mvpexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spyrdonapps.mvpexample.R
import com.spyrdonapps.mvpexample.data.YoutubeVideo
import com.spyrdonapps.mvpexample.util.GlideApp
import kotlinx.android.synthetic.main.item_youtube_channel.view.*

class YoutubeVideoAdapter : RecyclerView.Adapter<YoutubeVideoAdapter.ViewHolder>() {

    private var items: List<YoutubeVideo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_youtube_channel, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(list: List<YoutubeVideo>) {
        items = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(video: YoutubeVideo) {
            with(view) {
                titleTextView.text = video.title
                descriptionTextView.text = video.description
                GlideApp.with(context)
                    .load(video.thumbnailUrl)
                    .into(thumbnailImageView)
            }
        }
    }
}