import kotlin.math.round

class GapBuffer(initialCapacity: Int) {
    private val capacityIncreaseRate: Double = 2.0
    private val specialCharacter: Char = '_'

    private var capacity: Int = initialCapacity
    private var content: CharArray = CharArray(capacity)
    private val cursor: Cursor = Cursor(start = 0, size = capacity)

    fun insertCharacter(value: Char) {
        if (cursor.size < 1) return
        content[cursor.start] = value
        cursor.start++
        cursor.size--
    }

    fun insertString(value: String) {
        if (value.length > cursor.size) {
            increaseCapacity(value.length)
        }
        value.forEach { insertCharacter(it) }
    }

    fun removeCharacter() {
        if (cursor.start > 0) {
            content[cursor.start-1] = specialCharacter
            cursor.start--
            cursor.size++
        }
    }

    fun moveCursorRight() {
        if (cursor.end < content.size) {
            content[cursor.start] = content[cursor.end]
            content[cursor.end] = specialCharacter
            cursor.start++
        }
    }

    fun moveCursorLeft() {
        if (cursor.start > 0) {
            content[cursor.end-1] = content[cursor.start-1]
            content[cursor.start-1] = specialCharacter
            cursor.start--
        }
    }

    private fun increaseCapacity(requiredSize: Int) {
        val firstPart = content.slice(0 until cursor.start)
        val secondPart = content.slice(cursor.end until content.size)
        while (cursor.size < requiredSize) {
            capacity = round(capacity * capacityIncreaseRate).toInt()
            cursor.size = capacity - (firstPart.size + secondPart.size)
        }
        content = CharArray(capacity)
        (0 until cursor.start).forEach { content[it] = firstPart[it] }
        (cursor.start until cursor.end).forEach { content[it] = specialCharacter }
        (cursor.end until content.size).forEachIndexed { index, i -> content[i] = secondPart[index] }
        cursor.start = firstPart.size
    }

    override fun toString(): String {
        var result: String = content.joinToString(separator = "")
        result = result.replaceRange(
            range = cursor.start until cursor.end,
            replacement = specialCharacter.toString().repeat(cursor.size)
        )
        return result
    }

    fun toStringWithoutSpace(): String = content
        .filter { it != specialCharacter }
        .joinToString(separator = "")
}