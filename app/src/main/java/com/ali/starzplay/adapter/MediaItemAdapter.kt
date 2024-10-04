package com.ali.starzplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ali.core.model.MediaItem
import com.ali.starzplay.R
import com.bumptech.glide.Glide

class MediaItemAdapter(
    private var mediaItems: List<MediaItem> = listOf(),
    private val onItemClicked: (MediaItem) -> Unit
) : RecyclerView.Adapter<MediaItemAdapter.MediaItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)
        return MediaItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaItemViewHolder, position: Int) {
        val mediaItem = mediaItems[position]
        holder.bind(mediaItem)
    }

    override fun getItemCount(): Int = mediaItems.size

    fun submitList(newMediaItems: List<MediaItem>) {
        mediaItems = newMediaItems
        notifyDataSetChanged()
    }

    inner class MediaItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mediaImage: ImageView = itemView.findViewById(R.id.img_media_poster)
        private val mediaTitle: TextView = itemView.findViewById(R.id.txt_media_title)

        fun bind(mediaItem: MediaItem) {
            mediaTitle.text = mediaItem.title

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${mediaItem.posterPath}")
                .into(mediaImage)

            itemView.setOnClickListener {
                onItemClicked(mediaItem)
            }
        }
    }
}
