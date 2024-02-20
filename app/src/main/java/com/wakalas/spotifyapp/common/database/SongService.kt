package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.SongEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface SongService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.PLAYLIST_PATH + "/{playlistId}" + Constants.CANCIONES_PATH)
    suspend fun getSongsPlaylist(
        @Path("playlistId") playlistId: Long
    ): Response<MutableList<SongEntity>>

    @Headers("Content-Type: application/json")
    @GET(Constants.CANCIONES_PATH)
    suspend fun getSongs(): Response<MutableList<SongEntity>>
}
