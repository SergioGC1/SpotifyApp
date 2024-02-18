package com.wakalas.spotifyapp.common.entities

data class CancionEntity(
    var cancionId: Long,
    var titulo: String,
    var duracion: Int,
    var ruta: String,
    var numeroReproducciones: Long
)
