package com.wakalas.spotifyapp.common.entities

data class AlbumEntity(
    var albumId: Long,
    var titulo: String,
    var imagen: String,
    var patrocinado: Boolean,
    var fechaInicioPatrocinio: String,
    var fechaFinPatrocinio: String,
    var anyo: String
)
