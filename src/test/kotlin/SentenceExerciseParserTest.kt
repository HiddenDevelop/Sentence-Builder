import kotlin.test.Test
import kotlin.test.assertEquals

class SentenceExerciseParserTest {

    @Test
    fun `parses sentence exercise json`() {

        val rawJson = """
            {
              "sentence": "She goes to school every morning.",
              "words": [
                { "id": 1, "text": "She" },
                { "id": 2, "text": "goes" },
                { "id": 3, "text": "to" },
                { "id": 4, "text": "school" },
                { "id": 5, "text": "every" },
                { "id": 6, "text": "morning." }
              ],
              "correctOrder": [1, 2, 3, 4, 5, 6],
              "scrambledOrder": [4, 1, 6, 3, 2, 5]
            }
        """.trimIndent()

        val exercise = SentenceExerciseParser.parse(rawJson)

        assertEquals(
            "She goes to school every morning.",
            exercise.sentence
        )

        assertEquals(6, exercise.words.size)

        assertEquals(
            listOf(1, 2, 3, 4, 5, 6),
            exercise.correctOrder
        )

        assertEquals(
            listOf(4, 1, 6, 3, 2, 5),
            exercise.scrambledOrder
        )
    }
}