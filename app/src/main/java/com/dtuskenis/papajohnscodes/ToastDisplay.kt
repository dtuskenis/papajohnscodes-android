package com.dtuskenis.papajohnscodes

import android.content.Context
import android.widget.Toast

class ToastDisplay(private val context: Context) {

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
