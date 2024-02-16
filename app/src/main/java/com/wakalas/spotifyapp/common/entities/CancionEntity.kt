package com.wakalas.spotifyapp.common.entities

data class CancionEntity(
    var titulo: String,
    var id: Long,
    var duracion: Int,
    var ruta: String,
    var numeroReproducciones: Long
)
