package com.wakalas.spotifyapp.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.entities.AlbumEntity
import com.wakalas.spotifyapp.databinding.ItemAlbumBinding

class AlbumAdapter: ListAdapter<AlbumEntity, RecyclerView.ViewHolder>(AlbumDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val binding = ItemAlbumBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        context = parent.context

        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.item_album, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val album = getItem(position)

        with(holder as ViewHolder)
        {

            with(binding)
            {
                //titleTextView.text = album.titulo
            }
        }
    }

    class AlbumDiffCallback() : DiffUtil.ItemCallback<AlbumEntity>()
    {
        override fun areItemsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean
        {
            return oldItem.albumId == newItem.albumId
        }

        override fun areContentsTheSame(oldItem: AlbumEntity, newItem: AlbumEntity): Boolean
        {
            return oldItem == newItem
        }
    }
}