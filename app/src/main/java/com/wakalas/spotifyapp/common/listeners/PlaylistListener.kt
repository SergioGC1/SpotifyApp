package com.wakalas.spotifyapp.common.listeners

import com.wakalas.spotifyapp.common.entities.PlaylistEntity

interface PlaylistListener
{
    fun onClick(playlistEntity: PlaylistEntity)
}
