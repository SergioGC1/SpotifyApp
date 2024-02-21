package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.PlaylistEntity
import com.wakalas.spotifyapp.common.entities.ResponseEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaylistService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.PLAYLISTS_PATH)
    suspend fun getPlaylists(): Response<MutableList<PlaylistEntity>>

    @Headers("Content-Type: application/json")
    @POST(Constants.USUARIO_PATH + "/{userId}" + Constants.PLAYLISTS_PATH)
    suspend fun postPlaylist(
        @Path("userId") userId: Long,
        @Body playlistEntity: PlaylistEntity
    ): Response<ResponseEntity>
}
