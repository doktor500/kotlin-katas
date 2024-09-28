package uk.co.kenfos

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual

class BowlingSpec: FunSpec({
    data class Data(val input: String, val expectedScore: Int)
    context("bowling app calculates score for given input") {
        withData(
            Data("1-|1-|1-|1-|1-|1-|1-|1-|1-|1-|", 10),
            Data("2-|2-|2-|2-|2-|2-|2-|2-|2-|2-|", 20),
            Data("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||", 90),
            Data("-/|-/|-/|-/|-/|-/|-/|-/|-/|--|", 90),
            Data("X|--|-/|-/|-/|-/|-/|-/|-/|--|", 80),
            Data("-/|1/|-/|-/|-/|-/|-/|-/|-/|--|", 91),
            Data("5/|5/|5/|5/|5/|5/|5/|5/|5/||5/5", 150),
            Data("X|7/|9-|X|-8|8/|-6|X|X||81", 146),
            Data("X|X|X|X|X|X|X|X|X||XXX", 300),
        ) { (input, expectedScore) ->
            calculateSore(input) shouldBeEqual expectedScore
        }
    }
})