package com.dtuskenis.papajohnscodes

import android.app.Application

open class PromoCodesApplication: Application() {

    open val promoCodesProvider: PromoCodesProvider = RemotePromoCodesProvider
}