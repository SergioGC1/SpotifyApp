package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.PodcastEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PodcastService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.PODCASTS_USUARIO_PATH)
    suspend fun getPodcasts(@Path("id") id: Long): Response<MutableList<PodcastEntity>>
}
