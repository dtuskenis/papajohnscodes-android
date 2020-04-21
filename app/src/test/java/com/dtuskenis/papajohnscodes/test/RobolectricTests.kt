package com.dtuskenis.papajohnscodes.test

import androidx.test.core.app.ActivityScenario
import com.dtuskenis.papajohnscodes.BuildConfig
import com.dtuskenis.papajohnscodes.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class,
        minSdk = BuildConfig.MIN_SDK,
        maxSdk = BuildConfig.MAX_SDK)
class RobolectricTests {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        ShadowLooper.pauseMainLooper()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        ShadowLooper.unPauseMainLooper()
    }

    @After
    fun tearDown() {
        scenario.close()
        ShadowLooper.idleMainLooper()
    }

    @Test
    fun `Test promocode selection works `() {
        scenario.onActivity {
            MainActivityTestsCases.testPromoCodesSelectionWorks(it)
        }
    }
}
