package uk.co.kenfos

private const val FRAME = '|'
private const val STRIKE = 'X'
private const val SPARE = '/'
private const val MISS = '-'

private typealias FrameRolls = List<Int>

fun calculateSore(input: String): Int {
    val frameRolls = input.split(FRAME).filter { it.isNotEmpty() }.map { parseFrame(it) }
    return frameRolls
        .mapIndexed { index, frame -> Frame(frame, frameRolls.drop(index + 1).flatten().take(2)) }
        .sumOf { it.score }
}

private fun parseFrame(frame: String): FrameRolls {
    return frame.toList().fold(emptyList()) { rolls, roll ->
        val rollValue = when (roll) {
            STRIKE -> 10
            SPARE -> 10 - rolls.last()
            MISS -> 0
            else -> roll.digitToInt()
        }
        rolls.plus(rollValue)
    }
}

private data class Frame(val rolls: List<Int>, val nextRolls: FrameRolls) {
    private val frameScore = rolls.sumOf { it }
    private val isStrike = rolls.first() == 10
    private val isSpare = frameScore == 10 && !isStrike

    val score = when {
        isStrike -> frameScore + nextRolls.take(2).sumOf { it }
        isSpare -> frameScore + nextRolls.take(1).sumOf { it }
        else -> frameScore
    }
}