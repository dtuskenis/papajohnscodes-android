package com.dtuskenis.papajohnscodes.test

import android.content.ClipboardManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.dtuskenis.papajohnscodes.MainActivity
import com.dtuskenis.papajohnscodes.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTests {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val clipboard: ClipboardManager by lazy { activityRule.activity.getSystemService(ClipboardManager::class.java) }

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

    @Test
    fun testPromoCodesSelectionWorks() {
        onView(withId(R.id.recyclerView)).checkIfDisplayed()

        verify(TestItem.POTATO)
        verify(TestItem.WONDER)
    }

    private fun verify(testItem: TestItem) {
        onView(withId(R.id.recyclerView)).perform(scrollToPosition<RecyclerView.ViewHolder>(testItem.position))

        onView(withText(testItem.code)).checkIfDisplayed()
        onView(withText(testItem.description)).checkIfDisplayed()

        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(testItem.position, click()))

        assertEquals(testItem.code, clipboard.primaryClip!!.getItemAt(0).text)
    }

    private fun ViewInteraction.checkIfDisplayed() = check(matches(isDisplayed()))
}