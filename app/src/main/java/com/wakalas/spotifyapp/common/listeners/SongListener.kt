package com.wakalas.spotifyapp.common.listeners

import com.wakalas.spotifyapp.common.entities.SongEntity

interface SongListener {

    fun onClick(songEntity: SongEntity)

}