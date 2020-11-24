package com.dtuskenis.papajohnscodes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import kotlinx.android.synthetic.main.view_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val promoCodesProvider: PromoCodesProvider
            by lazy { (applicationContext as PromoCodesApplication).promoCodesProvider }

    private val clipboard: ClipboardManager
            by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_main)

        val codesAdapter = PromoCodesAdapter(onItemSelected = this::copyCodeToClipboard)

        recyclerView.adapter = codesAdapter

        loadData { codesAdapter.data = it }
    }

    private fun loadData(receiveData: (List<PromoCode>) -> Unit) {
        lifecycle.coroutineScope.launch {
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
        error.message?.let { toast(it) }
    }

    private fun copyCodeToClipboard(code: PromoCode) {
        clipboard.setPrimaryClip(ClipData.newPlainText(
            getString(R.string.clipboard_label),
            code.rawValue
        ))
        toast(getString(R.string.clipboard_toast))
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
