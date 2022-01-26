package com.main.hero_app.Model.Services

import com.main.hero_app.Model.HeroDetails.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("search/{name}")
    fun getHeroesByName(@Path("name") heroName:String ): Observable<HeroesList>
    @GET("{id}/appearance")
    fun getAppearanceById(@Path("id") heroId:String ): Observable<HeroAppearance>
    @GET("{id}/connections")
    fun getConnectionById(@Path("id") heroId:String ): Observable<HeroConnections>
    @GET("{id}/work")
    fun getWorkById(@Path("id") heroId:String ): Observable<HeroWork>
}
