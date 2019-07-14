package by.zdzitavetskaya_darya.moviedb.presentation.upcomingPresentation;

import android.widget.Toast;

import java.util.List;

import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseFragment;
import by.zdzitavetskaya_darya.moviedb.presentation.BaseView;
import moxy.presenter.InjectPresenter;

public class UpcomingFragment extends BaseFragment implements BaseView {

    @InjectPresenter
    UpcomingPresenter upcomingPresenter;

    @Override
    public void onMoviesSuccess(final List<Movie> movies) {
        adapter.updateAdapter(movies);
    }

    @Override
    public void onMessageShow() {
        Toast.makeText(getActivity(), getResources().getText(R.string.message_no_internet_connection),
                Toast.LENGTH_LONG).show();
    }
}
