package com.dtuskenis.papajohnscodes

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class PromoCodesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PromoCodesApplication)

            modules(module {
                factory {
                    Json {
                        ignoreUnknownKeys = true
                    }
                }

                factory {
                    androidContext()
                        .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                }

                factory<PromoCodesProvider> {
                    RemotePromoCodesProvider(json = get())
                }

                factory { ToastDisplay(context = androidContext()) }
            })
        }
    }
}
