package com.dtuskenis.papajohnscodes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object RemotePromoCodesProvider : PromoCodesProvider {

    private const val DEFAULT_ENDPOINT = "https://www.papajohns.by"

    private val api: PapaJohnsApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DEFAULT_ENDPOINT)
            .build()
            .create()
    }

    override suspend fun getCodes(): List<PromoCode> = api.getCodes().codes

    private interface PapaJohnsApi {

        @GET("api/stock/codes")
        suspend fun getCodes(): CodesResponse

        data class CodesResponse(val codes: List<PromoCode>)
    }
}
