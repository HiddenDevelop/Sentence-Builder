class SentenceGameState(
    exercise: SentenceExercise
) {

    private val game = SentenceGame(exercise)

    private val available = game.scrambledWords.toMutableList()
    private val selected = mutableListOf<SentenceWord>()

    val availableWords: List<SentenceWord>
        get() = available.toList()

    val selectedWords: List<SentenceWord>
        get() = selected.toList()

    val isComplete: Boolean
        get() = available.isEmpty()

    fun selectWord(index: Int) {
        require(index in available.indices) {
            "Available word index is out of range."
        }

        val word = available.removeAt(index)
        selected.add(word)
    }

    fun removeSelectedWord(index: Int) {
        require(index in selected.indices) {
            "Selected word index is out of range."
        }

        val word = selected.removeAt(index)
        available.add(word)
    }

    val canUndo: Boolean
    get() = selected.isNotEmpty()

    fun undoLastSelection() {
        if (selected.isEmpty()) {
            return
        }

        val word = selected.removeAt(selected.lastIndex)
        available.add(word)
    }

    fun reset() {
        available.clear()
        available.addAll(game.scrambledWords)

        selected.clear()
    }

    fun currentSentence(): String {
        return selected.joinToString(" ") { it.text }
    }

    fun isCorrect(): Boolean {
        if (!isComplete) {
            return false
        }

        return selected.map { it.id } ==
            game.exercise.correctOrder
    }

    
}