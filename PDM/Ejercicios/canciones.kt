class Song(
    val title: String,
    val artist: String,
    val releaseYear: Int,
    val playCount: Int
) {
    
    val isPopular: Boolean
        get() = playCount >= 1000

    
    fun printDescription() {
        println("$title, interpretada por $artist, se lanzó en $releaseYear.")
    }
}

fun main() {
    
    val song = Song("Shape of You", "Ed Sheeran", 2017, 1500)
    
    
    song.printDescription()

    
    if (song.isPopular) {
        println("Esta canción es popular.")
    } else {
        println("Esta canción no es popular.")
    }
}
