package com.main.hero_app.View.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import com.main.hero_app.R
import com.main.hero_app.View.Fragments.MyHeroFragment
import com.main.hero_app.View.Fragments.RecyclerListFragment


class MainActivity : AppCompatActivity() {
    //views
    private lateinit var INP_searchView:EditText
    private lateinit var searchResultsFragment : RecyclerListFragment
    private lateinit var suggestResultsFragment : MyHeroFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()
        if (savedInstanceState == null) {
            Log.d("pttt", "onCreate: savedInstanceState==null")
            addResultsFragment()
            addSuggestFragment()
        }
        Log.d("pttt", "onCreate: ")
        initViews()
        initSearchBoxListener()
    }

    private fun addSuggestFragment() {
        suggestResultsFragment= MyHeroFragment()
        val manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.add(R.id.activityMain_LST_CONTAINER_suggest,suggestResultsFragment).commit()
    }

    private fun addResultsFragment(){
        searchResultsFragment= RecyclerListFragment()
        val manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.add(R.id.activityMain_LST_CONTAINER_results,searchResultsFragment).commit()
    }
    private fun initSearchBoxListener() {
        INP_searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("pttt", "afterTextChanged: ")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("pttt", "beforeTextChanged: ")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("pttt", "onTextChanged: " + s.toString())
                searchResultsFragment.loadAPIData(s.toString())
            }
        })
    }

    private fun initViews() {
        Log.d("pttt ", "initViews: MainActivity")
        INP_searchView = findViewById(R.id.activityMain_INP_searchView)
    }



}