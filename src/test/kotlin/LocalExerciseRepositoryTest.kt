import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocalExerciseRepositoryTest {

    @Test
    fun `repository returns valid exercise`() {
        val repository = LocalExerciseRepository()

        val exercise = repository.getExercise()

        assertEquals(
            "She goes to school every morning.",
            exercise.sentence
        )

        assertTrue(
            SentenceExerciseValidator.validate(exercise)
        )
    }
}