import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ExerciseLoaderTest {

    @Test
    fun `loads exercise from resource`() {
        val exercise = ExerciseLoader.loadFromResource(
            "exercise.json"
        )

        assertEquals(
            "She goes to school every morning.",
            exercise.sentence
        )

        assertEquals(6, exercise.words.size)

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6),
            exercise.correctOrder
        )

        assertTrue(
            SentenceExerciseValidator.validate(exercise)
        )
    }

    @Test
    fun `missing resource throws exception`() {
        assertFailsWith<IllegalArgumentException> {
            ExerciseLoader.loadFromResource(
                "does-not-exist.json"
            )
        }
    }
}