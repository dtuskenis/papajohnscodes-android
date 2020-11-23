package com.dtuskenis.papajohnscodes

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import kotlinx.serialization.json.Json

open class PromoCodesApplication : Application() {

    val clipboardManager: ClipboardManager
            by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    protected val json = Json {
        ignoreUnknownKeys = true
    }

    open val promoCodesProvider: PromoCodesProvider = RemotePromoCodesProvider(json)

    val toastDisplay: ToastDisplay by lazy { ToastDisplay(this) }
}
