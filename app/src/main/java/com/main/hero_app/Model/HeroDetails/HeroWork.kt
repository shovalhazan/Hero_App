package com.main.hero_app.Model.HeroDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroWork (
    @SerializedName("occupation")
    @Expose
    val occupation:String,
    @SerializedName("base")
    @Expose
    val base:String
)
{
    override fun toString(): String {
        var str ="WORK\n"+"occupation : "+occupation+"\n"+"base : "+base
        return str
    }
}