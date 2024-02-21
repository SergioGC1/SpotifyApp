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
import com.wakalas.spotifyapp.common.entities.AlbumEntity
import com.wakalas.spotifyapp.databinding.ItemHorizontalBinding

class AlbumAdapter: ListAdapter<AlbumEntity, RecyclerView.ViewHolder>(AlbumDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val binding = ItemHorizontalBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        context = parent.context

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_horizontal, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val album = getItem(position)
        val images = Application.images
        with(holder as ViewHolder)
        {

            with(binding)
            {
                tv1.text = album.titulo
                tv2.text = album.fechaInicioPatrocinio
                Glide.with(context)
                    .load(images[(images.indices).random()])
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.imageView)
            }
        }
    }

    class AlbumDiffCallback() : DiffUtil.ItemCallback<AlbumEntity>()
    {
        override fun areItemsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean
        {
            return oldItem == newItem
        }
    }
}