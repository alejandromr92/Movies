package alejandromr92.com.movies.presentation.ui.adapters;

import alejandromr92.com.movies.R;
import alejandromr92.com.movies.presentation.model.MovieView;
import alejandromr92.com.movies.presentation.ui.adapters.holders.MovieHolder;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieHolder> implements Filterable {

    private List<MovieView> items;
    private List<MovieView> filteredItems;
    private WeakReference<Context> reference;

    public MovieListAdapter(List<MovieView> items, WeakReference<Context> reference) {
        this.items = items;
        this.filteredItems = items;
        this.reference = reference;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new MovieHolder(view, reference);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(filteredItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItems != null ? filteredItems.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredItems = items;
                } else {
                    List<MovieView> moviesFilteredList = new ArrayList<>();
                    for (MovieView row : items) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())){
                            moviesFilteredList.add(row);
                        }
                    }

                    filteredItems = moviesFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredItems = (List<MovieView>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
