import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SentenceGameTest {

    private fun createExercise(): SentenceExercise {
        return SentenceExercise(
            sentence = "She goes to school.",
            words = listOf(
                SentenceWord(1, "She"),
                SentenceWord(2, "goes"),
                SentenceWord(3, "to"),
                SentenceWord(4, "school.")
            ),
            correctOrder = listOf(1, 2, 3, 4),
            scrambledOrder = listOf(4, 1, 3, 2)
        )
    }

    @Test
    fun `scrambled words follow scrambled order`() {
        val game = SentenceGame(createExercise())

        assertEquals(
            listOf("school.", "She", "to", "goes"),
            game.scrambledWords.map { it.text }
        )
    }

    @Test
    fun `correct selection passes`() {
        val game = SentenceGame(createExercise())

        // scrambled:
        // 1 = school.
        // 2 = She
        // 3 = to
        // 4 = goes
        //
        // correct selection:
        // She goes to school.
        assertTrue(
            game.isCorrect(
                listOf(2, 4, 3, 1)
            )
        )
    }

    @Test
    fun `incorrect selection fails`() {
        val game = SentenceGame(createExercise())

        assertFalse(
            game.isCorrect(
                listOf(2, 3, 4, 1)
            )
        )
    }

    @Test
    fun `build sentence returns selected sentence`() {
        val game = SentenceGame(createExercise())

        assertEquals(
            "She goes to school.",
            game.buildSentence(
                listOf(2, 4, 3, 1)
            )
        )
    }

    @Test
    fun `repeated indexes are rejected`() {
        val game = SentenceGame(createExercise())

        assertFailsWith<IllegalArgumentException> {
            game.isCorrect(
                listOf(2, 4, 3, 3)
            )
        }
    }

    @Test
    fun `out of range index is rejected`() {
        val game = SentenceGame(createExercise())

        assertFailsWith<IllegalArgumentException> {
            game.isCorrect(
                listOf(2, 4, 3, 9)
            )
        }
    }

    @Test
    fun `missing indexes are rejected`() {
        val game = SentenceGame(createExercise())

        assertFailsWith<IllegalArgumentException> {
            game.isCorrect(
                listOf(2, 4, 3)
            )
        }
    }
}