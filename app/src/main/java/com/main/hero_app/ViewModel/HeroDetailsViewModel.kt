package com.main.hero_app.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.hero_app.Model.HeroDetails.*
import com.main.hero_app.Model.Services.HeroService
import com.main.hero_app.Model.Services.RetrofitHelper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException

class HeroDetailsViewModel : ViewModel() {
    val retrofitInstance = RetrofitHelper.getInstance().create(HeroService::class.java);
    val listData: MutableLiveData<ArrayList<String>> = MutableLiveData()

     fun getHeroesConnectionsById(id:String) {
        Log.d("pttt", "getHeroesConnectionsById: ")
        try {
            val o1:Observable<HeroAppearance> =retrofitInstance.getAppearanceById(id).subscribeOn(Schedulers.io())
            val o2:Observable<HeroConnections> =retrofitInstance.getConnectionById(id).subscribeOn(Schedulers.io())
            val o3:Observable<HeroWork> =retrofitInstance.getWorkById(id).subscribeOn(Schedulers.io())

            Observable.zip(o1,o2,o3,Function3<HeroAppearance,HeroConnections,HeroWork,ArrayList<String>>(){ h, c, w -> onResponse(h,c,w)})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ temp_list ->listData.postValue(temp_list); Log.d("pttt", "getHeroesConnectionsById: GOOS" + temp_list) },
                    { t -> Log.d("pttt", "onFailure: "+t.toString()) })
        } catch (e: HttpException){
            Log.d("pttt", "getHeroesByName: Exception"+e.message)
            e.stackTrace
        }
    }

    private fun onResponse(response1: HeroAppearance,response2: HeroConnections,response: HeroWork):ArrayList<String> {
        Log.d("pttt " , "onResponse: "+response1+" "+response2+ " "+response)
        val temp_list: ArrayList<String> = ArrayList()
        temp_list.add(response1.toString())
        temp_list.add(response2.toString())
        temp_list.add(response.toString())

        return temp_list
    }

}
