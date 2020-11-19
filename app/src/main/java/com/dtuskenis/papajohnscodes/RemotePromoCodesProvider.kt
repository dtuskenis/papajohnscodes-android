package com.dtuskenis.papajohnscodes

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

class RemotePromoCodesProvider(
    private val json: Json
) : PromoCodesProvider {

    private val api: PapaJohnsApi by lazy {
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .baseUrl(DEFAULT_ENDPOINT)
            .build()
            .create()
    }

    override suspend fun getCodes(): List<PromoCode> = api.getCodes().codes

    private interface PapaJohnsApi {

        @GET("api/stock/codes")
        suspend fun getCodes(): CodesResponse

        @Serializable
        data class CodesResponse(val codes: List<PromoCode>)
    }

    private companion object {
        const val DEFAULT_ENDPOINT = "https://www.papajohns.by"
    }
}
