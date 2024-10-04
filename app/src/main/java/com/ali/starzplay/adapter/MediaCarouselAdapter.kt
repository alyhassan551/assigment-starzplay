package com.ali.starzplay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ali.core.model.MediaItem
import com.ali.starzplay.R
import com.ali.starzplay.ui.model.MediaGroup

class MediaCarouselAdapter(
    private var mediaGroups: List<MediaGroup> = listOf(),
    private val onItemClicked: (MediaItem) -> Unit
) : RecyclerView.Adapter<MediaCarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val mediaGroup = mediaGroups[position]
        holder.bind(mediaGroup)
    }

    override fun getItemCount(): Int = mediaGroups.size

    fun submitList(newMediaGroups: List<MediaGroup>) {
        mediaGroups = newMediaGroups
        notifyDataSetChanged()
    }

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carouselTitle: TextView = itemView.findViewById(R.id.txt_carousel_title)
        private val mediaRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_media_items)

        fun bind(mediaGroup: MediaGroup) {
            carouselTitle.text = mediaGroup.mediaType
            mediaRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val mediaItemAdapter = MediaItemAdapter { mediaItem ->
                onItemClicked(mediaItem)
            }
            mediaRecyclerView.adapter = mediaItemAdapter
            mediaItemAdapter.submitList(mediaGroup.mediaItems)
        }
    }
}
