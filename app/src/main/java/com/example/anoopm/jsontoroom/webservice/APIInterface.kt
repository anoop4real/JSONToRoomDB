package com.example.anoopm.jsontoroom.webservice

import com.example.anoopm.jsontoroom.constants.Constants
import com.example.anoopm.jsontoroom.models.MusicAlbums
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIInterface {

    @GET("/api/music_albums")
    fun getMusicData(): Call<Array<MusicAlbums>>

    object APIServiceFactory {
        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseURL)
                .build()

            return retrofit.create(APIInterface::class.java);
        }
    }
}