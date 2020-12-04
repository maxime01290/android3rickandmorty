package com.example.rickandmortyapplication.Main.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private const val BASE_URL ="https://rickandmortyapi.com/api/"
    private var retrofitInstance:Retrofit? = null

    fun getRetrofitInstance():Retrofit{
        if(retrofitInstance==null){
            try {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }catch (e:Exception){
                e.message
                e.cause
            }
        }

        return retrofitInstance!!
    }

}