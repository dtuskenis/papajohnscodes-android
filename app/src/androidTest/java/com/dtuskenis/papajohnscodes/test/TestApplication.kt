package com.dtuskenis.papajohnscodes.test

import androidx.test.platform.app.InstrumentationRegistry
import com.dtuskenis.papajohnscodes.PromoCodesApplication
import com.dtuskenis.papajohnscodes.PromoCodesProvider

class TestApplication: PromoCodesApplication() {

    override val promoCodesProvider: PromoCodesProvider
        get() = TestPromoCodesProvider(InstrumentationRegistry.getInstrumentation().context.assets)
}