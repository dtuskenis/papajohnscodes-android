package com.dtuskenis.papajohnscodes.test

import android.content.res.AssetManager
import com.dtuskenis.papajohnscodes.PromoCode
import com.dtuskenis.papajohnscodes.PromoCodesProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.InputStream

class TestPromoCodesProvider(private val assets: AssetManager): PromoCodesProvider {

    private val promoCodesTypeToken = object : TypeToken<List<PromoCode>>(){}

    override val codes: Single<List<PromoCode>> =
        Single.fromCallable { readText { assets.open("promo_codes.json") } }
              .map { parsePromoCodes(it) }

    private fun readText(openStream: () -> InputStream): String {
        var inputStream: InputStream? = null
        try {
            inputStream = openStream()
            return inputStream.bufferedReader().readText()
        } finally {
            inputStream?.close()
        }
    }

    private fun parsePromoCodes(json: String): List<PromoCode> = Gson().fromJson(json, promoCodesTypeToken.type)
}