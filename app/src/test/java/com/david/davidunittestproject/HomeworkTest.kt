package com.david.davidunittestproject

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeworkTest {

    @Test
    fun `fib(0) equal to zero return true` () {
        val result = Homework.fib(0)
        assertThat(result).isEqualTo(0L)
    }

    @Test
    fun `fib(1) equal to 1 return true` () {
        val result = Homework.fib(1)
        assertThat(result).isEqualTo(1L)
    }

    @Test
    fun `fib(8) equal to 21 return true`() {
        val result = Homework.fib(8)
        assertThat(result).isEqualTo(21L)
    }

    @Test
    fun `if send (a+4) must return true` () {
        val result = Homework.checkBraces("(a+4)")
        assertThat(result).isTrue()
    }

    @Test
    fun `if send )( must return false` () {
        val result = Homework.checkBraces(")(")
        assertThat(result).isFalse()
    }

}