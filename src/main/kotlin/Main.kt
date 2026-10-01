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
    val state = SentenceGameState(exercise)

    println("Reorder the words into a correct sentence.")
    println("Select one word at a time.")
    println()

    while (!state.isComplete) {
        println("Available words:")

        state.availableWords.forEachIndexed { index, word ->
            println("${index + 1}. ${word.text}")
        }

        println()

        if (state.selectedWords.isNotEmpty()) {
            println("Current sentence:")
            println(state.currentSentence())
            println()
        }

        print("Choose a word number: ")

        val input = readlnOrNull()
            ?.trim()
            ?.toIntOrNull()

        if (input == null) {
            println("Please enter a number.")
            println()
            continue
        }

        try {
            state.selectWord(input - 1)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }

        println()
    }

    println("Your sentence:")
    println(state.currentSentence())
    println()

    return if (state.isCorrect()) {
        println("Correct!")
        true
    } else {
        println("Incorrect.")
        println("Correct sentence: ${exercise.sentence}")
        false
    }
}