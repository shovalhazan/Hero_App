package com.main.hero_app.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.Model.HeroDetails.HeroesList
import com.main.hero_app.Model.Services.HeroService
import com.main.hero_app.Model.Services.RetrofitHelper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

val myHeroNames:Array<String> = arrayOf("Wonder Woman", "Harry Potter", "Joker")
class RecyclerFragmentViewModel: ViewModel() {
    val retrofitInstance = RetrofitHelper.getInstance().create(HeroService::class.java);
    var _heroList :MutableLiveData<HeroesList> = MutableLiveData()
    val heroList: LiveData<HeroesList>
        get() = _heroList

     var _myHeroList :MutableLiveData<ArrayList<HeroDetailsModel>> = MutableLiveData()

    private val _heroStatus = MutableLiveData<HeroApiStatus>()
    val heroStatus: LiveData<HeroApiStatus>
        get() = _heroStatus

    init{
        _heroStatus.value = HeroApiStatus.NONE
    }

    fun getHeroListObserver():MutableLiveData<HeroesList>{
        Log.d("pttt", "getHeroListObserver: ")
        return _heroList
    }

    fun getHeroesByName(name:String) {
        Log.d("pttt", "onCreate: ")
        try {
                 retrofitInstance.getHeroesByName(name)
                 .debounce(400, TimeUnit.MILLISECONDS)
                 .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })
        } catch (e: Exception){
            Log.d("pttt", "getHeroesByName: Exception"+e.message)
            e.stackTrace
        }
    }
    fun getMyHeroesByName() {
        try {
            _heroStatus.value = HeroApiStatus.LOADING
            val o1: Observable<HeroesList> =retrofitInstance.getHeroesByName(myHeroNames[0]).subscribeOn(Schedulers.io())
            val o2: Observable<HeroesList> =retrofitInstance.getHeroesByName(myHeroNames[1]).subscribeOn(Schedulers.io())
            val o3: Observable<HeroesList> =retrofitInstance.getHeroesByName(myHeroNames[2]).subscribeOn(Schedulers.io())
            Observable.zip(o1,o2,o3,
                Function3<HeroesList, HeroesList, HeroesList,ArrayList<HeroDetailsModel>> { h1, h2, h3 -> onResponse(h1,h2,h3) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ temp_list ->_myHeroList.postValue(temp_list); Log.d("pttt", "getHeroesConnectionsById: GOOS" + temp_list) },
                    { t ->onFailure(t) })
        } catch (e: HttpException){
            Log.d("pttt", "getHeroesByName: Exception"+e.message)
            e.stackTrace
        }
    }
    private fun onFailure(t: Throwable?)  {
        Log.d("pttt", "onFailure: "+t.toString())
        _heroStatus.value = HeroApiStatus.NO_INTERNET_CONNECTION
    }

    private fun onResponse(response: HeroesList) {
        _heroList.postValue(response)
        _heroStatus.value = HeroApiStatus.DONE
    }
    private fun onResponse(h1:HeroesList,h2: HeroesList,h3: HeroesList): ArrayList<HeroDetailsModel> {
        val myHeroList:ArrayList<HeroDetailsModel> = ArrayList()
        myHeroList.add(h1.results[0])
        myHeroList.add(h2.results[0])
        myHeroList.add(h3.results[0])
        return myHeroList
    }

    public enum class HeroApiStatus{
        LOADING,
        NO_INTERNET_CONNECTION,
        DONE,
        NONE,

    }
}