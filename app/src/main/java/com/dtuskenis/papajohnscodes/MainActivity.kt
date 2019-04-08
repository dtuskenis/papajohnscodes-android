package com.dtuskenis.papajohnscodes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.view_main.*

class MainActivity: AppCompatActivity() {

    private val clipboard: ClipboardManager by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    private var apiRequest: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_main)

        val codesAdapter = PromoCodesAdapter(this::copyCodeToClipboard)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = codesAdapter
        }

        loadData { codesAdapter.data = it }
    }

    override fun onDestroy() {
        apiRequest?.dispose()
        apiRequest = null

        super.onDestroy()
    }

    private fun loadData(receiveData: (List<PromoCode>) -> Unit) {
        apiRequest = PromoCodesProvider.codes
            .doOnEvent { _, _ -> loadingIndicator.visibility = View.GONE }
            .subscribeBy(
                onSuccess = { receiveData(it) },
                onError = { handleError(it) }
            )
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        error.message?.let { toast(it) }
    }

    private fun copyCodeToClipboard(code: PromoCode) {
        clipboard.primaryClip = ClipData.newPlainText(getString(R.string.clipboard_label), code.rawValue)
        toast(getString(R.string.clipboard_toast))
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
