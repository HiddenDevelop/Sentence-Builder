object DemoExercises {

    fun all(): List<SentenceExercise> {
        return listOf(
            SentenceExercise(
                sentence = "She goes to school every morning.",
                words = listOf(
                    SentenceWord(1, "She"),
                    SentenceWord(2, "goes"),
                    SentenceWord(3, "to"),
                    SentenceWord(4, "school"),
                    SentenceWord(5, "every"),
                    SentenceWord(6, "morning.")
                ),
                correctOrder = listOf(1, 2, 3, 4, 5, 6),
                scrambledOrder = listOf(4, 1, 6, 3, 2, 5)
            ),

            SentenceExercise(
                sentence = "They are playing soccer outside.",
                words = listOf(
                    SentenceWord(1, "They"),
                    SentenceWord(2, "are"),
                    SentenceWord(3, "playing"),
                    SentenceWord(4, "soccer"),
                    SentenceWord(5, "outside.")
                ),
                correctOrder = listOf(1, 2, 3, 4, 5),
                scrambledOrder = listOf(3, 5, 1, 4, 2)
            ),

            SentenceExercise(
                sentence = "My brother likes cold pizza.",
                words = listOf(
                    SentenceWord(1, "My"),
                    SentenceWord(2, "brother"),
                    SentenceWord(3, "likes"),
                    SentenceWord(4, "cold"),
                    SentenceWord(5, "pizza.")
                ),
                correctOrder = listOf(1, 2, 3, 4, 5),
                scrambledOrder = listOf(4, 2, 5, 1, 3)
            )
        )
    }
}