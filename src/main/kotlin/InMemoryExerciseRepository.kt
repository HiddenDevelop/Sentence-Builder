class InMemoryExerciseRepository(
    private val exercises: List<SentenceExercise>
) : ExerciseRepository {

    private var currentIndex = 0

    init {
        require(exercises.isNotEmpty()) {
            "At least one exercise is required."
        }

        require(exercises.all { SentenceExerciseValidator.validate(it) }) {
            "All exercises must be valid."
        }
    }

    override fun getExercise(): SentenceExercise {
        val exercise = exercises[currentIndex]

        currentIndex = (currentIndex + 1) % exercises.size

        return exercise
    }
}