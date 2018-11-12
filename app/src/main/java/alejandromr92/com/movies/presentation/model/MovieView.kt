package alejandromr92.com.movies.presentation.model

class MovieView (var title: String = "", var year: String = "",
                      var overview: String = "", var pictureUrl: String = "") {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieView

        if (title != other.title) return false
        if (year != other.year) return false
        if (pictureUrl != other.pictureUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + year.hashCode()
        result = 31 * result + pictureUrl.hashCode()
        return result
    }
}