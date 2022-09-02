package com.example.wordsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    lateinit var navController : TestNavHostController
    lateinit var exampleFragmentScenario : FragmentScenario<Fragment>
    @Before
    fun setup(){
        navController = TestNavHostController( ApplicationProvider.getApplicationContext() )
        exampleFragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_Words)
        exampleFragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(),navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component(){
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2,click()))

        assertEquals(navController.currentDestination?.id,R.id.wordListFragment2)
    }

    @After
    fun endTest(){
        println("End test")
    }

    companion object{
        @BeforeClass
        @JvmStatic
        fun setupClass(){
            println("Set up class")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass(){
            println("Tear down class")
        }

    }
}