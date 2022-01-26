package com.main.hero_app.Model.HeroDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroAppearance(
        @SerializedName("gender")
        @Expose
        val gender:String,
        @SerializedName("race")
        @Expose
        val race:String,
        @SerializedName("eye-color")
        @Expose
        val eyes:String,
        @SerializedName("hair-color")
        @Expose
        val hair:String
){
        override fun toString(): String {
                var str ="APPEARANCE\n"+"gender : "+gender+"\n"+"race : "+race+"\n"+"eye color :"+eyes+"\n"+"hair color :"+hair+"\n"+"aliases : "
                return str
        }
}


