package alejandromr92.com.movies.presentation.ui.adapters.holders

import alejandromr92.com.movies.presentation.extensions.load
import alejandromr92.com.movies.presentation.model.MovieView
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(holder: MovieView) = with(itemView){

        movie_poster.load(holder.pictureUrl)

        movie_poster.visibility = View.VISIBLE

        movie_title.text = holder.title

        movie_year_released.text = holder.year

        movie_overview.text = holder.overview

        movie_scroll_overview.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }
    }
}
