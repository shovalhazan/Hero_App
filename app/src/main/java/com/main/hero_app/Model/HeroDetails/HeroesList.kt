package com.main.hero_app.Model.HeroDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroesList (
    @SerializedName("response")
    @Expose
    val response :String,
    @SerializedName("results")
    @Expose
    val results :ArrayList<HeroDetailsModel>,
    @SerializedName("error")
    @Expose
    val error :String

)