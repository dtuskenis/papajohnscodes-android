package com.dtuskenis.papajohnscodes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class MainActivity: AppCompatActivity() {

    private var apiRequest: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiRequest = PapaJohnsCodesProvider.codes
            .subscribeBy(
                onSuccess = {
                    toast("Success: $it")
                },
                onError = {
                    toast("Error: $it")
                }
            )
    }

    override fun onDestroy() {
        apiRequest?.dispose()
        apiRequest = null

        super.onDestroy()
    }

    private fun toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
