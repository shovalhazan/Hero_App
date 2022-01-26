package com.main.hero_app.Model.Services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val HTTPS_API_HERO_BASE_URL :String ="https://superheroapi.com/api/"
const val Token :String ="5023488897672909"

object RetrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HTTPS_API_HERO_BASE_URL + Token +"/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}