package com.dtuskenis.papajohnscodes.test

import com.dtuskenis.papajohnscodes.PromoCode
import com.dtuskenis.papajohnscodes.PromoCodesProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TestPromoCodesProvider : PromoCodesProvider {

    private val promoCodesTypeToken = object : TypeToken<List<PromoCode>>() {}

    override suspend fun getCodes(): List<PromoCode> =
        parsePromoCodes(json = loadPromoCodesJson())

    private fun loadPromoCodesJson(): String =
        javaClass.getResourceAsStream("/promo_codes.json")!!
            .use { it.bufferedReader().readText() }

    private fun parsePromoCodes(json: String): List<PromoCode> =
        Gson().fromJson(json, promoCodesTypeToken.type)
}
