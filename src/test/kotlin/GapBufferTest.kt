import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GapBufferTest {
    @Test
    fun insertingString() {
        val gapBuffer = GapBuffer(initialCapacity = 10)
        gapBuffer.insertString("string")
        Assertions.assertEquals("string____", gapBuffer.toString())
    }

    @Test
    fun increasingSize() {
        val gapBuffer = GapBuffer(initialCapacity = 10)
        gapBuffer.insertString("string1")
        gapBuffer.insertString("longstring2")
        Assertions.assertEquals("string1longstring2__", gapBuffer.toString())
    }

    @Test
    fun insertingMultipleStringsWithCursorMove() {
        val gapBuffer = GapBuffer(initialCapacity = 10)
        gapBuffer.insertString("str1")
        repeat(3) { gapBuffer.moveCursorLeft() }
        gapBuffer.insertString("str2")
        Assertions.assertEquals("sstr2__tr1", gapBuffer.toString())
    }

    @Test
    fun insertingStringAndRemovingCharacter() {
        val gapBuffer = GapBuffer(initialCapacity = 10)
        gapBuffer.insertString("str1")
        gapBuffer.removeCharacter()
        Assertions.assertEquals("str_______", gapBuffer.toString())
    }
}