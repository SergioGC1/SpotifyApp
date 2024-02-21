package com.wakalas.spotifyapp.common.entities

import java.util.Date

data class PlaylistEntity(
    var id: Long = 0L,
    var titulo: String,
    var numeroCanciones: Int = 0,
    var fechaCreacion: Date = Date()
)
