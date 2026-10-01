object ExerciseLoader {

    fun loadFromResource(resourceName: String): SentenceExercise {
        val resource = ExerciseLoader::class.java
            .classLoader
            .getResource(resourceName)
            ?: throw IllegalArgumentException(
                "Resource not found: $resourceName"
            )

        val rawJson = resource.readText()

        val exercise = SentenceExerciseParser.parse(rawJson)

        require(
            SentenceExerciseValidator.validate(exercise)
        ) {
            "Exercise resource is invalid: $resourceName"
        }

        return exercise
    }
}