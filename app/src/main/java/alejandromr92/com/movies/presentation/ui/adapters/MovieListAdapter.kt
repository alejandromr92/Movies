package alejandromr92.com.movies.presentation.ui.adapters

import alejandromr92.com.movies.R
import alejandromr92.com.movies.presentation.model.MovieView
import alejandromr92.com.movies.presentation.ui.adapters.holders.MovieHolder
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import java.lang.ref.WeakReference
import java.util.ArrayList

class MovieListAdapter(private val items: List<MovieView>, private val reference: WeakReference<Context>) :
    RecyclerView.Adapter<MovieHolder>(), Filterable {
    private var filteredItems: List<MovieView>? = null

    init {
        this.filteredItems = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(filteredItems!![position])
    }

    override fun getItemCount(): Int {
        return if (filteredItems != null) filteredItems!!.size else 0
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredItems = items
                } else {
                    val moviesFilteredList = ArrayList<MovieView>()
                    for (row in items) {
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            moviesFilteredList.add(row)
                        }
                    }

                    filteredItems = moviesFilteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredItems
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filteredItems = filterResults.values as List<MovieView>
                notifyDataSetChanged()
            }
        }
    }
}
