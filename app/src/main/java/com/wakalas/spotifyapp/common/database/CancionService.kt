package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.AlbumEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CancionService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.CANCIONES_PATH)
    suspend fun getAlbums(): Response<MutableList<AlbumEntity>>
}
