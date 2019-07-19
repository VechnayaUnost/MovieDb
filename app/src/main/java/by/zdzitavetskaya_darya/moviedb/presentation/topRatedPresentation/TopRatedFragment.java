package by.zdzitavetskaya_darya.moviedb.presentation.topRatedPresentation;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.SubMovie;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseFragment;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseView;
import moxy.presenter.InjectPresenter;

public class TopRatedFragment extends BaseFragment implements BaseView {

    @InjectPresenter
    TopRatedPresenter topRatedPresenter;

    @Override
    public void onMoviesSuccess(final List<SubMovie> subMovies) {
        final List<Movie> movies = new ArrayList<>(subMovies);
        adapter.addMovies(movies);
        isFetchingMovies = false;
    }

    @Override
    public void onMessageShow() {
        Toast.makeText(getActivity(), getResources().getText(R.string.message_no_internet_connection),
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void getMoreMovies(final int currentPage) {
        isFetchingMovies = true;
        this.currentPage = currentPage;
        topRatedPresenter.getMoviesFromNetwork(currentPage);
    }
}
