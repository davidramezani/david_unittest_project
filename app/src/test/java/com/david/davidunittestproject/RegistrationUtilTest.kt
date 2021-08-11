package com.david.davidunittestproject

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Philipp",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exist return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password return false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "david",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password was repeated incorrectly return false` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "david",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than 2 digits` () {
        val result = RegistrationUtil.validateRegistrationInput(
            "david",
            "asd1",
            "asd1"
        )
        assertThat(result).isFalse()
    }

}