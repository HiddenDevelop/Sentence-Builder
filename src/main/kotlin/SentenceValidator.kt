class SentenceValidator {

    fun isCorrect(
        userOrder: List<Int>,
        correctOrder: List<Int>
    ): Boolean {
        return userOrder == correctOrder
    }
}