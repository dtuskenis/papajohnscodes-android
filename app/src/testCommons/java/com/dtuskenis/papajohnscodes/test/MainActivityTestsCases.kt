package com.dtuskenis.papajohnscodes.test

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dtuskenis.papajohnscodes.R
import org.junit.Assert.*

object MainActivityTestsCases {

    private enum class TestItem(val code: String,
                                val description: String,
                                val position: Int) {
        POTATO(code = "POTATO",
               description = "Картофельные дольки - От 15.00 рублей - Минск",
               position = 1)
        ,
        WONDER(code = "WONDER35",
               description = "На выбор из 5 пицц 23ТРАД - От 33.00 рублей - Минск",
               position = 25)
        ;
    }

    fun testPromoCodesSelectionWorks(activity: Activity) {
        onView(withId(R.id.recyclerView)).checkIfDisplayed()

        val clipboard: ClipboardManager by lazy {
            activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        }

        verify(TestItem.POTATO, clipboard)
        verify(TestItem.WONDER, clipboard)
    }

    private fun verify(testItem: TestItem, clipboard: ClipboardManager) {
        onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(testItem.position))

        onView(withText(testItem.code)).checkIfDisplayed()
        onView(withText(testItem.description)).checkIfDisplayed()

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(testItem.position, click()))

        assertEquals(testItem.code, clipboard.primaryClip!!.getItemAt(0).text)
    }

    private fun ViewInteraction.checkIfDisplayed() = check(matches(isDisplayed()))
}