package com.dtuskenis.papajohnscodes.test

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * See 'testInstrumentationRunner' in build.gradle
 */
@Suppress("unused")
class InstrumentationRunner: AndroidJUnitRunner() {

    override fun newApplication(classLoader: ClassLoader,
                                className: String,
                                context: Context): Application =
        super.newApplication(classLoader,
                             TestApplication::class.java.name,
                             context)
}