package com.dtuskenis.papajohnscodes

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val application: PromoCodesApplication
            by lazy { applicationContext as PromoCodesApplication }

    private val clipboard: ClipboardManager
            by lazy { application.clipboardManager }

    private val promoCodesProvider: PromoCodesProvider
            by lazy { application.promoCodesProvider }

    private val toastDisplay
            by lazy { application.toastDisplay }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_main)

        val codesAdapter = PromoCodesAdapter(onItemSelected = this::copyCodeToClipboard)

        recyclerView.adapter = codesAdapter

        loadData { codesAdapter.data = it }
    }

    override fun onDestroy() {
        coroutineScope.cancel()

        super.onDestroy()
    }

    private fun loadData(receiveData: (List<PromoCode>) -> Unit) {
        coroutineScope.launch {
            try {
                receiveData(promoCodesProvider.getCodes())
            } catch (error: Throwable) {
                handleError(error)
            }
        }.invokeOnCompletion {
            loadingIndicator.visibility = View.GONE
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        error.message?.let { toastDisplay.showToast(it) }
    }

    private fun copyCodeToClipboard(code: PromoCode) {
        clipboard.setPrimaryClip(ClipData.newPlainText(
            getString(R.string.clipboard_label),
            code.rawValue
        ))
        toastDisplay.showToast(getString(R.string.clipboard_toast))
    }
}
