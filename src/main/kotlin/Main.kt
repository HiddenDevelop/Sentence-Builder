fun main() {
    val exercise = SentenceExercise(
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
    )

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
        .split(" ")
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