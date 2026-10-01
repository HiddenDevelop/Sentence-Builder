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

    if (!SentenceExerciseValidator.validate(exercise)) {
        println("Invalid exercise.")
        return
    }

    println("Sentence Builder")
    println()
    println("Reorder the words into a correct sentence.")
    println()

    val scrambledWords = exercise.scrambledOrder.map { id ->
        exercise.words.first { it.id == id }
    }

    scrambledWords.forEachIndexed { index, word ->
        println("${index + 1}. ${word.text}")
    }

    println()
    print("Enter the word numbers in the correct order: ")

    val input = readlnOrNull()?.trim().orEmpty()

    val selectedIndexes = input
        .split(" ")
        .mapNotNull { it.toIntOrNull() }

    if (selectedIndexes.size != scrambledWords.size) {
        println("Invalid answer. Enter each word number exactly once.")
        return
    }

    if (selectedIndexes.any { it !in 1..scrambledWords.size }) {
        println("Invalid answer. One or more numbers are out of range.")
        return
    }

    if (selectedIndexes.toSet().size != scrambledWords.size) {
        println("Invalid answer. Do not repeat word numbers.")
        return
    }

    val selectedWordIds = selectedIndexes.map { index ->
        scrambledWords[index - 1].id
    }

    val selectedSentence = selectedIndexes
        .map { index -> scrambledWords[index - 1].text }
        .joinToString(" ")

    println()
    println("Your sentence:")
    println(selectedSentence)

    println()

    if (
        SentenceValidator().isCorrect(
            userOrder = selectedWordIds,
            correctOrder = exercise.correctOrder
        )
    ) {
        println("Correct!")
    } else {
        println("Incorrect.")
        println("Correct sentence: ${exercise.sentence}")
    }
}