package com.dtuskenis.papajohnscodes.test

import android.content.res.AssetManager
import com.dtuskenis.papajohnscodes.PromoCode
import com.dtuskenis.papajohnscodes.PromoCodesProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single

class TestPromoCodesProvider(private val assets: AssetManager): PromoCodesProvider {

    private val promoCodesTypeToken = object : TypeToken<List<PromoCode>>(){}

    override val codes: Single<List<PromoCode>> =
        Single.fromCallable { loadPromoCodesJson() }
              .map { parsePromoCodes(it) }

    private fun loadPromoCodesJson(): String =
        assets.open("promo_codes.json").use { it.bufferedReader().readText() }

    private fun parsePromoCodes(json: String): List<PromoCode> =
        Gson().fromJson(json, promoCodesTypeToken.type)
}