package com.wakalas.spotifyapp.common.database

import com.wakalas.spotifyapp.common.entities.UserEntity
import com.wakalas.spotifyapp.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService
{
    @Headers("Content-Type: application/json")
    @POST(Constants.USUARIOS_PATH)
    suspend fun postUser(): Response<UserEntity>

    @Headers("Content-Type: application/json")
    @GET(Constants.USUARIO_BY_USERNAME_PATH + "/{username}")
    suspend fun getUser(@Path("username") username: String): Response<UserEntity>
}
