package com.main.hero_app.Model.HeroDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroConnections (
    @SerializedName("group-affiliation")
    @Expose
    val group:String,
    @SerializedName("relatives")
    @Expose
    val relatives:String
){
    override fun toString(): String {
        var str ="CONNECTIONS\n"+"group affiliation : "+group+"\n"+"relatives : "+relatives
        return str
    }
}

