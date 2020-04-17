package com.dtuskenis.papajohnscodes.test

import androidx.test.rule.ActivityTestRule
import com.dtuskenis.papajohnscodes.MainActivity
import org.junit.Rule
import org.junit.Test

class InstrumentationTests {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testPromoCodesSelectionWorks() {
        MainActivityTestsCases.testPromoCodesSelectionWorks(activityRule.activity)
    }
}