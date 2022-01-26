package com.main.hero_app.View.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.R
import com.main.hero_app.View.Adapters.MyPagerAdapter
import com.main.hero_app.ViewModel.HeroDetailsViewModel
import java.lang.Exception

class HeroDetailsActivity : AppCompatActivity() {
    private lateinit var IMG_hero_image: ImageView
    private lateinit var  LBL_name : TextView
    private lateinit var  LBL_biography : TextView
    private lateinit var BTN_shar : MaterialButton
    private lateinit var viewPager: ViewPager
    private lateinit var viewModel: HeroDetailsViewModel
    private lateinit var detailsList : ArrayList<String>

    companion object{
        const val  HERO_PARCELABLE_EXTRA="HERO_PARCELABLE_EXTRA";
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)
        var extras :HeroDetailsModel?=intent.getParcelableExtra(HERO_PARCELABLE_EXTRA)
        findViews()
        initViews(extras)
        extras?.id?.let { loadAPIData(it) }
        Log.d("pttt", "onCreate: "+extras)


    }


    private fun initViews(extras: HeroDetailsModel?) {
            Log.d("pttt", "initViews: ")
            LBL_name.text = extras?.name
            LBL_biography.text =LBL_biography.text.toString() +"\n"+extras?.biography.toString()
            Glide.with(IMG_hero_image)
                .load(extras?.image?.url)
                .into(IMG_hero_image)
    }
    fun loadAPIData(id: String) {
        Log.d("pttt", "loadAPIData: ")
        viewModel = ViewModelProvider(this).get(HeroDetailsViewModel::class.java)
        viewModel.getHeroesConnectionsById(id)
        viewModel.listData.observe(this, Observer<ArrayList<String>> {
            if (it != null) {
                Log.d("pttt", "loadAPIData: it!=null")
                detailsList = it
                Log.d("pttt", "loadAPIData: "+it)
                val pagerAdapter = MyPagerAdapter(this, detailsList)
                viewPager.adapter = pagerAdapter
                (viewPager.adapter as MyPagerAdapter).notifyDataSetChanged()
            } else {
                Log.d("pttt", "loadAPIData: it==null")
            }

        })
    }


    private fun findViews() {
        IMG_hero_image=findViewById(R.id.activityHeroDetails_IMG_hero_image)
        LBL_name=findViewById(R.id.activityHeroDetails_LBL_name)
        LBL_biography=findViewById(R.id.activityHeroDetails_LBL_biography)
        viewPager=findViewById(R.id.activityHeroDetails_Pager)
        BTN_shar=findViewById(R.id.activityHeroDetails_BTN_share)
        BTN_shar.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                openSocialMediaIntent()
            }
        })
    }

    fun openSocialMediaIntent(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"

        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        try {
            startActivity(shareIntent)
        }catch (e:Exception){
            Log.d("pttt", "openSocialMediaIntent: "+e.message)
        }

    }
}