package com.wakalas.spotifyapp.common.entities

import java.util.Date

data class UserEntity(
    var id: Long = 0L,
    var username: String,
    var password: String,
    var email: String,
    var genero: Char = 'X',
    var fechaNacimiento: Date = Date(2000, 1, 1),
    var pais: String = "",
    var codigoPostal: String = ""
)
