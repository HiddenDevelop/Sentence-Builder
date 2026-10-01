import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class SentenceGameStateTest {

    private fun createState(): SentenceGameState {
        val exercise = SentenceExercise(
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

        return SentenceGameState(exercise)
    }

    @Test
    fun `starts with all words available`() {
        val state = createState()

        assertEquals(
            listOf("school.", "She", "to", "goes"),
            state.availableWords.map { it.text }
        )

        assertTrue(state.selectedWords.isEmpty())
    }

    @Test
    fun `selecting word moves it to selected list`() {
        val state = createState()

        state.selectWord(1)

        assertEquals(
            listOf("school.", "to", "goes"),
            state.availableWords.map { it.text }
        )

        assertEquals(
            listOf("She"),
            state.selectedWords.map { it.text }
        )
    }

    @Test
    fun `selected word can be removed`() {
        val state = createState()

        state.selectWord(1)
        state.removeSelectedWord(0)

        assertTrue(state.selectedWords.isEmpty())

        assertEquals(4, state.availableWords.size)
    }

    @Test
    fun `current sentence reflects selected words`() {
        val state = createState()

        state.selectWord(1)
        state.selectWord(2)

        assertEquals(
            "She goes",
            state.currentSentence()
        )
    }

    @Test
    fun `correct completed sentence passes`() {
        val state = createState()

        // Initial:
        // 0 = school.
        // 1 = She
        // 2 = to
        // 3 = goes

        state.selectWord(1) // She

        // Remaining:
        // 0 = school.
        // 1 = to
        // 2 = goes

        state.selectWord(2) // goes

        // Remaining:
        // 0 = school.
        // 1 = to

        state.selectWord(1) // to
        state.selectWord(0) // school.

        assertTrue(state.isComplete)
        assertTrue(state.isCorrect())
    }

    @Test
    fun `incomplete answer is not correct`() {
        val state = createState()

        state.selectWord(1)

        assertFalse(state.isComplete)
        assertFalse(state.isCorrect())
    }

    @Test
    fun `reset restores initial state`() {
        val state = createState()

        state.selectWord(1)
        state.selectWord(2)

        state.reset()

        assertTrue(state.selectedWords.isEmpty())

        assertEquals(
            listOf("school.", "She", "to", "goes"),
            state.availableWords.map { it.text }
        )
    }

    @Test
    fun `invalid available index is rejected`() {
        val state = createState()

        assertFailsWith<IllegalArgumentException> {
            state.selectWord(100)
        }
    }
}