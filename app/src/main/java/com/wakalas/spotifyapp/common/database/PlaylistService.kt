package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PlaylistService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.PLAYLISTS_PATH)
    suspend fun getPlaylists(): Response<MutableList<PlaylistEntity>>
}
