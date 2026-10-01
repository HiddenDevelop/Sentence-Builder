class LocalExerciseRepository(
    private val resourceName: String = "exercise.json"
) : ExerciseRepository {

    override fun getExercise(): SentenceExercise {
        return ExerciseLoader.loadFromResource(resourceName)
    }
}