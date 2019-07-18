package by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import by.zdzitavetskaya_darya.moviedb.di.module.GlideApp;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.utils.Utility;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView{

    private Unbinder unbinder;
    private boolean appBarExpanded = true;
    private  boolean movieIsFavourite = false;
    private MenuItem favouriteMenuItem;
    private Movie movie;

    @InjectPresenter
    DetailPresenter detailPresenter;

    @BindView(R.id.activity_toolbar)
    Toolbar toolbar;

    @BindView(R.id.movie_backdrop)
    ImageView ivMovieBackdrop;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.movie_poster)
    ImageView ivMoviePoster;

    @BindView(R.id.movie_original_title)
    TextView tvOriginalTitle;

    @BindView(R.id.movie_original_language)
    TextView tvOriginalLanguage;

    @BindView(R.id.movie_release_date)
    TextView tvReleaseDate;

    @BindView(R.id.movie_vote_average)
    TextView tvVoteAverage;

    @BindView(R.id.movie_overview)
    TextView tvMovieOverview;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            detailPresenter.getMovieFromNetwork(getIntent().getIntExtra(Constants.ARG_ID, 0));
        }

        appBarLayout.addOnOffsetChangedListener((appBarLayout, i) -> {
            if (Math.abs(i) > 200) {
                appBarExpanded = false;
                invalidateOptionsMenu();
            } else {
                appBarExpanded = true;
                invalidateOptionsMenu();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.detail_activity_toolbar, menu);
        favouriteMenuItem = menu.findItem(R.id.action_favourite);

        if (!appBarExpanded) {
            favouriteMenuItem.setVisible(true);
        } else {
            favouriteMenuItem.setVisible(false);
        }

        if (movieIsFavourite) {
            setMenuItemIcon(R.color.colorPink);
        } else {
            setMenuItemIcon(android.R.color.white);
        }

        favouriteMenuItem.setOnMenuItemClickListener(item -> {
            changeFavouriteState();
            return false;
        });
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        changeFavouriteState();
    }

    private void changeFavouriteState() {
        movieIsFavourite = !movieIsFavourite;
        setFavouriteIcon();
        if (movieIsFavourite) {
            Toast.makeText(getApplicationContext(), "Added to Favourites!", Toast.LENGTH_LONG).show();
            detailPresenter.insertMovieIntoDatabase(movie);
        } else {
            Toast.makeText(getApplicationContext(), "Deleted from Favourites!", Toast.LENGTH_LONG).show();
            detailPresenter.deleteMovieFromFavourite(movie);
        }
    }

    private void setFavouriteIcon() {
        if (movieIsFavourite) {
            setFabIcon(R.color.colorPink);
        } else {
            setFabIcon(android.R.color.white);
        }
        invalidateOptionsMenu();
    }

    private void setFabIcon(final int id) {
        floatingActionButton.setColorFilter(getResources().getColor(id));
    }

    private void setMenuItemIcon(final int id) {
        final Drawable drawable = favouriteMenuItem.getIcon();
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, id));
    }

    @Override
    public void onMovieSuccess(final Movie movie) {
        this.movie = movie;
        detailPresenter.checkFavouriteState(movie);
    }

    @Override
    public void onError() {
        finish();
    }

    public void loadInfo(final Movie movie) {
        setFavouriteIcon();

        collapsingToolbarLayout.setTitle(movie.getTitle());

        GlideApp
                .with(this)
                .load(Constants.BASE_POSTER_URL + movie.getBackdropPath())
                .into(ivMovieBackdrop);


        GlideApp
                .with(this)
                .load(Constants.BASE_POSTER_URL + movie.getPosterPath())
                .into(ivMoviePoster);

        tvOriginalTitle.setText(movie.getOriginalTitle());
        tvOriginalLanguage.setText(movie.getOriginalLanguage());
        tvReleaseDate.setText(Utility.getFormatDate(movie.getReleaseDate()));
        tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvMovieOverview.setText(movie.getOverview());
    }

    @Override
    public void favouriteMovie(final Movie movie) {
        movieIsFavourite = true;
        loadInfo(movie);
    }

    @Override
    public void notFavouriteMovie() {
        movieIsFavourite = false;
        loadInfo(movie);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
