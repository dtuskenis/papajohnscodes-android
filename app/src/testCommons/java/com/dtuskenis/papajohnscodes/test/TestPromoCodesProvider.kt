package com.dtuskenis.papajohnscodes.test

import com.dtuskenis.papajohnscodes.PromoCode
import com.dtuskenis.papajohnscodes.PromoCodesProvider
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class TestPromoCodesProvider(
    private val json: Json
) : PromoCodesProvider {

    override suspend fun getCodes(): List<PromoCode> =
        json.decodeFromString(string = loadPromoCodesJson())

    private fun loadPromoCodesJson(): String =
        javaClass.getResourceAsStream("/promo_codes.json")!!
            .use { it.bufferedReader().readText() }
}
