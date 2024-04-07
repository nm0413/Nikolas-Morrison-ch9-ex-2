package com.msu.morrison.criminalintent

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import java.util.Date
import java.util.UUID

class CrimeDetailFragmentTest {

    @Test
    fun testCrimeDetailFragment_displayInitialData() {
        // Given a crime
        val initialCrime = Crime(
            id = UUID.randomUUID(),
            title = "Test Crime",
            date = Date(),
            isSolved = true
        )

        // Launch the fragment with initial data
        val scenario: FragmentScenario<CrimeDetailFragment> = launchFragmentInContainer(
            themeResId = R.style.Theme_CriminalIntent
        )
        scenario.onFragment { fragment ->
            fragment.crime = initialCrime
        }

        // Verify initial data is displayed
        onView(withId(R.id.crime_title)).check(matches(withText(initialCrime.title)))
        onView(withId(R.id.crime_date)).check(matches(withText(initialCrime.date.toString())))
        onView(withId(R.id.crime_solved)).check(matches(isChecked()))
    }

    @Test
    fun testCrimeDetailFragment_updateCrimeTitle() {
        // Launch the fragment
        val scenario: FragmentScenario<CrimeDetailFragment> = launchFragmentInContainer(
            themeResId = R.style.Theme_CriminalIntent
        )

        // Type a new title into the EditText
        val newTitle = "Updated Title"
        onView(withId(R.id.crime_title)).perform(typeText(newTitle))

        // Verify the EditText has the new title
        onView(withId(R.id.crime_title)).check(matches(withText(newTitle)))
    }

    @Test
    fun testCrimeDetailFragment_toggleSolvedState() {
        // Launch the fragment
        val scenario: FragmentScenario<CrimeDetailFragment> = launchFragmentInContainer(
            themeResId = R.style.Theme_CriminalIntent
        )

        // Click the solved checkbox
        onView(withId(R.id.crime_solved)).perform(click())

        // Verify the solved state is toggled
        onView(withId(R.id.crime_solved)).check(matches(isChecked()))
    }
}
