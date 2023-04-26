fun main(args: Array<String>) {
    val gapBuffer = GapBuffer(initialCapacity = 10)
    gapBuffer.insertString("Hello ")
    println(gapBuffer)

    gapBuffer.insertString("world!")
    println(gapBuffer)

    repeat(7) {
        gapBuffer.moveCursorLeft()
    }
    gapBuffer.insertCharacter(',')
    println(gapBuffer)

    repeat(7) {
        gapBuffer.moveCursorRight()
    }
    gapBuffer.removeCharacter()
    println(gapBuffer)

    println(gapBuffer.toStringWithoutSpace())
}