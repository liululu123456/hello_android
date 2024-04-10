package com.thoughtworks.androidtrain

import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import com.thoughtworks.androidtrain.ui.activity.LoginActivity
import com.thoughtworks.androidtrain.ui.activity.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class MainActivityTest {
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()

    }
    @Test
    fun testActivity() {
        mainActivity.findViewById<LinearLayout>(R.id.main).findViewWithTag<Button>("login").performClick()
        val expectedIntent: Intent = Intent(mainActivity, LoginActivity::class.java)
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)


        val loginPageActivity: LoginActivity = Robolectric.buildActivity(LoginActivity::class.java).create()
                .get()

        val rememberMeCheckbox: CheckBox = loginPageActivity.findViewById(R.id.checkbox)

        rememberMeCheckbox.performClick()
        assertTrue(rememberMeCheckbox.isChecked)
    }
}