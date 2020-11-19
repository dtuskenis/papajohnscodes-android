package com.dtuskenis.papajohnscodes

import android.app.Application
import kotlinx.serialization.json.Json

open class PromoCodesApplication : Application() {

    protected val json = Json {
        ignoreUnknownKeys = true
    }

    open val promoCodesProvider: PromoCodesProvider = RemotePromoCodesProvider(json)
}
