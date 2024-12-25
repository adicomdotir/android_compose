package ir.adicom.myapplication

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun subtract_isCorrect() {
        assertEquals(2, 4 - 2)
    }

    @Test
    fun `check that model is true format`() {
        assertEquals(0, 0)
    }

    @Test
    fun `check that model is false format`() {
        assertEquals(0, 1)
    }
}
