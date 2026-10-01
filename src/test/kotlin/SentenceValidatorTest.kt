import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SentenceValidatorTest {

    private val validator = SentenceValidator()

    @Test
    fun `correct word order passes`() {
        val correct = listOf(1, 2, 3, 4, 5)

        assertTrue(
            validator.isCorrect(
                userOrder = listOf(1, 2, 3, 4, 5),
                correctOrder = correct
            )
        )
    }

    @Test
    fun `incorrect word order fails`() {
        val correct = listOf(1, 2, 3, 4, 5)

        assertFalse(
            validator.isCorrect(
                userOrder = listOf(2, 1, 3, 4, 5),
                correctOrder = correct
            )
        )
    }
}