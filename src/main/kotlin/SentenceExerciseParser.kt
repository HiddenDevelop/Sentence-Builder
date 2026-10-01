import kotlinx.serialization.json.Json

object SentenceExerciseParser {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun parse(rawJson: String): SentenceExercise {
        return json.decodeFromString<SentenceExercise>(rawJson)
    }
}