package com.dtuskenis.papajohnscodes

interface PromoCodesProvider {

    suspend fun getCodes(): List<PromoCode>
}
