package com.wakalas.spotifyapp.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wakalas.spotifyapp.Application
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.entities.SongEntity
import com.wakalas.spotifyapp.common.listeners.SongListener
import com.wakalas.spotifyapp.databinding.ItemVerticalBinding

class SongFindAdapter(private var listener: SongListener):
    ListAdapter<SongEntity, RecyclerView.ViewHolder>(SongDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemVerticalBinding.bind(view)

        fun setListener(songEntity: SongEntity)
        {
            binding.root.setOnClickListener {
                listener.onClick(songEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        context = parent.context

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_vertical, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val cancion = getItem(position)
        val images = Application.images

        with(holder as ViewHolder)
        {
            setListener(cancion)
            with(binding)
            {
                tvName.text = cancion.titulo
                tvNumeroCanciones.text = cancion.duracionFormat()

                Glide.with(context)
                    .load(images[(images.indices).random()])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.ivImage)
            }
        }
    }

    class SongDiffCallback(): DiffUtil.ItemCallback<SongEntity>()
    {
        override fun areItemsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SongEntity, newItem: SongEntity): Boolean
        {
            return oldItem == newItem
        }
    }
}
