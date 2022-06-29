package com.saiful.testingplayground.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `any empty field returns false`() {
        val result = RegistrationUtil.validateInput(
            "",
            "",
            ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `any null field returns false`() {
        val result = RegistrationUtil.validateInput(
            null,
            null,
            null
        )

        assertThat(result).isFalse()
    }


    @Test
    fun `passwords not matched returns false`() {
        val result = RegistrationUtil.validateInput(
            "abc",
            "1234",
            "1233"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `passwords length not valid returns false`() {
        val result = RegistrationUtil.validateInput(
            "abc",
            "12",
            "12"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username & password return true`() {
        val result = RegistrationUtil.validateInput(
            "abc",
            "1234",
            "1234"
        )

        assertThat(result).isTrue()
    }

}