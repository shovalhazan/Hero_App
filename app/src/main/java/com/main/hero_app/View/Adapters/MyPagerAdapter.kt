package com.main.hero_app.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.main.hero_app.Model.CardModel
import com.main.hero_app.R


class MyPagerAdapter(val context : Context, val cardList : ArrayList<String>) : PagerAdapter() {

    override fun getCount(): Int {
       return cardList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(context).inflate(R.layout.fragment_card,container,false)
        val model = cardList[position]
        val text:TextView=view.findViewById<TextView>(R.id.fragment_card_LBL_card)
        text.text =model
        container.addView(view,position)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
    }

}