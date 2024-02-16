package com.wakalas.spotifyapp.common.entities

data class PlaylistEntity(
    var playlistId: Long,
    var titulo: String,
    var numeroCanciones: Int,
    var fechaCreacion: String
)
