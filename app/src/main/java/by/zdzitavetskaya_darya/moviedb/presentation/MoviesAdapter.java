package by.zdzitavetskaya_darya.moviedb.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import by.zdzitavetskaya_darya.moviedb.di.module.GlideApp;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.utils.Utility;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    MoviesAdapter(final List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());

        GlideApp.with(holder.moviePoster.getContext())
                .load(Constants.BASE_POSTER_URL + movie.getPosterPath())
                .into(holder.moviePoster);

        holder.releaseDate.setText(Utility.getFormatDate(movie.getReleaseDate()));

        holder.overview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title)
        TextView title;

        @BindView(R.id.movie_poster)
        ImageView moviePoster;

        @BindView(R.id.movie_overview)
        TextView overview;

        @BindView(R.id.movie_release_date)
        TextView releaseDate;

        ViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
