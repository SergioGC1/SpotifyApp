package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.AlbumEntity
import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AlbumService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.ALBUMS_USUARIO_PATH)
    suspend fun getAlbumsByUser(@Path("id") id:Long): Response<MutableList<AlbumEntity>>
}
