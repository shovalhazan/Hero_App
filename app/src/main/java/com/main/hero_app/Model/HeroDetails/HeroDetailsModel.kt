package com.main.hero_app.Model.HeroDetails

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeroDetailsModel (
    @SerializedName("id")
    @Expose
    val id :String?,
    @SerializedName("name")
    @Expose
    val name :String?,
    @SerializedName("biography")
    @Expose
    val biography:HeroBiografhy?,
    @SerializedName("image")
    @Expose
    val image :HeroImage?

    ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(HeroBiografhy::class.java.classLoader),
        parcel.readParcelable(HeroImage::class.java.classLoader)
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeParcelable(biography, flags)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HeroDetailsModel> {
        override fun createFromParcel(parcel: Parcel): HeroDetailsModel {
            return HeroDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<HeroDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}
