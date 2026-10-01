fun main() {
    val repository: ExerciseRepository =
        InMemoryExerciseRepository(
            DemoExercises.all()
        )

    println("Sentence Builder")
    println("================")
    println()

    var score = 0
    var roundsPlayed = 0
    var keepPlaying = true

    while (keepPlaying) {
        val exercise = repository.getExercise()

        val correct = playRound(exercise)

        roundsPlayed++

        if (correct) {
            score++
        }

        println()
        println("Score: $score / $roundsPlayed")
        println()

        print("Play another sentence? (y/n): ")

        val answer = readlnOrNull()
            ?.trim()
            ?.lowercase()

        keepPlaying = answer == "y" || answer == "yes"

        println()
    }

    println("Final score: $score / $roundsPlayed")
    println("Thanks for playing!")
}

fun playRound(exercise: SentenceExercise): Boolean {
    val game = SentenceGame(exercise)

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

    return try {
        val sentence = game.buildSentence(selectedIndexes)

        println()
        println("Your sentence:")
        println(sentence)
        println()

        if (game.isCorrect(selectedIndexes)) {
            println("Correct!")
            true
        } else {
            println("Incorrect.")
            println("Correct sentence: ${exercise.sentence}")
            false
        }
    } catch (e: IllegalArgumentException) {
        println()
        println("Invalid answer: ${e.message}")
        false
    }
}