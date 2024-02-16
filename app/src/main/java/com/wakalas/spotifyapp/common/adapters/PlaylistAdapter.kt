package com.wakalas.spotifyapp.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wakalas.spotifyapp.R
import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.databinding.ItemPlaylistBinding

class PlaylistAdapter: ListAdapter<PlaylistEntity, RecyclerView.ViewHolder>(PlaylistDiffCallback())
{
    private lateinit var context: Context

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val binding = ItemPlaylistBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_playlist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        val album = getItem(position)

        with(holder as ViewHolder) {

            with(binding) {
                tituloTextView.text = album.titulo
            }
        }
    }

    class PlaylistDiffCallback(): DiffUtil.ItemCallback<PlaylistEntity>()
    {
        override fun areItemsTheSame(oldItem: PlaylistEntity, newItem: PlaylistEntity): Boolean
        {
            return oldItem.playlistId == newItem.playlistId
        }

        override fun areContentsTheSame(oldItem: PlaylistEntity, newItem: PlaylistEntity): Boolean
        {
            return oldItem == newItem
        }
    }
}
