class SentenceGame(
    val exercise: SentenceExercise
) {

    init {
        require(
            SentenceExerciseValidator.validate(exercise)
        ) {
            "Invalid sentence exercise."
        }
    }

    val scrambledWords: List<SentenceWord> =
        exercise.scrambledOrder.map { id ->
            exercise.words.first { it.id == id }
        }

    fun buildSentence(selectedIndexes: List<Int>): String {
        validateIndexes(selectedIndexes)

        return selectedIndexes
            .map { index -> scrambledWords[index - 1].text }
            .joinToString(" ")
    }

    fun isCorrect(selectedIndexes: List<Int>): Boolean {
        validateIndexes(selectedIndexes)

        val selectedWordIds = selectedIndexes.map { index ->
            scrambledWords[index - 1].id
        }

        return SentenceValidator().isCorrect(
            userOrder = selectedWordIds,
            correctOrder = exercise.correctOrder
        )
    }

    private fun validateIndexes(selectedIndexes: List<Int>) {
        require(selectedIndexes.size == scrambledWords.size) {
            "Each word must be selected exactly once."
        }

        require(
            selectedIndexes.all { it in 1..scrambledWords.size }
        ) {
            "One or more indexes are out of range."
        }

        require(
            selectedIndexes.toSet().size == selectedIndexes.size
        ) {
            "Indexes must not be repeated."
        }
    }
}