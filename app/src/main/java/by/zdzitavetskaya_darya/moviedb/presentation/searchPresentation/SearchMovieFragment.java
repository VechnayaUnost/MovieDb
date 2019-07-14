package by.zdzitavetskaya_darya.moviedb.presentation.searchPresentation;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import java.util.List;

import butterknife.BindView;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseFragment;
import moxy.presenter.InjectPresenter;

public class SearchMovieFragment extends BaseFragment implements SearchMovieView {

    @InjectPresenter
    SearchPresenter searchPresenter;

    @BindView(R.id.no_result_message)
    TextView tvNoResult;

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchMenuItem.setVisible(true);
        searchPresenter.searchObservable(searchView);
    }

    @Override
    public void onMoviesSuccess(final List<Movie> movies) {
        adapter.updateAdapter(movies);
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
}
