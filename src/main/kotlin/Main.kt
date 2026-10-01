fun main() {
    val repository: ExerciseRepository =
        LocalExerciseRepository()

    val exercise = repository.getExercise()

    val game = SentenceGame(exercise)

    println("Sentence Builder")
    println()
    println("Reorder the words into a correct sentence.")
    println()

    game.scrambledWords.forEachIndexed { index, word ->
        println("${index + 1}. ${word.text}")
    }

    println()
    print("Enter the word numbers in the correct order: ")

    val input = readlnOrNull()?.trim().orEmpty()

    val selectedIndexes = input
        .split("\\s+".toRegex())
        .mapNotNull { it.toIntOrNull() }

    try {
        val sentence = game.buildSentence(selectedIndexes)

        println()
        println("Your sentence:")
        println(sentence)
        println()

        if (game.isCorrect(selectedIndexes)) {
            println("Correct!")
        } else {
            println("Incorrect.")
            println("Correct sentence: ${exercise.sentence}")
        }
    } catch (e: IllegalArgumentException) {
        println()
        println("Invalid answer: ${e.message}")
    }
}