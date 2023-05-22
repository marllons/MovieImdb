package com.marllons.movieimdb.presenter.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marllons.mshttp.MsHttpModules
import com.marllons.movieimdb.R
import com.marllons.movieimdb.di.movieImdbModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class MainFragmentTest : AutoCloseKoinTest() {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(listOf(MsHttpModules.all, movieImdbModules))
    }

    @Before
    fun setup() {
        launchFragmentInContainer<MainFragment>(themeResId = R.style.Theme_MovieImdb)
    }

    @Test
    fun should_show_users_when_view_is_created() {

    }
}