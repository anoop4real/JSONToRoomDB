package com.example.anoopm.jsontoroom.store

import com.example.anoopm.jsontoroom.models.MusicAlbums
import com.example.anoopm.jsontoroom.webservice.APIInterface
import com.example.anoopm.jsontoroom.webservice.Result
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DataStore private constructor() {

    companion object {

        fun getMusicAlbumDataWith(responseCallback: (Result<Array<MusicAlbums>>) -> Unit){
            val service = APIInterface.APIServiceFactory.create()

            service.getMusicData().enqueue(object : retrofit2.Callback<Array<MusicAlbums>> {
                override fun onFailure(call: Call<Array<MusicAlbums>>, t: Throwable) {
                    //Handle failure
                    responseCallback(Result.error(Exception("Unable to fetch data")))
                }

                override fun onResponse(call: Call<Array<MusicAlbums>>, response: Response<Array<MusicAlbums>>) {
                    val albumInfo = response.body()
                    println("Request Success")
                    print("CountryData is" + albumInfo)
                    responseCallback(Result.success(albumInfo))
                }

            })
        }
    }
}