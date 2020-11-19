package com.dtuskenis.papajohnscodes.test

import com.dtuskenis.papajohnscodes.PromoCodesApplication
import com.dtuskenis.papajohnscodes.PromoCodesProvider

class TestApplication : PromoCodesApplication() {

    override val promoCodesProvider: PromoCodesProvider
        get() = TestPromoCodesProvider(json)
}
