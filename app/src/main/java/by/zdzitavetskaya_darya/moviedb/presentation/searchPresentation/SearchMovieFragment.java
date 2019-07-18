package by.zdzitavetskaya_darya.moviedb.presentation.searchPresentation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseFragment;
import moxy.presenter.InjectPresenter;

public class SearchMovieFragment extends BaseFragment implements SearchMovieView {

    @InjectPresenter
    SearchPresenter searchPresenter;

    @BindView(R.id.no_result_message)
    TextView tvNoResult;

    private SearchView searchView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_toolbar, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchPresenter.searchObservable(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onMoviesSuccess(final List<SubMovie> subMovies) {
        final List<Movie> movies = new ArrayList<>(subMovies);
        if (!isFetchingMovies) {
            adapter.updateAdapter(movies);
        } else {
            adapter.addMovies(movies);
            isFetchingMovies = false;
        }
    }

    @Override
    public void onMessageShow() {
        adapter.clearList();
        tvNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListClear() {
        adapter.clearList();
    }

    @Override
    public void onMessageHide() {
        tvNoResult.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void getMoreMovies(final int currentPage) {
        isFetchingMovies = true;
        this.currentPage = currentPage;
        searchPresenter.getMoviesFromNetwork(currentPage, searchView.getQuery().toString());
    }
}
