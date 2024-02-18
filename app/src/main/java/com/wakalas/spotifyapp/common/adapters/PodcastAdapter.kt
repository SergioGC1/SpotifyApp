package com.wakalas.spotifyapp.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.entities.PodcastEntity
import com.wakalas.spotifyapp.databinding.ItemPodcastBinding

class PodcastAdapter: ListAdapter<PodcastEntity, RecyclerView.ViewHolder>(PodcastDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val binding = ItemPodcastBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        context = parent.context

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_podcast, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val podcast = getItem(position)

        with(holder as ViewHolder) {

            with(binding) {
                titleTextView.text = podcast.titulo
                descriptionTextView.text = podcast.descripcion
                yearTextView.text = podcast.anyo
            }
        }
    }

    class PodcastDiffCallback() : DiffUtil.ItemCallback<PodcastEntity>()
    {
        override fun areItemsTheSame(oldItem: PodcastEntity, newItem: PodcastEntity): Boolean
        {
            return oldItem.podcastId == newItem.podcastId
        }

        override fun areContentsTheSame(oldItem: PodcastEntity, newItem: PodcastEntity): Boolean
        {
            return oldItem == newItem
        }
    }
}