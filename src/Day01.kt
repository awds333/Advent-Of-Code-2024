import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (aList, bList) = input.map { line ->
            line.split("   ")
                .map(String::toInt)
        }
            .associate { it[0] to it[1] }
            .let { placesMap ->
                placesMap.keys to placesMap.values
            }

        return aList.sorted().zip(bList.sorted())
            .sumOf { (a, b) ->
                abs(a - b)
            }
    }

    fun part2(input: List<String>): Int {
        val (aList, bList) = input.map { line ->
            line.split("   ")
                .map(String::toInt)
        }
            .associate { it[0] to it[1] }
            .let { placesMap ->
                placesMap.keys.distinct().sorted() to
                        placesMap.values.sorted()
            }

        var bPointer = 0
        return aList.sumOf { location ->
            if (bPointer >= bList.size) 0
            else {
                var appearances = 0
                while (location > bList.getOrElse(bPointer) { Int.MAX_VALUE }) {
                    bPointer++
                }

                while (location == bList.getOrNull(bPointer)) {
                    appearances++
                    bPointer++
                }

                appearances * location
            }
        }
    }


    val testInput = readInput("Day01")
    check(part1(readInput("Day01")) == 3714264)

    check(part2(testInput) == 18805872)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
