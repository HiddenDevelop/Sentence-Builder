import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InMemoryExerciseRepositoryTest {

    @Test
    fun `repository returns exercises in order`() {
        val exercises = DemoExercises.all()

        val repository =
            InMemoryExerciseRepository(exercises)

        assertEquals(
            exercises[0],
            repository.getExercise()
        )

        assertEquals(
            exercises[1],
            repository.getExercise()
        )

        assertEquals(
            exercises[2],
            repository.getExercise()
        )
    }

    @Test
    fun `repository loops back to first exercise`() {
        val exercises = DemoExercises.all()

        val repository =
            InMemoryExerciseRepository(exercises)

        repository.getExercise()
        repository.getExercise()
        repository.getExercise()

        assertEquals(
            exercises[0],
            repository.getExercise()
        )
    }

    @Test
    fun `empty repository is rejected`() {
        assertFailsWith<IllegalArgumentException> {
            InMemoryExerciseRepository(
                emptyList()
            )
        }
    }
}