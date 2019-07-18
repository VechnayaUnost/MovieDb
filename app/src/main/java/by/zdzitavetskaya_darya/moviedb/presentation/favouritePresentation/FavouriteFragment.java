package by.zdzitavetskaya_darya.moviedb.presentation.favouritePresentation;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseFragment;
import moxy.presenter.InjectPresenter;

public class FavouriteFragment extends BaseFragment implements FavouriteView {

    @InjectPresenter
    FavouritePresenter favouritePresenter;

    @Override
    public void onResume() {
        super.onResume();
        favouritePresenter.getMoviesFromDatabase();
    }

    @Override
    protected void getMoreMovies(final int currentPage) {

    }

    @Override
    public void onMoviesSuccess(final List<Movie> movies) {
        adapter.updateAdapter(movies);
    }
}
