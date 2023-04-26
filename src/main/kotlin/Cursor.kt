data class Cursor(
    var start: Int,
    var size: Int
)

val Cursor.end: Int get() = this.start + this.size