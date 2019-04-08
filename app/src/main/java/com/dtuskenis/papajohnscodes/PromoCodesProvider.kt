package com.dtuskenis.papajohnscodes

import io.reactivex.Single

interface PromoCodesProvider {

    val codes: Single<List<PromoCode>>
}