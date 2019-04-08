package com.dtuskenis.papajohnscodes

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object PromoCodesProvider {

    private const val DEFAULT_ENDPOINT = "https://www.papajohns.by"

    private val api: PapaJohnsApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(DEFAULT_ENDPOINT)
            .build()
            .create<PapaJohnsApi>()
    }

    val codes: Single<List<PromoCode>> =
        api.getCodes()
            .map { it.codes }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private interface PapaJohnsApi {

        @GET("api/stock/codes")
        fun getCodes(): Single<CodesResponse>

        data class CodesResponse(val codes: List<PromoCode>)
    }
}