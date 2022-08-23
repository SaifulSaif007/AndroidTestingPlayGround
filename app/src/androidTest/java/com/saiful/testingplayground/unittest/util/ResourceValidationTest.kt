package com.saiful.testingplayground.unittest.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.saiful.testingplayground.R
import org.junit.After
import org.junit.Before

class ResourceValidationTest {

    private lateinit var resourceValidation: ResourceValidation

    @Before
    fun setup() {
        resourceValidation = ResourceValidation()
    }

    @After
    fun tearDown(){
        // Anything to execute after each test run
    }

    @Test
    fun stringSameAsResource_ReturnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result =
            resourceValidation.isResourceEqual(context, R.string.app_name, "TestingPlayGround")
        assertThat(result).isTrue()
    }

    @Test
    fun stringDifferentAsResource_ReturnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceValidation.isResourceEqual(context, R.string.app_name, "Tsdlhsahdk")
        assertThat(result).isFalse()
    }
}