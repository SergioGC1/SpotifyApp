package com.wakalas.spotifyapp.common.utils

object Constants
{
    // Routes
    const val BASE_URL = "http://lab7.navelsystems.com"

    // Playlists
    const val PLAYLISTS_PATH = "/playlists"
    const val PLAYLIST_PATH = "/playlist"
    const val PLAYLISTS_USUARIO_PATH = "/usuario/{id}/playlists"
    const val PLAYLIST_ADD_SONG_PATH = "/playlist/{playlistId}/cancion/{cancionId}"

    // Albumes
    const val ALBUMS_PATH = "/albums"
    const val ALBUMS_USUARIO_PATH = "/usuario/{id}/albums"

    // Canciones
    const val CANCIONES_PATH = "/canciones"

    // Podcasts
    const val PODCASTS_PATH = "/podcasts"
    const val PODCASTS_USUARIO_PATH = "/usuario/{id}/podcasts"

    // Usuarios
    const val USUARIOS_PATH = "/usuarios"
    const val USUARIO_PATH = "/usuario"
    const val USUARIO_BY_USERNAME_PATH = "/usuarioByUsername"
}
