package com.main.hero_app.View.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.HttpException
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.R
import com.main.hero_app.View.Activities.HeroDetailsActivity


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * wrapper around a view that contains the layout for an individual item  in the list,Defines the appearance of a display for each item.
     **/
    lateinit var LBL_heroName : TextView
    lateinit var IMG_heroImg : ImageView
    lateinit var thisView : View

    init {
        // Define click listener for the ViewHolder's View.
        LBL_heroName=view.findViewById(R.id.rowItem_LBL_name);
        IMG_heroImg=view.findViewById(R.id.rowItem_IMG)
        this.thisView = view
    }


    fun bind(data : HeroDetailsModel){
        initViewClickListener(data)
        Log.d("pttt", "bind: ")
        LBL_heroName.text = data.name
        try{
            Glide.with(IMG_heroImg)
                .load(data.image?.url)
                .thumbnail(0.25f)
                .circleCrop()
                .into(IMG_heroImg)
        }catch (e:HttpException){
            Log.d("pttt", "bind: "+e.message)
            IMG_heroImg.setImageResource(R.drawable.ic_person_foreground)
        }

    }

    private fun initViewClickListener(data : HeroDetailsModel){
        thisView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                openHeroDetailsActivity(data);
            }
        })
    }
    private fun openHeroDetailsActivity(data : HeroDetailsModel){
        val context: Context = thisView.context
        val intent : Intent = Intent(context, HeroDetailsActivity::class.java)
        intent.putExtra(HeroDetailsActivity.HERO_PARCELABLE_EXTRA,data)
        context.startActivity(intent)
    }
}