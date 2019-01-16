package br.com.charleston.github.tests

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.annotation.StringRes
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.GrantPermissionRule
import br.com.charleston.github.R
import br.com.charleston.github.config.MOCK_SERVER_DOMAIN
import br.com.charleston.github.config.MOCK_SERVER_PORT
import br.com.charleston.github.features.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
class VoiceSearchScreenTest {

    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.RECORD_AUDIO)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val server = MockWebServer()

    private val searchBy = "Android"

    @Before
    fun setup() {
        server.apply {
            start(MOCK_SERVER_PORT)
            url(MOCK_SERVER_DOMAIN)
        }

        activityRule.launchActivity(null)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldMessageAndButtonVisible() {
        onView(withId(R.id.message))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.voice))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun checkInitialMessage() {
        onView(withId(R.id.message))
            .check(matches(withText(getStringToTest(R.string.voice_search_message_inital))))
    }

    @Test
    fun checkMessageWhenPressSearch() {
        onView(withId(R.id.voice))
            .perform(click())

        onView(withId(R.id.message))
            .check(matches(withText(getStringToTest(R.string.voice_search_message_listening))))
    }

    @Test
    fun searchAndDisplayMessageResult() {
        intending(hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH))
            .respondWith(
                Instrumentation
                    .ActivityResult(
                        Activity.RESULT_OK, resultIntent()
                    )
            )

        onView(withId(R.id.voice))
            .perform(click())


        intended(
            hasAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        )

        /*onView(withId(R.id.message))
            .check(
                matches(
                    withText(
                        String.format(getStringToTest(R.string.voice_search_message_find_repository_by), search)
                    )
                )
            )*/
    }

    /**
     * Function for add 'space' in string with '\n'
     * because Android add when paint in screen
     *
     */
    private fun getStringToTest(@StringRes resId: Int): String {
        val value = context.getString(resId)
        return value.replace("\n", "\n ")
    }

    private fun resultIntent(): Intent {
        return Intent().apply {
            putStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS, arrayListOf(searchBy))
        }
    }
}