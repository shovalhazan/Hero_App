package com.main.hero_app.Model.HeroDetails

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class HeroBiografhy(
    @SerializedName("full-name")
    @Expose
    val fullName:String?,
    @SerializedName("aliases")
    @Expose
    val aliases: ArrayList<String>?,
    @SerializedName("place-of-birth")
    @Expose
    val placeBirth:String?,
    @SerializedName("first-appearance")
    @Expose
    val firstAppearance:String?,
    @SerializedName("publisher")
    @Expose
    val poblisher:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeStringList(aliases)
        parcel.writeString(placeBirth)
        parcel.writeString(firstAppearance)
        parcel.writeString(poblisher)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HeroBiografhy> {
        override fun createFromParcel(parcel: Parcel): HeroBiografhy {
            return HeroBiografhy(parcel)
        }

        override fun newArray(size: Int): Array<HeroBiografhy?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        var str ="full name : "+fullName+"\n"+"place of birth : "+placeBirth+"\n"+"first appearance :"+firstAppearance+"\n"+"poblisher"+poblisher+"\n"+"aliases : "
        if (aliases != null) {
            for(a in aliases)
                str+=a +" "
        }
        return str
    }
}