package io.sengokudaikon.dbfinder.infrastructure.enums

sealed interface Choice<out A> {
    fun values(): List<A>
    data class SingleChoice<out A>(val a: A) : Choice<A> {
        override fun values() = listOf(a)
    }

    data class ChoiceOfTwo<out A>(val a: A, val b: A) : Choice<A> {
        override fun values() = listOf(a, b)
    }

    data class ChoiceOfThree<out A>(val a: A, val b: A, val c: A) :
        Choice<A> {
        override fun values() = listOf(a, b, c)
    }

    data class ChoiceOfFour<out A>(val a: A, val b: A, val c: A, val d: A) :
        Choice<A> {
        override fun values() = listOf(a, b, c, d)
    }

    companion object {
        fun <A> fromList(list: List<A>): Choice<A> {
            return when (list.size) {
                1 -> SingleChoice(list[0])
                2 -> ChoiceOfTwo(list[0], list[1])
                3 -> ChoiceOfThree(list[0], list[1], list[2])
                4 -> ChoiceOfFour(
                    list[0],
                    list[1],
                    list[2],
                    list[3],
                )

                else -> throw IllegalArgumentException("List must have 1, 2, 3 or 4 elements")
            }
        }
    }
}
