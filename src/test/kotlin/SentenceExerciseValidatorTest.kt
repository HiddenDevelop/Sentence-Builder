import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SentenceExerciseValidatorTest {

    @Test
    fun `valid exercise passes`() {

        val exercise = SentenceExercise(
            sentence = "She goes home.",
            words = listOf(
                SentenceWord(1, "She"),
                SentenceWord(2, "goes"),
                SentenceWord(3, "home.")
            ),
            correctOrder = listOf(1, 2, 3),
            scrambledOrder = listOf(3, 1, 2)
        )

        assertTrue(
            SentenceExerciseValidator.validate(exercise)
        )
    }

    @Test
    fun `missing word id fails`() {

        val exercise = SentenceExercise(
            sentence = "She goes home.",
            words = listOf(
                SentenceWord(1, "She"),
                SentenceWord(2, "goes")
            ),
            correctOrder = listOf(1, 2, 3),
            scrambledOrder = listOf(2, 1, 3)
        )

        assertFalse(
            SentenceExerciseValidator.validate(exercise)
        )
    }

    @Test
    fun `identical scrambled order fails`() {

        val exercise = SentenceExercise(
            sentence = "She goes home.",
            words = listOf(
                SentenceWord(1, "She"),
                SentenceWord(2, "goes"),
                SentenceWord(3, "home.")
            ),
            correctOrder = listOf(1, 2, 3),
            scrambledOrder = listOf(1, 2, 3)
        )

        assertFalse(
            SentenceExerciseValidator.validate(exercise)
        )
    }
}