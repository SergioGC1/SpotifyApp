package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.PodcastEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface PodcastService
{
    @Headers("Content-Type: application/json")
    @GET(Constants.PODCASTS_PATH)
    suspend fun getPodcasts(): Response<MutableList<PodcastEntity>>
}
