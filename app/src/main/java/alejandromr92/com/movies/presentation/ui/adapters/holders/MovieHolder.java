package alejandromr92.com.movies.presentation.ui.adapters.holders;

import alejandromr92.com.movies.R;
import alejandromr92.com.movies.presentation.model.MovieView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

public class MovieHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_poster)
    ImageView poster;

    @BindView(R.id.movie_title)
    TextView title;

    @BindView(R.id.movie_year_released)
    TextView yearReleased;

    @BindView(R.id.movie_scroll_overview)
    ScrollView scrollOverview;

    @BindView(R.id.movie_overview)
    TextView overview;

    private Picasso picasso;

    public MovieHolder(View itemView, WeakReference<Context> context) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        picasso = new Picasso.Builder(context.get()).build();
    }

    public void bind(final MovieView holder){

        picasso.load(holder.getPictureUrl())
                .fit()
                .centerCrop()
                .into(poster);

        poster.setVisibility(View.VISIBLE);

        title.setText(holder.getTitle());

        yearReleased.setText(holder.getYear());

        overview.setText(holder.getOverview());
    }

    @OnTouch(R.id.movie_scroll_overview)
    public boolean onScrollOverviewTouch(View v){
        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
