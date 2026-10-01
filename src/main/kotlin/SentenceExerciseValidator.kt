object SentenceExerciseValidator {

    fun validate(exercise: SentenceExercise): Boolean {

        val wordIds = exercise.words.map { it.id }

        if (wordIds.isEmpty()) {
            return false
        }

        if (wordIds.toSet().size != wordIds.size) {
            return false
        }

        if (exercise.correctOrder.size != wordIds.size) {
            return false
        }

        if (exercise.scrambledOrder.size != wordIds.size) {
            return false
        }

        if (exercise.correctOrder.toSet() != wordIds.toSet()) {
            return false
        }

        if (exercise.scrambledOrder.toSet() != wordIds.toSet()) {
            return false
        }

        if (exercise.scrambledOrder == exercise.correctOrder) {
            return false
        }

        return true
    }
}