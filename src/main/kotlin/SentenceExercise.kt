import kotlinx.serialization.Serializable

@Serializable
data class SentenceWord(
    val id: Int,
    val text: String
)

@Serializable
data class SentenceExercise(
    val sentence: String,
    val words: List<SentenceWord>,
    val correctOrder: List<Int>,
    val scrambledOrder: List<Int>
)