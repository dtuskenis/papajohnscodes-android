package com.dtuskenis.papajohnscodes.test

import com.dtuskenis.papajohnscodes.PromoCodesApplication
import com.dtuskenis.papajohnscodes.PromoCodesProvider
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class TestApplication : PromoCodesApplication() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules(module {
            factory<PromoCodesProvider>(override = true) {
                TestPromoCodesProvider(json = get())
            }
        })
    }
}
